package designpattern.factory.factory_method;

public class FactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IWorkFactory studentWorkFactory = new StudentWorkFactory();
		studentWorkFactory.getWork().doWork();
		
		IWorkFactory teachWorkFactory = new TeacherWorkFactory();
		teachWorkFactory.getWork().doWork();

	}

}
