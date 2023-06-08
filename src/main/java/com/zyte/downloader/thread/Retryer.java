package com.zyte.downloader.thread;

import com.zyte.downloader.utils.SleepUtils;
import lombok.SneakyThrows;

class Retryer {

	private final Runnable runnable;
	private final int maxRetry;
	private final long sleepOnErrorMilisec;
	private long attempt;

	Retryer (final Runnable runnable, final int maxRetry, final long sleepOnErrorMilisec) {
		this.runnable = runnable;
		this.maxRetry = maxRetry;
		this.sleepOnErrorMilisec = sleepOnErrorMilisec;
	}

	void retry () {
		try {
			runnable.run();
		} catch (final Exception e) {
			handleExceptionOnRun(e);
		}
	}

	private void handleExceptionOnRun(final Exception e) {
		increaseAttemptAndChekLimit(e);
		SleepUtils.sleep(sleepOnErrorMilisec, "Interrupted while sleeping on file write exception!");
		retry();
	}

	@SneakyThrows
	private void increaseAttemptAndChekLimit(final Exception e) {
		if (attempt >= maxRetry) {
			throw e;
		}
		attempt++;
	}
}
