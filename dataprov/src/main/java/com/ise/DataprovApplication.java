package com.ise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"cn.iselab","com.ise"})
//yklsf9s58rNK7#Kf
//ssh ubuntu@111.231.68.200
public class DataprovApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataprovApplication.class, args);
	}

}
