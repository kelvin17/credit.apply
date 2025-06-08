package com.loan.approve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.loan.approve.repository.mapper")
@SpringBootApplication
public class ApprovalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApprovalApplication.class, args);
	}

}
