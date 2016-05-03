package com.lostportals.aequitas;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public DataSource getDataSource() {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		builder.setType(EmbeddedDatabaseType.H2).addScript("my-schema.sql").addScript("my-test-data.sql");
//		return builder.build();
//	}
}
