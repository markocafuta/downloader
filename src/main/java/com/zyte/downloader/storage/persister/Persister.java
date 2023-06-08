package com.zyte.downloader.storage.persister;

public interface Persister<T> {
	void persist(T object);
}
