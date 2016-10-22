package org.stone.DivideAndConquer;

import java.util.Scanner;

import org.stone.entity.Node;

/**
 * @Title_PlaneShortestDistance.java
 * @author_Stone6762
 * @CreationTime_2016年10月21日 下午7:12:05
 * @Description 找出平面上n个点中
 * 
 *              任意两个点的距离中
 * 
 *              最短的那个
 */
public class PlaneShortestDistance {

	/** 
	 * @Description求平面上距离最短的两个点
	 * @param nodes 点集合
	 * @return
	 */
	private static Node[] shortestDistance(Node[] nodes) {
		Node[] shortestNode = new Node[2];

		
		
		
		
		
		
		
		
		
		return shortestNode;
	}

	/** 
	 * @Description求平面两点间的距离
	 * @param node1
	 * @param node2
	 * @return根号下（x1-x2）平方+（y1-y2）平方
	 */
	private static double distance(Node node1, Node node2) {
		return Math.sqrt((node1.x - node2.x) * (node1.x - node2.x)
				+ (node1.y - node2.y) * (node1.y - node2.y));
	}

	public static void main(String[] args) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		Node[] nodes = null;
		while (scan.hasNext()) {
			System.out.println("请先输入平面点的个数   再输入各个点的坐标，      中间用空格分割即可");
			try {
				int nodeNumber = scan.nextInt();
				nodes = new Node[nodeNumber];
				for (int i = 0; i < nodes.length; i++) {
					nodes[i] = new Node(scan.nextDouble(), scan.nextDouble());
				}
			} catch (Exception e) {
				throw new Exception("输入的格式不正确");
			}
			Node[] shortNodes = shortestDistance(nodes);
			System.out.println("最短的两个点的坐标为：");
			for (int i = 0; i < shortNodes.length; i++) {
				System.out.println(shortNodes[i].toString());
			}
			System.out.println("最短的距离为"+distance(shortNodes[0], shortNodes[1]));
		}
	}
}
