package com.zyte.downloader.storage.config;

import com.zyte.downloader.utils.ArgumentParser;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Component
public class StorageProperties implements OutputPath {

	private static final String PROPERTY = "out";
	private static final String DEFAULT_PATH = "out.txt";

	private final Path outputPath;

	protected StorageProperties (final ApplicationArguments args) {
		outputPath = ArgumentParser.getPathArgument(args, PROPERTY, DEFAULT_PATH);
	}
}
