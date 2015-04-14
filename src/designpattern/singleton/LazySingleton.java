package designpattern.singleton;

public class LazySingleton {
	
	private static Object syncObj = new Object();
	
	private static LazySingleton m_instance = null;

	private LazySingleton() {
	}

	// 静态工厂方法， 在第一次被调用时才将自己实例化
	synchronized public static LazySingleton getInstance() {
		//这样写的话在高并发的情况下会创建多个实例。
//		if (m_instance == null) {
//			m_instance = new LazySingleton();
//		}
		//可以改为这样写
		if (m_instance == null) {
			synchronized (syncObj) {
				if (m_instance == null) {
					m_instance = new LazySingleton();
				}
			}
		}
		
		return m_instance;
	}
}
