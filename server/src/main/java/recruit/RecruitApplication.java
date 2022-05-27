/**
 * @file RecruitApplication.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("recruit.dao")
@EnableOpenApi
public class RecruitApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class, args);
	}
}
