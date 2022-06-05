/**
 * @file WebMvcConfig.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("/index.html");
    registry.addViewController("/admin").setViewName("/admin/index.html");
    registry.addViewController("/app").setViewName("/app/index.html");
    registry.addViewController("/test").setViewName("/test/index.html");
  }
}
