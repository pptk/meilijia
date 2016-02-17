package com.weiwei.meilijia.yi;

import java.math.BigDecimal;
import java.util.Random;

public class RandClass {
	public static String y,s;
	public static String RandomInty(int max1,int min1) {
		int scale = 2;// 表示两位小数
		int roundingMode = 4;// 四舍五入
		Random random1 = new Random();// 随机函数对象

		float xx = random1.nextFloat();// 随机一个浮点数
		BigDecimal bd = new BigDecimal((double) xx);
		bd = bd.setScale(scale, roundingMode);
		xx = bd.floatValue();

		float yz = random1.nextInt(max1) % (max1 - min1 + 1) + min1 + xx;
		y = String.valueOf(yz);

		return y;
	}
	
}
