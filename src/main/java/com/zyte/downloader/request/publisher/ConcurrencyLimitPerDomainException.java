package com.zyte.downloader.request.publisher;

class ConcurrencyLimitPerDomainException extends RuntimeException {
	ConcurrencyLimitPerDomainException (final int limit) {
		super("Concurency limit per domain reached! Limit: " + limit);
	}
}
