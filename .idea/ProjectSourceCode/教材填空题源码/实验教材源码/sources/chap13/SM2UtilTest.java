package chap19;

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
    C1C2C3, C1C3C2;//���ּ��ܱ�׼��ǰ��Ϊ�ɱ�׼������Ϊ�±�׼
}
//BouncyCastle 1.68�汾���в��ԣ�1.57��ǰ��֧���±�׼
public class SM2UtilTest {
static {  //����BC����
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)  
			new BouncyCastleProvider();
	}    
  // ����sm2��Կ��
  static KeyPair createECKeyPair() {
      //ʹ�ñ�׼���ƴ���EC�������ɵĲ����淶
      final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
      // ��ȡһ����Բ�������͵���Կ��������
      final KeyPairGenerator kpg;
      try {
          kpg = KeyPairGenerator.getInstance("EC", "BC");
         // ʹ��SM2���㷨���������ָ�������Դ��ʼ����Կ������
          kpg.initialize(sm2Spec, new SecureRandom());
          // ͨ����Կ������������Կ��
          return kpg.generateKeyPair();
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
  }  
   /*
	 *��Կ����
   * @param publicKey SM2��Կ
   * @param data      ��������
   * @param modeType  ����ģʽ
   * @return          �������� 
   */
	public static byte[] encrypt(BCECPublicKey publicKey, byte[] inputBytes, int modeType) {
      //����ģʽ 
      SM2Engine.Mode mode;
      if (modeType == 1) {//������ģʽ���ܱ�׼
          mode = SM2Engine.Mode.C1C3C2;
      } else {//���þ�ģʽ���ܱ�׼
          mode = SM2Engine.Mode.C1C2C3;
      }
      //ͨ����Կ�����ȡ��Կ�Ļ����������
      ECParameterSpec ecParameterSpec = publicKey.getParameters();
      ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
              ecParameterSpec.getG(), ecParameterSpec.getN());
      //ͨ����Կֵ�͹�Կ��������������Կ��������
      ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(publicKey.getQ(), ecDomainParameters);
      //���ݼ���ģʽʵ����SM2��Կ��������
      SM2Engine sm2Engine = new SM2Engine(mode);
      //��ʼ����������
      sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
      byte[] arrayOfBytes = null;
      try {
          //ͨ������������ֽ������м���
          arrayOfBytes = sm2Engine.processBlock(inputBytes, 0, inputBytes.length);
      } catch (Exception e) {
          System.out.println("SM2����ʱ�����쳣:" + e.getMessage());
          e.printStackTrace();
      }
	  return arrayOfBytes;
  }
   /**
   * ˽Կ����
   *
   * @param privateKey  SM˽Կ
   * @param cipherData ��������
	 * @param modeType ����ģʽ
   * @return           ���ܺ������
   */
  public static byte[] decrypt(BCECPrivateKey privateKey, byte[] cipherBytes, int modeType) {
      //����ģʽ
      SM2Engine.Mode mode;
      if (modeType == 1) {
          mode = SM2Engine.Mode.C1C3C2;
      } else {
          mode = SM2Engine.Mode.C1C2C3;
      }
      //��ʮ�������ַ�������ת��Ϊ�ֽ����飨��Ҫ�����һ�£������ǣ����ܺ���ֽ�����ת��Ϊ��ʮ�������ַ�����
      //byte[] cipherDataByte = Hex.decode(cipherData);

      //ͨ��˽Կ�����ȡ˽Կ�Ļ����������
      ECParameterSpec ecParameterSpec = privateKey.getParameters();
      ECDomainParameters ecDomainParameters = new ECDomainParameters(ecParameterSpec.getCurve(),
              ecParameterSpec.getG(), ecParameterSpec.getN());

      //ͨ��˽Կֵ��˽Կ������������˽Կ��������
      ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(privateKey.getD(),
              ecDomainParameters);
      //ͨ������ģʽ�����������沢��ʼ��
      SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
      sm2Engine.init(false, ecPrivateKeyParameters);
      byte[] arrayOfBytes = null;
      try {
          //ͨ����������������ֽڴ����н���
          arrayOfBytes = sm2Engine.processBlock(cipherBytes, 0, cipherBytes.length);
         } catch (Exception e) {
          System.out.println("SM2����ʱ�����쳣" + e.getMessage());
      }
      return arrayOfBytes;
  }
  //��Բ����ECParameters ASN.1 �ṹ
  private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
  //��Բ���߹�Կ��˽Կ�Ļ����������
  private static ECParameterSpec ecDomainParameters = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
	/**
   * ��Կ�ַ���ת��Ϊ BCECPublicKey ��Կ����
   *
   * @param pubKeyHex 64�ֽ�ʮ�����ƹ�Կ�ַ���(�����Կ�ַ���Ϊ65�ֽ��׸��ֽ�Ϊ0x04����ʾ�ù�ԿΪ��ѹ����ʽ������ʱ��Ҫɾ��)
   * @return BCECPublicKey SM2��Կ����
   */
  public static BCECPublicKey getECPublicKeyByPublicKeyHex(String pubKeyHex) {
      //��ȡ64�ֽ���Ч��SM2��Կ�������Կ�׸��ֽ�Ϊ0x04��
      if (pubKeyHex.length() > 128) {
          pubKeyHex = pubKeyHex.substring(pubKeyHex.length() - 128);
      }
      //����Կ���Ϊx,y��������32�ֽڣ�
      String stringX = pubKeyHex.substring(0, 64);
      String stringY = pubKeyHex.substring(stringX.length());
      //����Կx��y����ת��ΪBigInteger����
      BigInteger x = new BigInteger(stringX, 16);
      BigInteger y = new BigInteger(stringY, 16);
      //ͨ����Կx��y����������Բ���߹�Կ�淶
      ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y), ecDomainParameters);
      //ͨ����Բ���߹�Կ�淶����������Բ���߹�Կ���󣨿�����SM2���ܼ���ǩ��
      return new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
  } 
  /**
   * ˽Կ�ַ���ת��Ϊ BCECPrivateKey ˽Կ����
   *
   * @param privateKeyHex: 32�ֽ�ʮ������˽Կ�ַ���
   * @return BCECPrivateKey:SM2˽Կ����
   */
  public static BCECPrivateKey getBCECPrivateKeyByPrivateKeyHex(String privateKeyHex) {
      //��ʮ������˽Կ�ַ���ת��ΪBigInteger����
      BigInteger d = new BigInteger(privateKeyHex, 16);
      //ͨ��˽Կ��˽Կ�������������Բ����˽Կ�淶
      ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecDomainParameters);
      //ͨ����Բ����˽Կ�淶����������Բ����˽Կ���󣨿�����SM2���ܺ�ǩ����
      return new BCECPrivateKey("EC", ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
  } 
	/**
   * ����ǩ֤
   *
   * @param msg: 32�ֽ�ʮ������������
	 * @param privateKey:SM2˽Կ����
   * @return ʮ�������ַ�����ǩ��
   */
	public static byte[] Sm2Sign(byte[] msg,PrivateKey privateKey)throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, 
	     InvalidKeyException,CertPathBuilderException, SignatureException, CertificateException {
	    // ����SM2sign with sm3 ǩ����ǩ�㷨ʵ��
		Signature signature = Signature.getInstance("SM3withSm2", "BC");
		// ǩ�� ǩ����Ҫʹ��˽Կ��ʼ��ǩ��ʵ��
		signature.initSign(privateKey);
		// ǩ��ԭ�� 
		signature.update(msg);
		// ����ǩ��ֵ����Ϊ����ֵ
		byte[] signatureValue = signature.sign();
		return signatureValue;
	}
	/**
   * ǩ����֤
   *
   * @param msg: ʮ�����������ַ�����
	 * @param fingerprint: �ṩ��ʮ�������ֽ�����ǩ��
	 * @param publicKey:SM2��Կ����
   * @return ǩ���Ƿ���ȷ
   */
	public static boolean Sm2Verify(byte[] msg,byte[] fingerprint,PublicKey publicKey)throws IOException,
		NoSuchAlgorithmException,CertPathBuilderException, NoSuchProviderException, InvalidAlgorithmParameterException, 
		 InvalidKeyException, SignatureException  {
		// ����SM2sign with sm3 ǩ����ǩ�㷨ʵ��
		Signature signature = Signature.getInstance("SM3withSm2", "BC");		
		//��ǩ ǩ����Ҫʹ�ù�Կ��ʼ��ǩ��ʵ��
		signature.initVerify(publicKey);
		// д�����ǩ��ǩ��ԭ��
		signature.update(msg);
		// ��ǩ
		return signature.verify(fingerprint);
	}
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, 
		InvalidAlgorithmParameterException, CertPathBuilderException,InvalidKeyException, SignatureException, CertificateException {
      String publicKeyHex = null;
      String privateKeyHex = null;
	   //������Կ��˽Կ��Կ��
      KeyPair keyPair = createECKeyPair();
	   PrivateKey privateKey = keyPair.getPrivate();
      PublicKey publicKey = keyPair.getPublic();
		if (publicKey instanceof BCECPublicKey) {
          //��ȡ65�ֽڷ�ѹ����ʮ�����ƹ�Կ��(0x04)
		   byte[] publicKeyBytes = ((BCECPublicKey) publicKey).getQ().getEncoded(false);
          publicKeyHex = Hex.toHexString(publicKeyBytes);
          System.out.println("---->SM2��Կ��" + publicKeyHex);
      }
		if (privateKey instanceof BCECPrivateKey) {
          //��ȡ32�ֽ�ʮ������˽Կ��
		   privateKeyHex = ((BCECPrivateKey) privateKey).getD().toString(16);
          System.out.println("---->SM2˽Կ��" + privateKeyHex);
      }
      // ��Կ����
      String plainText = "=========����������=========";
	   byte[] plainBytes = plainText.getBytes("utf-8");
	   System.out.println("\n---->���ģ�" + plainText);
      //��ʮ�����ƹ�Կ��ת��Ϊ BCECPublicKey ��Կ����
      BCECPublicKey bcecPublicKey = getECPublicKeyByPublicKeyHex(publicKeyHex);
      byte[] encryptBytes = encrypt(bcecPublicKey, plainBytes, 1);
	   String encryptData = Hex.toHexString(encryptBytes); 
      System.out.println("---->���ܽ����" + encryptData);
      // ˽Կ����
      //��ʮ������˽Կ��ת��Ϊ BCECPrivateKey ˽Կ����
      BCECPrivateKey bcecPrivateKey = getBCECPrivateKeyByPrivateKeyHex(privateKeyHex);
      byte[] decryBytes = decrypt(bcecPrivateKey, encryptBytes, 1);
//�����ܺ���ֽڴ�ת��Ϊutf8�ַ�������ַ�������Ҫ�����ļ���ʱ�ַ���ת�����ֽڴ���ָ�����ַ����뱣��һ�£�
      String data = new String(decryBytes,"utf-8");
	   System.out.println("--->���ܽ����" + data);
		//ǩ������֤
		String msgText =  "Hello world";
		byte[] msgBytes = msgText.getBytes("UTF-8");
		byte[] signatureValue = Sm2Sign(msgBytes,privateKey);
		System.out.println("   MsgText: " + msgText);
		System.out.println("   signature: " + Hex.toHexString(signatureValue));
		System.out.println("   Signature verify result: " + Sm2Verify(msgBytes,signatureValue,publicKey));
  }
}