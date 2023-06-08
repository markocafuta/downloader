package com.zyte.downloader.request.publisher;

import com.zyte.downloader.request.model.Request;

public interface ConcurrencyPerDomainCounter {
	void increment (Request request);
	void decrement (Request request);
}
