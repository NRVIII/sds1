package com.devsuperior.dspesquisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DspesquisaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DspesquisaApplication.class, args);
	}

}
