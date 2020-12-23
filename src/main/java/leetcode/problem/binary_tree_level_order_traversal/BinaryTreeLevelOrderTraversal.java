package leetcode.problem.binary_tree_level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * @author Yankj
 *
 */
public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		String line = "[3,9,20,null,null,15,7]";
        TreeNode root = Util.stringToTreeNode(line);
        List<List<Integer>> ret = new Solution().zigzagLevelOrder(root);
        String out = Util.int2dListToString(ret);
        System.out.print(out);

	}

}

class Util {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	if(root == null) {
    		return list;
    	}
    	int level = 0;
    	levelOrderTravel(root, list, level);
    	return list;
    }
    
    public void levelOrderTravel(TreeNode root, List<List<Integer>> list, int level) {
    	Stack<TreeNode> nodeStack = new Stack<TreeNode>();
    	nodeStack.push(root);
    	
    	while (!nodeStack.isEmpty()) {
    		List<Integer> subList = new ArrayList<Integer>();
    		int currLevelNodeCount = nodeStack.size();
    		
    		Queue<TreeNode> currLevelNodeQueue = new LinkedList<TreeNode>();
    		for(int i=0;i<currLevelNodeCount;i++) {
    			TreeNode currLevelNode = nodeStack.pop();
    			subList.add(currLevelNode.val);
    			
    			// 向队列中添加下一层节点，假设root对应level为0，所在层的level为偶数，从左到右，所在层level为奇数，从右到左
    			if(level % 2 == 0) {
    				if(currLevelNode.left != null) {
    					currLevelNodeQueue.offer(currLevelNode.left);
    				}
    				if(currLevelNode.right != null) {
    					currLevelNodeQueue.offer(currLevelNode.right);
    				}
    			}else if(level % 2 == 1) {
    				if(currLevelNode.right != null) {
    					currLevelNodeQueue.offer(currLevelNode.right);
    				}
    				if(currLevelNode.left != null) {
    					currLevelNodeQueue.offer(currLevelNode.left);
    				}
    			}
    		}
    		// 如果直接在for循环内部压到栈里面，那么直接就后入先出弹出栈了，顺序也就乱了，达不到层序遍历
    		// 有两种思路：
    		// 1、没个for循环中使用list或者queue收集节点，结束for循环后压入栈中
    		// 2、在每个while循环loop中新创建一个newStack，for循环中压入新的stack中，每个while循环loop结束的时候把老的stack指向新stack
    		// 把本层级的节点压到栈里面
    		while(!currLevelNodeQueue.isEmpty()) {
    			nodeStack.push(currLevelNodeQueue.poll());
    		}
    		list.add(subList);
    		// 本轮循环处理完成
    		level++;
		}
    }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
