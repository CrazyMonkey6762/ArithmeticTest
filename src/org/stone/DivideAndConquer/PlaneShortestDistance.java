package org.stone.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.stone.entity.Node;
import org.stone.utils.NodeComparable8Y;

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

	private static Node[] nodes;

	/**
	 * @Description求平面上距离最短的两个点
	 * @param nodes
	 *            点集合
	 * @return
	 */
	public static double shortestDistance(int low, int height) {

		// 1.在不可再分的情况下
		if (height - low == 1) {
			return distance(nodes[low], nodes[height]);
		}
		if (height - low == 2) {
			double distance1 = distance(nodes[low], nodes[low + 1]);
			double distance2 = distance(nodes[height], nodes[low + 1]);
			return min(distance1, distance2);
		}
		// 2.再可分的情况下

		/*
		 * 最短距离一定在三者中产生 
		 * 1.左边的最短 
		 * 2.右边的最短
		 * 3.一个在左边 一个在右边
		 */
		int mid = (low + height) / 2;
		double shotestDistance = min(shortestDistance(low, mid),
				shortestDistance(mid + 1, height));

		// 3.1获取中间的备选点
		List<Node> list = new ArrayList<Node>();
		for (int i = low; i <= height; i++) {
			//如果横坐标都超出了shotestDistance，那么肯定不可能成为最短距离的候选点
			if ((nodes[i].x > nodes[mid].x - shotestDistance)
					&& (nodes[i].x < nodes[mid].x + shotestDistance)) {
				list.add(nodes[i]);
			}
		}
		Node[] tempNodes = (Node[]) list.toArray();
		int tempLength = tempNodes.length;
		// 对其按照y坐标排序
		Arrays.sort(tempNodes, 0, tempNodes.length, new NodeComparable8Y());
		// 3.2求备选点和shotestDistance间的最小值
		for (int i = 0; i < tempLength; i++) {
			//由格子原理可以求得最多只有6个点
			int k = (i + 7) > tempLength ? tempLength : i + 7;
			for (int j = i + 1; j < k; j++) {
				//因为是按照y坐标排序的，一旦i和j的y坐标大于shotestDistance，那么下面的j点与i的距离肯定更大
				if (Math.abs(tempNodes[j].y - tempNodes[i].y) >= shotestDistance) {
					break;
				}
				shotestDistance = min(shotestDistance,
						distance(tempNodes[j], tempNodes[i]));
			}
		}
		return shotestDistance;
	}

	/**
	 * @Description 求AB的最小值
	 * @param a
	 * @param b
	 * @return
	 */
	public static double min(double a, double b) {
		return a > b ? b : a;
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
		int nodeNumber = 0;
		System.out.println("请先输入平面点的个数   再输入各个点的坐标，      中间用空格分割即可");
		while (scan.hasNext()) {
			try {
				nodeNumber = scan.nextInt();
				nodes = new Node[nodeNumber];
				for (int i = 0; i < nodes.length; i++) {
					nodes[i] = new Node(scan.nextDouble(), scan.nextDouble());
				}
			} catch (Exception e) {
				throw new Exception("输入的格式不正确");
			}
			//按照x坐标排序，然后再操作
			Arrays.sort(nodes);
			double result = shortestDistance(0, nodeNumber - 1);
			System.out.println("最短距离为" + result);
			System.out.println("请先输入平面点的个数   再输入各个点的坐标，     中间用空格分割即可");
		}
	}
}
