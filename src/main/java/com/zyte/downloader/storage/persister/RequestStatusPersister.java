package com.zyte.downloader.storage.persister;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyte.downloader.download.model.RequestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestStatusPersister implements Persister<RequestStatus> {

	private final Writer writer;

	@Override
	public void persist (final RequestStatus status) {
		try {
			persistRequestStatus(status);
		} catch (final Exception e) {
			log.error("Error persisting request status: {}", status.toString(), e);
		}
	}

	private void persistRequestStatus (final RequestStatus status) {
		final String content = prepareForPersist(status);
		writer.add(content);
	}

	private static String prepareForPersist (final RequestStatus status) {
		return toJson(map(status));
	}

	private static JsonRequestStatus map (final RequestStatus status) {
		return JsonRequestStatus.builder()
			.id(status.getRequest().getId())
			.url(status.getRequest().getTargetURI().toASCIIString())
			.status(status.getStatus())
			.responseSize(status.getSize())
			.build();
	}

	@SneakyThrows
	private static String toJson (final JsonRequestStatus status) {
		return new ObjectMapper().writeValueAsString(status) + System.lineSeparator();
	}

	@Getter
	@Builder
	@JsonPropertyOrder({ "id", "url", "status", "responseSize" })
	private static class JsonRequestStatus {
		private final long id;
		private final String url;
		private final String status;
		private long responseSize;
	}
}
