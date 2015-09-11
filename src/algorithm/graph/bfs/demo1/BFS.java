package algorithm.graph.bfs.demo1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * http://www.jb51.net/article/52614.htm
 * 	本文以实例形式讲述了基于Java的图的广度优先遍历算法实现方法，具体方法如下：
	用邻接矩阵存储图方法：
	1.确定图的顶点个数和边的个数
	
	2.输入顶点信息存储在一维数组vertex中
	
	3.初始化邻接矩阵；
	
	4.依次输入每条边存储在邻接矩阵arc中
	输入边依附的两个顶点的序号i,j；
	将邻接矩阵的第i行第j列的元素值置为1；
	将邻接矩阵的第j行第i列的元素值置为1；
	广度优先遍历实现：
	1.初始化队列Q
	2.访问顶点v；visited[v]=1;顶点v入队Q;
	3.while(队列Q非空)
	v=队列Q的队头元素出队；
	w=顶点v的第一个邻接点
	while(w存在)
	如果w未被访问，则访问顶点w;visited[w]=1;顶点w入队列Q
	w=顶点v的下一个邻接点
	
 * @author Yan
 *
 */
public class BFS {

	// 存储节点信息
	private Object[] vertices;
	// 存储边的信息数组
	private int[][] arcs;
	// 边的条数
	private int vexnum;
	// 记录第i个节点是否被访问过
	private boolean[] visited;
	// 构建一个临时链表存已经遍历过的节点
	private List<Object> temp = new ArrayList<Object>();

	/**
	 * @param args
	 * 
	 * @author TD_LSW
	 */
	public static void main(String[] args) {

		BFS g = new BFS(8);
		Character[] vertices = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
		g.addVertex(vertices);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(2, 6);
		g.addEdge(2, 7);

		System.out.println("图的广度优先遍历：");
		g.bfs();
	}

	// 广度优先遍历实现
	private void bfs() {
		// TODO Auto-generated method stub
		for (int i = 0; i < vexnum; i++) {
			visited[i] = false;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < vexnum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				visit(i);
				q.add(i);
				while (!q.isEmpty()) {
					int j = (Integer) q.remove().intValue();
					// 判断如果全部遍历完了就不需要循环了
					if (temp.size() == vexnum) {
						q.removeAll(q);
						return;
					}
					for (int k = this.firstAdjVex(j); k >= 0; k = this
							.nextAdjVex(j, k)) {
						if (!visited[k]) {
							q.add(k);
							visited[k] = true;
							visit(k);
						}
					}
				}
			}
		}

	}

	// 查找下一个节点
	public int firstAdjVex(int i) {
		for (int j = 0; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	public int nextAdjVex(int i, int k) {
		for (int j = k + 1; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	// 初始化图的边
	private void addEdge(int i, int j) {
		// TODO Auto-generated method stub
		if (i == j)
			return;
		arcs[i][j] = 1;
		arcs[j][i] = 1;

	}

	// 初始化图的节点
	private void addVertex(Object[] object) {
		// TODO Auto-generated method stub
		this.vertices = object;
	}

	// 图的初始化
	public BFS(int n) {
		// TODO Auto-generated constructor stub
		vexnum = n;
		vertices = new Object[n];
		arcs = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				arcs[i][j] = 0;
			}
		}
	}

	private void visit(int i) {
		// TODO Auto-generated method stub
		temp.add(vertices[i]);
		System.out.print(vertices[i] + " ");
	}

}