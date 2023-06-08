package com.zyte.downloader.task.config;

import com.zyte.downloader.utils.ArgumentParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Slf4j
@Service
public class ConcurrencyProperties implements ConcurrencyLimit {

	private static final int DEFAULT_CONCURRENCY = 1;
	protected static final String CONCURRNECY = "max-parallel";

	private final int concurrencyLimit;

	protected ConcurrencyProperties (final ApplicationArguments args) {
		concurrencyLimit = getConcurrency(args, CONCURRNECY);
	}

	@Override
	public int getConcurrencyLimit () {
		return concurrencyLimit;
	}

	protected static int getConcurrency(final ApplicationArguments args, final String property) {
		final int concurrency = ArgumentParser.getIntArgument(args, property, DEFAULT_CONCURRENCY);
		return getValidOrDefualt(property, concurrency);
	}

	private static int getValidOrDefualt (final String property, final int value) {
		if (value < 1)
			return logInvalidConcurrencyAndReturnDefault(property, value);

		return value;
	}

	private static int logInvalidConcurrencyAndReturnDefault (final String property, final int value) {
		log.warn("{}: {} not valid. Setting default: {}", property, value, DEFAULT_CONCURRENCY);
		return DEFAULT_CONCURRENCY;
	}
}
