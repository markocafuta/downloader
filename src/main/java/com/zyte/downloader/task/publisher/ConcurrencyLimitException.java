package com.zyte.downloader.task.publisher;

class ConcurrencyLimitException extends RuntimeException {
	ConcurrencyLimitException (final int limit) {
		super("Concurency limit reached! Limit: " + limit);
	}
}
