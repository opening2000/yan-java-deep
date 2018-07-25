package algorithm.sort.binary;

import java.util.Arrays;

public class BinarySortUtil {

	public static void main(String[] args) {
		int[] ary= new int[]{3,41,369,1,2,41,4,5,9};
		binarySort(ary);
		System.out.println(Arrays.toString(ary));
	}

	
	public static void binarySort(int[] ary){
		
		// 从第二位开始，左边是有序数组
		for(int i=1;i<ary.length;i++){
			
			int tmp = ary[i];
			
			// 假设i左边是有序数组，那么有序数组长度为i-1
			// 使用二分法找到ary[i]在i左侧有序数组中的位置，使用插入排序
			int low = 0;
			int high = i-1;
			
			// 找到ary[i]在i左侧有序数组中的位置，找到比ary[i]小的元素的下标，将此下标右侧元素都右移一位
			
			// 1、ary[i]在i左侧有序数组中间
			// 2、ary[i]比i左侧有序数组中元素都大
			// 3、ary[i]比i左侧有序数组中元素都小
			while(low <= high){
				int middle = (low + high) / 2;
				
				if(ary[middle] == tmp){
					// ary[i]对应的下标为middle
					// 因为要使用high标记恰好比ary[i]小的下标，所以high为middle-1
					high = middle - 1;
					break;
				}else if(ary[middle] < tmp){
					low = middle + 1;
				}else{
					high = middle - 1;
				}
			}
			
			// 退出上面while循环的方式有两种：
			// 1、break退出。这种情况下high是恰好比ary[i]小于（或相等，当有多个和ary[i]相等的值的时候是相等）的下标
			// 2、不符合low<=high条件。这种情况下high比low要小，那么必定是比ary[i]小的元素的下标
			// 也就是说high右侧一个元素必定要>=ary[i]
			
			// 使用插入排序将ary[i]插入到i左侧的有序数组中
			for(int j=i-1;j>high;j--){
				ary[j+1] = ary[j];
			}
			ary[high+1] = tmp;
		}
	}
	
}
