package interview.question.recursion2loop.demo2;

import java.util.Arrays;
import java.util.Stack;

public class QuickSortToLoop {

	public static void main(String[] args) {
        int[] ary = new int[]{49,38,65,97,76,13,27,49,78,34,12,64,5,4,62
                ,99,98,54,56,17,18,23,34,15,35,25,53,51}; 
        
        quickSortByRecursion(ary, 0, ary.length-1);
        System.out.println(Arrays.toString(ary));
        
        ary = new int[]{49,38,65,97,76,13,27,49,78,34,12,64,5,4,62
                ,99,98,54,56,17,18,23,34,15,35,25,53,51}; 
        
        quickSortByLoop(ary);
        System.out.println(Arrays.toString(ary));
        
        ary = new int[]{49,38,65,97,76,13,27,49,78,34,12,64,5,4,62
                ,99,98,54,56,17,18,23,34,15,35,25,53,51}; 
        
        quickSortByLoop2(ary);
        System.out.println(Arrays.toString(ary));
        
	}

	/**
	 * 通过递归的方式进行快速排序
	 * @param ary
	 * @param left
	 * @param right
	 */
	public static void quickSortByRecursion(int[] ary, int left, int right){
		int i = left;
		int j = right;
		
		int key = ary[i];
		
		// 必须有这一步，否则i=0，j=0的时候就会一直在这一步循环
		if(i >= j){
			return ;
		}
		
		while(i < j){
			
			// 对i和j进行++和--操作的时候，循环条件必须判断i<j，因为++和--操作会导致i和j变化
			// 从右向左找比key小的值
			while(i < j && ary[j] >= key){
				j--;
			}
			if(i < j){
				ary[i] = ary[j];
			}
			
			// 从左向右找比key大的值
			while(i < j && ary[i] <= key){
				i++;
			}
			if(i < j){
				ary[j] = ary[i];
			}
			
		}
		ary[i] = key;
		
		// 排序左边区间
		quickSortByRecursion(ary, left, i-1);
		// 排序右边区间
		quickSortByRecursion(ary, i+1, right);
		
	}
	
	/**
	 * 使用循环和栈的方式进行快速排序
	 * @param ary
	 */
	public static void quickSortByLoop(int[] ary){
		// stack中存储int[2]的数组，ary[0]表示区间的左边界，ary[1]表示区间的右边界
		Stack<Integer[]> stack = new Stack<Integer[]>();
		
		Integer[] border = {0, ary.length - 1};
		stack.push(border);
		
		while(!stack.empty()){
			Integer[] elelemt = stack.pop();
			
			// 左右边界
			int left = elelemt[0];
			int right = elelemt[1];

			int i = left;
			int j = right;
			
			// 快速排序中的每一轮处理
			int key = ary[i];
			
			// 必须有这一步，否则i=0，j=0的时候就会一直在这一步循环
			if(i >= j){
				// 注意当使用的是栈的方式的时候，因为不存在方法递归，而是使用的循环，所以推出本轮的方式是使用continue，而不是break
				continue ;
			}
			
			while(i < j){
				
				// 对i和j进行++和--操作的时候，循环条件必须判断i<j，因为++和--操作会导致i和j变化
				// 从右向左找比key小的值
				while(i < j && ary[j] >= key){
					j--;
				}
				if(i < j){
					ary[i] = ary[j];
				}
				
				// 从左向右找比key大的值
				while(i < j && ary[i] <= key){
					i++;
				}
				if(i < j){
					ary[j] = ary[i];
				}
				
			}
			ary[i] = key;
			
			// 快速排序一轮处理结束
			// 将区间边界加入到栈中
			Integer[] leftRegion = {left, i-1};
			stack.push(leftRegion);
			Integer[] rightRegion = {i+1, right};
			stack.push(rightRegion);
		}
		
	}
	
	/**
	 * 使用循环和栈的方式进行快速排序
	 * 区间的边界是当做两个元素分别压入栈中（注意先进后出）
	 * @param ary
	 */
	public static void quickSortByLoop2(int[] ary){
        Stack<Integer> stack = new Stack<Integer>();
        int start = 0;
        int end = ary.length - 1;
        stack.push(start);
        stack.push(end);
        while(!stack.isEmpty()){
            end = stack.pop(); // 顺序很重要
            start = stack.pop();
            
            // 快速排序中的一轮
            if (start < end){ // 开始排序

            	int i = start;
    			int j = end;
    			
    			// 快速排序中的每一轮处理
    			int key = ary[i];
    			
    			// 必须有这一步，否则i=0，j=0的时候就会一直在这一步循环
    			if(i >= j){
    				// 注意当使用的是栈的方式的时候，因为不存在方法递归，而是使用的循环，所以推出本轮的方式是使用continue，而不是break
    				continue ;
    			}
    			
    			while(i < j){
    				
    				// 对i和j进行++和--操作的时候，循环条件必须判断i<j，因为++和--操作会导致i和j变化
    				// 从右向左找比key小的值
    				while(i < j && ary[j] >= key){
    					j--;
    				}
    				if(i < j){
    					ary[i] = ary[j];
    				}
    				
    				// 从左向右找比key大的值
    				while(i < j && ary[i] <= key){
    					i++;
    				}
    				if(i < j){
    					ary[j] = ary[i];
    				}
    				
    			}
    			ary[i] = key;

                // ----右半边----
                stack.push(j + 1);
                stack.push(end);
                // ----左半边----
                stack.push(start);
                stack.push(i - 1);
            }
        }
	}
	
}
