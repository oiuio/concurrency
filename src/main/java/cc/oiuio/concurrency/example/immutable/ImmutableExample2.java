package cc.oiuio.concurrency.example.immutable;

import cc.oiuio.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {


	private static Map<Integer, Integer> map = Maps.newHashMap();

	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
		map = Collections.unmodifiableMap(map);
	}

	public static void main(String[] args) {

		log.info("{}", map);
	}
}


