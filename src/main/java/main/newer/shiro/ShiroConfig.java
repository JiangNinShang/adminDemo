package main.newer.shiro;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	// ShiroFilterFactoryBean:3
	@Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*
        anon:无需认证可以访问
        authc：必须认证才能访问
        user：必须拥有 记住我 功能才能用
        perms：拥有对某个资源的权限才能访问
        role:拥有某个角色权限才能访问
         */
        //登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //用户进入登录界面，无需认证
        filterMap.put("/", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/tologin", "anon");
        //用户进入系统，必须进行登录验证
        filterMap.put("/sys/*", "authc");
        //角色拦截
        filterMap.put("/sys/admin", "authc,roles[admin]");
        filterMap.put("/sys/user", "authc,roles[user]");
        filterMap.put("/sys/company", "authc,roles[company]");
        //权限拦截  设置admin下的所有方法都需要增删改查权限
        filterMap.put("/sys/admin/*", "perms[admin:add,admin:update,admin:delete,admin:find]");
        //设置user下的add，update，delete需要特定权限
        filterMap.put("/sys/user/find", "perms[user:find]");
        filterMap.put("/sys/user/delete", "perms[user:delete]");
        filterMap.put("/sys/user/add", "perms[user:add]");
        filterMap.put("/sys/user/update", "perms[user:update]");

        //设置登出
        filterMap.put("/logout", "logout");

        //设置admin下的某个方法需要某个权限
        // filterMap.put("/sys/admin/find", "perms[admin:find]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置401（无权限界面）
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        return shiroFilterFactoryBean;
    }

	// DefaultWebSecurityManager:2
	@Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联userRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
	// 创建 realm对象,需要自定义:1
	@Bean("userRealm")
	public 	UserRealm getUserRealm() {
		return new UserRealm();
	}
}
