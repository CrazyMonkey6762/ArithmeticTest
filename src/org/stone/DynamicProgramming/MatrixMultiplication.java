package org.stone.DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

import org.stone.utils.MyConsoleClear;

/**
 * @ClassName_MatrixMultiplication
 * @author_Stone6762
 * @CreationTime_2016年10月28日 下午5:00:18
 * @Description_矩阵连乘问题 动态规划的特点就是：最优的结果一定包含最优的子结构 那么对于一个 A1A2A3...An的矩阵连乘问题
 * 
 *                     其中Ai...Aj矩阵间的连乘最小值为：
 *                     d[i,j]={d[i,k]+d[k+1,j]+m[i-1]m[k]m[j]}
 *                     其中，k为最优的分割点，m[i-1]表示第i个矩阵的行数，m[k]表示第k个矩阵的列，m[j]表示第j个矩阵的列数
 *                     从最底层向上开始递归，对于计算后的结果要进行保存，下次需要计算的时候直接查询就好，类似于记忆搜索
 */
public class MatrixMultiplication {

	/**
	 * @m存储的是矩阵的行列信息m[i-1]表示第i个矩阵的行数 m[i]表示第i个矩阵的列数
	 */
	private static int[] m;

	/**
	 * @d存储的是局部最优解
	 */
	private static int[][] d;

	/**
	 * @Description_枚举法遍历全部的划分可能性
	 * @param left
	 * @param right
	 * @return
	 */
	public static int best_Enum(int left, int right) {
		if (left == right) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (int i = left; i < right; i++) {
			int count = best_Enum(left, i) + best_Enum(i + 1, right);
			count += m[left - 1] * m[i] * m[right];
			if (min > count) {
				min = count;
			}
		}
		return min;
	}

	/**
	 * @Description记忆法_将已经计算出的结果进行保存_减少计算的次数
	 * @param left
	 * @param right
	 * @return
	 */
	public static int best_Memo(int left, int right) {
		if (left == right) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (int i = left; i < right; i++) {
			if (d[left][i] == 0) {
				d[left][i] = best_Enum(left, i);
			}
			if (d[i + 1][right] == 0) {
				d[i + 1][right] = best_Enum(i + 1, right);
			}
			int count = d[left][i] + d[i + 1][right];
			count += m[left - 1] * m[i] * m[right];
			if (min > count) {
				min = count;
			}
		}
		return min;
	}

	/**
	 * @Description动态规划
	 * @param n
	 * @return
	 * 
	 * 
	 */
	public static int best_DP(int n) {
		
		//其中len表示区间长度
		for (int len = 1; len <=n; len++) {//求i到n之间的
			
			//求区间i到j之间的最优解
			for (int i = 1, j = i + len; j <=n; i++, j++) {
				int min = Integer.MAX_VALUE;
				//求区间i和j之间任意一点加括号所求的最小值
				for (int k = i; k < j; k++) {//k表示断开加括号的地方
					int count = d[i][k] + d[k + 1][j] + m[i - 1] * m[k] * m[j];
					if (count < min) {
						min = count;
					}
				}
				d[i][j] = min;
			}
		}
		return d[1][n];
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请先输入矩阵的个数n，以及对应的n+1表示行列的值");
		while (scan.hasNext()) {
			int n = scan.nextInt();
			m = new int[n + 1];
			d = new int[n+1][n+1];
			for (int i = 0; i <= n; i++) {
				m[i] = scan.nextInt();
			}
			int count1 = best_Enum(1, n );
			int count2 = best_Memo(1, n );
			int count3 = best_DP(n);
			System.out.println("枚举法求的次数：   " + count1);
			System.out.println("记忆法求的次数: " + count2);
			System.out.println("DP求的次数:  " + count3);
			for (int i = 0; i < d.length; i++) {
				System.out.println(Arrays.toString(d[i]));
			}
			System.out.println("是否继续？(Y/N)");
			String string = scan.next();
			if (string.equals("Y") || string.equals("y")) {
				MyConsoleClear.clear();
				System.out.println("请先输入矩阵的个数n，以及对应的n+1表示行列的值");
			} else {
				break;
			}
		}
	}
}
