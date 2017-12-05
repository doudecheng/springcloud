package com.xqt.util;
import java.util.Random;

public class RandomUtils {

		/**
		 * 产生0～max的随机整数 包括0 不包括max
		 * 
		 * @param max
		 *            随机数的上限
		 * @return
		 */
		public static int getRandom(int max) {
			return new Random().nextInt(max);
		}

		/**
		 * 产生 min～max的随机整数 包括 min 但不包括 max
		 * 
		 * @param min
		 * @param max
		 * @return
		 */
		public static int getRandom(int min, int max) {
			int r = getRandom(max - min);
			return r + min;
		}

		/**
		 * 产生0～max的随机整数 包括0 不包括max
		 * 
		 * @param max
		 *            随机数的上限
		 * @return
		 */
		public static long getRandomLong(long max) {
			long randNum = (long) (Math.random() * max);// + minId;
			return randNum;
		}

		/**
		 * 产生 min～max的随机整数 包括 min 但不包括 max
		 * 
		 * @param min
		 * @param max
		 * @return
		 */
		public static long getRandomLong(long min, long max) {
			long r = getRandomLong(max - min);
			return r + min;
		}

		/**
		 * 产生size位的随机整数
		 * 
		 * @param size
		 *            字符串的长度
		 * @return
		 */
		public static String getRandomString(int size) {

			StringBuilder build = new StringBuilder();
			for (int i = 0; i < size; i++) {
				build.append(new Random().nextInt(10));
			}
			return build.toString();
		}
	}

