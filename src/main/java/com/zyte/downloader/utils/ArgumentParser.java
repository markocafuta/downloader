package com.zyte.downloader.utils;

import lombok.experimental.UtilityClass;
import org.springframework.boot.ApplicationArguments;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ArgumentParser {

	private String getArgument (final ApplicationArguments args, final String name, final String defaultVal) {
		if (args == null)
			return defaultVal;

		final List<String> optionValues = args.getOptionValues(name);
		if (optionValues == null)
			return defaultVal;

		return Optional
			.of(optionValues.get(0))
			.orElse(defaultVal);
	}

	public int getIntArgument(final ApplicationArguments args, final String name, final int defaultVal) {
		final String val = getArgument(args, name, defaultVal + "");
		return parseInt(name, val);
	}

	public Path getPathArgument(final ApplicationArguments args, final String name, final String defualtOutput) {
		final String output = getArgument(args, name, defualtOutput);
		return createPath(name, output);
	}

	private Path createPath (final String name, final String output) {
		try {
			return Paths.get(output);
		} catch (final InvalidPathException e) {
			throw new IllegalArgumentException("Illegal path argument " + name + ": " + output);
		}
	}

	private int parseInt(final String arg, final String val) {
		try {
			return Integer.parseInt(val);
		} catch (final NumberFormatException e) {
			throw new IllegalArgumentException("Argument " + arg + ": " + val + " can not be converted to integer");
		}
	}
}
