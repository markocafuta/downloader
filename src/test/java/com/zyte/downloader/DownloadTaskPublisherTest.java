package com.zyte.downloader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class DownloadTaskPublisherTest {

	@Test
	void test() {
		final Map<String, AtomicInteger> map = new ConcurrentHashMap<>();
		final int result = map.compute("test", (k, v) -> new AtomicInteger(1)).get();
		Assertions.assertEquals(1, result);
	}

}
