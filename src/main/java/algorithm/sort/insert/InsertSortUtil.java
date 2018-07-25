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
		int[] ary= new int[]{3,41,369,1,2,41,4,5,9};
		System.out.println(Arrays.toString(ary));
		directSort(ary);
		System.out.println(Arrays.toString(ary));
		
		
		ary= new int[]{3,41,369,1,2,41,4,5,9};
		insertSort(ary);
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
	
	/**
	 * 插入法排序
	 * @param ary
	 */
	public static void insertSort(int[] ary){
		
		// 从第二位开始，假设i左侧都是有序数组，对ary[i]进行插入查找，将ary[i]插入
		for(int i=1;i<ary.length;i++){
			// 找到ary[i]应该插入的位置
			// 也就是找到恰好比ary[i]小的元素的位置
			
			// 使用简单的循环来从右向左找比ary[i]小的元素的下标
			int index = i-1;
			
			for(int j=i-1;j>=0;j--,index--){
				// 不管ary[i]比ary[j]大还是小，index都应该随着j移动（注意ary[i]比i左侧元素都大或者都小的情况）
				if(ary[j] < ary[i]){
					break;
				}
			}
			
			// 上面的循环结束后，index标记的是恰好比ary[i]小的元素的下标
			// index右侧的元素必定>=ary[i]
			// 开始使用插入法来对ary[i]进行排序
			// 将index右侧的元素都右移一位，将ary[i]中的值放到ary[index+1]的位置
			int tmp = ary[i];
			for(int j=i-1;j>index;j--){
				ary[j+1] = ary[j];
			}
			ary[index+1] = tmp;
		}
		
	}
	
}
