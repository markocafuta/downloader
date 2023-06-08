package com.zyte.downloader.task.consumer;

import com.zyte.downloader.task.model.Task;
import com.zyte.downloader.task.publisher.TaskPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public abstract class TaskConsumer<T extends Task, R> {

	private final TaskPublisher<T> publisher;

	protected abstract Mono<R> consume(T task);

	protected Flux<R> consume () {
		return publisher
			.tasks()
			.flatMap(this::consume)
		;
	}
}
