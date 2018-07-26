package algorithm.sort.shell;

import java.util.Arrays;


/**
 * 
 * 希尔排序
 * 		希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），是直接插入排序算法的一种更高效的改进版本。
 * 		希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。
 * 		希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 		随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。 
 * 
 * 
 * @author Yan
 *
 */
public class ShellSortUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testSort();
	}

	public static void testSort(){
		int[] ary = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 42, 48, 85};
        System.out.println(Arrays.toString(ary));
		shellSort(ary);
		System.out.println(Arrays.toString(ary));
		
		ary = new int[]{72, 6, 57, 88, 60, 42, 83, 73, 42, 48, 85};
		System.out.println(Arrays.toString(ary));
		shellSort2(ary);
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
						//System.out.println(Arrays.toString(ary));
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
				// 此处i表示第几组
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
