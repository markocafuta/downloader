package com.zyte.downloader.download.client;

import com.zyte.downloader.download.model.Status;
import reactor.core.publisher.Mono;

import java.net.URI;

interface DownloadClient {
	Mono<Status> download(URI uri);
}
