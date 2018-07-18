package interview.question.recursion2loop.demo2;

import java.util.Arrays;


/**
 * 快速排序(参考百度百科)
 * 
 * 设要排序的数组是A[0]……A[N-1]，首先任意选取一个数据（通常选用数组的第一个数）作为关键数据，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。值得注意的是，快速排序不是一种稳定的排序算法，也就是说，多个相同的值的相对位置也许会在算法结束时产生变动。
 * 一趟快速排序的算法是：
 * 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
 * 2）以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
 * 3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换；
 * 4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换；
 * 5）重复第3、4步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[i]不大于key的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i， j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）。
 * 
 * @author Yan
 *
 */
public class QuickSort {

	public static void main(String[] args) {
        int[] a = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        System.out.println(Arrays.toString(a));
        quickSort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));
        
	}

	public static void quickSort(int[] ary, int left, int right){
		int key = ary[left];
		
		int i = left;
		int j = right;
		
		while(i<j){
			// 找到比key小的值
			for(;j>i;j--){
				if(ary[j] <= key){
					break;
				}
			}
			
			if(i < j){
				ary[i] = ary[j];
			}
			
			// 找到比key大的值
			for(;i<j;i++){
				if(ary[i] >= key){
					break;
				}
			}
			
			if(i < j){
				ary[j] = ary[i];
			}
			
			System.out.println(Arrays.toString(ary));
		}
		ary[i] = key;
		System.out.println(i + ":" + j);
		
		if(left < i){
			// 左边区间排序
			quickSort(ary, left, i-1);
		}
		
		if(right > i){
			// 右边区间排序
			quickSort(ary, i+1, right);
		}
		
	}
}
