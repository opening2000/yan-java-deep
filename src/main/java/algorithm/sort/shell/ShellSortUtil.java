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
		int[] ary = new int[100000];
		for(int i=0 ; i < ary.length ; i++){
			ary[i] = ary.length - i;
		}
		//[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]这个特例需要考虑
		//[1, 2, 9, 5, 3, 4, 7, 8, 6, 10]
		System.out.println(Arrays.toString(ary));
		long a = System.currentTimeMillis();
		shellSort(ary);
		long b = System.currentTimeMillis();
		System.out.println(Arrays.toString(ary));
		System.out.println(b-a);
		
		ary = new int[100000];
		for(int i=0 ; i < ary.length ; i++){
			ary[i] = ary.length - i;
		}
		System.out.println(Arrays.toString(ary));
		long c = System.currentTimeMillis();
		shellSort2(ary);
		long d = System.currentTimeMillis();
		System.out.println(Arrays.toString(ary));
		System.out.println(d-c);
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
	
	/***
	 * 希尔排序另外一种实现
	 */
	public static void shellSort2(int[] ary){
		if(ary != null && ary.length > 1){
			int d = ary.length/2;
			while(d > 0){
				//一般步长是多少，就会分成多少个组，步长较小的情况，大步长以后考虑
				for(int i=0;i<d;i++){
					//对每个分组中的元素进行插入排序
					for(int j=0+i+d;j<ary.length;j+=d){
						//起始时拿数组中的第二个元素和第一个比较
						for(int k=j;k>=d;k-=d){
							if(ary[k] < ary[k-d]){
								int tmp = ary[k];
								ary[k] = ary[k-d];
								ary[k-d] = tmp;
							}else{
								break;
							}
						}
					}
				}
				d = d/2;
			}
		}
	}
}
