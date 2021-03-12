package jvm.oom;

public class StackOverflowErrorDemo {
	public static void main(String[] args) {
		javaKeeper();
	}

	private static void javaKeeper() {
		javaKeeper();
	}
}
