/**  
 * @Project: jxoa
 * @Title: RSAUtils.java
 * @Package com.oa.commons.util
 * @date 2013-7-31 上午9:07:34
 * @Copyright: 2013 
 */
package com.oa.commons.util;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import com.oa.commons.model.RSAPublicKeyModel;


/**
 * 
 * 类名：RSAUtils
 * 功能：RSA 加密解密
 * 详细：与js加密配合，需导入bouncycastle.jar,
 * 		加密中文时，js先用encodeURIComponent()进行中文转码，然后再加密，
 * 		java解密之后再用URLDecoder.decode(text, "utf-8")进行 解码
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-7-31 上午9:07:34
 *
 */
public class RSAUtils {
	
	/** 算法名称 */
	private static final String ALGORITHOM = "RSA";
	/** 密钥大小 */
	private static final int KEY_SIZE = 1024;
	/** 默认的安全服务提供者 */
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

	private static KeyPairGenerator keyPairGen = null;
	private static KeyFactory keyFactory = null;
	
	static {
		try {
			keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM,DEFAULT_PROVIDER);
			keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取公钥
	 * @param password 密码种子
	 * @return
	 */
	private static RSAPublicKey getPublicKey(String password) {
		KeyPair keyPair = generateKeyPair(password);
		if (keyPair != null) {
			return (RSAPublicKey) keyPair.getPublic();
		}
		return null;
	}
	/**
	 * 获取公钥 Model
	 * @param password 密码种子
	 * @return
	 */
	public static RSAPublicKeyModel getPublicKeyModel(String password) {
		KeyPair keyPair = generateKeyPair(password);
		if (keyPair != null) {
			RSAPublicKey publicKey= (RSAPublicKey)keyPair.getPublic();
			RSAPublicKeyModel model=new RSAPublicKeyModel();
			model.setHexModulus(new String(Hex.encode(publicKey.getModulus().toByteArray())));
			model.setHexPublicExponent(new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
			return model;
		}
		return null;
	}
	/**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
     * 
     * @param encrypttext 密文。
     * @return {@code encrypttext} 的原文字符串。
     */
    public static String decryptStringByJs(String password,String encrypttext) {
        
        return StringUtils.reverse(decryptString(password,encrypttext));
    }
    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串。
     * 
     * @param encrypttext 密文。
     * @return {@code encrypttext} 的原文字符串。
     */
    public static String decryptString(String password,String encrypttext) {
        String text = decrypt(password,encrypttext);
        if(text == null) {
            return null;
        }
        return text;
    }

	/**
	 * 生成并返回RSA密钥对
	 * @param password 密码种子
	 * @return
	 */
	private static synchronized KeyPair generateKeyPair(String password) {
		try {
			keyPairGen.initialize(KEY_SIZE,new SecureRandom(password.getBytes()));
			return  keyPairGen.generateKeyPair();
			
		} catch (InvalidParameterException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	 /**
     * 使用指定的私钥解密数据。
     * 
     * @param privateKey 给定的私钥。
     * @param data 要解密的数据。
     * @return 原数据。
     */
    private static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }
	
	/**
     * 使用默认的私钥解密给定的字符串。
     * <p />
     * 若{@code encrypttext} 为 {@code null}或空字符串则返回 {@code null}。
     * 私钥不匹配时，返回 {@code null}。
     * @param password 密码种子
     * @param encrypttext 密文。
     * @return 原文字符串。
     */
    private static String decrypt(String password,String encrypttext) {
        if(StringUtils.isBlank(encrypttext)) {
            return null;
        }
        KeyPair keyPair = generateKeyPair(password);
        try {
            byte[] en_data = Hex.decode(encrypttext);
            byte[] data = decrypt(keyPair.getPrivate(), en_data);
            return new String(data);
        } catch(NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
    /**
     * 使用默认的公钥加密给定的字符串。
     * <p />
     * 若{@code plaintext} 为 {@code null} 则返回 {@code null}。
     * 
     * @param plaintext 字符串。
     * @return 给定字符串的密文。
     */
    public static String encryptString(String hexModulus, String hexPublicExponent,String plaintext) {
        if(plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        RSAPublicKey publicKey = getPublidKey(hexModulus,hexPublicExponent);
        try {
            byte[] en_data = encrypt(publicKey, data);
            return new String(Hex.encode(en_data));
        } catch(NullPointerException ex) {
           
        } catch(Exception ex) {
           
        }
        return null;
    }
    /**
     * 使用指定的公钥加密数据。
     * 
     * @param publicKey 给定的公钥。
     * @param data 要加密的数据。
     * @return 加密后的数据。
     */
    private static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }

    /**
     * 根据给定的16进制系数和专用指数字符串构造一个RSA专用的公钥对象。
     * 
     * @param modulus 系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    private static RSAPublicKey getPublidKey(String hexModulus, String hexPublicExponent) {
        if(StringUtils.isBlank(hexModulus) || StringUtils.isBlank(hexPublicExponent)) {
            return null;
        }
        byte[] modulus = null;
        byte[] publicExponent = null;
      
            modulus = Hex.decode(hexModulus);
            publicExponent = Hex.decode(hexPublicExponent);
       
        if(modulus != null && publicExponent != null) {
            return generatePublicKey(modulus, publicExponent);
        }
        return null;
    }
    /**
     * 根据给定的系数和专用指数构造一个RSA专用的公钥对象。
     * 
     * @param modulus 系数。
     * @param publicExponent 专用指数。
     * @return RSA专用公钥对象。
     */
    private static RSAPublicKey generatePublicKey(byte[] modulus, byte[] publicExponent) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus),new BigInteger(publicExponent));
     
        try {
			return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        
      
    }
    
	public static void main(String[] args) {
		
	
		//传入密码种子，获取公钥，分发公钥（e1,n）
		RSAPublicKey publicKey = RSAUtils.getPublicKey("password");

		//公钥-系数(n)
		String hexModulus=new String(Hex.encode(publicKey.getModulus().toByteArray()));
		System.out.println("public key modulus:"+ hexModulus);

		//公钥-指数(e1)
		String hexPublicExponent=new String(Hex.encode(publicKey.getPublicExponent().toByteArray()));
		System.out.println("public key exponent:"+ hexPublicExponent);
		//需要加密的字符串
		String plaintext="123";
		
		//JS加密后的字符串
		//String pppp = "6e269bdf7f7670ffaff669d86d93e63fd91f2d6dd4f7e57ca58c614de65c0828e608bdd0f3e81d3630f8533f5286e0f55caa005ebe6d3faf3b9cc130d9ecff51d1f77eeed48156431cbf5de62cf6899084a4e92890827edbebbf506ecbb022de4a0e3131f2ffefe3368c7b7f929362edb4af419b837456c275f643a5c760a4ff";
		
		String jmmw=RSAUtils.encryptString(hexModulus, hexPublicExponent, plaintext);
		
		System.out.println("加密密文：" + jmmw);
		
		//解密后的字符串
		String kkkk = RSAUtils.decryptString("password",jmmw);

		System.out.println("解密后文字：" + kkkk);
		
		
		
		
	}
	
	
	
}
