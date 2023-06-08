package com.zyte.downloader.storage.config;

import com.zyte.downloader.thread.ConcurrentRunnableProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "writer")
public class FileWriterProperties extends ConcurrentRunnableProperties implements WriterProperties {
}
