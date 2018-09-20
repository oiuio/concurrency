package cc.oiuio.concurrency.example.commonUnsafe;

import cc.oiuio.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ArrayListExample {

	//请求总数
	public static int clientTotal = 5000;

	//并发线程数
	public static int threadTotal = 200;

	private static List<Integer> list = Lists.newArrayList();

	public static void main(String[] args) throws InterruptedException {
		//线程池
		ExecutorService executorService = Executors.newCachedThreadPool();
		//信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		//计数器闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("size:{}", list.size());
	}

	private static void update(int i) {
		list.add(i);
	}
}
