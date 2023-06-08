package com.zyte.downloader.testutils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.net.URI;

@UtilityClass
public class URIGenerator {

	private static final String WEBSITE_URL = "http://www.google.com";

	@SneakyThrows
	public URI generate() {
		return new URI(WEBSITE_URL);
	}

}
