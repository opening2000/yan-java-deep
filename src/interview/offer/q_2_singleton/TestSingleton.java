package interview.offer.q_2_singleton;

public class TestSingleton {

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		singleton1.setWord("abc");
		singleton2.setWord("def");
		
		System.out.println(singleton1.getWord());
		System.out.println(singleton2.getWord());
		/**
		 * 输出结果都为def，表是创建的是个单例
		 */
	}

}
