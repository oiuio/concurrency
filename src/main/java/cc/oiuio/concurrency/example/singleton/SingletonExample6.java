package cc.oiuio.concurrency.example.singleton;

import cc.oiuio.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候被创建
 */
@ThreadSafe
public class SingletonExample6 {

	//私有构造函数
	private SingletonExample6() {

	}

	/**
	 * 静态代码是按顺序执行的，注意顺序
	 */

	//单例对象
	private static SingletonExample6 instance =null;

	static {
		instance = new SingletonExample6();
	}

	//静态工厂方法
	public static SingletonExample6 getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(getInstance().hashCode());
	}
}
