package com.example.Item;

import com.example.Item.model.Student;
import com.example.Item.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Arrays;

@SpringBootApplication
@EnableWebSecurity
public class ItemApplication {


	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ItemApplication.class, args);
	}



}
