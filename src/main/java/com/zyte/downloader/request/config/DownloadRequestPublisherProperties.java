package com.zyte.downloader.request.config;

import com.zyte.downloader.thread.ConcurrentRunnableProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "request")
public class DownloadRequestPublisherProperties extends ConcurrentRunnableProperties implements RequestPublisherProperties {
}
