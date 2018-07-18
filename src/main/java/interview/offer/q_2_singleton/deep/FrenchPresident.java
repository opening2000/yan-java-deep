package interview.offer.q_2_singleton.deep;

public class FrenchPresident extends President{

	public FrenchPresident(){
		
	}
	
	public static FrenchPresident getInstance(){
		return (FrenchPresident) President.getInstance(FrenchPresident.class.getName());
	}
	
	public void sayHello(){
		System.out.println("Helo every one , I'm French president " + name + " .");
	}
}
