package com.zyte.downloader.testutils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@UtilityClass
class MultiThreadExecutor {

	<T> void execute (final int threadCount, final Runnable func) throws InterruptedException {
		final ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++)
			executor.execute(() -> createTaskAndDecreaseCountDownLatch(func, latch));
		latch.await();
	}

	private <T> void createTaskAndDecreaseCountDownLatch (final Runnable func, final CountDownLatch latch) {
		func.run();
		latch.countDown();
	}
}
