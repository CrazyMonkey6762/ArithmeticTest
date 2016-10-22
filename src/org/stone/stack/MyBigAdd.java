package org.stone.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName_BigAdd大数相加
 * @author_Stone6762
 * @CreationTime_2014年12月28日 下午10:29:35
 * @Description_算法描述
 * */
/*
 * 算法描述 （1）将两个加数的相对应位从高位到低位依次压入栈，SA和SB
 * （2）若两个栈都非空，则依次从栈中弹出栈顶数字相加，将结果的个位数添加到结果栈Sum中，结果的十位数与下次的和相加
 * （3）若某个加数栈为空，则将非空栈的栈顶元素弹出与进位数相加，结果的个位数放到Sum中，若最高位有进位， 则最后将1压入栈
 * （4）若两个栈均为空，则结果栈中保存的就是最后的结果，注意栈顶是结果中的最高位
 */
public class MyBigAdd {

	private static Scanner scan;

	/**
	 * @Describe_将表示数字的字符串从高位到低位的形式压入栈_并去除其中的空格
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private static Stack<String> numSplit(String str) throws Exception {

		Stack<String> s = new Stack<String>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (' ' == c) {
				continue;
			}
			if (c >= '0' && c <= '9') {
				s.push(c + "");
			} else {
				throw new Exception("错误： 输入了非数字类型的字符！  ");
			}
		}
		return s;
	}

	/**
	 * @Describe_求两个大数的和_加数和被加数以字符串的形式给出_计算的结果也以字符串的形式返回
	 * @param a
	 * @param b
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static String add(String a, String b) {
		Stack<String> sum = new Stack<String>();
		try {
			Stack<String> sA = numSplit(a);
			Stack<String> sB = numSplit(b);

			int tSum;// 表示两位的和
			boolean isCarry = false;// 是否有进位
			// 当两个栈都非空时，依次出栈，然后相加
			while (!sA.isEmpty() && !sB.isEmpty()) {
				tSum = Integer.valueOf((String) sA.pop())
						+ Integer.valueOf((String) sB.pop());
				if (isCarry) {
					tSum++;
					isCarry = false;// 将低位的进位状态消除掉，防止影响下次的使用
				}
				if (tSum >= 10) {
					tSum -= 10;
					isCarry = true;
				}
				sum.push("" + tSum);
			}
			Stack<String> temp = !sA.isEmpty() ? sA : sB;

			// 当其中一个为空时，对另一个非空的进行操作
			while (!temp.isEmpty()) {
				int t = Integer.valueOf((String) temp.pop());
				if (isCarry) {// 第一个isCarry为上面的残留
					t++;
					if (t >= 10) {
						t -= 10;
					} else {
						isCarry = false;// 只有当上一次是true，且这一次没有进位时，才需要对isCarry进行修改
					}
				}
				sum.push("" + t);
			}
			// 对最后一次相加的十位的数字进行判断
			if (isCarry) {
				sum.push("" + 1);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return stack2Str(sum);
		}

	}

	/**
	 * @Describe_将一个栈中的数据从栈顶到栈底变成一个字符串
	 * @param sum
	 * @return
	 */
	private static String stack2Str(Stack<String> s) {
		String str = "";
		while (!s.isEmpty()) {
			str += s.pop().toString();
		}
		return str;
	}

}
