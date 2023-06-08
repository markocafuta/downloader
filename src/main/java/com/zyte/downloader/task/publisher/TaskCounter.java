package com.zyte.downloader.task.publisher;

import com.zyte.downloader.task.config.ConcurrencyLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class TaskCounter implements ConcurrencyCounter {

	private final AtomicInteger counter = new AtomicInteger();

	private final int concurrencyLimit;

	public TaskCounter(final ConcurrencyLimit concurrencyProperties) {
		concurrencyLimit = concurrencyProperties.getConcurrencyLimit();
	}

	@Override
	public void increment () {
		counter.updateAndGet(this::increaseIfLowerThenLimit);
	}

	@Override
	public void decrement () {
		counter.decrementAndGet();
	}

	private int increaseIfLowerThenLimit(int counter) {
		if (counter < concurrencyLimit)
			return ++counter;

		throw new ConcurrencyLimitException(concurrencyLimit);
	}
}
