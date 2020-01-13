package com.desafiob2w.br;

import CoreDomain.Interfaces.IPlanetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude= { DataSourceAutoConfiguration.class } )
@ComponentScan(basePackages = {"CoreDomain.Interfaces", "UserInterface.Controller", "CoreDomain.Services", "InfraEstructure.Services"})
@EnableMongoRepositories(basePackageClasses = { IPlanetRepository.class })
public class DesafioB2wApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioB2wApplication.class, args);
    }

}
