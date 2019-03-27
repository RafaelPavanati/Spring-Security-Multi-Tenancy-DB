package com.cicloi.eletronica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@EnableConfigurationProperties
@SpringBootApplication
public class EletronicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EletronicaApplication.class, args);
    }

//    	registry.addMapping("/**")
//                .allowedOrigins("*")
//				.allowedMethods("HEAD,GET,POST,PUT,DELETE,PATCH,OPTIONS".split(","))
//            .allowedHeaders(("Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,"
//                                    + "Access-Control-Request-Headers,App-Context,App-Links,Authorization,"
//                                    + "User-Access,Filter-Encoded").split(","));

}
