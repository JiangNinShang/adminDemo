package main.newer.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import main.newer.util.JwtFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目初始启动的时候,依次创建"sessionManager","securityManager","shiroFilter"对象交由spring管理
 * 先创建sessionManager,然后以sessionManager为参数创建securityManager,最后以securityManager为参数创建shiroFilter
 */
@Configuration
public class ShiroConfig {
	 //redis地址
    @Value("${spring.redis.host}")
    private String host;
    //redis端口
    @Value("${spring.redis.port}")
    private int port;
    //redis连接超时时间
    @Value("${spring.redis.timeout}")
    private int timeout;
    //redis密码
    @Value("${spring.redis.password}")
    private String password;
 
    //设置session会话过期时间为两小时
//    private static final Integer expireTime = 3600 * 2;

    /**
     * 1.创建realm对象，使用自定义类：JwtRealm 配置，需实现 Realm 接口
     */
    @Bean(name = "jwtRealm")
    public JwtRealm myJwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        // 设置加密算法
        CredentialsMatcher credentialsMatcher = new JwtCredentialsMatcher();
        // 设置加密次数
        jwtRealm.setCredentialsMatcher(credentialsMatcher);
        return jwtRealm;
    }

    /**
     * 1.创建realm对象，使用自定义类：ShiroRealm 配置，需实现 Realm 接口
     */
    @Bean(name = "userRealm")
    public UserRealm myShiroRealm() {
        UserRealm userRealm = new UserRealm();
        // 设置用于匹配密码的加密算法
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
        // 设置加密次数
        credentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 2.创建DefaultWebSecurityManager
     */
    @Bean(name = "dwSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            @Qualifier("userRealm") UserRealm userRealm,
            @Qualifier("jwtRealm") JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 1.Authenticator：根据条件选择根据哪个realm进行认证
        securityManager.setAuthenticator(authenticator());

        // 2.Realm
        List<Realm> realms = new ArrayList<>(2);
        realms.add(jwtRealm);
        realms.add(userRealm);
        securityManager.setRealms(realms);

        // 3.关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }
    /**
     * 配置 ModularRealmAuthenticator
     */
    @Bean
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new MultiRealmAuthenticator();
        // 设置多 Realm的认证策略，默认 AtLeastOneSuccessfulStrategy
        AuthenticationStrategy strategy = new FirstSuccessfulStrategy();
        authenticator.setAuthenticationStrategy(strategy);
        return authenticator;
    }

    @Resource
    private JwtFilter jwtFilter;

    /**
     * 3.创建ShiroFilterFactoryBean，配置访问资源需要的权限
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("dwSecurityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/wdnmd/login");
        factoryBean.setSuccessUrl("/");
        factoryBean.setUnauthorizedUrl("/");
        //首页
        //shiroFilterFactoryBean.setSuccessUrl("/success");
        //错误页面，认证不通过跳转
        //factoryBean.setUnauthorizedUrl("/error");

        // 添加 jwt 专用过滤器，拦截除 /login 和 /logout 外的请求
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwtFilter", jwtFilter);
        factoryBean.setFilters(filterMap);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         *   anon：无需认证（登录）可以访问
         *   authc：必须认证才可以访问
         *   user：如果使用rememberMe的功能可以直接访问
         *   perms：该资源必须得到资源权限才可以访问
         *   role：该资源必须得到角色权限才可以访问
         */
        //此处应该使用LinkedHashMap，否则会出现资源只能加载一次然后就被拦截的情况
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
     		// swagger放行
     		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
     		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
     		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
     		filterChainDefinitionMap.put("/v2/api-docs", "anon");
     		// 自定义
     		filterChainDefinitionMap.put("/wdnmd/login", "anon");
     		filterChainDefinitionMap.put("/wdnmd/getName", "anon");
     		filterChainDefinitionMap.put("/wdnmd/register", "anon");
     		// 对所有用户认证
     		filterChainDefinitionMap.put("/**", "authc");
     		factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }


    /**
     * 交由 Spring 来自动地管理 Shiro-Bean 的生命周期
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 为 Spring-Bean 开启对 Shiro 注解的支持
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启aop注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("dwSecurityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 不向 Spring容器中注册 JwtFilter Bean，防止 Spring 将 JwtFilter 注册为全局过滤器
     * 全局过滤器会对所有请求进行拦截，而此处只需要拦截除 /login 和 /logout 外的请求
     * 另一种简单做法是：直接去掉 jwtFilter()上的 @Bean 注解
     */
    @Bean
    public FilterRegistrationBean<Filter> registration(@Qualifier("jwtFilter") JwtFilter filter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

}
