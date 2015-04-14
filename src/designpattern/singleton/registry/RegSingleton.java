package designpattern.singleton.registry;

import java.util.HashMap;

/**
 * 登记式单例类：
 * 登记式单例这个单例实际上维护的是一组单例类的实例（父类 和子类），
 * 将这些实例存放在一个Map（登记薄）中，对于已经登记过的实例，则从工厂直接返回，
 * 对于没有登记的，则先登记，而后返回。 
 */
public class RegSingleton {
	private static HashMap m_registry = new HashMap();

	static {
		RegSingleton x = new RegSingleton();
		m_registry.put(x.getClass().getName(), x);
	}

	protected RegSingleton() {
	}

	public static RegSingleton getInstance(String name) {
		if (name == null) {
			name = "RegSingleton";
		}
		if (m_registry.get(name) == null) {
			try {
				m_registry.put(name, Class.forName(name).newInstance());
			} catch (Exception e) {
				System.out.println("Error happened.");
			}
		}
		return (RegSingleton) (m_registry.get(name));
	}

	// 一个测试方法
	public String about() {
		return "Hello, I am RegSingleton";
	}
}
