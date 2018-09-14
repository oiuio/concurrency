package cc.oiuio.concurrency.atomic;

import cc.oiuio.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample3 {

	//请求总数
	public static int clientTotal = 5000;

	//并发线程数
	public static int threadTotal = 200;

	public static LongAdder count = new LongAdder();

	public static void main(String[] args) throws InterruptedException {
		//线程池
		Executor executorService = Executors.newCachedThreadPool();
		//信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		//计数器闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		log.info("count:{}", count);

	}

	private static void add() {
		count.increment();
	}

}
