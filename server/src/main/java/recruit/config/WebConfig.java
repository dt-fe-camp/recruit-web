/**
 * @file WebConfig.java
 * @author markJia(markjia@tencent.com)
 */

package recruit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/")
        .resourceChain(false);

    registry
        .addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");

    registry.addResourceHandler("/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);

  }
}