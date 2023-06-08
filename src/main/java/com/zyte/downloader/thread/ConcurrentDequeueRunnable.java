package com.zyte.downloader.thread;

import com.zyte.downloader.utils.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class ConcurrentDequeueRunnable<T> implements DequeueRunnable<T> {

	private boolean run = true;
	private final ConcurrentLinkedDeque<T> dequeu = new ConcurrentLinkedDeque<>();

	private final long waitingNewSleepMilisec;
	private final int maxRetry;
	private final long sleepOnErrorMilisec;

	protected ConcurrentDequeueRunnable (final RunnableProperties properties) {
		waitingNewSleepMilisec = properties.getWaitingNewSleepMilisec();
		maxRetry = properties.getMaxRetry();
		sleepOnErrorMilisec = properties.getSleepOnErrorMilisec();
	}

	protected abstract void process (final T object);

	private String runnableName() {
		return getClass().getSimpleName();
	}

	@Override
	public void add(final T item) {
		dequeu.add(item);
		log.debug("[{}] Added to tail of dequeue.", runnableName());
	}

	@Override
	public void addFirst(final T item) {
		dequeu.addFirst(item);
	}

	@Override
	public T poll () {
		return dequeu.poll();
	}

	@PostConstruct
	public void start () {
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(this);
	}

	public void stop() {
		log.info("[{}] Stoping thread...", runnableName());
		run = false;
	}

	@Override
	public void run() {
		log.info("[{}] Starting thread...", runnableName());
		while (run) {
			processIfAny();
		}
		log.info("[{}] Thread stopped!", runnableName());
	}

	private void processIfAny () {
		T item = dequeu.poll();
		while (item != null)
			item = processAndGetNext(item);

		SleepUtils.sleep(waitingNewSleepMilisec, "Interrupted while sleeping when waiting for new item to process from dequeue!");
	}

	private T processAndGetNext(final T item) {
		processWithRetry(item);
		return dequeu.poll();
	}

	private void processWithRetry(final T item) {
		final Retryer retryer = new Retryer(processFunc(item), maxRetry, sleepOnErrorMilisec);
		retryer.retry();
	}

	private Runnable processFunc(final T object) {
		return () -> process(object);
	}
}
