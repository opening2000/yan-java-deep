package algorithm.sort.merge;

import java.util.Arrays;

public class MergeSortUtil {

	public static void main(String[] args) {
		int[] a = {72, 6, 57, 88, 60, 42, 83, 73, 42, 48, 85};
        System.out.println(Arrays.toString(a));
        mergeSort(a, 0, 1);
        System.out.println(Arrays.toString(a));
	}

	/**
	 * 
	 * @param ary 待排序数组
	 * @param start 待排序数组其实元素下标
	 * @param length 每次归并的有序集合的长度
	 */
	public static void mergeSort(int[] ary, int start, int length){

		int size = ary.length;
		
		int regionCount = size / (length * 2);
		
		// 按照length进行分区后剩下的不足一个区的元素
		int rest = size % (length*2);
		
		if(regionCount == 0){
			return ;
		}
		
		//　------进行一趟归并排序-------//
		for(int i=0;i<regionCount;i++){
			start = i*2*length;
			merge(ary, start, start + length, start+2*length-1);
		}
		
		//　-------将剩下的数和倒数一个有序集合归并-------//
		if(rest > 0){
			// 将剩下的数和倒数两个有序集合归并（为什么这么分，要保证ary数组按照length拆分成两个分区及rest剩下的元素时，下面的merge可以把所有的元素合并起来）
			merge(ary, size-rest-2*length, size-rest, size-1);
		}
		
		//　-------递归执行下一趟归并排序------//
		// 将两个长度为length的待排序数组合并起来
		mergeSort(ary, 0, length * 2);
	}
	
	/**
	 * 二路归并
	 * 原理：将两个有序表合并和一个有序表
	 * 
	 * @param ary 
	 * @param start 第一个有序表的起始下标
	 * @param middle 第二个有序表的起始下标
	 * @param end 第二个有序表的结束小标
	 */
	public static void merge(int[] ary, int start, int middle, int end){
		int[] tmp = new int[end - start + 1];
		// i 用于遍历第一个有序表
		int i = start;
		// j 用于遍历第二个有序表
		int j = middle;
		// k 用于遍历 tmp
		int k = 0;
		
		
		// 遍历第一个和第二个有序表，将两个有序表和为一个
		// 此处只处理两个有序表中元素都还没有遍历完时的情况
		while(i < middle && j <= end){
			if(ary[i] < ary[j]){
				tmp[k] = ary[i];
				i++;
				k++;
			}else{
				tmp[k] = ary[j];
				j++;
				k++;
			}
		}
		
		// 第二个有序表已经遍历完，第一个有序表还没有遍历完的情况
		while(i < middle){
			tmp[k] = ary[i];
			i++;
			k++;
		}
		
		// 第一个有序表已经遍历完，第二个有序表还没有遍历完的情况
		while(j <= end){
			tmp[k] = ary[j];
			j++;
			k++;
		}
		System.arraycopy(tmp, 0, ary, start, tmp.length);
	}
}
