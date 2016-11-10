package org.stone.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

import org.stone.entity.PackThing;

/**  
 * @ClassName_Knapsack
 * @author_Stone6762  
 * @CreationTime_2016年11月10日 下午8:54:15
 * @Description_背包问题
 * 
 */
public class Knapsack {

	/** 
	 * @Description在物品可切割的前提下求满背包能装的最大价值
	 * @param things
	 * @param leftWeight
	 * @return
	 */
	private static double maxValue1(PackThing[]things,double leftWeight){
		Arrays.sort(things);
		double maxValue=0;
		for (int i = 0; i < things.length; i++) {
			if (things[i].weight<=leftWeight) {
				maxValue+=things[i].value;
			}else {
				maxValue+=leftWeight*things[i].ValueWRatio;
				break;
			}
		}
		return maxValue;
	}
	
	/** 
	 * @Description在物品不可切割的前提下求满背包能装的最大价值
	 * @param things
	 * @param leftWeight
	 * @return
	 */
	private static double maxValue2(PackThing[]things,double leftWeight){
		Arrays.sort(things);
		double maxValue=0;
		for (int i = 0; i < things.length; i++) {
			if (things[i].weight<=leftWeight) {
				maxValue+=things[i].value;
			}else {
				
				break;
			}
		}
		return maxValue;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请先输入物品的个数n以及背包的容量V，然后分别输入物品的价值v和重量w");
		while (scan.hasNext()) {
			int n=scan.nextInt();
			double maxWeight=scan.nextDouble();
			PackThing[]things=new PackThing[n];
			for (int i = 0; i < n; i++) {
				double v=scan.nextDouble();
				double w=scan.nextDouble();
				things[i]=new PackThing(w, v);
			}
			double maxValue=maxValue1(things, maxWeight);
			System.out.println("该背包最多装价值为  ：  "+maxValue+"  的物品");
		}
	}
}
