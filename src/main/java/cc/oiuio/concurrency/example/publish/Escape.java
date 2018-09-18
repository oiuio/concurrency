package cc.oiuio.concurrency.example.publish;

import cc.oiuio.concurrency.annoations.NotRecommend;
import cc.oiuio.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

	private int thisCanBeEscape = 0;

	public Escape(){
		new innerClass();
	}

	private class innerClass{

		public innerClass(){
			log.info("{}",Escape.this.thisCanBeEscape);
		}

	}

	public static void main(String[] args) {
		new Escape();
	}

}
