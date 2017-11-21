package datastruct.tree.binarytree.demo2;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreOrderTree<Character> trees = new PreOrderTree<Character>();
		String test = new String("abcdefghijklmn");
		for (int i = 0; i < test.length(); i++) {
			trees.insert(test.charAt(i));
		}
		List<Character> list = trees.listNode();
		for (char k : list) {
			System.out.println(k);
		}
		System.out.println("---------------------------");
		System.out.println(trees.getParent('d'));
		System.out.println(trees.getParent('p'));
		System.out.println(trees.getParent('c'));
		System.out.println(trees.getParent('k'));
	}
}

class PreOrderTree<E> {

	//根节点
	private Node<E> node;

	/** 父节点 **/
	private Node<E> prenode;
	/** 当前节点 **/
	private Node<E> curnode;

	/** 节点删除 后序遍历 **/
	boolean delete(E e) {
		// TODO Auto-generated method stub
		Node<E> delnode = findByValue(node, e);

		return false;
	}

	/**
	 * node原根部节点 从bgnode节点开始删除
	 * 
	 * 返回类型：删除后的Node 遍历删除指定节点后面的节点 后续遍历
	 **/
	public Node<E> delNodes(Node<E> node, Node<E> bgnode) {
		while (node.Lnode != null) {
			node = node.Lnode;
			prenode = node;
		}
		return node;
	}

	/** 得到节点值为e的第一个节点 **/
	public Node<E> findByValue(Node<E> node, E e) {
		if (node.e == e) {
			return node;
		} else if (node.Lnode != null && !node.Lnode.equals(e)) {
			return findByValue(node.Lnode, e);
		} else if (node.Rnode != null && !node.Rnode.equals(e)) {
			return findByValue(node.Rnode, e);
		} else if (node.Lnode != null && node.Lnode.equals(e)) {
			return node.Lnode;
		} else if (node.Rnode != null && node.Rnode.equals(e)) {
			return node.Rnode;
		}
		return null;
	}

	/**
	 * 查找Node节点之后的所有子节点集合并返回，若无则返回null
	 **/
	public List<E> findByNodeList(Node<E> node) {
		List<E> list = new ArrayList<E>();
		list.add(node.e);
		return prenode(list, node);
	}

	void update(E e1, E e2) {
		// TODO Auto-generated method stub

	}

	public PreOrderTree() {
		node = new Node<E>();
	}

	public Node<E> getNode() {
		return node;
	}

	/***************************************************************************
	 * 根节点遍历
	 **************************************************************************/
	public List<E> prenode(List<E> list, Node<E> node) {
		if (node.Lnode != null) {
			list.add(node.Lnode.e);
			prenode(list, node.Lnode);
		}
		if (node.Rnode != null) {
			list.add(node.Rnode.e);
			prenode(list, node.Rnode);
		}
		return list;
	}

	/** 遍历出二叉树的所有元素 **/
	public List<E> listNode() {
		List<E> list = new ArrayList<E>();
		list.add(node.e);
		return prenode(list, node);
	}

	boolean isFind(E e) {
		// TODO Auto-generated method stub
		List<E> lists = listNode();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).equals(e)) {
				return true;
			}
		}
		return false;
	}

	void insert(E e) {
		insert(node, e);
	}

	/** 插入 **/
	void insert(Node<E> node, E e) {
		if (node.e == null) {
			node.e = e;
		} else if (node.Lnode == null) {
			node.Lnode = new Node<E>();
			insert(node.Lnode, e);
		} else if (node.Rnode == null) {
			node.Rnode = new Node<E>();
			insert(node.Rnode, e);
		} else {
			//当前节点的左右子节点都不为空了，往左子节点添加子级
			//这样构造出来的是个不平衡的二叉树
			insert(node.Lnode, e);
		}
	}

	public E getParent(E e) {
		boolean flag = true;
		curnode = node;
		// prenode=node;
		if (node.e == e) {
			return null;
		}
		//目前的这种逻辑会导致右节点找不到父节点
		while (flag) {
			if (curnode.Lnode != null && curnode.Lnode.e != e) {
				// prenode=curnode;
				curnode = curnode.Lnode;
			} else if (curnode.Lnode != null && curnode.Lnode.e == e) {
				return curnode.e;
			} else if (curnode.Rnode != null && !curnode.Rnode.equals(e)) {
				// prenode=curnode;
				curnode = curnode.Rnode;
			} else if (curnode.Rnode != null && curnode.Rnode.equals(e)) {
				return curnode.e;
			} else {
				return null;
			}
		}
		return null;
	}
}

class Node<E> {
	//当前节点的左节点
	Node<E> Lnode;
	//当前节点的右节点
	Node<E> Rnode;
	//当前节点的值
	E e;

	public Node<E> getLnode() {
		return Lnode;
	}

	public void setLnode(Node<E> lnode) {
		Lnode = lnode;
	}

	public Node<E> getRnode() {
		return Rnode;
	}

	public void setRnode(Node<E> rnode) {
		Rnode = rnode;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

}