package com.som.library.librarySystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.som.library.librarySystem")
@SpringBootApplication
public class LibrarySystemApplication implements CommandLineRunner {
//public  class LibrarySystemApplication {
	@Autowired
CommandLineController commandLineController;

	public static void main(String[] args) {

		SpringApplication.run(LibrarySystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commandLineController.displayOptions();
	}
}
