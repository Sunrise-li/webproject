package com.taotao.common.utils;

import java.util.Random;

/**
 * 各种id生成策略
 * @author Sunrise
 *
 */
public class IDUtils {

	/**
	 * 图片名生成
	 */
	public static String genImageName() {
		return genNameOrId(999);
	}
	/**
	 * 商品ID生成
	 */
	public static long genItemId() {
		//取当前时间
		return	new Long(genNameOrId(99));
	}

	private static String genNameOrId(int length) {
		//取得当前时间
		long millis = System.currentTimeMillis();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = "" + length;
		str =  millis + String.format("%0"+str.length()+"d", end3);
		return str;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i< 1000; i++) {
			System.out.println(genNameOrId(999));
		}
	}
}
