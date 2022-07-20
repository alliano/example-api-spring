package com.example.api.configure.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc // => untuk resove bug karna udah ga rekomended lagi make swager di spring 2.5 keatas
public class SwagerConfiguration {

   @Bean
   public Docket api(){
      /**
       * jika patameter RequestHandlerSelector nya kita chaining dengan .any() maka itu artinya 
       * swager akan membuat dokumentasi secara keseluruhan (swager akan membuat dokumentasi untuk controler model entity dto dan lain2)
       * dan jika kita ingin menentukan mana folder mana yang kita mau buat dikumentasi nya kita bisa chaining dengan 
       * .basePackage() dan kita krimkan parameter atau path package yang mau kita buat dokumentasinya
       */
      return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.example.api.controllers"))
      .paths(PathSelectors.any())
      .build();
   }

   public ApiInfo apiInfo(){
      return new ApiInfoBuilder()
      .title("API demo documentation")
      .description("documentation API for fronent developmenr")
      .termsOfServiceUrl("https://alliano.com")
      .contact(getContact())
      .license("Alliano License").build();
   }

   private Contact getContact(){
      return new Contact("alliano", "https://alliano.com", "allianoanoanymous@gmail.com");
   }
}
