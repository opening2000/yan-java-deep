package datastruct.tree.btree.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
 
 
public class BTree<E extends Comparable<E>> {
 
    private static final int DEFAULT_T = 2;
    private BTNode root;
    // 最小度数
    private int t;
    // 非根节点的最少关键字个数
    private int minKeyNum;
    // 非根节点的最大关键字个数
    private int maxKeyNum;
 
    
    private class BTNode {
        // 关键字个数
        public int n = 0;
        public List<E> keys = new ArrayList<E>(maxKeyNum);
        public List<BTNode> children = new ArrayList<BTNode>(maxKeyNum + 1);
        public boolean isLeaf = true;
 
        
        public void insertKey(int index, E key) {
            keys.add(index, key);
            n ++;
            if (keys.size() > maxKeyNum) {
                keys.remove(maxKeyNum);
            }
        }
        
        
        public E removeKey(int index) {
            E key = keys.remove(index);
            n --;
            return key;
        }
 
        
        public void insertChild(int index, BTNode child) {
            children.add(index, child);
            if (children.size() > maxKeyNum + 1) {
                children.remove(maxKeyNum + 1);
            }
        }
        
        
        public BTNode removeChild(int index) {
            BTNode child = children.remove(index);
            return child;
        }
 
    }
 
    public BTree() {
        this(DEFAULT_T);
    }
    
    
    public BTree(int degree) {
        if (degree < 2) {
            t = DEFAULT_T;
        }
        this.t = degree;
        this.minKeyNum = degree - 1;
        this.maxKeyNum = 2 * degree - 1;
        BTNode node = new BTNode();
        this.root = node;
    }
 
    
    private void sliptChild(BTNode x, int index) {
        // 新增结点
        BTNode z = new BTNode();
        BTNode y = x.children.get(index);
        z.isLeaf = y.isLeaf;
        for (int j = 0; j < minKeyNum; j++) {
            z.insertKey(j, y.keys.get(j + t));
        }
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.insertChild(j, y.children.get(j + t));
            }
        }
        z.n = minKeyNum;
        y.n = minKeyNum;
        x.insertChild(index + 1, z);
        x.insertKey(index, y.keys.get(minKeyNum));
    }
 
    
    private void insertNoFull(BTNode x, E key) {
        int i = x.n - 1;
        if (x.isLeaf) {
            while (i >= 0 && key.compareTo(x.keys.get(i)) < 0)
                i--;
            x.insertKey(i + 1, key);
        } else {
            while (i >= 0 && key.compareTo(x.keys.get(i)) < 0)
                i--;
            i = i + 1;
            if (x.children.get(i).n == maxKeyNum) {
                sliptChild(x, i);
                if (key.compareTo(x.keys.get(i)) > 0)
                    i = i + 1;
            }
            insertNoFull(x.children.get(i), key);
        }
    }
 
    
    public void insert(E key) {
        BTNode r = root;
        if (root.n == maxKeyNum) {
            BTNode newRoot = new BTNode();
            root = newRoot;
            newRoot.isLeaf = false;
            newRoot.insertChild(0, r);
            sliptChild(newRoot, 0);
            insertNoFull(newRoot, key);
        } else
            insertNoFull(r, key);
    }
    
    
    public void delete(E key) {
        delete(root, key);
    }
    
    
    private void delete(BTNode x, E key) {
        // 该过程需要保证，对非根节点执行删除操作时，其关键字个数至少为t。
        int n = x.n;
        assert n >= t || x == root;
        int i = 0;
        while (i < n && key.compareTo(x.keys.get(i)) > 0) 
            i ++;
        if (i < n && key.equals(x.keys.get(i))) {
            if (x.isLeaf) {
                // 1 如果当前结点是叶子结点，直接删除关键字
                x.removeKey(i);
            } else {
                BTNode y = x.children.get(i);
                BTNode z = x.children.get(i + 1); 
                if (y.n >= t) {
                    // 2.a
                    E preKey = deleteMaxKey(y);
                    x.keys.set(i, preKey);
                } else if (z.n >= t) {
                    // 2.b
                    E nextKey = deleteMinKey(z);
                    x.keys.set(i, nextKey);
                } else {
                    // 2.c
                    int ySize = y.n;
                    int zSize = z.n;
                    y.insertKey(ySize, key);
                    ySize ++;
                    boolean isChildLeaf = y.isLeaf;
                    for (int j = 0; j < zSize; j++) {
                        y.insertKey(ySize, z.keys.get(j));
                        if (! isChildLeaf) {
                            y.insertChild(ySize, z.children.get(j));
                        }
                        ySize ++;
                    }
                    if (! isChildLeaf) {
                        y.insertChild(ySize, z.children.get(zSize- 1));
                    }
                    x.removeKey(i);
                    x.removeChild(i + 1);
                    if (x.n == 0) {
                        root = y;
                    }
                    delete(y, key);
                }
            }
        } else if (x.isLeaf) {
            // 没有找到该关键字，直接返回
            return;
        } else {
            BTNode child = x.children.get(i);
            boolean isChildLeaf = child.isLeaf;
            if (child.n >= t) {
                delete(child, key);
            } else if (i > 0 && x.children.get(i - 1).n >= t){
                // 3.a 左兄弟满足条件
                BTNode leftBrother = x.children.get(i - 1);
                int leftBrotherKeyNum = leftBrother.n;
                E leftBrotherLastKey = leftBrother.keys.get(leftBrotherKeyNum - 1);
                child.insertKey(0, x.keys.get(i - 1));
                x.keys.set(i - 1, leftBrotherLastKey);
                if (!isChildLeaf) {
                    BTNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
                    child.insertChild(0, leftBrotherLastChild);
                    leftBrother.removeChild(leftBrotherKeyNum);
                }
                leftBrother.removeKey(leftBrotherKeyNum - 1);
                delete(child, key);
            } else if (i < x.n && x.children.get(i + 1).n >= t) {
                // 3.a 右兄弟满足条件
                BTNode rightBrother = x.children.get(i + 1);
                E rightBrotherFirstKey = rightBrother.keys.get(0);
                int childKeyNum = child.n;
                child.insertKey(childKeyNum, x.keys.get(i));
                x.keys.set(i, rightBrotherFirstKey);
                if (!isChildLeaf) {
                    BTNode rightBrotherFirstChild = rightBrother.children.get(0);
                    child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
                    rightBrother.removeChild(0);
                }
                rightBrother.removeKey(0);
                delete(child, key);
            } else if (i > 0){
                // 3.b 存在左兄弟，合并
                BTNode leftBrother = x.children.get(i - 1);
                int leftBrotherKeyNum = leftBrother.n;
                leftBrother.insertKey(leftBrotherKeyNum, x.keys.get(i - 1));
                leftBrotherKeyNum ++;
                for (int j = 0; j < t - 1; j ++) {
                    leftBrother.insertKey(leftBrotherKeyNum, child.keys.get(j));
                    if (!isChildLeaf) {
                        leftBrother.insertChild(leftBrotherKeyNum, child.children.get(j));
                    }
                    leftBrotherKeyNum ++;
                }
                if (!isChildLeaf) {
                    leftBrother.insertChild(leftBrotherKeyNum, child.children.get(t - 1));
                }
                x.removeChild(i);
                x.removeKey(i - 1);
                if (x.n == 0) {
                    root = leftBrother;
                }
                delete(leftBrother, key);
            } else {
                // 3.b 存在右兄弟，合并
                BTNode rightBrother = x.children.get(i + 1);
                int childKeyNum = child.n;
                child.insertKey(childKeyNum, x.keys.get(i));
                childKeyNum ++;
                for (int j = 0; j < t - 1; j ++) {
                    child.insertKey(childKeyNum, rightBrother.keys.get(j));
                    if (!isChildLeaf) {
                        child.insertChild(childKeyNum, rightBrother.children.get(j));
                    }
                    childKeyNum ++;
                }
                if (! isChildLeaf) {
                    child.insertChild(childKeyNum, rightBrother.children.get(t - 1));
                }
                x.removeKey(i);
                x.removeChild(i + 1);
                if (x.n == 0) {
                    root = child;
                }
                delete(child, key);
            }
        }
    }
    
    
    private E deleteMinKey(BTNode x) {
        if (x.isLeaf) {
            return x.removeKey(0);
        } else {
            BTNode child = x.children.get(0);
            boolean isChildLeaf = child.isLeaf;
            BTNode rightBrother = x.children.get(1);
            if (child.n >= t) {
                return deleteMinKey(child);
            } else if (rightBrother.n >= t){
                // 3.a
                E rightBrotherFirstKey = rightBrother.keys.get(0);
                int childKeyNum = child.n;
                child.insertKey(childKeyNum, x.keys.get(0));
                x.keys.set(0, rightBrotherFirstKey);
                if (!isChildLeaf) {
                    BTNode rightBrotherFirstChild = rightBrother.children.get(0);
                    child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
                    rightBrother.removeChild(0);
                }
                rightBrother.removeKey(0);
                return deleteMinKey(child);
            } else {
                // 3.b
                int childKeyNum = child.n;
                child.insertKey(childKeyNum, x.keys.get(0));
                childKeyNum ++;
                for (int j = 0; j < t - 1; j ++) {
                    child.insertKey(childKeyNum, rightBrother.keys.get(j));
                    if (!isChildLeaf) {
                        child.insertChild(childKeyNum, rightBrother.children.get(j));
                    }
                    childKeyNum ++;
                }
                if (! isChildLeaf) {
                    child.insertChild(childKeyNum, rightBrother.children.get(t - 1));
                }
                x.removeChild(1);
                x.removeKey(0);
                return deleteMinKey(child);
            }
        }
    }
    
    
    private E deleteMaxKey(BTNode x) {
        int keyNum = x.n;
        if (x.isLeaf) {
            return x.removeKey(keyNum - 1);
        } else {
            BTNode child = x.children.get(keyNum);
            boolean isChildLeaf = child.isLeaf;
            BTNode leftBrother = x.children.get(keyNum - 1);
            int leftBrotherKeyNum = leftBrother.n;
            
            if (child.n >= t) {
                return deleteMaxKey(child);
            } else if (leftBrother.n >= t){
                // 3.a
                E leftBrotherLastKey = leftBrother.keys.get(leftBrotherKeyNum - 1);
                child.insertKey(0, x.keys.get(keyNum - 1));
                x.keys.set(keyNum - 1, leftBrotherLastKey);
                if (!isChildLeaf) {
                    BTNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
                    child.insertChild(0, leftBrotherLastChild);
                    leftBrother.removeChild(leftBrotherKeyNum);
                }
                leftBrother.removeKey(leftBrotherKeyNum - 1);
                return deleteMaxKey(child);
            } else {
                // 3.b
                leftBrother.insertKey(leftBrotherKeyNum, x.keys.get(keyNum - 1));
                leftBrotherKeyNum ++;
                for (int j = 0; j < t - 1; j ++) {
                    leftBrother.insertKey(leftBrotherKeyNum, child.keys.get(j));
                    if (!isChildLeaf) {
                        leftBrother.insertChild(leftBrotherKeyNum, child.children.get(j));
                    }
                    leftBrotherKeyNum ++;
                }
                if (!isChildLeaf) {
                    leftBrother.insertChild(leftBrotherKeyNum, child.children.get(t - 1));
                }
                // 删除关键字和孩子的操作最好放在后面来执行
                x.removeChild(keyNum);
                x.removeKey(keyNum - 1);
                return deleteMaxKey(leftBrother);
            }
        }
    }
    
    
    public void print() {
        Queue<BTNode> queue = new LinkedBlockingQueue<BTNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BTNode node = queue.poll();
            for (int i = 0; i < node.n; i ++) {
                System.out.print(node.keys.get(i) + " ");
            }
            System.out.println();
            if (!node.isLeaf) {
                for (int i = 0; i < node.n + 1; i ++) {
                    queue.add(node.children.get(i));
                }
            }
        }
        
    }
 
    
    public static void main(String[] args) {
        BTree<String> bTree = new BTree<String>(2);
        bTree.insert("F");
        bTree.insert("S");
        bTree.insert("Q");
        bTree.insert("K");
        bTree.insert("C");
        bTree.insert("L");
        bTree.insert("H");
        bTree.insert("T");
        bTree.insert("V");
        bTree.insert("W");
        bTree.insert("M");
        bTree.insert("R");
        bTree.insert("N");
        bTree.insert("P");
        bTree.insert("A");
        bTree.insert("B");
        bTree.insert("X");
        bTree.insert("Y");
        bTree.insert("D");
        bTree.insert("Z");
        bTree.insert("E");
//        bTree.delete("Z");
//        bTree.delete("W");
//        bTree.delete("D");
//        bTree.delete("H");
//        bTree.delete("K");
//        bTree.delete("A");
//        bTree.delete("Y");
//        bTree.delete("F");
//        bTree.delete("M");
//        bTree.delete("Q");
//        bTree.delete("R");
//        bTree.delete("V");
//        bTree.delete("P");
//        bTree.delete("E");
//        bTree.delete("B");
//        bTree.delete("S");
//        bTree.delete("N");
//        bTree.delete("X");
//        bTree.delete("T");
//        bTree.delete("C");
//        bTree.delete("L");
        bTree.print();
    }
 
}