package com.mphasis.demo.stream.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sachi
 *
 */
@SpringBootApplication
public class RabbitSinkApplication {

	public static void main(String[] args){
		SpringApplication.run(RabbitSinkApplication.class, args);
	}
}
