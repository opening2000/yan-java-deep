package algorithm.sort.binary;

public class BinarySearchUtil {

	public static void main(String[] args) {
		int[] ary = new int[]{6, 12, 15, 18, 22, 25, 28, 35, 46, 58, 60};
		System.out.println(binarySearchByRecursion(ary, 25, 0, ary.length-1));
		System.out.println(binarySearchByLoop(ary, 25));
	}

	/**
	 * 使用递归的方式实现二分查找
	 * @param ary
	 * @param x
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binarySearchByRecursion(int[] ary, int x, int low, int high){
		
		if(low > high){
			return -1;
		}
		
		int middle = (low + high) / 2;
		
		if(x == ary[middle]){
			return middle;
		}else if(x < ary[middle]){
			return binarySearchByRecursion(ary, x, low, middle - 1);
		}else{
			return binarySearchByRecursion(ary, x, middle + 1, high);
		}
	}
	
	/**
	 * 使用循环的方式来实现二分查找
	 * @param ary
	 * @param x
	 * @return
	 */
	public static int binarySearchByLoop(int[] ary, int x){
		int low = 0;
		int high = ary.length - 1;
		
		int middle = (low + high) / 2;
		
		while(low <= high){
			
			if(x == ary[middle]){
				return middle;
			}else if (x < ary[middle]) {
				high = middle - 1;
			}else{
				low = middle + 1;
			}
			
		}
		
		return -1;
	}
	
}
