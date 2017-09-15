package com.github.kai.blog.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KaiBlogUiApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KaiBlogUiApplication.class).web(true).run(args);
	}
}
