package com.zyte.downloader.request.publisher;

import com.zyte.downloader.request.model.DownloadRequest;
import com.zyte.downloader.request.model.Request;
import com.zyte.downloader.thread.DequeueRunnable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class DownloadRequestCreator implements RequestCreator {

	private final DequeueRunnable<Request> taskCollection;

	private final AtomicLong requestId = new AtomicLong();

	@Override
	public long create (final URI uri) {
		final long id = generateId();
		createAndPublish(id, uri);
		return id;
	}

	private long generateId () {
		return requestId.addAndGet(1);
	}

	private void createAndPublish (final long id, final URI uri) {
		final Request request = new DownloadRequest(id, uri);
		taskCollection.add(request);
	}
}
