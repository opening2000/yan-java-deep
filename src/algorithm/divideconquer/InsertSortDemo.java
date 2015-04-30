package algorithm.divideconquer;

import java.util.Arrays;
import java.util.Scanner;

public class InsertSortDemo {

	public static void main(String[] args) {
		int[] ary = new int[]{8,5,6,9,7,1,3,13,10,4,2,11};
		System.out.println(Arrays.toString(ary));
		directSort(ary);
		System.out.println(Arrays.toString(ary));
		
		int[] ary2 = new int[]{8,5,6,9,7,1,3,13,10,4,2,11};
		System.out.println(Arrays.toString(ary2));
		halfInsertSort(ary2);
		System.out.println(Arrays.toString(ary2));
	}
	
	/**
	 * 直接插入排序法
	 * @param ary
	 */
	public static void directSort(int[] ary) {
		if(ary.length >= 2){
			//从第二个元素开始和左侧元素比较，如果第i个元素比某个元素小，那么将第i个元素插入到这个元素左侧
			for(int i=1;i<ary.length;i++){
				int j = i-1;
				int tmp = ary[i];
				//如果tmp比左侧的元素小，那么左侧的元素右移
				while(j>=0 && tmp < ary[j]){
					ary[j+1] = ary[j];
					j--;
				}
				//当j游走到某个位置时，tmp比ary[j]大或者相等时，j不再向左游走
				//此时tmp还是要赋值给j+1的
				ary[j+1] = tmp;
			}
		}
	}

	public static void halfInsertSort(int[] ary) {
		if(ary.length >= 2){
			//从第二个元素开始和左侧元素比较
			for(int i=1;i<ary.length;i++){
				int tmp = ary[i];
				
				int low = 0;
				int high = i-1;
				//循环条件需要是<=而不是<
				while(low <= high){
					int mid = (low + high)/2;
					//tmp比low大，比high小于等于
					if(tmp < ary[mid]){
						high = mid - 1;
					}else{
						low = mid + 1;
					}
				}
				
				for(int j=i-1;j>high;j--){
					ary[j+1] = ary[j];
//					System.out.println(Arrays.toString(ary));
				}
				ary[high + 1] = tmp;
//				System.out.println(Arrays.toString(ary));
			}
		}
	}
}
