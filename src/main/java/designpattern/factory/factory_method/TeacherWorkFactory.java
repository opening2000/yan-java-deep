package designpattern.factory.factory_method;

public class TeacherWorkFactory implements IWorkFactory{

	public Work getWork() {
		return new TeacherWork();
	}

}
