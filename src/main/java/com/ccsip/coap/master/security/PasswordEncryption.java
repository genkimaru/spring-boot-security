package com.ccsip.coap.master.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PasswordEncryption {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";

	public static final int KEY_SIZE = 1024;
	public static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMz00rZn/IQs8h0SBLSoqXjL6G+VUFBeeYHZxLHbKmhShbgCUm7hwMooQ2CuQDGZTmt5+8okJGSEIOZG1VXmEPOKJohLspRrJ51kyRB4BpdNwRWr4SoYtD3wKluPbr5vMQ2F+PJA5WRDfOamcUTwGklRBQwDbCZNx350ei2S1gIQIDAQAB";
	public static String privateKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMz00rZn/IQs8h0SBLSoqXjL6G+VUFBeeYHZxLHbKmhShbgCUm7hwMooQ2CuQDGZTmt5+8okJGSEIOZG1VXmEPOKJohLspRrJ51kyRB4BpdNwRWr4SoYtD3wKluPbr5vMQ2F+PJA5WRDfOamcUTwGklRBQwDbCZNx350ei2S1gIQIDAQAB";

	public static String getMD5(String clearText) {

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			return base64en.encode(md5.digest(clearText.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static KeyPair genKeyPair(int keyLength) throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		return keyPairGenerator.generateKeyPair();
	}

	public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");// java默认"RSA"="RSA/ECB/PKCS1Padding"
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}

	public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}

	public static String decrypt(String content, String privateKey) throws Exception {
		return new String(decrypt(content.getBytes(), getPrivateKey(privateKey)));
	}

	public static PublicKey getPublicKey(String publicKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}

	public static PrivateKey getPrivateKey(String privateKey) throws Exception {
		byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	public static String[] getRSAKeys() throws Exception {
		try {
			KeyPair keyPair = genKeyPair(1024);
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			return new String[] { new String(Base64.getEncoder().encode(publicKey.getEncoded())),
					new String(Base64.getEncoder().encode(privateKey.getEncoded())) };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String data = "hello world";

	public static void main(String[] args) throws Exception {
		// // TODO Auto-generated method stub
		//
		// KeyPair keyPair = genKeyPair(1024);
		//
		// // 获取公钥，并以base64格式打印出来
		// PublicKey publicKey = keyPair.getPublic();
		// System.out.println("公钥：" + new
		// String(Base64.getEncoder().encode(publicKey.getEncoded())));
		//
		// // 获取私钥，并以base64格式打印出来
		// PrivateKey privateKey = keyPair.getPrivate();
		// System.out.println("私钥：" + new
		// String(Base64.getEncoder().encode(privateKey.getEncoded())));
		//
		// // 公钥加密
		// byte[] encryptedBytes = encrypt(data.getBytes("UTF-8"), publicKey);
		// System.out.println("加密后：" + new String(encryptedBytes, "UTF-8"));
		//
		// // 私钥解密
		// byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
		// System.out.println("解密后：" + new String(decryptedBytes));

		// TODO Auto-generated method stub

		// 获取公钥
		PublicKey publicKey = getPublicKey(publicKeyString);

		// 获取私钥
		PrivateKey privateKey = getPrivateKey(privateKeyString);

		// 公钥加密
		byte[] encryptedBytes = encrypt(data.getBytes(), publicKey);
		System.out.println("加密后：" + new String(encryptedBytes));

		// 私钥解密
		byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
		System.out.println("解密后：" + new String(decryptedBytes));
	}

}
