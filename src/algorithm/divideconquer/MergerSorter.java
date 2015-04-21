package algorithm.divideconquer;

/**
 * 1.分治算法的基本思想是将一个规模为N的问题分解为K个规模较小的子问题，
 * 这些子问题相互独立且与原问题性质相同。求出子问题的解，就可得到原问题的解。 
 * 分治法解题的一般步骤： 
 * （1）分解，将要解决的问题划分成若干规模较小的同类问题； 
 * （2）求解，当子问题划分得足够小时，用较简单的方法解决；
 * （3）合并，按原问题的要求，将子问题的解逐层合并构成原问题的解。 
 * 
 * 2.归并排序：是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * */
public class MergerSorter {

	private static final int[] ARRAY = { 2, 5, 10, 30, 60, 40, 5, 6, 66 };

	public int[] sort(int[] array) {
		if (array.length == 1)
			return array;
		final int dividePos = array.length / 2;
		int[] array1 = new int[dividePos];
		System.arraycopy(array, 0, array1, 0, array1.length);
		int[] array2 = new int[array.length - dividePos];
		System.arraycopy(array, dividePos, array2, 0, array2.length);
		return merge(sort(array1), sort(array2));
	}

	public int[] merge(int[] a1, int[] a2) {
		int[] result = new int[a1.length + a2.length];
		int cursor = 0;
		int cursor1 = 0;
		int cursor2 = 0;
		while (cursor1 < a1.length && cursor2 < a2.length) {
			if (a1[cursor1] > a2[cursor2]) {
				result[cursor++] = a2[cursor2++];
			} else {
				result[cursor++] = a1[cursor1++];
			}
		}

		while (cursor1 < a1.length) {
			result[cursor++] = a1[cursor1++];
		}

		while (cursor2 < a2.length) {
			result[cursor++] = a2[cursor2++];
		}

		return result;
	}

	public static void main(String args[]) {
		int[] r = new MergerSorter().sort(ARRAY);
		for (int t : r) {
			System.out.print(t + ",");
		}
	}
}
