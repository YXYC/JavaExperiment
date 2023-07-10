package Certificate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CertificateUtils {
    private static final String STORE_PASS = "lzu320200932281";
    private static final String ALIAS = "myCertificate";
    private static final String KEYSTORE_PATH = "E:\\myKeystore.keystore";
    private static final String CERT_PATH = "E:\\myCer.cer";
    private static final String PLAIN_TEXT = "Hello,lzu.";
    /** JDK6只支持X.509标准的证书 */
    private static final String CERT_TYPE = "X.509";

    public static void main(String[] args) throws IOException {

        KeyStore keyStore = getKeyStore(STORE_PASS, KEYSTORE_PATH);
        PrivateKey privateKey = getPrivateKey(keyStore, ALIAS, STORE_PASS);
        X509Certificate certificate = getCertificateByKeystore(keyStore, ALIAS);

        byte[] encodedText = encode(PLAIN_TEXT.getBytes(), privateKey);
        byte[] signature = sign(certificate, privateKey, PLAIN_TEXT.getBytes());

        X509Certificate receivedCertificate = getCertificateByCertPath(
                CERT_PATH, CERT_TYPE);
        PublicKey publicKey = getPublicKey(receivedCertificate);
        byte[] decodedText = decode(encodedText, publicKey);
        System.out.println("Decoded Text : " + new String(decodedText));
        System.out.println("Signature is : "
                + verify(receivedCertificate, decodedText, signature));
    }

    public static KeyStore getKeyStore(String storepass, String keystorePath)
            throws IOException {
        InputStream inputStream = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            inputStream = new FileInputStream(keystorePath);
            keyStore.load(inputStream, storepass.toCharArray());
            return keyStore;
        } catch (KeyStoreException | NoSuchAlgorithmException
                | CertificateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return null;
    }

    public static PrivateKey getPrivateKey(KeyStore keyStore, String alias,
                                           String password) {
        try {
            return (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        } catch (UnrecoverableKeyException | KeyStoreException
                | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(Certificate certificate) {
        return certificate.getPublicKey();
    }

    public static X509Certificate getCertificateByKeystore(KeyStore keyStore,
                                                           String alias) {
        try {
            return (X509Certificate) keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static X509Certificate getCertificateByCertPath(String path,
                                                           String certType) throws IOException {
        InputStream inputStream = null;
        try {
            // 实例化证书工厂
            CertificateFactory factory = CertificateFactory
                    .getInstance(certType);
            // 取得证书文件流
            inputStream = new FileInputStream(path);
            // 生成证书
            Certificate certificate = factory.generateCertificate(inputStream);

            return (X509Certificate) certificate;
        } catch (CertificateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return null;

    }

    public static byte[] sign(X509Certificate certificate,
                              PrivateKey privateKey, byte[] plainText) {
        try {
            Signature signature = Signature.getInstance(certificate
                    .getSigAlgName());
            signature.initSign(privateKey);
            signature.update(plainText);
            return signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException
                | SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verify(X509Certificate certificate,
                                 byte[] decodedText, final byte[] receivedignature) {
        try {
            Signature signature = Signature.getInstance(certificate
                    .getSigAlgName());
            /** 注意这里用到的是证书，实际上用到的也是证书里面的公钥 */
            signature.initVerify(certificate);
            signature.update(decodedText);
            return signature.verify(receivedignature);
        } catch (NoSuchAlgorithmException | InvalidKeyException
                | SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] encode(byte[] plainText, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public static byte[] decode(byte[] encodedText, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(encodedText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}