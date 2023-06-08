package com.zyte.downloader.download.model;

import com.zyte.downloader.request.model.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class DownloadRequestStatus implements RequestStatus {
	private final Request request;
	private final String status;
	private final long size;
}
