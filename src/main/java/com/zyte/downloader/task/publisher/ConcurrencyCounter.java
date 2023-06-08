package com.zyte.downloader.task.publisher;

public interface ConcurrencyCounter {
	void increment ();
	void decrement ();
}
