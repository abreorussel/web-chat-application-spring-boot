package com.nse.webchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.client.WebClient;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@CrossOrigin
@SpringBootApplication
@EnableEncryptableProperties
public class ChatApplicationUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplicationUserApplication.class, args);
	}
	
	
	@Bean
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}

}
