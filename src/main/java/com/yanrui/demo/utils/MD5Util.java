package com.yanrui.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;

public class MD5Util {

	/**
	 * @Description: 对字符串进行md5加密 
	 */
	public static String getMD5Str(String strValue)  {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Base64.encodeBase64String(Objects.requireNonNull(md5).digest(strValue.getBytes()));
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("imooc");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
