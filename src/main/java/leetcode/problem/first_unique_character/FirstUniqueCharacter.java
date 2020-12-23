package leetcode.problem.first_unique_character;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * @author Yankj
 *
 */
public class FirstUniqueCharacter {

	public static void main(String[] args) {
		String s = "loveleetcode";
		Solution solution = new Solution();
		System.out.println(solution.firstUniqChar(s));

	}

}

class Solution {
    public int firstUniqChar(String s) {
        int[] charCountAry = new int[26];
        int[] charIndexAry = new int[26];
        for(int i=0;i<charIndexAry.length;i++) {
        	charIndexAry[i] = -1;
        }
        
        char[] chars = s.toCharArray();
        
        for(int i=0;i<chars.length;i++) {
        	charCountAry[(int)(chars[i]-'a')]++;
        	if(charCountAry[(int)(chars[i]-'a')] == 1) {
        		charIndexAry[(int)(chars[i]-'a')] = i;
        	}else {
        		charIndexAry[(int)(chars[i]-'a')] = -1;
        	}
        }
        
        int firstUniqCharIndex = -1;
        
        Arrays.parallelSort(charIndexAry);
        
        for(int i=0;i<charIndexAry.length;i++) {
        	if(charIndexAry[i] > -1) {
        		firstUniqCharIndex = charIndexAry[i];
        		break;
        	}
        }
        
        return firstUniqCharIndex;
    }
}
