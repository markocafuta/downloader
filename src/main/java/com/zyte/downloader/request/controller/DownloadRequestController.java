package com.zyte.downloader.request.controller;

import com.zyte.downloader.request.publisher.RequestCreator;
import com.zyte.downloader.utils.UriUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DownloadRequestController {

	private final RequestCreator requestCreator;

	@PostMapping("/download")
	public long createDownloadRequest(@RequestBody final String url) {
		log.debug("Received download request: {}", url);
		return createRequest(url);
	}

	private long createRequest (final String url) {
		final URI uri = UriUtils.createWebURI(url);
		return requestCreator.create(uri);
	}
}
