package com.zyte.downloader.storage.consumer;

import com.zyte.downloader.download.model.Status;

public interface StatusConsumer<T extends Status> {
	void consume(T status);
}
