package cc.oiuio.concurrency.example.atomic;

import cc.oiuio.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample4 {

	private static AtomicReference<Integer> count = new AtomicReference<>(0);

	public static void main(String[] args) {
		count.compareAndSet(0, 2);
		count.compareAndSet(1, 3);
		count.compareAndSet(2, 4);
		log.info("count:{}", count);
	}

}
