/**
 * 选择排序
 * 		第一次从下标为0的开始下标为0的这个数与后面的n-1个进行比较；找出最小或者最大的放在下标为0的这个位置；
 * 		第二次从下标为1的开始比较；查询剩下的最大或者最小值；放在下标为1的位置；以此类推；直到排序完成
 * 
 * 冒泡排序
 * 		依次比较相邻的两个数，将小数放在前面，大数放在后面。
 * 		即在第一趟：首先比较第1个和第2个数，将小数放前，大数      放后。然后比较第2
 * 		个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。至此第一趟结束，将最大的数放到了最后。
 * 		在第二趟：仍从第一对数开始比较
 * 		（因为可能由于第2个数和第3个数的交换，使得第1个数不再小于第2个 数），将小数放前中，大数放后，一直比较到倒数第二个数（倒数第一的位置上已经是最大的），第二趟
 * 		结束，在倒数第二的位置上得到一个新的最大数（其实在整个数列中是第二大的数）。如此下去，重复以上过程，直至最终完成排序。
 * 		这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的顶端（升序或降序排列），就如同碳酸饮料中二氧化碳的气泡最终会上浮到顶端一样，故名“冒泡排序”。
 * 
 * 二分查找（二分查找又称折半查找）
 * 二分排序（二分排序就是折半插入排序）
 * 		二分排序是指利用二分法的思想对插入排序进行改进的一种插入排序算法，不同于二叉排序，可以利用数组的特点快速定位指定索引的元素。
 * 		用自己的话总结就是，从第二位开始，使用二分查找找到这个数在左侧有序数组中的下标，将下标右侧的元素右移一位，将这个数插入到下标中
 * 
 * 插入排序
 * 快速排序
 * 归并排序
 * 		归并排序（MERGE-SORT）是建立在归并操作上的一种有效的排序算法,该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 		将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
 * 
 * 希尔排序
 * 		希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），是直接插入排序算法的一种更高效的改进版本。
 * 		希尔排序是非稳定排序算法。该方法因D.L.Shell于1959年提出而得名。
 * 		希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。 
 * 
 * 
 * https://blog.csdn.net/cxxc1976549895/article/details/71276124
 *
 * 
 * 
 * 
 * 
 **********************************************************************
 * 外排序
 * 
 * 大数据多路归并排序
 * https://blog.csdn.net/wongson/article/details/49209903
 * 
 * 多路归并
 * https://blog.csdn.net/u010367506/article/details/23565421
 * 
 * 常见的内排序和外排序算法
 * https://www.epubit.com/selfpublish/article/333;jsessionid=4D120BF407C6047A794DD322A181CC8F
 * 
 * 
 * 算法问题分类---Top-K问题与多路归并排序
 * https://blog.csdn.net/hishentan/article/details/39099923
 * 
 * 败者树
 * https://www.cnblogs.com/iyjhabc/p/3141665.html
 * 
 * 胜者树与败者树
 * https://blog.csdn.net/wypblog/article/details/8074831
 * 
 */
package algorithm.sort;