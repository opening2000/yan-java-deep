package algorithm.sort.shell;

import java.util.Arrays;

public class ShellSortUtil {

	public ShellSortUtil() {
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
		//[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]这个特例需要考虑
		//[1, 2, 9, 5, 3, 4, 7, 8, 6, 10]
		System.out.println(Arrays.toString(ary));
		shellSort(ary);
		System.out.println(Arrays.toString(ary));
		
	}
	/**
	 * 希尔排序，插入排序的一种
	 * @param ary
	 */
	public static void shellSort(int[] ary){
		if(ary != null && ary.length > 1){
			int d = ary.length/2;
			while(d>0){
				for(int i=d;i<ary.length;i++){
					int j = i-d;
					while(j>=0 && ary[j] > ary[j+d]){
						int tmp = ary[j];
						ary[j] = ary[j+d];
						ary[j+d] = tmp;
						j = j-d;
						System.out.println(Arrays.toString(ary));
					}
				}
				d = d/2;
			}
		}
	}
}
