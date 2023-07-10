import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import javax.crypto.IllegalBlockSizeException;
import java.util.*;

public class BcSm4Test {

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, IOException {
        File f = new File("src/BcSm4Test.java");
        FileInputStream inputStream = new FileInputStream(f);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String text =new String(bytes,"utf-8");

        //String text = "SM4无线局域网标准的分组数据算法,对称加密，密钥长度和分组长度均为128位";
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
                System.out.println(" SM4明文数据： ");
                System.out.println(text);
                System.out.println(" SM4加密key： " + Hex.toHexString(key));
                System.out.println(" SM4加密iv： " + Hex.toHexString(iv));
                byte[] encrypt = BcSm4Util.encrypt(s, key, iv, text.getBytes("utf-8"));
                System.out.println(" SM4加密密文:"+Hex.toHexString(encrypt));
                //SM4解密
                byte[] decrypt = BcSm4Util.decrypt(s, key, iv, encrypt);
                System.out.println(" SM4解密数据：");
                System.out.println(new String(decrypt,"utf-8"));
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