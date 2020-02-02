package com.demo.loginportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.demo.loginportal.model.auth.UserAuthData;

@SpringBootApplication
public class FiPortalApplication implements CommandLineRunner {

	@Autowired
	private UserAuthData authData;

	public static void main(String[] args) {
		SpringApplication.run(FiPortalApplication.class, args);
	}

	@Override 
	public void run(String... strings) throws Exception {
		System.out.println(authData); }

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("AuthData/AuthenticationData.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}
}
