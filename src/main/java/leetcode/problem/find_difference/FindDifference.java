package leetcode.problem.find_difference;

/**
 * 
 * https://leetcode-cn.com/problems/find-the-difference/
 * @author Yankj
 *
 */
public class FindDifference {

	public static void main(String[] args) {
		String s = "abcd";
		String t = "abcde";
		Solution2 solution = new Solution2();
		System.out.println(solution.findTheDifference(s, t));
	}

}
class Solution {
    public char findTheDifference(String s, String t) {
    	int[] sCount = new int[26];
    	int[] tCount = new int[26];
    	
    	char[] sAry = s.toCharArray();
    	char[] tAry = t.toCharArray();
    	
    	for(int i=0;i<sAry.length;i++) {
    		sCount[(int)(sAry[i]-'a')]++;
    	}
    	for(int i=0;i<tAry.length;i++) {
    		tCount[(int)(tAry[i]-'a')]++;
    	}
    	
    	char result = ' ';
    	for(int i=0;i<sCount.length;i++) {
    		if(tCount[i] > sCount[i]) {
    			result = (char)('a' + i);
    		}
    	}
    	
    	return result;
    } 
}

class Solution2 {
    public char findTheDifference(String s, String t) {
    	int sumS = 0;
    	int sumT = 0;
    	
    	char[] sAry = s.toCharArray();
    	char[] tAry = t.toCharArray();
    	
    	for(int i=0;i<sAry.length;i++) {
    		sumS +=(int)(sAry[i]);
    	}
    	for(int i=0;i<tAry.length;i++) {
    		sumT +=(int)(tAry[i]);
    	}
    	
    	return (char)(sumT-sumS);
    } 
}
