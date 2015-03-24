package designpattern.factory.factory_method;

public class StudentWorkFactory implements IWorkFactory{

	public Work getWork() {
		
		return new StudentWork();
	}

}
