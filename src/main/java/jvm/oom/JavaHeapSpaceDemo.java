package jvm.oom;

/**
 * 
 * 
 * 
 * JVM参数：-Xmx12m
 * 
 * 
 * @author Yan
 *
 */
public class JavaHeapSpaceDemo {

	static final int SIZE = 2 * 1024 * 1024;

    public static void main(String[] a) {
        int[] i = new int[SIZE];
    }
}
