package interview.question.lowerLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 现有List<Integer> list，编码删除list中大于3的元素
 * 
 * @author Yan
 *
 */
public class RemoveElementInArray {

	public static void main(String[] args) {
//		fun1();
//		fun2();
//		fun3();
		fun4();
	}

	/**
	 * 初始化一个待处理的的list
	 * @return
	 */
	public static List<Integer> initList(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(2);
		
		return list;
	}
	
	// 会漏掉一些应该删除的元素
	public static void fun1(){
		// 初始化list
		List<Integer> list = initList();
		
		System.out.println(Arrays.toString(list.toArray()));
		
		for(int i = 0;i<list.size();i++){
			if(list.get(i) > 3){
				list.remove(i);
			}
		}

		System.out.println(Arrays.toString(list.toArray()));
	}
	
	// 可以正确处理
	public static void fun2(){
		// 初始化list
		List<Integer> list = initList();
		
		System.out.println(Arrays.toString(list.toArray()));
		
		for(int i = list.size()-1;i>=0;i--){
			if(list.get(i) > 3){
				list.remove(i);
			}
		}

		System.out.println(Arrays.toString(list.toArray()));
	}
	
	// 对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常。
	public static void fun3(){
		// 初始化list
		List<Integer> list = initList();
		
		System.out.println(Arrays.toString(list.toArray()));
		
		for(Integer integer:list){
			if(integer > 3){
				list.remove(integer);
			}
		}

		System.out.println(Arrays.toString(list.toArray()));
	}
	
	// 可以正确
	public static void fun4(){
		// 初始化list
		List<Integer> list = initList();
		
		System.out.println(Arrays.toString(list.toArray()));
		
		for(Iterator<Integer> iterator = list.iterator();iterator.hasNext();){
			Integer integer = iterator.next();
			if(integer > 3){
				// 使用迭代器的remove方法不会报java.util.ConcurrentModificationException异常
				iterator.remove();
				
				// 不能直接使用list.remove方法，否则会报异常 java.util.ConcurrentModificationException
				//list.remove(integer);
			}
		}

		System.out.println(Arrays.toString(list.toArray()));
	}
	
	
}
