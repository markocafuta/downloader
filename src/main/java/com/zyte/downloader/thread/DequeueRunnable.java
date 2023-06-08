package com.zyte.downloader.thread;

public interface DequeueRunnable <T> extends Runnable {
	void add(T item);
	void addFirst(T item);
	T poll ();
	void start();
	void stop();
}
