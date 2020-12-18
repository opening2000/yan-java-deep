package leetcode.leetbook.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

/**
 * 实际上这不算是层序遍历
 * 
 * @author Yankj
 *
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        levelOrder(root, list, 0);
        return list;
    }

    public void levelOrder(TreeNode root, List<List<Integer>> list, int level) {
        if(root == null){
            return ;
        }
        List<Integer> subList = null;
        while(list.size() < (level+1)){
            subList = new ArrayList<>();
            list.add(subList);
        }
        subList = list.get(level);

        subList.add(root.val);
        int nextLevel = level + 1;
        levelOrder(root.left, list, nextLevel);
        levelOrder(root.right, list, nextLevel);
    }
}

class Solution2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
        	List<Integer> level = new ArrayList<>();
        	int levelNodeCount = queue.size();
        	
        	for(int i=0;i<levelNodeCount;i++) {
        		TreeNode node = queue.poll();
        		
        		TreeNode left = node.left;
        		if(left != null) {
        			queue.offer(left);
        		}
        		TreeNode right = node.right;
        		if(right != null) {
        			queue.offer(right);
        		}
        		level.add(node.val);
        	}
        	
        	list.add(level);
        }

        return list;
    }
}