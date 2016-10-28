package org.stone.DivideAndConquer;

import java.math.BigInteger;
import java.util.Scanner;

import org.stone.stack.MyBigAdd;
import org.stone.utils.MyConsoleClear;

/**
 * @ClassName_MyBigIntegerMutiply
 * @author_Stone6762
 * @CreationTime_2016年10月22日 下午3:39:57
 * @Description_ 大整数乘法A x B AB可以为不同的位数 （但是相差不能太大，不能超过long的范围） 采用的是： 分治的思想，二分
 *               a1=前半段 a0=后半段 b1=前半段 b0=后半段 a = a1*10^(n1/2)+a0　　　　-----n1为a的位数
 *               b =b1*10^(n2/2)+b0　　　　　-----n2为b的位数
 */
public class MyBigIntegerMutiply1 {

	/**
	 * @Description:未优化的大数相乘
	 * @param a
	 * @param b
	 * @return a*b={a1*10^(n1/2)+a0}*{b1*10^(n2/2)+b0} 展开后整理得： a*b=
	 *         (a1*b1)*10^[(n1+n2)/2] +(a1*b0)*10^(n1/2) +(a0*b1)*10^(n2/2)
	 *         +(a0*b0) 四次“大数”乘法，三次移位，四次“大数”加法
	 */
	public static String Mutiply1(String a, String b)// 用字符串读入2个大整数
	{
		String result = "";
		if (a.length() == 1 || b.length() == 1)// 递归结束的条件
			// 其中一个长度为1，另一个不一定
			result = "" + Long.valueOf(a) * Long.valueOf(b);
		else// 如果2个字符串的长度都 >= 2
		{
			// 1.分割成 a1 a0 b1 b0
			int lengthA0 = a.length() / 2;
			int lengthA1 = a.length() - lengthA0;
			String a1 = a.substring(0, lengthA1); // 截取前一半的字符串(较短的一半)
			String a0 = a.substring(lengthA1, a.length()); // 截取后一半的字符串

			int lengthB0 = b.length() / 2;
			int lengthB1 = b.length() - lengthB0;
			String b1 = b.substring(0, lengthB1);
			String b0 = b.substring(lengthB1, b.length());
			// * a*b=
			// * (a1*b1)* 10^[(n1+n2)/2 ]
			// * +(a1*b0)*10^(n1/2)
			// * +(a0*b1)*10^(n2/2)
			// * +(a0*b0)
			// 2.计算展开式中的乘法
			String a1b1 = Mutiply1(a1, b1);
			String a1b0 = Mutiply1(a1, b0);
			String a0b1 = Mutiply1(a0, b1);
			String a0b0 = Mutiply1(a0, b0);

			// 3.模拟移位
			String resulta1b1 = a1b1;
			for (int i = 0; i < lengthA0 + lengthB0; i++) {
				resulta1b1 += "0";
			}
			String resulta1b0 = a1b0;
			for (int i = 0; i < lengthA0; i++) {
				resulta1b0 += "0";
			}
			String resulta0b1 = a0b1;
			for (int i = 0; i < lengthB0; i++) {
				resulta0b1 += "0";
			}
			// 4.大数相加
			result = MyBigAdd.add(resulta1b1, resulta1b0);
			result = MyBigAdd.add(result, resulta0b1);
			result = MyBigAdd.add(result, a0b0);
		}
		return result;
	}

	/**
	 * @Description:优化大数相乘
	 * @param a
	 * @param b
	 * @return a*b={ a1*10^(n1/2)+a0 } * { b1*10^(n2/2)+b0 } 对展开式进行优化
	 */
	public static String Mutiply2(String a, String b)// 用字符串读入2个大整数
	{
		String result = "";
		if (a.length() == 1 || b.length() == 1)// 递归结束的条件
			result = "" + Long.valueOf(a) * Long.valueOf(b);
		else// 如果2个字符串的长度都 >= 2
		{
			// 1.分割成 a1 a0 b1 b0
			int lengthA0 = a.length() / 2;
			int lengthA1 = a.length() - lengthA0;
			String a1 = a.substring(0, lengthA1); // 截取前一半的字符串(较短的一半)
			String a0 = a.substring(lengthA1, a.length()); // 截取后一半的字符串

			int lengthB0 = b.length() / 2;
			int lengthB1 = b.length() - lengthB0;
			String b1 = b.substring(0, lengthB1);
			String b0 = b.substring(lengthB1, b.length());
			// * a*b=
			//
			//
			// 2.计算展开式中的乘法

			// 3.模拟移位

			// 4.大数相加

		}
		return result;
	}

	/**
	 * @Description拿BigInteger自身大数相乘来判断自身算法的正确与否
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入十进制的大数a和b");
		while (scan.hasNext()) {

			String aString = scan.next();
			String bString = scan.next();

			BigInteger aBigInteger = new BigInteger(aString);
			BigInteger bBigInteger = new BigInteger(bString);

			String reslut1 = aBigInteger.multiply(bBigInteger).toString();
			String result2 = Mutiply1(aString, bString);

			System.out.println("标准答案：  " + reslut1);
			System.out.println("计算结果：  " + result2);

			System.out.println("结果是否正确：  " + reslut1.equals(result2));

			System.out.println("是否继续？(Y/N)");
			String string = scan.next();
			if (string.equals("Y") || string.equals("y")) {
				MyConsoleClear.clear();
				System.out.println("请输入十进制的大数a和b");
			} else {
				break;
			}
		}
	}
}
