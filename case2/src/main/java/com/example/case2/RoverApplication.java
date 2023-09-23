package com.example.case2;

import com.example.case2.controller.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoverApplication implements CommandLineRunner {

	@Autowired
	private UserInterface ui;

	public static void main(String[] args) {
		SpringApplication.run(RoverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ui.mainLoop();
	}

}
