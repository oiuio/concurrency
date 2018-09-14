package cc.oiuio.concurrency.atomic;

import cc.oiuio.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

	@Getter
	public volatile int count = 200;

	public static void main(String[] args) {
		AtomicIntegerFieldUpdater<AtomicExample5> updater
				= AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

		AtomicExample5 example5 = new AtomicExample5();
		if (updater.compareAndSet(example5, 100, 500)) {
			log.info("update success , {}", example5.getCount());
		} else {
			log.info("update failed , {}", example5.getCount());
		}


	}

}
