package  main.newer.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 自定义的认证过滤器，用来拦截Header中携带 JWT token的请求
 *
 * 代码的执行流程 preHandle -> isAccessAllowed -> isLoginAttempt -> executeLogin
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {
	private static Logger logger = LoggerFactory.getLogger(BasicHttpAuthenticationFilter.class);
    /**
     * 前置处理
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        // 跨域时会首先发送一个option请求，这里让option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 后置处理
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
        // 添加跨域支持
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse resp = WebUtils.toHttp(response);
        this.fillCorsHeader(req, resp);
    }

    /**
     * 添加跨域支持
     */
    protected void fillCorsHeader(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
    }

    /**
     * 过滤器拦截请求的入口方法
     * 返回 true 则允许访问，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     *      如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解
     *      但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     * 返回false 则禁止访问，会进入 onAccessDenied()
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String url = WebUtils.toHttp(request).getRequestURI();
        logger.debug("isAccessAllowed url:{}", url);
        // 用用来判断是否是登录请求，此处不会拦截登录请求，用来检测Header中是否包含 JWT token 字段
        if (this.isLoginRequest(request, response)) {
            return false;
        }
        boolean allowed = false;
        try {
            // 检测Header里的 JWT token内容是否正确，尝试使用 token进行登录
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { // not found any token
        	logger.error("Not found any token");
        } catch (Exception e) {
        	logger.error("Error occurs when login", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 检测Header中是否包含 JWT token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = WebUtils.toHttp(request);
        return req.getHeader(JwtConst.AUTH_HEADER) == null;
    }

    /**
     * 身份验证,检查 JWT token 是否合法
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
                    + "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token); // 交给 Shiro 去进行登录验证，如果错误会抛出异常并被捕获，如果没有抛出异常则代表登入成功，返回true
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 从 Header 里提取 JWT token
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = WebUtils.toHttp(request);
        String authorization = req.getHeader(JwtConst.AUTH_HEADER);
        JwtToken token = new JwtToken(authorization);
        return token;
    }

    /**
     * isAccessAllowed()方法返回false，会进入该方法，表示拒绝访问
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(servletRequest);
        HttpServletResponse resp = WebUtils.toHttp(servletResponse);

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());

        // 设置响应码为401或者直接输出消息
        String url = req.getRequestURI();
        logger.error("onAccessDenied url：{}", url);

        PrintWriter writer = resp.getWriter();
        writer.write("{\"errCode\": 401, \"msg\": \"UNAUTHORIZED\"}");
        fillCorsHeader(req, resp);
        return false;
    }

    /**
     * Shiro 利用 JWT token 登录成功，会进入该方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        HttpServletResponse resp = WebUtils.toHttp(response);
        String newToken = null;
        //刷新Token
        if (token instanceof JwtToken) {
            newToken = JwtUtil.refreshTokenExpired(token.getCredentials().toString(), JwtConst.SECRET);
        }
        if (newToken != null) {
            resp.setHeader(JwtConst.AUTH_HEADER, newToken);
        }
        return true;
    }

    /**
     * Shiro 利用 JWT token 登录失败，会进入该方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {
        // 此处直接返回 false ，交给后面的  onAccessDenied()方法进行处理
    	logger.error("登录失败，token:" + token + ",error:" + e.getMessage(), e);
        return false;
    }
}