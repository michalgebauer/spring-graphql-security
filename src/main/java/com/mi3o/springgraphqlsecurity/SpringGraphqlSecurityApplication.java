package com.mi3o.springgraphqlsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringGraphqlSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGraphqlSecurityApplication.class, args);
	}
}
