package designpattern.singleton;

public class ActiveSingleton {
	
//	private static final ActiveSingleton m_instance = new ActiveSingleton();
	//个人认为final不是必需的，需要的时候再酌情添加
	private static ActiveSingleton m_instance = new ActiveSingleton();
	
	// 在类加载的时候就实例化一个自己的对象
	private ActiveSingleton() {
	}

	// 每次调用该工厂方法返回该实例
	public static ActiveSingleton getInstance() {
		return m_instance;
	}
}
