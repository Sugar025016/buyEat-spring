package com.buy_eat.buy_eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.buy_eat.buy_eat.config.AppConfig;

@SpringBootApplication
@Import(AppConfig.class)
public class BuyEatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyEatApplication.class, args);
	}

}
