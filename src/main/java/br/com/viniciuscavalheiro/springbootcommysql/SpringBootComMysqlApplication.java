package br.com.viniciuscavalheiro.springbootcommysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.com.viniciuscavalheiro.springbootcommysql")
@EntityScan(basePackages = "br.com.viniciuscavalheiro.springbootcommysql.model")
public class SpringBootComMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootComMysqlApplication.class, args);
	}

}
