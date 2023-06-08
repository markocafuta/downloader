package com.zyte.downloader.thread;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConcurrentRunnableProperties implements RunnableProperties {
	private long waitingNewSleepMilisec = 100;
	private long sleepOnErrorMilisec = 10;
	private int maxRetry = 5;
}
