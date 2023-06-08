package com.zyte.downloader.download.client;

import com.zyte.downloader.download.model.DownloadRequestStatus;
import com.zyte.downloader.download.model.RequestStatus;
import com.zyte.downloader.download.model.Status;
import com.zyte.downloader.request.model.Request;
import com.zyte.downloader.request.publisher.ConcurrencyPerDomainCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestDownloader implements Downloader {

	private final DownloadClient client;
	private final ConcurrencyPerDomainCounter domainCounter;

	@Override
	public Mono<RequestStatus> download (final Request request) {
		final URI uri = request.getTargetURI();
		return client
			.download(uri)
			.map(status -> createRequestStatus(request, status));
	}

	private RequestStatus createRequestStatus(final Request request, final Status status) {
		domainCounter.decrement(request);
		log.info("[{}] Download finished with status: {} and size: {}", request.getId(), status.getStatus(), status.getSize());
		return new DownloadRequestStatus(request, status.getStatus(), status.getSize());
	}
}
