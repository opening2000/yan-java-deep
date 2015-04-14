package interview.offer.q_2_singleton;

public class Singleton {

	private String word;
	
	/**
	 * 私有的构造方法，私有很重要，否则，外部还是可以调用构造方法
	 */
	private Singleton(){
		
	}
	
	private static Singleton singleton = new Singleton();
	
	public static Singleton getInstance(){
		return singleton;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
}
