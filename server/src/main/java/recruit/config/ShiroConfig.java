/**
 * @file ShiroConfig.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import java.util.Hashtable;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import recruit.realm.AccountRealm;

@Configuration
public class ShiroConfig {

  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(
    @Qualifier("securityManager") DefaultWebSecurityManager manager
  ) {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    factoryBean.setSecurityManager(manager);
    // 权限设置
    Map<String, String> map = new Hashtable<>();
    map.put("/manager", "authc");
    map.put("/api/manager/**", "authc");
    factoryBean.setFilterChainDefinitionMap(map);
    factoryBean.setLoginUrl("/auth/login");
    return factoryBean;
  }

  @Bean
  public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(accountRealm);
    return manager;
  }

  @Bean
  public AccountRealm accountRealm() {
    return new AccountRealm();
  }
}
