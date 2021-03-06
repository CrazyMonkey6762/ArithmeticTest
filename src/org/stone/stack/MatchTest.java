package org.stone.stack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Stack;

import org.stone.utils.MyConsoleClear;

/**
 * @Title_MatchTest.java 
 * @author_Stone6762  
 * @CreationTime_2016年10月21日 下午7:11:30
 * @Description利用栈实现分隔符匹配问题
 * ()[]{}/*
 * 
 * 此时的代码仅能处理单行的字符串，不能处理多行的匹配问题
 * 可以在字符串输入那里加以判断，
 * 当单行为  "##"时，判断为输入结束
 */
public class MatchTest {

	/**
	 * @LEFT记录分隔符为“左”分隔符
	 */
	private final int LEFT = 0;

	/**
	 * @RIGHT记录分隔符为“右”分隔符
	 */
	private final int RIGHT = 1;

	/**
	 * @OTHER记录其他字符
	 */
	private final int OTHER = 2;

	/**
	 * @Describe_判断分隔符的类型_左_右_非法
	 * @param str
	 * @return
	 */
	private int verifyFlag(String str) {
		if ("(".equals(str) || "[".equals(str) || "{".equals(str)
				|| "/*".equals(str)) {
			return LEFT;
		} else if (")".equals(str) || "]".equals(str) || "}".equals(str)
				|| "*/".equals(str)) {
			return RIGHT;
		} else {
			return OTHER;

		}

	}

	/**
	 * @Describe_判断左分隔符str1和右分隔符str2是否匹配
	 * @param str1
	 * @param str2
	 * @return
	 */
	private boolean matches(String str1, String str2) {
		if (("(".equals(str1) && ")".equals(str2))
				|| ("{".equals(str1) && "}".equals(str2))
				|| ("[".equals(str1) && "]".equals(str2))
				|| ("/*".equals(str1) && "*/".equals(str2))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Describe_判断是否匹配
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public boolean isLegal(String str) throws Exception {
		if (!"".equals(str) && str != null) {
			Stack<String> S = new Stack<String>();
			int length = str.length();
			for (int i = 0; i < length; i++) {
				// 取出元素
				char c = str.charAt(i);
				String t = String.valueOf(c);

				// 对 分隔符/**/特别处理
				if (i != length) {
					if (('/' == c && '*' == str.charAt(i + 1))
							|| ('*' == c && '/' == str.charAt(i + 1))) {
						t = t.concat(String.valueOf(str.charAt(i + 1)));
						i++;
					}
				}
				// 如果是左分隔符，入栈，如果是右分隔符，出栈，看是否匹配，如果不匹配，报错
				if (LEFT == verifyFlag(t)) {
					S.push(t);
				} else if (RIGHT == verifyFlag(t)) {
					if (S.isEmpty()) {
						throw new Exception("错误：   java语法不合法,缺少左分隔符");
					} else if (!matches(S.pop().toString(), t)) {
						throw new Exception("错误：   java语法不合法,左右分隔符不匹配");
					}
				}
			}
			// 对整个语句遍历后，如果栈非空，证明栈中还有未被匹配的左分隔符，此时是错误的
			if (!S.isEmpty()) {
				throw new Exception("错误： java语句不合法,缺少右分隔符");
			} else {
				return true;
			}
		} else {
			throw new Exception("错误：Java语句为空 ！ ");
		}
	}

	public static void main(String[] args) throws Exception {
		MatchTest m = new MatchTest();
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入想要判断的语句      单行    ##表示输入结束");
		while (scan.hasNext()) {
			String aimString="";
			while (scan.hasNext()) {
				String temString=scan.nextLine();
				if (temString.equals("##")) {
					break;
				}
				aimString+=temString;
			}
			MyConsoleClear.clear();
			if (m.isLegal(aimString)) {
				
				System.out.println("语句正确");
				
			} else {
				System.out.println("错误：语句不合法");
			}
			System.out.println("请输入想要判断的语句");
			
			System.out.println("是否继续？(Y/N)");
			String string = scan.next();
			if (string.equals("Y") || string.equals("y")) {
				MyConsoleClear.clear();
				System.out.println("请输入想要判断的语句      单行    ##表示输入结束");
			} else {
				break;
			}
			
			
			
		}
		scan.close();
	}
	
	
	
	
}
