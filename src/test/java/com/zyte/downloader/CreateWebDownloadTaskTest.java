package com.zyte.downloader;

import com.zyte.downloader.request.publisher.RequestCreator;
import com.zyte.downloader.testutils.URIGenerator;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;


@Slf4j
class CreateWebDownloadTaskTest {

	private static final URI targetURI = URIGenerator.generate();

	private RequestCreator taskCreator;

//	@BeforeEach
//	private void beforeEach(){
//		final DownloadTaskStorage taskStorage = new WebDownloadTaskStorage();
//		taskCreator = new WebDownloadTaskCreator(taskStorage);
//	}
//
//	@Test
//	protected void createFirstDownloadTask_taskIdIsOne() {
//		final Integer taskId = taskCreator.create(targetURI);
//		assertEquals(1, taskId);
//	}
//
//	@Test
//	protected void createTwoDownloadTasks_firstTaskIdIsLowerThenSecond() {
//		final Integer taskId1 = taskCreator.create(targetURI);
//		assertEquals(1, taskId1);
//
//		final Integer taskId2 = taskCreator.create(targetURI);
//		assertTrue(taskId1 < taskId2);
//	}
//
//	@Test
//	@SneakyThrows
//	protected void createXDownloadTasksSimultaneously_lastCreatedTaskIdIsX() {
//		final int threadCount = 10000;
//		final Runnable func = getCreateTaskFunc();
//		MultiThreadExecutor.execute(threadCount, func);
//		Integer getId = taskCreator.create(targetURI);
//		assertEquals(threadCount + 1, getId);
//	}
//
//	private Runnable getCreateTaskFunc() {
//		return () -> taskCreator.create(targetURI);
//	}
}
