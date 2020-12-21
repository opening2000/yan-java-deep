package leetcode.problem.group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * https://leetcode-cn.com/problems/group-anagrams/
 * @author Yankj
 *
 */
public class GroupAnagrams {

	public static void main(String[] args) {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		
		Solution2 solution = new Solution2();
		System.out.println(solution.groupAnagrams(strs));
	}

}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> resultList = new ArrayList<List<String>>();
    	if(strs == null || strs.length == 0) {
    		return resultList;
    	}
    	
    	Map<String, Integer> wordIndexMap = new HashMap<String, Integer>();
    	
    	for(String str:strs) {
    		char[] charAry = str.toCharArray();
    		Arrays.sort(charAry);
    		String newWord = String.valueOf(charAry);
    		
    		if(wordIndexMap.containsKey(newWord)) {
    			int index = wordIndexMap.get(newWord);
    			List<String> subAry = resultList.get(index);
    			subAry.add(str);
    		}else {
    			int index = resultList.size();
    			List<String> subAry = new ArrayList<String>();
    			subAry.add(str);
    			resultList.add(subAry);
    			wordIndexMap.put(newWord, index);
    		}
    	}
    	return resultList;
    }
}


class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
