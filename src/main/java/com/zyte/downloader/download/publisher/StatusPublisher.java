package com.zyte.downloader.download.publisher;

import com.zyte.downloader.download.model.Status;
import reactor.core.publisher.Flux;

public interface StatusPublisher<T extends Status> {
	Flux<T> statuses ();
}
