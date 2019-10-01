package com.imran.validation.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigure
{

	public static final Contact DEFAULT_CONTACT = new Contact("Imran Shaikh", "https://imran.com", "imran@shaikh.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Rest Api Documentation", "Follow this to create client",
			"1.1", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	public static final Set<String> produce = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
	public static final Set<String> consume = new HashSet<String>(Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(produce).consumes(consume);
	}
	
	/**
	 *  produces(produce) - on documentation it will display what ever in
	 *   public static final Set<String> produce = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
	 *   here application/json and application/xml will display.
	 *   
	 *   consumes(consume) - same as produce(produce) method.
	 *   
	 *   public static final Contact DEFAULT_CONTACT = new Contact("Imran Shaikh", "https://imran.com", "imran@shaikh.com");
	 *	 public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Rest Api Documentation", "Follow this to create client",
	 *		"1.1", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0"); --> configure for info node
	 *
	 *   
	 */
}
