package com.zyte.downloader.task.publisher;

import com.zyte.downloader.request.config.RequestPublisherProperties;
import com.zyte.downloader.task.model.Task;
import com.zyte.downloader.thread.ConcurrentDequeueRunnable;
import com.zyte.downloader.utils.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public abstract class GenericTaskPublisher<T extends Task> extends ConcurrentDequeueRunnable<T> implements TaskPublisher<T> {

	private final Sinks.Many<T> sink = Sinks.many().unicast().onBackpressureBuffer();

	private final ConcurrencyCounter counter;
	private final long waitingNewSleepMilisec;

	protected GenericTaskPublisher (
		final RequestPublisherProperties properties,
		final ConcurrencyCounter counter)
	{
		super(properties);
		waitingNewSleepMilisec = properties.getWaitingNewSleepMilisec();
		this.counter = counter;
	}

	protected abstract T next(T task);


	@Override
	public Flux<T> tasks () {
		return sink.asFlux();
	}

	@Override
	protected void process(final T task) {
		try {
			publishNextTask(task);
		} catch (final Exception e) {
			log.error("Error publishing next task! ", e);
		}
	}


	private void publishNextTask (final T task) {
		final T nextTask = next(task);
		if (nextTask != null)
			sinkTask(nextTask);
		else
			sleep();
	}

	private void sleep () {
		SleepUtils.sleep(waitingNewSleepMilisec, "Interrupeted while sleeping when waiting for new item to sink");
	}

	private void sinkTask(final T task) {
		try {
			incrementCounterAndSink(task);
		} catch(final ConcurrencyLimitException e) {
			sleep();
		}
	}

	private void incrementCounterAndSink (final T task) {
		counter.increment();
		log.debug("[{}] Sinking task", task.getId());
		sink.tryEmitNext(task);
	}
}
