package com.zyte.downloader;

import com.zyte.downloader.utils.UriUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

class UriUtilsTest {

	private static final String BASE_DOMAIN = "test.com";

	@SneakyThrows
	@Test
	void extractTopPrivateDomain_withWWW() {
		final String url = "http://www." + BASE_DOMAIN + "/path";
		final String result = extractTopPrivateDomain(url);
		Assertions.assertEquals(BASE_DOMAIN, result);
	}

	@SneakyThrows
	@Test
	void extractTopPrivateDomain_withoutWWW() {
		final String url = "http://" + BASE_DOMAIN + "/path";
		final String result = extractTopPrivateDomain(url);
		Assertions.assertEquals(BASE_DOMAIN, result);
	}


	private static String extractTopPrivateDomain (final String url) throws URISyntaxException {
		final URI uri = new URI(url);
		return UriUtils.extractTopPrivateDomain(uri);
	}
}
