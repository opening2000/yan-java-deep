package interview.offer.q_3_array;

/**
 * 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author Yan
 *
 */
public class SearcheFromDyadicArrayDemo {

	/**
	 * 从二维数组中查找对应的数字，如果存在返回true，否则返回false
	 * @param matrix     以数组形式存储的二维数组
	 * @param rows       二维数组的行数
	 * @param columns    二维数组的列数
	 * @param num        要查找的数字
	 * @return
	 */
	public boolean searchFromDyadicArray(int[] matrix , int rows , int columns , int num){
		boolean found = false;
		
		if(matrix != null && rows > 0 && columns > 0){
			int row = 0;
			int column = columns - 1;
			while(row < rows && column >= 0){
				if(matrix[row * columns + column] == num){
					found = true;
					break;
				}else if (matrix[row * columns + column] < num) {
					row += 1;
				}else{
					column -= 1;
				}
			}
			
		}
		
		
		return found;
	}
	
	public static void main(String[] args) {
		SearcheFromDyadicArrayDemo search = new SearcheFromDyadicArrayDemo();
		
		int[] matrix = new int[]{1, 2, 8 , 9,
				2, 4, 9, 12,
				4, 7, 10, 13,
				6, 8, 11, 15};
		int rows = 4;
		int columns = 4;
		
		boolean result1 = search.searchFromDyadicArray(matrix, rows, columns, 5);
		boolean result2 = search.searchFromDyadicArray(matrix, rows, columns, 7);
		
		System.out.println(5 + ":" + result1);
		System.out.println(7 + ":" + result2);
		
	}

}
