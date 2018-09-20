package cc.oiuio.concurrency.example.commonUnsafe;


import cc.oiuio.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	public static int clientTotal = 5000;

	//并发线程数
	public static int threadTotal = 200;

	public static void main(String[] args) throws InterruptedException {
		//线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		//信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		//计数器闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update();
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
	}

	private static void update() {
		try {
			simpleDateFormat.parse("2018920");
		} catch (ParseException e) {
			log.error("exception :", e);
		}
	}


}
