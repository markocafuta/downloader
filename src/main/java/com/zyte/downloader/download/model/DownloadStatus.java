package com.zyte.downloader.download.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DownloadStatus implements Status {
	private final String status;
	private final long size;
}
