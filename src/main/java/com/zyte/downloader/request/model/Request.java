package com.zyte.downloader.request.model;

import com.zyte.downloader.task.model.Task;

import java.net.URI;

public interface Request extends Task {
	URI getTargetURI();
}
