package com.dcxxjs.core.utils.CommRSA;



import com.dcxxjs.core.utils.RSA.Base64Utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * RSA算法加密/解密工具类
 */
public class CommRSAUtils {

	public static String loadPublicKeyByFile() {
	return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHS1kdrrq/SzSjEKY90NIhTYr4\n" +
			"Ada59o/9VyUKaoGsTj9096htpMPCyL61aSPB0s0u9DAcgp3Hdw3vu2I6lqNpSH74\n" +
			"coDiJwRtwxei+G1m0/B8tFHtRC1JRGZZ9vjptin+88hw/kgfAQCkQ+2Bhj1jz8Ab\n" +
			"wdM9axlh+2mS5thxfQIDAQAB";
	}

	/**
	 * 从字符串中加载公钥
	 *
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 */
	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA",new BouncyCastleProvider());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 公钥加密过程
	 *
	 * @param publicKey     公钥
	 * @param data 明文数据
	 * @return
	 * @throws Exception 加密过程中的异常信息
	 */
	public static String encrypt(RSAPublicKey publicKey,String data) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			// 使用默认RSA
			cipher = Cipher.getInstance("RSA",new BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			//解决加密过程明文超过秘钥长度
			int MAX_DECRYPT_BLOCK = publicKey.getModulus().bitLength() / 8 -11;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] plainTextData=data.getBytes();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			int inputLen = plainTextData.length;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(plainTextData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(plainTextData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return Base64Utils.encode(decryptedData);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
}
