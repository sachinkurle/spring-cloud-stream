package com.mphasis.demo.stream.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sachi
 *
 */
@SpringBootApplication
public class RabbitSourceApplication {

	public static void main(String[] args){
		SpringApplication.run(RabbitSourceApplication.class, args);
	}
}
