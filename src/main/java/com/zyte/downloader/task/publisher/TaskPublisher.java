package com.zyte.downloader.task.publisher;

import com.zyte.downloader.task.model.Task;
import reactor.core.publisher.Flux;

public interface TaskPublisher<T extends Task> {
	Flux<T> tasks ();
}
