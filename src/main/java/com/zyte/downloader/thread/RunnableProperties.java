package com.zyte.downloader.thread;

public interface RunnableProperties {
	long getWaitingNewSleepMilisec();
	long getSleepOnErrorMilisec();
	int getMaxRetry();
}
