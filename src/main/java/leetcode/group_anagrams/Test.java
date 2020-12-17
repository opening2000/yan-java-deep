package leetcode.group_anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class Test {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
          
        Map<String,String> map = new HashMap<String,String>();
        map.put("A", "A");
        map.put("B", "B");
        map.put("C", "C");
        map.put("D", "D");
        map.put("E", "E");
        //List<String> valuesList = (List<String>) map.values();
        List<String> valuesList = new ArrayList<String>(map.values());
        for(String str:valuesList){
            System.out.println(str);
        }
    }
 
}
