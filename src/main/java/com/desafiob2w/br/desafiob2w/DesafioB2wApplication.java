package com.desafiob2w.br.desafiob2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "Interfaces", "Controller", "Services"})
@EnableMongoRepositories(basePackages = {"Interfaces"})
public class DesafioB2wApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioB2wApplication.class, args);
    }

}
