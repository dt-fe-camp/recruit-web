/**
 * @file SwaggerConfig.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("recruit.controllers"))
        .paths(PathSelectors.any())
        .build();
  }

  // 构建 api文档的详细信息函数
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        // 页面标题
        .title("管理系统API接口文档")
        .description("XX项目描述")
        // 创建人
        .contact(new Contact("cxt", "作者url", "作者邮箱"))
        // 版本号
        .version("1.0")
        // 描述
        .description("系统API描述")
        .build();
  }
}
