package leetcode.problem.Dota2Senate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/dota2-senate/
 * @author Yankj
 *
 */
public class Dota2Senate {

	public static void main(String[] args) {
		Solution3 solution = new Solution3();
		System.out.println(solution.predictPartyVictory("RDD"));
	}

}

class Solution {
    public String predictPartyVictory(String senate) {
    	System.out.println(senate);
    	char[] nextTurnSenate = new char[senate.length()];
        //StringBuilder nextTurnSenate = new StringBuilder();

        int radiantEffectiveCount = 0;
        int direEffectiveCount = 0;

        char[] senates = senate.toCharArray();

        for(int i=0;i<senates.length;i++){
            if(senates[i] == 'D'){
                // 之前 Radiant 还未使用权利的参议员，会优先禁止掉后面对方一位参议员的权利
                // 所以如果之前有还未使用权利的 Radiant ，这一个 Dire 的权利则被禁止掉
                if(radiantEffectiveCount > 0){
                	radiantEffectiveCount--;
                    // 这一个 Dire 的权利则被禁止掉，不能参加下一轮选举
                	nextTurnSenate[i] = '_';
                }else{
                    // 这一个 Dire 可以用于禁止后面对方的权利
                	direEffectiveCount++;
                    // 这一个 Dire 的权利没有被禁止，可以参加下一轮选举
                	nextTurnSenate[i] = 'D';
                }
            }else{
                // 之前 Dire 还未使用权利的参议员，会优先禁止掉后面对方一位参议员的权利
                // 所以如果之前有还未使用权利的 direEffectiveCount ，这一个 Radiant 的权利则被禁止掉
                if(direEffectiveCount > 0){
                	direEffectiveCount--;
                    // 这一个 Radiant 的权利则被禁止掉，不能参加下一轮选举
                	nextTurnSenate[i] = '_';
                }else{
                	radiantEffectiveCount++;
                    nextTurnSenate[i] = 'R';
                }
            }
        }
        // 判断一下，如果本轮没有使用权利的人数和总人数相同，则获胜
        if(direEffectiveCount == senates.length){
            return "Dire";
        }
        if(radiantEffectiveCount == senates.length){
            return "Radiant";
        }

        // 存在一种情况
        // 一轮结束，还有R或者D的权利没使用的，此时应该禁止掉对方下一轮开始的权利

        for(int i=0;i<nextTurnSenate.length;i++){
        	if(nextTurnSenate[i] == 'R' && direEffectiveCount > 0){
        		direEffectiveCount--;
        		nextTurnSenate[i] = '_';
        	}
        	if(nextTurnSenate[i] == 'D' && radiantEffectiveCount > 0){
        		radiantEffectiveCount--;
        		nextTurnSenate[i] = '_';
        	}
        }

        String nextSenates = String.valueOf(nextTurnSenate).replace("_", "");
        return predictPartyVictory(nextSenates);
    }
}

class Solution2 {
    public String predictPartyVictory(String senate) {
    	System.out.println(senate);
    	char[] nextTurnSenate = new char[senate.length()];
        //StringBuilder nextTurnSenate = new StringBuilder();

        char[] senates = senate.toCharArray();

        for(int i=0;i<senates.length;i++){
        	char us = 'D';
        	char enemy = 'R';
        	if(senates[i] == 'D'){
        		us = 'D';
        		enemy = 'R';
            }else if(senates[i] == 'R'){
        		us = 'R';
        		enemy = 'D';
            }else {
            	// senate[i] == '_'; 的情况
            	continue;
            }
        	
        	// 首先自己在下一轮有投票权
        	nextTurnSenate[i] = us;
        	// 权利还有没有
        	boolean right = true;
        	
        	for(int j=i+1;j<senates.length;j++) {
        		if(senates[j] == enemy) {
        			senates[j] = '_';
        			nextTurnSenate[j] = '_';
        			right = false;
        			break;
        		}
        	}
        	// 自己后面的参议员都是己方，禁掉自己前方参议员的下一轮权力
        	if(right) {
        		for(int j=0;j<i;j++) {
        			if(nextTurnSenate[j] == enemy) {
        				nextTurnSenate[j] = '_';
        				right = false;
        				break;
        			}
        		}
        	}
        	
        	if(right) {
        		return us=='D'?"Dire":"Radiant";
        	}
        }
        String nextSenates = String.valueOf(nextTurnSenate).replace("_", "");
        return predictPartyVictory(nextSenates);
    }
}


class Solution3 {
    public String predictPartyVictory(String senate) {
    	Queue<Integer> dire = new LinkedList<Integer>();
    	Queue<Integer> radiant = new LinkedList<Integer>();
    	
    	int n = senate.length();
    	
    	for(int i=0;i<senate.length();i++) {
    		if(senate.charAt(i) == 'R') {
    			radiant.offer(i);
    		}else if(senate.charAt(i) == 'D') {
    			dire.offer(i);
    		}
    	}
//    	offer，add 区别：
//    	一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
//    	这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。
//    	poll，remove 区别：
//    	remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似， 但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。
//    	peek，element区别：
//    	element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null。
    	while(!dire.isEmpty() && !radiant.isEmpty()) {
    		int direIndex = dire.poll();
    		int radiantIndex = radiant.poll();
    		if(radiantIndex < direIndex) {
    			radiant.offer(radiantIndex+n);
    		}else {
    			dire.offer(direIndex+n);
    		}
    	}
    	
    	if(dire.isEmpty()) {
    		return "Radiant";
    	}else if(radiant.isEmpty()) {
    		return "Dire";
    	}
    	
    	return null;
    }
}