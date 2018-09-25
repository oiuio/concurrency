package cc.oiuio.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {

	private final static int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {

		ExecutorService exec = Executors.newCachedThreadPool();

		final Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					semaphore.acquire();
					test(threadNum);
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("{}", e);
				} finally {
				}
			});
		}
		exec.shutdown();
	}

	private static void test(int threadNum) throws InterruptedException {
		log.info("{}", threadNum);
		Thread.sleep(1000);
	}
}
