package cc.oiuio.concurrency.example.singleton;

import cc.oiuio.concurrency.annoations.Recommend;
import cc.oiuio.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时被创建
 */
@ThreadSafe
@Recommend
public class SingletonExample3 {

	//私有构造函数
	private SingletonExample3() {

	}

	//单例对象
	private static SingletonExample3 instance = null;

	//静态工厂方法
	public static synchronized SingletonExample3 getInstance() {
		if (instance == null) {
			instance = new SingletonExample3();
		}
		return instance;
	}

}
