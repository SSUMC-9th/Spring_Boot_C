package com.example.UMCChapter4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UmcChapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(UmcChapter4Application.class, args);
	}

}
