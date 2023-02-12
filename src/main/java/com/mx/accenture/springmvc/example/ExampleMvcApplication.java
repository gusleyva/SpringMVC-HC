package com.mx.accenture.springmvc.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleMvcApplication.class, args);
		/*
		String bearerToken = TokenUtils.createToken("admin");
		System.out.println("Bearer " + bearerToken);

		String token = bearerToken.replace("Bearer ","");
		UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(usernamePAT);
		*/
	}

}
