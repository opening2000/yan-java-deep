package algorithm.sort.insert;

import java.util.Arrays;

public class InsertSortUtil {

	public InsertSortUtil() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testSort();
	}

	public static void testSort(){
		int[] ary = new int[10];
		for(int i=0 ; i < ary.length ; i++){
			ary[i] = ary.length - i;
		}
		System.out.println(Arrays.toString(ary));
		sort(ary);
		System.out.println(Arrays.toString(ary));
		
	}
	
	/**
	 * 插入排序，按照由小到大排序
	 * @param ary
	 */
	public static void sort(int[] ary){
		/*
		 * 从第二位开始，与前面的有序数组由后到前的进行比较，如果比某位数大，插入到该位右边，插入前比该值大的需要右移
		 * */
		for(int i=1;i<ary.length;i++){
			for(int j=i-1;j>=0;j--){
				if(ary[i]>ary[j]){
					
				}
			}
		}
	}
}
