package interview.offer.q_2_singleton.deep;

public class AmericanPresident extends President{

	public AmericanPresident(){
		
	}
	
	public static AmericanPresident getInstance(){
		return (AmericanPresident)President.getInstance(AmericanPresident.class.getName());
	}
	
	public void sayHello(){
		System.out.println("Helo every one , I'm American president " + name + " .");
	}
}
