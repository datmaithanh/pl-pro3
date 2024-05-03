package com.PL_Pro3_WebwithSpringBoot.Pro3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages= "com.PL_Pro3_WebwithSpringBoot.Pro3.controller.admincontroller" +
//        "com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceadmin.ThanhVienAdminService")
public class Pro3Application {

	public static void main(String[] args) {
		SpringApplication.run(Pro3Application.class, args);
	}
}
