package com.zyte.downloader;

import com.zyte.downloader.testutils.URIGenerator;
import org.junit.jupiter.api.Test;

import java.net.URI;

class StoreWebDownloadTaskTest {

	private static final URI targetURI = URIGenerator.generate();

	@Test
	void test(){
	}

//	private DownloadTaskStorage taskStorage;
//
//	@BeforeEach
//	private void beforeEach(){
//		 taskStorage = new WebDownloadTaskStorage();
//	}
//
//	@Test
//	protected void saveDownloadTask_findById() {
//		int taskId = 1;
//		DownloadTask task = new WebDownloadTask(taskId, targetURI);
//		taskStorage.save(task);
//		findAndAssertTask(taskId);
//	}
//
//	@SneakyThrows
//	@Test
//	protected void saveXDownloadTasksSimultaneously_findXTasksById() {
//		int threadCount = 10000;
//		AtomicInteger atomicTaskId = new AtomicInteger();
//		Runnable func = getSaveTaskFunc(atomicTaskId);
//		MultiThreadExecutor.execute(threadCount, func);
//		assertMultiThreadSavedTasks(threadCount);
//	}
//
//	private Runnable getSaveTaskFunc(AtomicInteger atomicTaskId) {
//		return () -> {
//			Integer taskId = atomicTaskId.addAndGet(1);
//			DownloadTask task = new WebDownloadTask(taskId, targetURI);
//			taskStorage.save(task);
//		};
//	}
//
//	private void assertMultiThreadSavedTasks(int threadCount) {
//		for (int i = 1; i <= threadCount; i++) {
//			findAndAssertTask(i);
//		}
//	}
//
//	private void findAndAssertTask (final int taskId) {
//		DownloadTask task = taskStorage.find(taskId);
//		assertNotNull(task);
//		assertEquals(taskId, task.getId());
//		assertEquals(targetURI, task.getTargetURI());
//	}

}
