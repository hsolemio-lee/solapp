package com.sol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class SolAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolAppApplication.class, args);
	}

}
