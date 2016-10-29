package org.stone.DynamicProgramming;

import java.util.Scanner;

import org.stone.utils.MyConsoleClear;

/**
 * @ClassName_LongestIncreasingSubsequence
 * @author_Stone6762
 * @CreationTime_2016年10月29日 下午2:34:59
 * @Description_最长公共子序列
 */
public class LongestCommonSubsequence {

	private static int[][] Lcs;

	public static int lCS(String str1, String str2) {
		int lengthStr1 = str1.length();
		int lengthStr2 = str2.length();
		Lcs = new int[lengthStr1][lengthStr2];
		int max = 0;
		for (int i = 0; i < lengthStr1; i++) {
			for (int j = 0; j < lengthStr2; j++) {

				// 计算初始值：lcs[0][x]和lcs[x][0]的值
				if (i == 0 || j == 0) {
					if (str1.charAt(i) == str2.charAt(j)) {
						Lcs[i][j] = 1;
					} else {
						if (i == 0 && j == 0) {
							Lcs[i][j] = 0;
						} else if (i == 0 && j != 0) {
							Lcs[i][j] = Lcs[i][j - 1];
						} else {
							Lcs[i][j] = Lcs[i - 1][j];
						}
					}
					// 其他lcs[i][j]的值
				} else {
					if (str1.charAt(i) == str2.charAt(j)) {
						Lcs[i][j] = Lcs[i - 1][j - 1] + 1;
					} else {
						Lcs[i][j] = Lcs[i - 1][j] > Lcs[i][j - 1] ? Lcs[i - 1][j]
								: Lcs[i][j - 1];
					}
				}
				if (Lcs[i][j] > max) {
					max = Lcs[i][j];
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入要判断的字符串1和字符串2,每个单独一行");
		while (scan.hasNext()) {
			String str1 = scan.next();
			String str2 = scan.next();
			int result = lCS(str1, str2);
			System.out.println("两个字符串最长公共子序列的长度为：     " + result);

			System.out.println("是否继续？(Y/N)");
			String string = scan.next();
			if (string.equals("Y") || string.equals("y")) {
				MyConsoleClear.clear();
				System.out.println("请输入要判断的字符串1和字符串2,每个单独一行");
			} else {
				break;
			}
		}
	}
}
