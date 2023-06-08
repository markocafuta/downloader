package com.zyte.downloader.download.model;

import com.zyte.downloader.request.model.Request;

public interface RequestStatus extends Status {
	Request getRequest ();
}
