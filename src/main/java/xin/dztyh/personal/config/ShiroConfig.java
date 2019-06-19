package xin.dztyh.personal.config;


import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.dztyh.personal.shiro.ShiroLoginFilter;
import xin.dztyh.personal.shiro.ShiroUserRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tyh
 * @Package cn.itcast.ssm.config
 * @Description:
 * @date 18-7-20 下午3:01
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        //设置session过期时间，默认为30分钟，以毫秒为单位
        sessionManager.setGlobalSessionTimeout(60*60*1000);
        //自动扫描线程，清理超时会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //不允许URL重写，可以开启
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager(ShiroUserRealm userRealm, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean("ShiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置安全管理
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/html/manager/home.html");
        shiroFilter.setUnauthorizedUrl("/");
        //自定义拦截器
        Map<String,Filter> filterMap=new LinkedHashMap<>();
        filterMap.put("loginFilter",new ShiroLoginFilter());
        shiroFilter.setFilters(filterMap);
        //设置拦截器放行路径
        Map<String,String> filterUrl=new LinkedHashMap<>();
        filterUrl.put("/getMainInfo","anon");
        filterUrl.put("/submitFeedback","anon");
        filterUrl.put("/login","anon");
        filterUrl.put("/captcha.jpg","anon");
        filterUrl.put("/loginOut","logout");
        filterUrl.put("/CSS/style404.css","anon");
        filterUrl.put("/images/picture.jpg","anon");
        //设置拦截目录
        filterUrl.put("/html/**","loginFilter,authc");
//        filterUrl.put("/infoManager/*","loginFilter,authc");
        filterUrl.put("/manager/*","loginFilter,authc");
        filterUrl.put("/info/*","loginFilter,authc");
        filterUrl.put("/druid/**","loginFilter,authc");


        shiroFilter.setFilterChainDefinitionMap(filterUrl);
        return shiroFilter;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     *开启Shiro的注解,需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     *配置以下两个bean即可实现此功能
     * @return
     */
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 加入缓存机制
     * @return
     */
    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager=new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

}
