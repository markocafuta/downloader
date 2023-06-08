package com.zyte.downloader.storage.consumer;

import com.zyte.downloader.download.model.RequestStatus;
import com.zyte.downloader.download.publisher.StatusPublisher;
import com.zyte.downloader.storage.persister.Persister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestStatusConsumer implements StatusConsumer<RequestStatus> {

	private final StatusPublisher<RequestStatus> publisher;
	private final Persister<RequestStatus> persister;

	@EventListener(ApplicationReadyEvent.class)
	public Disposable subscribe() {
		return publisher
			.statuses()
			.subscribe(this::consume);
	}

	@Override
	public void consume (final RequestStatus status) {
		log.info("[{}] Persisting request status...", status.getRequest().getId());
		persister.persist(status);
	}
}