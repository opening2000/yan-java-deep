package algorithm.divideconquer;

/*分治法——归并排序 
 * 二路归并排序的分治策略是： 
 （1）划分：将待排序序列r1, r2, …, rn划分为两个长度相等的子序列r1, …, rn/2和rn/2＋1, …, rn； 
 （2）求解子问题：分别对这两个子序列进行排序，得到两个有序子序列； 
 （3）合并：将这两个有序子序列合并成一个有序序列。 
 */
public class MergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = { 34, 21, 56, 43, 99, 37, 78, 10 };// 这里对8个元素进行排序
		int low = 0, high = 7;// 初始化low和high的值，即数组的起始和终止的坐标
		// 辅助数组b，作为临时数组
		int b[] = new int[a.length];
		// 输出排序前的数组
		System.out.print("排序前：");
		for (int i = 0; i <= high; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		// 归并排序
		mergerSort(a, low, high, b);
		// 输出排序后的数组
		System.out.print("排序后：");
		for (int i = 0; i <= high; i++) {
			System.out.print(a[i] + " ");
		}
	}

	/**
	 * 分治和归并
	 * 
	 * @param a
	 * @param low
	 * @param high
	 * @param b
	 */
	public static void mergerSort(int a[], int low, int high, int b[]) {
		System.out.println("into mergerSort : low = " + low + " , high = " + high);
		int mid = 0;
		if (low < high) {
			mid = (high + low) / 2;// 分治位置,即将数组拆分的位置
			System.out.println("mid = " + mid);
			mergerSort(a, low, mid, b);
			mergerSort(a, mid + 1, high, b);
			merger(a, low, mid, high, b);// 归并
		}
		System.out.println("exit mergerSort : low = " + low + " , high = " + high);
	}

	/**
	 * 合并两个有序子序列
	 * 
	 * @param a
	 * @param low
	 * @param mid
	 * @param high
	 * @param b
	 *            辅助数组
	 */
	public static void merger(int[] a, int low, int mid, int high, int b[]) {
		System.out.println("into merger : low = " + low + " , mid = " + mid + " , high = " + high);
		int i = low;
		int j = mid + 1;
		int p = 0;
		// 合并两个有序数组 子序列1 a[low..mid] 子序列2 a[mid+1..high]
		while (i <= mid && j <= high) {
			b[p++] = (a[i] <= a[j]) ? a[i++] : a[j++];
		}
		// 如果子序列1没有合并完则直接复制到复制数组中去
		while (i <= mid) {
			b[p++] = a[i++];
		}
		// 如果子序列2没有合并完则直接复制到复制数组中去
		while (j <= high) {
			b[p++] = a[j++];
		}
		// 把辅助数组的元素复制到原来的数组中去
		for (p = 0, i = low; i <= high; i++, p++) {
			a[i] = b[p];
		}
		System.out.println("exit merger : low = " + low + " , mid = " + mid + " , high = " + high);
	}
}