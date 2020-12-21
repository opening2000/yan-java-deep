package leetcode.problem.word_pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * https://leetcode-cn.com/problems/word-pattern/
 * @author Yankj
 *
 */
public class WordPattern {

	public static void main(String[] args) {
		Solution solution = new Solution();
//		String pattern = "abba";
//		String str = "dog cat cat dog";
		String pattern = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd";
		String str = "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";
		System.out.println(solution.wordPattern(pattern, str));
	}

}


class Solution {
    public boolean wordPattern(String pattern, String s) {
    	char[] patternCharAry = pattern.toCharArray();
        String[] wordAry = s.split(" ");
        
        if(patternCharAry.length != wordAry.length) {
        	return false;
        }
    	
    	Map<String , List<Integer>> wordIndexMap1 = new HashMap<>();
        Map<String , List<Integer>> wordIndexMap2 = new HashMap<>();
        List<String> noRepeatWordList1 = new ArrayList<>();
        List<String> noRepeatWordList2 = new ArrayList<>();
        
        for(int i=0;i<patternCharAry.length;i++) {
        	String key = String.valueOf(patternCharAry[i]);
        	
        	List<Integer> list = null;
        	if(wordIndexMap1.containsKey(key)) {
        		list = wordIndexMap1.get(key);
        	}else {
        		list = new ArrayList<>();
        		noRepeatWordList1.add(key);
        	}
        	list.add(i);
        	wordIndexMap1.put(key, list);
        }
        
        for(int i=0;i<wordAry.length;i++) {
        	String key = wordAry[i];
        	
        	List<Integer> list = null;
        	if(wordIndexMap2.containsKey(key)) {
        		list = wordIndexMap2.get(key);
        	}else {
        		list = new ArrayList<>();
        		noRepeatWordList2.add(key);
        	}
        	list.add(i);
        	wordIndexMap2.put(key, list);
        }
        
        if(noRepeatWordList1.size() != noRepeatWordList2.size()) {
        	return false;
        }
        
        for(int i=0;i<noRepeatWordList1.size();i++) {
        	String key1 = noRepeatWordList1.get(i);
        	String key2 = noRepeatWordList2.get(i);
        	
        	List<Integer> list1 = wordIndexMap1.get(key1);
        	List<Integer> list2 = wordIndexMap2.get(key2);
        	
        	if(list1.size() != list2.size()) {
        		return false;
        	}
        	
        	for(int j=0;j<list1.size();j++) {
        		// 之前使用==判断，是因为Integer缓存
        		//if(list1.get(j) != list2.get(j)) {
        		if(!list1.get(j).equals(list2.get(j))) {
        			return false;
        		}
        	}
        }
        
        return true;
    }
}
