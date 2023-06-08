package com.zyte.downloader.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SleepUtils {

	public void sleep(final long milisec, final String interruptMessage) {
		try {
			Thread.sleep(milisec);
		} catch (final InterruptedException e) {
			log.warn(interruptMessage, e);
		}
	}
}
