package com.example.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.api.services.AppUserService;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfiguration {  

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   @Autowired
   private AppUserService appUserService;

   @Bean(value = "filterChain")
   public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
      //ini kita disable dulu csrf nya 
      http.csrf().disable()
      //ini untuk request post ke endpoin /api/users/register diizinkan tampa login
      .authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
      //selain dari request post /api/users/register maka akan di autentikasi dengan http basic
      .anyRequest().fullyAuthenticated().and().httpBasic()
      //ini untuk sessionManagement nya menggunkan SessionCreationPolice.STATLES
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      //ini unutk memperkenalkan userservice dan password encoder kita ke spring security nya
      .and().authenticationProvider(daoAuthenticationProvider());
      return http.build();
   }

   private DaoAuthenticationProvider daoAuthenticationProvider(){
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
      daoAuthenticationProvider.setUserDetailsService(this.appUserService);
      return daoAuthenticationProvider;
   }
   
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf)throws Exception{
      return authConf.getAuthenticationManager();
   }
   
   @Bean(value = "webMvcConfigurer")
   public WebMvcConfigurer webMvcConfigurer(){
      return new WebMvcConfigurer() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("**/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowedOrigins("*");
            WebMvcConfigurer.super.addCorsMappings(registry);
         }
      };
   }
}
