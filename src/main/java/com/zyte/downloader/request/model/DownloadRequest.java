package com.zyte.downloader.request.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.net.URI;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class DownloadRequest implements Request {
	private final long id;
	private final URI targetURI;
}
