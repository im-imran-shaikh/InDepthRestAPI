# InDepthRestAPI

NOTE : In latest Hateoas some modification has been done instead of Resource class use EntityModel.

 
	import org.springframework.hateoas.EntityModel;
	import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
	
	EntityModel<User> model = new EntityModel<>(user);
	WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
	model.add(linkTo.withRel("all-users"));

1) Swagger2 is used for documentation
 
 	Step 1 : To enable swagger in you application add the following depencency
 	
 		<!-- Swagger2 for documentation -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.6.1</version>
		</dependency>

		<!-- Swagger2 for ui documentation -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.6.1</version>
		</dependency>
		
		To access, swagger Json formet documentation from browser use http://localhost:8888/v2/api-docs and
		for UI use http://localhost:8888/swagger-ui.html
 
 	

 	Step 2 : Now, Configure Swagger
		
			Create a class SwaggerConfigure
		
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
			
			ApiModel annotation is use on documentation of rest api and ApiModelProperty annotation use for 
 			furnishing the property ApiModelProperty(note = "it must contaikn atleast 2 letter")
			
			
-------------------------------------------------------------------------------------------------------------------------------------------------------------
2) 	how to throw our own exception

	Step 1 : Create a model as per your requirement to display data
			package com.imran.validation.exception;

			import java.util.Date;
			
			import lombok.AllArgsConstructor;
			import lombok.Getter;
			
			@AllArgsConstructor
			@Getter
			public class ExceptionResponse
			{
				private Date timeStamp;
				private String detail;
				private String message;
			}
			
			
	Step 2 : create own exception class to throw exception message
	
			package com.imran.validation.exception;
		
			import org.springframework.http.HttpStatus;
			import org.springframework.web.bind.annotation.ResponseStatus;
			
			@ResponseStatus(HttpStatus.NOT_FOUND)
			public class LaptopNotFoundException extends RuntimeException
			{
				public LaptopNotFoundException(String message)
				{
					super(message);
				}
			}
			
	Step 3 : Configure a controller to handle you exception
	
			package com.imran.validation.exception;
		
			import java.util.Date;
			
			import org.springframework.http.HttpHeaders;
			import org.springframework.http.HttpStatus;
			import org.springframework.http.ResponseEntity;
			import org.springframework.web.bind.MethodArgumentNotValidException;
			import org.springframework.web.bind.annotation.ControllerAdvice;
			import org.springframework.web.bind.annotation.ExceptionHandler;
			import org.springframework.web.bind.annotation.RestController;
			import org.springframework.web.context.request.WebRequest;
			import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
			
			@ControllerAdvice
			@RestController
			public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
			{
				
				/**
				 *  if any internal server exception occur this method will trigger
				 * @param exception
				 * @param request
				 * @return
				 * @throws Exception
				 */
				@ExceptionHandler(Exception.class)
				private final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) throws Exception
				{
					ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
							exception.getMessage());
					
					return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				/**
				 *  if user not found this method will trigger
				 * @param exception
				 * @param request
				 * @return
				 * @throws Exception
				 */
				@ExceptionHandler(LaptopNotFoundException.class)
				private final ResponseEntity<Object> handleUserNotFoundException(Exception exception, WebRequest request) throws Exception
				{
					ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
							exception.getMessage());
					
					return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
				}
				
				/** 
				 *  If validation fail this handleMethodArgumentNotValid will trigger automatically
				 */
				@Override
				protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
						HttpHeaders headers, HttpStatus status, WebRequest request)
				{
					ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
							exception.getBindingResult().toString());
					return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
				}
			}
			
		Step 4 : Now throw LaptopNotFoundException where needed
			
				@Override
				public List<Laptop> findAll()
				{
					List<Laptop> laptop = laptopRepository.findAll();
					if (laptop.isEmpty())
						throw new LaptopNotFoundException("Laptops not found");
			
					return laptop;
				}
				
---------------------------------------------------------------------------------------------------------------------------------------				

3) how to use validation in spring boot

	Step 1 : In your model class user javax.validation.constraints annotation
	
			package com.imran.validation.dto;

			import javax.persistence.Entity;
			import javax.persistence.GeneratedValue;
			import javax.persistence.Id;
			import javax.validation.constraints.Digits;
			import javax.validation.constraints.NotNull;
			import javax.validation.constraints.Size;
			
			import io.swagger.annotations.ApiModel;
			import io.swagger.annotations.ApiModelProperty;
			import lombok.Data;
			
			@Data
			@Entity
			@ApiModel(description = "All about laptop model")
			public class Laptop
			{
				@GeneratedValue
				@Id
				@ApiModelProperty(notes = "Id is autoincremented")
				private int id;
				
				@Size(min = 1, max = 10)
				@ApiModelProperty(notes = "brand name should contain at least one letter and atmost 10 letter")
				private String brand;
				
				@Size(max = 256)
				@NotNull
				@ApiModelProperty(notes = "spec should not be null and maximum character allowed for spec is 256")
				private String spec;
				
				@NotNull
				@Digits(fraction = 0, integer = 5)
				@ApiModelProperty(notes = "The Maximum digits of price is 5 and it should be not null")
				private int price;
			}
			
			Note : Api model and ApiModelProperty annotation is of swagger documentation annotations. 
			
	Step 2 : In you controller use @Valid annotation
	
			@PostMapping(path = "/addLaptop")
			private ResponseEntity<Object> addLaptop(@RequestBody @Valid Laptop laptop)
			{
				return laptopService.add(laptop);
			}			
			
			Note : If validation fail  handleMethodArgumentNotValid() will trigger.
			
------------------------------------------------------------------------------------------------------------------------------------

4) how to add link with response using HATEOS (Hypermedia As The Engine Of Application State).

	Step 1 : Add following dependency
	
			<!-- HATEOS -->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-hateoas</artifactId>
			</dependency>	
			
	Step 2 : Create a link and add in you response
	
	
			import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
			import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
			import org.springframework.hateoas.mvc.ControllerLinkBuilder;
			import org.springframework.http.ResponseEntity;
			import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
	
			@Override
			public Resource<Laptop> findById(int id)
			{
				Optional<Laptop> laptop = laptopRepository.findById(id);
				if (!laptop.isPresent())
					throw new LaptopNotFoundException("The id : " + id + " is not found");
		
				/*
				 * Using HATEOS(Hypertext as the Engine of Application State) to add
				 * link
				 */
				Resource<Laptop> resource = new Resource<Laptop>(laptop.get());
		
				// ControllLinkBuilder is used to create a link
				ControllerLinkBuilder link = linkTo(methodOn(PageController.class).getAllLaptops());
		
				// you can add link as many as you want
				ControllerLinkBuilder getByIdLink = linkTo(methodOn(PageController.class).getById(id));
				resource.add(link.withRel("all-laptop"));
				resource.add(getByIdLink.withRel("getById"));
				return resource;
			}	
			
----------------------------------------------------------------------------------------------------------------------------------------						

5) you can also response to client when they use post or put method by providing links to them (where that data is added)
 
	@Override
	public ResponseEntity<Object> add(Laptop laptop)
	{
		laptopRepository.save(laptop);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(laptop.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
---------------------------------------------------------------------------------------------------------------------------------
