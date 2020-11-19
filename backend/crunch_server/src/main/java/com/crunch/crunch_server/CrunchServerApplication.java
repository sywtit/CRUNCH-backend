package com.crunch.crunch_server;

import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrunchServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrunchServerApplication.class, args);

	}

}
