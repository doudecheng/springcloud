package com.xqt.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 数据安全公共类
 * 
 * <pre>
 * 报文签名、验签、报文加密、解密等
 * </pre>
 * 
 * @author 梁华
 * 
 */
public class DataSecurityUtil {

	private final static Logger log = Logger.getLogger(DataSecurityUtil.class);
	
	private static byte[] iv = {1,2,3,4,5,6,7,8};

	/**
	 * 参数签名
	 * 
	 * @param params
	 * @param signature
	 * @return
	 */
	public static String signature(List<String> params) {
		params.add(SecurityConfig.secKey);
		// 1. 将参数进行字典序排序
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		// 2. 将参数字符串拼接成一个字符串进行sha1加密，并进行调制
		StringBuilder builder = new StringBuilder();
		for (String str : params) {
			builder.append(str);
		}
		String temp = SHA1.encode(builder.toString());
		return temp;
	}

	/**
	 * 参数验签
	 * 
	 * @param params
	 * @param signature
	 * @return
	 */
	public static boolean checkSignature(List<String> params, String signature) {

		// 签名
		String temp = DataSecurityUtil.signature(params);
		if (temp.equals(signature)) {
			return true;
		}
		return false;
	}

	/**
	 * 报文加密
	 * 
	 * @param oriByte
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String oriStr, String keyStr) {

		Cipher cipher = null;
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKey key = getKey(keyStr);
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] sealTxt = cipher.doFinal(oriStr.getBytes("utf-8"));
			BASE64Encoder encoder = new BASE64Encoder();
			String ret = encoder.encode(sealTxt);
			return ret;
		} catch (Exception ee) {
			ee.printStackTrace();
			log.error("报文加密失败");
		} finally {
			cipher = null;
		}
		return null;
	}

	/**
	 * 报文解密
	 * 
	 * @param sealTxt
	 * @param keyStr
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String sealTxt, String keyStr) {

		if (StringUtil.isEmpty(sealTxt)) {
			return null;
		}

		Cipher cipher = null;
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKey key = getKey(keyStr);
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] sealByte = decoder.decodeBuffer(sealTxt);
			byte[] byteFina = cipher.doFinal(sealByte);
			return new String(byteFina, "utf-8");
		} catch (Exception ee) {
			ee.printStackTrace();
			log.error("报文解密失败");
		} finally {
			cipher = null;
		}
		return null;
	}

	private static SecretKey getKey(String key) throws Exception {
		// 实例化DESede密钥
		DESKeySpec dks = new DESKeySpec(key.getBytes("utf-8"));
		// 实例化密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 生成密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		return secretKey;
	}
	
}
