package main.newer.springBoot;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //配置类
@EnableSwagger2  //开启Swagger2的自动配置
public class Swagger2 {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apilnfo())
				.select()//通过.select()方法，去配置扫描接口，RequestHandlerSelectors配置如何扫描接口
				.apis(RequestHandlerSelectors.basePackage("mian.newer.web.controller"))
				.build();
	}
	
	private ApiInfo apilnfo() {
		Contact contact = new Contact("wanglihui", null, null);
		return new ApiInfo(
				 "Swagger学习", // 标题
		           "学习演示如何配置Swagger", // 描述
		           "v1.0", // 版本
		           "http://terms.service.url/组织链接", // 组织链接
		           contact, // 联系人信息
		           "Apach 2.0 许可", // 许可
		           "许可链接", // 许可连接
		           new ArrayList<>()// 扩展);
		           );
	}
}
