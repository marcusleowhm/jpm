package com.example.case2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RoverApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RoverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.println("Type \"help\" to see the list of commands");
			System.out.print(" > ");

			String input = sc.nextLine();
			String[] line = input.trim().split(" ");
			String command = line[0];

			if (command.equals("exit")) {
				break;
			}
			System.out.println("hello");
		}
	}
}
