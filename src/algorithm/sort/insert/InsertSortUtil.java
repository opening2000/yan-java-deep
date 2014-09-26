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
		//[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]这个特例需要考虑
		//[1, 2, 9, 5, 3, 4, 7, 8, 6, 10]
		ary = new int[]{1,2,9,5,3,4,7,8,6,10};
		System.out.println(Arrays.toString(ary));
		directSort(ary);
		System.out.println(Arrays.toString(ary));
		
	}
	
	/**
	 * 直接插入排序，按照由小到大排序
	 * @param ary
	 */
	public static void directSort(int[] ary){
		/*
		 * 从第二位开始，与前面的有序数组由后到前的进行比较，如果比某位数大，插入到该位右边，插入前比该值大的需要右移
		 * */
		for(int i=1;i<ary.length;i++){
			//我们拿ary[i]这个值与它之前的有序数列进行比较，从后向前比较
			for(int j=i;j>0;j--){
				//实现的时候，可以判断，如果比前面的值小，就进行交换
				if(ary[j]<ary[j-1]){
					int tmp = ary[j];
					ary[j] = ary[j-1];
					ary[j-1] = tmp;
				}else{
					break;
				}
			}
		}
	}
	
	
}
