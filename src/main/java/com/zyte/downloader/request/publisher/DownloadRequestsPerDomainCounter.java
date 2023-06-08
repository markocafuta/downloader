package com.zyte.downloader.request.publisher;

import com.zyte.downloader.request.config.DomainConcurrencyLimit;
import com.zyte.downloader.request.model.Request;
import com.zyte.downloader.task.publisher.ConcurrencyCounter;
import com.zyte.downloader.utils.UriUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class DownloadRequestsPerDomainCounter implements ConcurrencyPerDomainCounter {

	private final Map<String, AtomicInteger> countPerDomain = new ConcurrentHashMap<>();

	private final int concurrencyLimitPerDomain;
	private final ConcurrencyCounter concurrencyCounter;

	public DownloadRequestsPerDomainCounter (final DomainConcurrencyLimit properties, final ConcurrencyCounter concurrencyCounter) {
		concurrencyLimitPerDomain = properties.getConcurrencyPerDomainLimit();
		this.concurrencyCounter = concurrencyCounter;
	}

	@Override
	public void increment (final Request request) {
		final String domain = extractDomain(request);
		countPerDomain.compute(domain, (domainKey, counter) -> incrementAndGet(counter));
	}

	@Override
	public void decrement (final Request request) {
		final String domain = extractDomain(request);
		countPerDomain.compute(domain, (domainKey, counter) -> decrementAndGet(counter));
		concurrencyCounter.decrement();
	}


	private static String extractDomain (final Request req) {
		final URI taskURI = req.getTargetURI();
		return UriUtils.extractTopPrivateDomain(taskURI);
	}

	private AtomicInteger incrementAndGet (final AtomicInteger counter) {
		if (counter == null)
			return new AtomicInteger(1);

		counter.updateAndGet(this::increaseIfLowerThenLimit);
		return counter;
	}

	private static AtomicInteger decrementAndGet (final AtomicInteger counter) {
		counter.decrementAndGet();
		return counter;
	}

	private int increaseIfLowerThenLimit(int counter) {
		if (counter < concurrencyLimitPerDomain)
			return ++counter;

		throw new ConcurrencyLimitPerDomainException(concurrencyLimitPerDomain);
	}
}
