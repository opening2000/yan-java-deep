package interview.question.recursion2loop.demo1;


/**
 * 
 * 二分法使用递归、循环解决
 * 
 * @author Yan
 *
 */
public class RecursionToLoopDemo2 {

	public static void main(String[] args) {
		int[] ary = new int[]{1,2,3,4,5,6,7,8,9,10,
								11,12,13,14,15,16,17,18,19};
		
		int key = 12;
		
		System.out.println(rankByLoop(key, ary));
		System.out.println(rankByRecursion(key, ary, 0, ary.length-1));

	}

	/**
	 * 使用循环进行二分排序
	 * 
	 * @param key
	 * @param ary
	 * @return
	 */
	public static int rankByLoop(int key, int[] ary){
		// 数组必须是有序的
		int low = 0;
		int high = ary.length - 1;
		
		while (low <= high) {
			// 被查找的键要么不存在，要不在ary[lo ... hi]之间
			int mid = low + (high - low) / 2;
			if(key < ary[mid]){
				high = mid - 1;
			}else if(key > ary[mid]){
				low = mid + 1;
			}else{
				return mid;
			}
		}
		
		return -1;
	}
	
	/**
	 * 使用递归法进行二分排序
	 * 
	 * @param key 要搜索的值
	 * @param ary 数组
	 * @param low 下限
	 * @param high 上限
	 * @return
	 */
	public static int rankByRecursion(int key, int[] ary, int low, int high){
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if(key == ary[mid]){
				return mid;
			}else if(key < ary[mid]){
				high = mid - 1;
				rankByRecursion(key, ary, low, high);
			}else{
				low = mid + 1;
				rankByRecursion(key, ary, low, high);
			}
		}
		return -1;
	}
	
}
