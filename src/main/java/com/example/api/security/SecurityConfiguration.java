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

      http.csrf().disable()
      .authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
      .and().authorizeRequests().anyRequest().authenticated()
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(daoAuthenticationProvider());
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
            .allowedOrigins("*");
            WebMvcConfigurer.super.addCorsMappings(registry);
         }
      };
   }
   



   // @Bean(name = "filterChain")
   // public SecurityFilterChain filterChain(HttpSecurity security)throws Exception{
   //       security.authorizeRequests()
   //       .antMatchers("/api/users/register").permitAll()
   //       .and().authorizeRequests().anyRequest().authenticated();
   //       return security.build();
   //    }
      
   //    @Bean(name = "webSecureCostum")
   //    public WebSecurityCustomizer webSecurityCustomizer(){
   //       return (web) -> web.ignoring().antMatchers("/images/**","/js/**","/webjars/**");
   //    }
   
   // http.authorizeRequests()
   // .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
   // .and().csrf().disable().authorizeRequests().anyRequest().authenticated().and()
   // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
   // .and().authenticationProvider(daoAuthenticationProvider());
   // return http.build();
      
      
      // @Autowired
      // private AppUserService userService;
      
      // @Autowired
      // private BCryptPasswordEncoder bCryptPasswordEncoder;
      
      // @Bean(name = "filterChain")
      // public SecurityFilterChain flterChain(HttpSecurity httpSecurity)throws Exception{
         //    httpSecurity.authorizeHttpRequests(auth -> {
            //       auth.antMatchers(HttpMethod.POST, "/api/users/register")
            //       .permitAll().anyRequest().authenticated();
            //    }).httpBasic();
            //    return httpSecurity.build();
            // }
            
            // public void config(AuthenticationManagerBuilder auth) throws Exception{
               //    auth.authenticationProvider(daoAuthenticationProvider());
   // }
   
   // @Bean(name = "daoAuthProv")
   // public DaoAuthenticationProvider daoAuthenticationProvider(){
   //    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
   //    provider.setPasswordEncoder(this.bCryptPasswordEncoder);
   //    provider.setUserDetailsService(this.userService);
   //    return provider;
   // }
   // @Bean(name = "filterChain")
   // public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http){
   //   http.authorizeExchange().pathMatchers("/js/**","/css/**","/webjars/**").permitAll()
   //   .and().authorizeExchange()
   //   .pathMatchers(HttpMethod.POST, "/api/users/register").permitAll()
   //   .anyExchange().authenticated().and().httpBasic().and().formLogin().and().logout();
   //   return http.build();
   // }
}
