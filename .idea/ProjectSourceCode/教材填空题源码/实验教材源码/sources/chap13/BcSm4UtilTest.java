//BcSm4UtilTest.java
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.*;
import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;
import java.util.*;     

class BcSm4Util {   
    public static final String ALGORITHM_NAME = "SM4";
    public static final String DEFAULT_KEY = "random_seed";
    // 128-32位16进制；256-64位16进制
    public static final int DEFAULT_KEY_SIZE = 128;
    static {//加载BouncyCastleProvider（简称BC）驱动
			if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
				Security.addProvider(new BouncyCastleProvider());
    }
 	//生成默认密钥
 public static byte[] generateKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKey(DEFAULT_KEY, DEFAULT_KEY_SIZE);
}
    //用种子seed生成密钥
    public static byte[] generateKey(String seed) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKey(seed, DEFAULT_KEY_SIZE);
    }
    //用种子seed生成指定长度keySize的密钥
    public static byte[] generateKey(String seed, int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
		//返回有BC生成的指定算法的密钥对象
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//SecureRandom生成伪随机数对象
        if (null != seed && !"".equals(seed)) {
            random.setSeed(seed.getBytes());//设置密钥种子
        }
		
        kg.init(keySize, random);//用密钥种子随机数初始化生成keySize位密钥
        return kg.generateKey().getEncoded();//返回密钥
    }

    /*
	 * Sm4加密方法，调用Sm4core的加解密算法
	 * @param algorithmName 使用的算法/模式/dPadding模式，如SM4/ECB/NoPadding
	 * @param key 密钥
     * @param iv 初始向量(ECB模式下载NULL，CBC模式时作为偏移量)
     * @param data 明文数据
	 * @return 返回加密后的密文数据  
     * @throws Exception
	*/
    public static byte[] encrypt(String algorithmName, byte[] key, byte[] iv, byte[] data) throws Exception {
        return sm4core(algorithmName,Cipher.ENCRYPT_MODE, key, iv, data);
    }
	/*
	 * Sm4解密方法，调用Sm4core的加解密方法
	 * @param algorithmName 使用的算法/模式/dPadding模式，如SM4/ECB/NoPadding
	 * @param key 密钥
     * @param iv 初始向量(ECB模式下载NULL，CBC模式时作为偏移量)
     * @param data 密文数据
	 * @return 返回解密后的明文数据  
     * @throws Exception
	*/ 
    public static byte[] decrypt(String algorithmName, byte[] key, byte[] iv, byte[] data) throws Exception {
        return sm4core(algorithmName, Cipher.DECRYPT_MODE, key, iv, data);
    }
	/*
	 * Sm4加解密方法
	 * @param data 加解密数据
	 * @param key 密钥
	 * @param algorithmName 使用的算法/模式/dPadding模式，如SM4/ECB/NoPadding
     * @param iv 初始向量(ECB模式下载NULL，CBC模式时作为偏移量)
     * @param mode 加密或解密模式，Cipher.ENCRYPT_MODE，Cipher.DECRYPT_MODE分别为加密、解密模式
	 * @return 加密模式时返回密文，解密模式时返回明文  
     * @throws Exception
	*/
    private static byte[] sm4core(String algorithmName, int mode, byte[] key, byte[] iv, byte[] data) throws Exception {
        //生成指定算法模式的Cipher对象，Cipher 完成加解密工作
		Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
		//cipher.init 初始化cipher对象，根据mode方式确定加密或解密操作
        if (algorithmName.contains("/ECB/")) {//ECB模式时不需要iv初始向量
            cipher.init(mode, sm4Key);
        } else {//用算法参数mode、密钥sm4Key和随机数据源ivParameterSpec初始化
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(mode, sm4Key, ivParameterSpec);
        }
		//对数据data加密或解密（mode为Cipher.ENCRYPT_MODE时加密，为Cipher.DECRYPT_MODE时解密）。
        return cipher.doFinal(data);
    }
}

public class BcSm4UtilTest {
	
    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
		String text = "SM4无线局域网标准的分组数据算法,对称加密，密钥长度和分组长度均为128位";
		//String text = "0123456789ABCDEFFEDCBA9876543210";//十六进制字符串
		//String text = "中23456789ABCDEF0123456789ABCDEF";
		String keyHex = "0123456789ABCDEFFEDCBA9876543210";
		//byte[] key = BcSm4Util.generateKey();
       	byte[] key = Hex.decode(keyHex);
		String ivHex = "4F723F7349774F063C0C477A367B3278";
		byte[] iv = null;
       
	    List<String> algorithm = new ArrayList<>();
		/*
		algorithm.add(("SM4/CBC/NOPADDING"));
        algorithm.add(("SM4/CBC/PKCS5PADDING"));
        algorithm.add(("SM4/CBC/ISO10126PADDING"));
		algorithm.add(("SM4/PCBC/NOPADDING"));
        algorithm.add(("SM4/PCBC/PKCS5PADDING"));
        algorithm.add(("SM4/PCBC/ISO10126PADDING"));
        algorithm.add(("SM4/CTR/NOPADDING"));
        algorithm.add(("SM4/CTR/PKCS5PADDING"));
        algorithm.add(("SM4/CTR/ISO10126PADDING"));
        algorithm.add(("SM4/CTS/NOPADDING"));
        algorithm.add(("SM4/CTS/PKCS5PADDING"));
        algorithm.add(("SM4/CTS/ISO10126PADDING"));
		*/
		algorithm.add(("SM4/ECB/NOPADDING"));
        algorithm.add(("SM4/ECB/PKCS5PADDING"));
        algorithm.add(("SM4/ECB/ISO10126PADDING"));
     
		if (iv == null)
           iv = Hex.decode(ivHex);
        for (String s : algorithm) {
            //SM4加密
            try {
                System.out.println("\n SM4加密算法： " + s);
                System.out.println(" SM4明文数据： " + text);
                System.out.println(" SM4加密key： " + Hex.toHexString(key));
                System.out.println(" SM4加密iv： " + Hex.toHexString(iv));
                byte[] encrypt = BcSm4Util.encrypt(s, key, iv, text.getBytes("utf-8"));
                System.out.println(" SM4加密密文:"+Hex.toHexString(encrypt));
                //SM4解密
                byte[] decrypt = BcSm4Util.decrypt(s, key, iv, encrypt);
                System.out.println(" SM4解密数据："+new String(decrypt,"utf-8"));
            } catch (Exception e) {
                if (e instanceof IllegalBlockSizeException) {
                    System.err.println(" SM4解密数据：算法 "+ s +" 数据需手工对齐!!!");
                } else {
						System.err.println(" SM4解密数据：算法" + s+"::"+ e.getMessage());
					}
			} finally {
					System.err.println(" ---------------------------------------");
				}
        }
    }
}