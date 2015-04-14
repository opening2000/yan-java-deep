package interview.offer.q_2_singleton.deep;

public class TestSingletonDemo {

	public static void main(String[] args) {
		President p = President.getInstance(President.class.getName());
		p.setName("aaa");
		p.sayHello();
		
		President fp = FrenchPresident.getInstance();
		fp.setName("abc");
		President fp2 = new FrenchPresident();
		fp2.setName("def");
		
		President ap = AmericanPresident.getInstance();
		ap.setName("zzx");
		President ap2 = new AmericanPresident();
		ap2.setName("wwe");
		
		fp.sayHello();
		fp2.sayHello();
		ap.sayHello();
		ap2.sayHello();
		/*
		 * Helo every one , I'm president aaa .
		 * Helo every one , I'm French president abc .
		 * Helo every one , I'm French president def .
		 * Helo every one , I'm American president zzx .
		 * Helo every one , I'm American president wwe .
		 * 
		 * 从结果可以看出，登记式单例只实现了集成，没有完全实现单例
		 * */
	}

}
