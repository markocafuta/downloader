package com.zyte.downloader.download.client;

import com.zyte.downloader.download.model.DownloadStatus;
import com.zyte.downloader.download.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebDownloadClient implements DownloadClient {


	@Override
	public Mono<Status> download (final URI uri) {
		return WebClient
			.create(uri.toASCIIString())
			.get()
			.accept(MediaType.TEXT_HTML)
			.exchangeToMono(WebDownloadClient::processResponse)
			.onErrorReturn(WebDownloadClient::logError, errorStatus())
			;
	}

	private static Mono<Status> processResponse (final ClientResponse response) {
		final String statusCode = response.statusCode().getReasonPhrase();
		if (response.statusCode().is2xxSuccessful() || response.statusCode().is3xxRedirection())
			return response
				.bodyToMono(String.class)
				.map(res -> new DownloadStatus(statusCode, res.getBytes().length));
		else
			return response
				.createException()
				.log()
				.map(e -> new DownloadStatus(statusCode, 0));
	}

	private static boolean logError(final Throwable e) {
		log.error("Error while downloading!", e);
		return true;
	}

	private static Status errorStatus() {
		return new DownloadStatus("ERROR", 0);
	}
}
