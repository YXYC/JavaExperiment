package ExperimentSourceCode_10.SM2;

//import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
//import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.engines.SM2Engine;
//import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Signature;
import java.security.*;
import java.security.spec.ECGenParameterSpec;//new 
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.math.BigInteger;
import java.io.*;
enum Mode  {
    C1C2C3, C1C3C2;//两种加密标准，前者为旧标准，后者为新标准
}
//BouncyCastle 1.68版本进行测试，1.57以前不支持新标准
public class SM2UtilTest {
static {  //加载BC驱动
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)  
			new BouncyCastleProvider();
	}    
  // 生成sm2密钥对
  static KeyPair createECKeyPair() {
      Security.addProvider(new BouncyCastleProvider()); //需要添加，否则会报错
      //使用标准名称创建EC参数生成的参数规范
      final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
      // 获取一个椭圆曲线类型的密钥对生成器
      final KeyPairGenerator kpg;
      try {
          kpg = KeyPairGenerator.getInstance("EC", "BC");
         // 使用SM2的算法域参数集和指定的随机源初始化密钥生成器
          kpg.initialize(sm2Spec, new SecureRandom());
          // 通过密钥生成器生成密钥对
          return kpg.generateKeyPair();
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
  }  
   /*
	 *公钥加密
   * @param publicKey SM2公钥
   * @param data      明文数据
   * @param modeType  加密模式
   * @return          密文数据 
   */
	public static byte[] encrypt(BCECPublicKey publicKey, byte[] inputBytes, int modeType) {
      //加密模式 
      SM2Engine.Mode mode;
      if (modeType == 1) {//采用新模式加密标准
          mode = SM2Engine.Mode.C1C3C2;
      } else {//采用旧模式加密标准
          mode = SM2Engine.Mode.C1C2C3;
      }
      //通过公钥对象获取公钥的基本域参数。
      ECParameterSpec ecParameterSpec = publicKey.getParameters();
      ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
              ecParameterSpec.getG(), ecParameterSpec.getN());
      //通过公钥值和公钥基本参数创建公钥参数对象
      ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(publicKey.getQ(), ecDomainParameters);
      //根据加密模式实例化SM2公钥加密引擎
      SM2Engine sm2Engine = new SM2Engine(mode);
      //初始化加密引擎
      sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
      byte[] arrayOfBytes = null;
      try {
          //通过加密引擎对字节数串行加密
          arrayOfBytes = sm2Engine.processBlock(inputBytes, 0, inputBytes.length);
      } catch (Exception e) {
          System.out.println("SM2加密时出现异常:" + e.getMessage());
          e.printStackTrace();
      }
	  return arrayOfBytes;
  }
   /**
   * 私钥解密
   *
   * @param privateKey  SM私钥
   * @param cipherBytes 密文数据
	 * @param modeType 加密模式
   * @return           解密后的明文
   */
  public static byte[] decrypt(BCECPrivateKey privateKey, byte[] cipherBytes, int modeType) {
      //解密模式
      SM2Engine.Mode mode;
      if (modeType == 1) {
          mode = SM2Engine.Mode.C1C3C2;
      } else {
          mode = SM2Engine.Mode.C1C2C3;
      }
      //将十六进制字符串密文转换为字节数组（需要与加密一致，加密是：加密后的字节数组转换为了十六进制字符串）
      //byte[] cipherDataByte = Hex.decode(cipherData);

      //通过私钥对象获取私钥的基本域参数。
      ECParameterSpec ecParameterSpec = privateKey.getParameters();
      ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
              ecParameterSpec.getG(), ecParameterSpec.getN());

      //通过私钥值和私钥基本参数创建私钥参数对象
      ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(privateKey.getD(),
              ecDomainParameters);
      //通过解密模式创建解密引擎并初始化
      SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
      sm2Engine.init(false, ecPrivateKeyParameters);
      byte[] arrayOfBytes = null;
      try {
          //通过解密引擎对密文字节串进行解密
          arrayOfBytes = sm2Engine.processBlock(cipherBytes, 0, cipherBytes.length);
         } catch (Exception e) {
          System.out.println("SM2解密时出现异常" + e.getMessage());
      }
      return arrayOfBytes;
  }
  //椭圆曲线ECParameters ASN.1 结构
  private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
  //椭圆曲线公钥或私钥的基本域参数。
  private static ECParameterSpec ecDomainParameters = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
	/**
   * 公钥字符串转换为 BCECPublicKey 公钥对象
   *
   * @param pubKeyHex 64字节十六进制公钥字符串(如果公钥字符串为65字节首个字节为0x04：表示该公钥为非压缩格式，操作时需要删除)
   * @return BCECPublicKey SM2公钥对象
   */
  public static BCECPublicKey getECPublicKeyByPublicKeyHex(String pubKeyHex) {
      //截取64字节有效的SM2公钥（如果公钥首个字节为0x04）
      if (pubKeyHex.length() > 128) {
          pubKeyHex = pubKeyHex.substring(pubKeyHex.length() - 128);
      }
      //将公钥拆分为x,y分量（各32字节）
      String stringX = pubKeyHex.substring(0, 64);
      String stringY = pubKeyHex.substring(stringX.length());
      //将公钥x、y分量转换为BigInteger类型
      BigInteger x = new BigInteger(stringX, 16);
      BigInteger y = new BigInteger(stringY, 16);
      //通过公钥x、y分量创建椭圆曲线公钥规范
      ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecDomainParameters);
      //通过椭圆曲线公钥规范，创建出椭圆曲线公钥对象（可用于SM2加密及验签）
      return new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
  } 
  /**
   * 私钥字符串转换为 BCECPrivateKey 私钥对象
   *
   * @param privateKeyHex: 32字节十六进制私钥字符串
   * @return BCECPrivateKey:SM2私钥对象
   */
  public static BCECPrivateKey getBCECPrivateKeyByPrivateKeyHex(String privateKeyHex) {
      //将十六进制私钥字符串转换为BigInteger对象
      BigInteger d = new BigInteger(privateKeyHex, 16);
      //通过私钥和私钥域参数集创建椭圆曲线私钥规范
      ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecDomainParameters);
      //通过椭圆曲线私钥规范，创建出椭圆曲线私钥对象（可用于SM2解密和签名）
      return new BCECPrivateKey("EC", ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
  } 
	/**
   * 明文签证
   *
   * @param msg: 32字节十六进制明数组
	 * @param privateKey:SM2私钥对象
   * @return 十六进制字符数组签名
   */
	public static byte[] Sm2Sign(byte[] msg,PrivateKey privateKey)throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, 
	     InvalidKeyException,CertPathBuilderException, SignatureException, CertificateException {
	    // 生成SM2sign with sm3 签名验签算法实例
		Signature signature = Signature.getInstance("SM3withSm2", "BC");
		// 签名 签名需要使用私钥初始化签名实例
		signature.initSign(privateKey);
		// 签名原文 
		signature.update(msg);
		// 计算签名值，作为返回值
		byte[] signatureValue = signature.sign();
		return signatureValue;
	}
	/**
   * 签名验证
   *
   * @param msg: 十六进制明文字符数组
	 * @param fingerprint: 提供的十六进制字节数组签名
	 * @param publicKey:SM2公钥对象
   * @return 签名是否正确
   */
	public static boolean Sm2Verify(byte[] msg,byte[] fingerprint,PublicKey publicKey)throws IOException,
		NoSuchAlgorithmException,CertPathBuilderException, NoSuchProviderException, InvalidAlgorithmParameterException, 
		 InvalidKeyException, SignatureException  {
		// 生成SM2sign with sm3 签名验签算法实例
		Signature signature = Signature.getInstance("SM3withSm2", "BC");		
		//验签 签名需要使用公钥初始化签名实例
		signature.initVerify(publicKey);
		// 写入待验签的签名原文
		signature.update(msg);
		// 验签
		return signature.verify(fingerprint);
	}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, 
		InvalidAlgorithmParameterException, CertPathBuilderException,InvalidKeyException, SignatureException, CertificateException {
      String publicKeyHex = null;
      String privateKeyHex = null;
	   //产生公钥、私钥密钥对
      KeyPair keyPair = createECKeyPair();
	   PrivateKey privateKey = keyPair.getPrivate();
      PublicKey publicKey = keyPair.getPublic();
		if (publicKey instanceof BCECPublicKey) {
          //获取65字节非压缩的十六进制公钥串(0x04)
		   byte[] publicKeyBytes = ((BCECPublicKey) publicKey).getQ().getEncoded(false);
          publicKeyHex = Hex.toHexString(publicKeyBytes);
          System.out.println("---->SM2公钥：" + publicKeyHex);
      }
		if (privateKey instanceof BCECPrivateKey) {
          //获取32字节十六进制私钥串
		   privateKeyHex = ((BCECPrivateKey) privateKey).getD().toString(16);
          System.out.println("---->SM2私钥：" + privateKeyHex);
      }
      // 公钥加密
      String plainText = "=========待加密数据=========";
	   byte[] plainBytes = plainText.getBytes("utf-8");
	   System.out.println("\n---->明文：" + plainText);
      //将十六进制公钥串转换为 BCECPublicKey 公钥对象
      BCECPublicKey bcecPublicKey = getECPublicKeyByPublicKeyHex(publicKeyHex);
      byte[] encryptBytes = encrypt(bcecPublicKey, plainBytes, 1);
	   String encryptData = Hex.toHexString(encryptBytes); 
      System.out.println("---->加密结果：" + encryptData);
      // 私钥解密
      //将十六进制私钥串转换为 BCECPrivateKey 私钥对象
      BCECPrivateKey bcecPrivateKey = getBCECPrivateKeyByPrivateKeyHex(privateKeyHex);
      byte[] decryBytes = decrypt(bcecPrivateKey, encryptBytes, 1);
//将解密后的字节串转换为utf8字符编码的字符串（需要与明文加密时字符串转换成字节串所指定的字符编码保持一致）
      String data = new String(decryBytes,"utf-8");
	   System.out.println("--->解密结果：" + data);
		//签名与验证
		String msgText =  "Hello world";
		byte[] msgBytes = msgText.getBytes("UTF-8");
		byte[] signatureValue = Sm2Sign(msgBytes,privateKey);
		System.out.println("   MsgText: " + msgText);
		System.out.println("   signature: " + Hex.toHexString(signatureValue));
		System.out.println("   Signature verify result: " + Sm2Verify(msgBytes,signatureValue,publicKey));
  }
}