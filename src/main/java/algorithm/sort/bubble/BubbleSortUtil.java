package algorithm.sort.bubble;

import java.util.Arrays;

public class BubbleSortUtil {

	public BubbleSortUtil() {
		// TODO Auto-generated constructor stub
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
	 * 冒泡排序法
	 * @param ary
	 */
	public static void sort(int[] ary){
		for(int i=0;i<(ary.length - 1);i++){
			for(int j=i+1;j<ary.length;j++){
				if(ary[i]>ary[j]){
					int tmp = ary[i];
					ary[i] = ary[j];
					ary[j] = tmp;
				}
			}
		}
	}
}
