/**
 * @file SecurityConfig.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import recruit.filter.JwtAuthenticationTokenFilter;
import recruit.filter.MyUsrPwdAuthFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean()
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  static String LOGIN_URL = "/auth/api/login";

  @Autowired
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // .sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        // .and()
        // .logout()
        // // .logoutUrl("/logout")
        // .invalidateHttpSession(true)
        // .clearAuthentication(true)
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // //登出成功后访问的地址
        // .logoutSuccessUrl("/hello")
        // .permitAll()
        .authorizeRequests()
        .antMatchers("/app/**", "/auth/**", "/static/**", "/api/auth/**").permitAll()
        .anyRequest().authenticated()

        .and()
        .addFilterAt(useMyUsrPwdAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginProcessingUrl(LOGIN_URL)
        .permitAll()

        .and()
        .rememberMe()
        .key("RECRUIT_WEB")
        .rememberMeParameter("rememberMe")
        .rememberMeCookieName("RECRUIT_AUTH_TOKEN")
        .userDetailsService(userDetailsService)

        .and()
        .csrf()
        .disable();

    http.exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .accessDeniedHandler(accessDeniedHandler);
  }

  @Bean
  MyUsrPwdAuthFilter useMyUsrPwdAuthFilter() throws Exception {
    MyUsrPwdAuthFilter filter = new MyUsrPwdAuthFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationSuccessHandler(new MyAuthSuccessHandler());
    filter.setAuthenticationFailureHandler(new MyAuthFailureHandler());
    filter.setFilterProcessesUrl(LOGIN_URL);
    TokenBasedRememberMeServices tokenBaseRememberMe = new TokenBasedRememberMeServices("RECRUIT_WEB", userDetailsService);
    tokenBaseRememberMe.setCookieName("RECRUIT_AUTH_TOKEN");
    filter.setRememberMeServices(tokenBaseRememberMe);
    return filter;
  }

  // @Bean
  // public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
  //   PersistentTokenBasedRememberMeServices persistenceTokenBasedservice = new PersistentTokenBasedRememberMeServices(
  //       "rememberme", userDetailsService, persistentTokenRepository());
  //   persistenceTokenBasedservice.setAlwaysRemember(true);
  //   return persistenceTokenBasedservice;
  // }

  /**
   * 添加 UserDetailsService， 实现自定义登录校验，数据库查询
   */
  // @Override
  // protected void configure(AuthenticationManagerBuilder builder) throws Exception {
  //   builder.userDetailsService(userDetailsService);
  // }
}
