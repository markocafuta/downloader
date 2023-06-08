package com.zyte.downloader.download.publisher;

import com.zyte.downloader.download.client.Downloader;
import com.zyte.downloader.download.model.RequestStatus;
import com.zyte.downloader.request.model.Request;
import com.zyte.downloader.task.consumer.TaskConsumer;
import com.zyte.downloader.task.publisher.TaskPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestStatusPublisher extends TaskConsumer<Request, RequestStatus> implements StatusPublisher<RequestStatus> {

	private final Downloader downloader;

	public RequestStatusPublisher (final Downloader downloader, final TaskPublisher<Request> publisher) {
		super(publisher);
		this.downloader = downloader;
	}

	@Override
	public Flux<RequestStatus> statuses () {
		return consume();
	}

	@Override
	protected Mono<RequestStatus> consume (final Request request) {
		log.debug("[{}] Downloading from: {} ...", request.getId(), request.getTargetURI());
		return downloader.download(request);
	}
}
