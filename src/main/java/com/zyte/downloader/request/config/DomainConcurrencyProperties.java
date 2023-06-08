package com.zyte.downloader.request.config;

import com.zyte.downloader.task.config.ConcurrencyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DomainConcurrencyProperties extends ConcurrencyProperties implements DomainConcurrencyLimit {

	private static final String CONCURRNECY_PER_DOMAIN = "max-per-domain";

	private final int concurrencyPerDomainLimit;

	public DomainConcurrencyProperties (final ApplicationArguments args) {
		super(args);
		concurrencyPerDomainLimit = getConcurrencyPerDomain(args, getConcurrencyLimit());
	}

	@Override
	public int getConcurrencyPerDomainLimit () {
		return concurrencyPerDomainLimit;
	}

	private static int getConcurrencyPerDomain(final ApplicationArguments args, final int concurrencyLimit) {
		final int concurrencyPerDomain = getConcurrency(args, CONCURRNECY_PER_DOMAIN);
		if (concurrencyPerDomain > concurrencyLimit)
			return logInvalidAndReturnConcurrenyLimit(concurrencyPerDomain, concurrencyLimit);

		return concurrencyPerDomain;
	}

	private static int logInvalidAndReturnConcurrenyLimit(final int concurrencyPerDomain, final int concurrencyLimit) {
		log.warn("{}: {} is greater then {}: {}. Setting {} to value of {}: {}",
			CONCURRNECY_PER_DOMAIN,
			concurrencyPerDomain,
			CONCURRNECY,
			concurrencyLimit,
			CONCURRNECY_PER_DOMAIN,
			CONCURRNECY,
			concurrencyLimit);

		return concurrencyLimit;
	}
}
