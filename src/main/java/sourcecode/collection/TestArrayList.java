package sourcecode.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TestArrayList {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("aaa");
		list.remove(0);
		
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("aaa");
		ll.remove(1);
		ll.addFirst("aaaa");
		
		HashMap<Integer, String> map = new HashMap<Integer, String>(4);
		for(int i=0;i<20;i++) {
			map.put(i, ""+i);
		}
		
	}

}
