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

	public static int[][] LCS(String str1, String str2) {
		int[][] opt = new int[str2.length() + 1][str1.length() + 1];

		for (int i = 0; i <= str2.length(); i++) {
			opt[i][0] = 0;
		}

		for (int j = 0; j <= str1.length(); j++) {
			opt[0][j] = 0;
		}

		for (int j = 1; j <= str1.length(); j++) {
			for (int i = 1; i <= str2.length(); i++) {
				if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
					opt[i][j] = opt[i - 1][j - 1] + 1;
				} else {
					opt[i][j] = (opt[i - 1][j] >= opt[i][j - 1] ? opt[i - 1][j]
							: opt[i][j - 1]);
				}
			}
		}

		return opt;
	}

	public static void printSubsequence(int[][] lcs, String str1, String str2, int i, int j) {

		if (i == 0 || j == 0) {
			return;
		}

		if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
			System.out.print(str1.charAt(i - 1));
			printSubsequence(lcs, str1, str2, i - 1, j - 1); 
		} else if (lcs[i - 1][j] >= lcs[i][j]) {
			printSubsequence(lcs, str1, str2, i - 1, j);
		} else {
			printSubsequence(lcs, str1, str2, i, j - 1);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入要判断的字符串1和字符串2,每个单独一行");
		while (scan.hasNext()) {
			String str1 = scan.next();
			String str2 = scan.next();
			int result = lCS(str1, str2);
			System.out.println("两个字符串最长公共子序列的长度为：     " + result);
			
			System.out.println("两个字符串的最长公共子序列为：");
			printSubsequence(Lcs, str1, str2, str1.length(), str2.length());

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
