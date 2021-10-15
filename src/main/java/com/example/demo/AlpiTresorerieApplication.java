package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class AlpiTresorerieApplication extends SpringBootServletInitializer{
public class AlpiTresorerieApplication {
    
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//        return application.sources(AlpiTresorerieApplication.class);
//    }
	public static void main(String[] args) {
		SpringApplication.run(AlpiTresorerieApplication.class, args);
	}

}
