package org.stone.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @ClassName_MyConsoleClear
 * @author_Stone6762
 * @CreationTime_2016年10月28日 下午4:31:03
 * @Description_
 */
public class MyConsoleClear {

	/**
	 * @Description: 实现清屏的效果
	 * @throws AWTException
	 */
	public static void clear() {
		try {
			Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON3_MASK); // 按下鼠标右键
			r.mouseRelease(InputEvent.BUTTON3_MASK); // 释放鼠标右键
			r.keyPress(KeyEvent.VK_CONTROL); // 按下Ctrl键
			r.keyPress(KeyEvent.VK_R); // 按下R键
			r.keyRelease(KeyEvent.VK_R); // 释放R键
			r.keyRelease(KeyEvent.VK_CONTROL); // 释放Ctrl键
			r.delay(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
