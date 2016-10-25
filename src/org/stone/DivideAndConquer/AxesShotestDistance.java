package org.stone.DivideAndConquer;

import java.util.Arrays;
import java.util.Scanner;

import org.stone.entity.Node;

/**
 * @ClassName_AxesShotestDistance
 * @author_Stone6762
 * @CreationTime_2016年10月25日 上午10:39:02
 * @Description_为了方便理解平面上的最短距离可以先求坐标轴上最短的两点
 * 
 * 
 */
public class AxesShotestDistance {

	private static Node[] nodes;
	private static Node node1;
	private static Node node2;

	// 1、不用递归，直接求
	/**
	 * @Description直接求所有距离中最短的那对
	 * @param low
	 * @param height
	 * @return
	 */
	public static double ShotestDistance1(int low, int height) {
		double shotestDistance = Double.MAX_VALUE;
		double tempDistance = 0;
		for (int i = 0; i < nodes.length - 1; i++) {
			tempDistance = distance(nodes[i], nodes[i + 1]);
			if (shotestDistance > tempDistance) {
				shotestDistance = tempDistance;
				node1 = nodes[i];
				node2 = nodes[i + 1];
			}
		}
		return shotestDistance;
	}

	/**
	 * @Description利用分治算法求
	 * @param low
	 * @param height
	 * @return
	 */
	public static double ShotestDistance2(int low, int height) {

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
		int mid = (low + height) / 2;
		/*
		 * 最短距离一定在三者中产生
		 * 左边的最短
		 * 右边的最短
		 * 左边最大和右边最小的距离
		 */
		double shotestDistance = min(ShotestDistance2(low, mid),
				ShotestDistance2(mid + 1, height));
		
		double midDistace=distance(nodes[mid], nodes[mid+1]);
		
		if (midDistace<shotestDistance) {
			shotestDistance=midDistace;
		}
		return shotestDistance;
	}

	public static double min(double a, double b) {
		return a > b ? b : a;
	}

	public static double distance(Node nodeA, Node nodeB) {
		return Math.abs(nodeA.x - nodeB.x);
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
					nodes[i] = new Node(scan.nextDouble());
				}
			} catch (Exception e) {
				throw new Exception("输入的格式不正确");
			}

			Arrays.sort(nodes);
			double result1 = ShotestDistance1(0, nodeNumber-1);
			double result2 = ShotestDistance2(0, nodeNumber-1);
			System.out.println("最短的两个点的坐标为：");
			System.out.println(node1.toString());
			System.out.println(node2.toString());
			System.out.println("最短的距离为" + result1);
			System.out.println(result2);
			System.out.println("请先输入平面点的个数   再输入各个点的坐标，      中间用空格分割即可");

		}
	}
}
