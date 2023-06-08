package com.zyte.downloader.storage.persister;

import com.zyte.downloader.storage.config.OutputPath;
import com.zyte.downloader.storage.config.WriterProperties;
import com.zyte.downloader.thread.ConcurrentDequeueRunnable;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class FileWriter extends ConcurrentDequeueRunnable<String> implements Writer {

	private final Path outputPath;

	public FileWriter (final OutputPath outputProperties, final WriterProperties writerProperties) {
		super(writerProperties);
		outputPath = outputProperties.getOutputPath();
	}

  @Override
  @SneakyThrows
	protected void process(final String content) {
	  Files.write(outputPath, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
	}
}
