package com.example.api.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.api.configure.utils.AuditorAwareConfiguration;

/**
 * @EnableJpaAuditing
 * ini untuk mengaktifkan audit di Jpa melalui konfigurasi anotasi
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")// => auditorAware -> ini nama bean nya yg kita buat dibawah
public class BeanAuditorAwareConf {
   
   @Bean(name = "auditorAware")
   public AuditorAware<String> auditorAware(){
      return new AuditorAwareConfiguration();
   }
}
