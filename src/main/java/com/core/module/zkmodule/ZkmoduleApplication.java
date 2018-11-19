package com.core.module.zkmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZkmoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZkmoduleApplication.class, args);
	}
}
