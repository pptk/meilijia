package com.weiwei.meilijia.yi;

import java.math.BigDecimal;
import java.util.Random;

public class RandClass {
	public static String y,s;
	public static String RandomInty(int max1,int min1) {
		int scale = 2;// ��ʾ��λС��
		int roundingMode = 4;// ��������
		Random random1 = new Random();// �����������

		float xx = random1.nextFloat();// ���һ��������
		BigDecimal bd = new BigDecimal((double) xx);
		bd = bd.setScale(scale, roundingMode);
		xx = bd.floatValue();

		float yz = random1.nextInt(max1) % (max1 - min1 + 1) + min1 + xx;
		y = String.valueOf(yz);

		return y;
	}
	
}
