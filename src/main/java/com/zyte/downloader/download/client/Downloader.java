package com.zyte.downloader.download.client;

import com.zyte.downloader.download.model.RequestStatus;
import com.zyte.downloader.request.model.Request;
import reactor.core.publisher.Mono;

public interface Downloader {
	Mono<RequestStatus> download(Request request);
}
