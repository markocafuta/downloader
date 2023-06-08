package com.zyte.downloader.utils;

import com.google.common.net.InternetDomainName;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@UtilityClass
public class UriUtils {

	public String extractTopPrivateDomain(final URI uri) {
		final String host = uri.getHost();
		return InternetDomainName
			.from(host)
			.topPrivateDomain()
			.toString();
	}

	public URI createWebURI(final String requestUrl) {
		String url = requestUrl;
		if (!url.startsWith("http")) {
			url = "http://" + url;
		}
		return createURI(url);
	}

	private URI createURI(final String url) {
		try {
			return new URI(url);
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException("Invalid URL: " + url);
		}
	}
}
