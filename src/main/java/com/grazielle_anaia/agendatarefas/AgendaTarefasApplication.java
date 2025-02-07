package com.grazielle_anaia.agendatarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendaTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaTarefasApplication.class, args);
	}

}
