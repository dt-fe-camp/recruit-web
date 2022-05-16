/**
 * @file RecruitApplication.java
 * @author afcfzf(9301462@qq.com)
 */

package recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("recruit.dao")
public class RecruitApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class, args);
	}
}
