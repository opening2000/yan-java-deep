package interview.offer.q_2_singleton.deep;

import java.util.HashMap;

public class President {

	protected String name = "";
	
	private static HashMap<String, President> presidentMap = new HashMap<String, President>();
	
	static{
		President president = new President();
		presidentMap.put(president.getClass().getName(), president);
	}
	
	protected President(){
		
	}
	
	public static President getInstance(String className){
		if(className == null || "".equals(className.trim())){
			className = President.class.getName();
		}
		
		if(presidentMap.get(className) == null){
			try {
				presidentMap.put(className, (President)Class.forName(className).newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		return presidentMap.get(className);
	}
	
	public void sayHello(){
		System.out.println("Helo every one , I'm president " + name + " .");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
