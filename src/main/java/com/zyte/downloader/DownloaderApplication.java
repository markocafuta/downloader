package com.zyte.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DownloaderApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DownloaderApplication.class, args);
	}

}
