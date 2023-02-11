package com.accesodatos.springbootjdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Spring Boot JDBC Template application.
 */
@SpringBootApplication
public class Application {
	/**
	 * The entry point of the application.
	 *
	 * @param {string[]} args - The command-line arguments.
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application.
		SpringApplication.run(Application.class, args);
	}
}
