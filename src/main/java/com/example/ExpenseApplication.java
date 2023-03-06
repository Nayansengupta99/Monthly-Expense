package com.example;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ExpenseApplication {

	public static void main(String[] args) {
		
		
		Properties props = new Properties();
		//String mongoPass = System.getenv("MONGOPASS");
		String mongoPass="eX4N8sMkadbT4ppl";
		String mongoDBUrl = "mongodb+srv://nayan97:" + mongoPass
				+ "@cluster0.cgcpm.mongodb.net/bill?retryWrites=true&w=majority";
		props.put("server.port", "8081");
		props.put("spring.data.mongodb.uri", mongoDBUrl);
		props.put("spring.data.mongodb.databasee", "expense");
		props.put("spring.jpa.defer-datasource-initialization", "true");

		new SpringApplicationBuilder(ExpenseApplication .class).properties(props).run(args);
		//SpringApplication.run(DemoApplication.class, args);
	}

}
