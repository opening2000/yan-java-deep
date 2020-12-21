package leetcode.problem.increasing_digits;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/monotone-increasing-digits/
 * @author Yankj
 *
 */
public class MonotoneIncreasingDigits {

	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		// 10,1234,332,1000,101,989998
		System.out.println(solution.monotoneIncreasingDigits(989998));

	}

}

/**
 * 此方法未通过
 * @author Yankj
 *
 */
class Solution {
    public int monotoneIncreasingDigits(int N) {
        int len = 0;
        List<Integer> nums = new ArrayList<Integer>();
        while(N > 0) {
        	int tmp = N % 10;
        	nums.add(tmp);
        	N = N / 10;
        	len++;
        }
        
        Integer[] ary = nums.toArray(new Integer[0]);
        int[] target = new int[len];
        int sum = 0;
        if(len > 0) {
        	for(int i=0;i<len-1;i++) {
        		if(ary[i] == 0) {
        			target[len-1-i] = 9;
        			ary[i] = 9;
        			target[len-1-i-1] = ary[i+1] - 1;
        			ary[i+1] -= 1;
        			
        			if((len-i) >= 0 && (len-i) <= (len-1)) {
        				target[len-i] = 9;
        			}
        			if((i-1) >= 0 && (i-1) <= (len-1)) {
        				ary[i-1] = 9;
        			}
        			i = -1;
        			sum = 0;
        			continue;
        		}
        		if(ary[i] >= ary[i+1] && ary[i] > 0) {
        			target[len-1-i] = ary[i];
        		}else if(ary[i+1] >= 0){
        			target[len-1-i] = 9;
        			ary[i] = 9;
        			target[len-1-i-1] = ary[i+1] - 1;
        			ary[i+1] -= 1;
        		}
        		sum += Math.pow(10, i) * ary[i];
        	}
        	sum += Math.pow(10, len-1) * ary[len-1];
        }
        
    	return sum;
    }
}


class Solution2 {
    public int monotoneIncreasingDigits(int N) {
        int len = 0;
        List<Integer> nums = new ArrayList<Integer>();

//        int minIndex = 0;
        int index = -1;
        int lastVal = 10;
        int currentVal = 0;
        int minVal = 10;
        while(N > 0) {
        	currentVal = N % 10;
        	nums.add(currentVal);
        	
        	if(currentVal > lastVal) {
        		index = len;
        		currentVal = currentVal - 1;
        		
        		if(currentVal < 0) {
        			currentVal = 0;
        			index = len + 1;
        		}
        	}
//        	if(index > minIndex) {
//        		minIndex = index;
//        	}
        	
        	// 下一位
        	N = N / 10;
        	len++;
        	lastVal = currentVal;
        }
        
        Integer[] ary = nums.toArray(new Integer[0]);
        int[] target = new int[len];
        int sum = 0;
        if(len > 0) {
        	for(int i=0;i<len;i++) {
        		if(i<index) {
        			ary[i] = 9;
        		}else if(i==index) {
        			ary[i] -= 1;
        		}
        		sum += Math.pow(10, i) * ary[i];
        	}
        	//sum += Math.pow(10, len-1) * ary[len-1];
        }
        
    	return sum;
    }
}