package com.zyte.downloader.request.config;

import com.zyte.downloader.task.config.ConcurrencyLimit;

public interface DomainConcurrencyLimit extends ConcurrencyLimit {
	int getConcurrencyPerDomainLimit ();
}
