package algorithm.sort.bubble;

import java.util.Arrays;

/**
 * 
 * 冒泡排序
 * 		依次比较相邻的两个数，将小数放在前面，大数放在后面。
 * 		即在第一趟：首先比较第1个和第2个数，将小数放前，大数      放后。然后比较第2
 * 		个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。至此第一趟结束，将最大的数放到了最后。
 * 		在第二趟：仍从第一对数开始比较
 * 		（因为可能由于第2个数和第3个数的交换，使得第1个数不再小于第2个 数），将小数放前中，大数放后，一直比较到倒数第二个数（倒数第一的位置上已经是最大的），第二趟
 * 		结束，在倒数第二的位置上得到一个新的最大数（其实在整个数列中是第二大的数）。如此下去，重复以上过程，直至最终完成排序。
 * 		这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的顶端（升序或降序排列），就如同碳酸饮料中二氧化碳的气泡最终会上浮到顶端一样，故名“冒泡排序”。
 */
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
		int[] ary = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
		System.out.println(Arrays.toString(ary));
		bubbleSort(ary);
		System.out.println(Arrays.toString(ary));
		
	}
	
	/**
	 * 冒泡排序
	 * @param ary
	 */
	public static void bubbleSort(int[] ary){
		// 第i轮
		for(int i=0;i<(ary.length-1);i++){
			// 每一轮确定[length-i-1]位置的元素是最大的
			for(int j=0;j<(ary.length-i-1);j++){
				// j比j+1的元素大，则交换位置，大数换到j+1的位置
				if(ary[j] > ary[j+1]){
					int tmp = ary[j];
					ary[j] = ary[j+1];
					ary[j+1] = tmp;
				}
			}
		}
	}
	
}
