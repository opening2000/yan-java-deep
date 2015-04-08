package datastruct.full;


public class HuffmanTreeNode extends BinTreeNode {

	/**
	 * 权值
	 * */
	private int weight;

	/**
	 * 编码
	 * */
	private String coding;

	public HuffmanTreeNode(int weight) {
		this(weight, null);
	}

	public HuffmanTreeNode(int weight, Object e) {
		super(e);
		this.weight = weight;
	}

	// 改写父类方法
	public HuffmanTreeNode getParent() {
		return (HuffmanTreeNode) super.getParent();
	}


	public HuffmanTreeNode getLChild() {
		return (HuffmanTreeNode) super.getLChild();
	}


	public HuffmanTreeNode getRChild() {
		return (HuffmanTreeNode) super.getRChild();
	}


	// get&set 方法
	public int getWeight() {
		return weight;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	// 通过结点数组生成Huffman 树
	private static HuffmanTreeNode buildHuffmanTree(HuffmanTreeNode[] nodes) {
		int n = nodes.length;
		if (n < 2)
			return nodes[0];
		List l = new ListArray(); // 根结点线性表，按weight 从大到小有序
		for (int i = 0; i < n; i++)
			// 将结点逐一插入线性表
			insertToList(l, nodes[i]);
		for (int i = 1; i < n; i++) { // 选择weight 最小的两棵树合并，循环n-1 次
			HuffmanTreeNode min1 = (HuffmanTreeNode) l.remove(l.getSize() - 1);
			HuffmanTreeNode min2 = (HuffmanTreeNode) l.remove(l.getSize() - 1);
			HuffmanTreeNode newRoot = new HuffmanTreeNode(min1.getWeight()
					+ min2.getWeight());
			newRoot.setLChild(min1);
			newRoot.setRChild(min2); // 合并
			insertToList(l, newRoot);// 新树插入线性表
		}
		return (HuffmanTreeNode) l.get(0);// 返回Huffman 树的根
	}

	// 将结点按照weight 从大到小的顺序插入线性表
	private static void insertToList(List l, HuffmanTreeNode node) {
		for (int j = 0; j < l.getSize(); j++)
			if (node.getWeight() > ((HuffmanTreeNode) l.get(j)).getWeight()) {
				l.insert(j, node);
				return;
			}
		l.insert(l.getSize(), node);
	}

	// 递归生成Huffman 编码
	private static void generateHuffmanCode(HuffmanTreeNode root) {
		if (root == null)
			return;
		if (root.hasParent()) {
			if (root.isLChild())
				root.setCoding(root.getParent().getCoding() + "0"); // 向左为0
			else
				root.setCoding(root.getParent().getCoding() + "1"); // 向右为1
		}
		generateHuffmanCode(root.getLChild());
		generateHuffmanCode(root.getRChild());
	}
}
