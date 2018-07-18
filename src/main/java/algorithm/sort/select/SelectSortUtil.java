package algorithm.sort.select;

import java.util.Arrays;

/**
 * 选择排序
 * 	第一次从下标为0的开始下标为0的这个数与后面的n-1个进行比较；找出最小或者最大的放在下标为0的这个位置；
 * 	第二次从下标为1的开始比较；查询剩下的最大或者最小值；放在下标为1的位置；以此类推；直到排序完成
 * 
 * @author Yan
 *
 */
public class SelectSortUtil {

	public static void main(String[] args) {
		int[] ary = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        System.out.println(Arrays.toString(ary));
        selectSort(ary);
        System.out.println(Arrays.toString(ary));

	}

	public static void selectSort(int[] ary){
		
		for(int i=0;i<ary.length-1;i++){
			int minNumberIndex = i;

			// 知道到下标从i起的元素中最小的元素，放在下标为i的位置
			for(int j=i+1;j<ary.length;j++){
				if(ary[j]<ary[minNumberIndex]){
					minNumberIndex = j;
				}
			}
			// 将最小的元素放在下标为i的位置
			// 如果下标为i的元素是最小的，那么不需要换到i的位置
			if(minNumberIndex > i){
				// 交换位置
				int tmp = ary[i];
				ary[i] = ary[minNumberIndex];
				ary[minNumberIndex] = tmp;
			}
			
		}
	}
	
}
