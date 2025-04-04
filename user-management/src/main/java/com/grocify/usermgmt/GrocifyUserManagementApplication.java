package com.grocify.usermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.grocify.commonlibs.*")
@EnableJpaAuditing
@EntityScan("com.grocify.commonlibs.*")
@ComponentScan(basePackages = "com.grocify.*")
public class GrocifyUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrocifyUserManagementApplication.class, args);
	}

}
