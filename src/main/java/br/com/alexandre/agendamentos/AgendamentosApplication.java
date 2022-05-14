package br.com.alexandre.agendamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentosApplication.class, args);
	}

}
