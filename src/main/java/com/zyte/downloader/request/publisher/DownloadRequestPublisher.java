package com.zyte.downloader.request.publisher;

import com.zyte.downloader.request.config.RequestPublisherProperties;
import com.zyte.downloader.request.model.Request;
import com.zyte.downloader.task.publisher.ConcurrencyCounter;
import com.zyte.downloader.task.publisher.GenericTaskPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
@Service
public class DownloadRequestPublisher extends GenericTaskPublisher<Request> {

	private final ConcurrencyPerDomainCounter domainCounter;

	public DownloadRequestPublisher (
		final RequestPublisherProperties publisherProperties,
		final ConcurrencyCounter concurrencyCounter,
		final ConcurrencyPerDomainCounter domainCounter)
	{
		super(publisherProperties, concurrencyCounter);
		this.domainCounter = domainCounter;
	}

	@Override
	protected Request next (final Request request) {
		final LinkedList<Request> skiped = new LinkedList<>();
		final Request nextRequest = next(request, skiped);
		republish(skiped);
		return nextRequest;
	}

	private Request next(final Request req, final LinkedList<Request> skiped) {
		if (req == null)
			return null;

		return increaseDomainCounter(skiped, req);
	}


	private Request increaseDomainCounter (final LinkedList<Request> skiped, final Request req) {
		try {
			domainCounter.increment(req);
			return req;
		} catch (final ConcurrencyLimitPerDomainException e) {
			return handleConcurrencyLimitReached(skiped, req);
		}
	}

	private Request handleConcurrencyLimitReached (final LinkedList<Request> skiped, final Request req) {
		skiped.add(req);
		return next(poll(), skiped);
	}


	private void republish(final Deque<Request> skiped) {
		if (skiped.isEmpty())
			return;

		skiped
			.descendingIterator()
			.forEachRemaining(this::addFirst);
		skiped.clear();
	}
}
