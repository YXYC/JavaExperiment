import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.security.Security;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;
public class BcSm3Test {
    static{
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
            Security.addProvider(new BouncyCastleProvider());
    }
    //采用SM3算法默认密钥生成摘要，作为返回值
    public static byte[] digest(byte[] input){
        //生成一个sm3算法的对象
        SM3Digest sm3Digest = new SM3Digest();
        //按照指定的字节来更新摘要
        sm3Digest.update(input, 0, input.length);
        byte[] ret = new byte[sm3Digest.getDigestSize()];
        //产生信息摘要ret
        sm3Digest.doFinal(ret, 0);
        return ret;
    }
    /**
     * 计算源数据srcData的信息摘要与提供的信息摘要digestbytes是否一致
     * @param srcData     明文字节数组
     * @param digestbytes 手工提供的摘要字节数组（16进制数组）
     * @return 验证结果
     * @explain 验证明文生成的hash数组与手工给出的摘要数组是否相同
     */
    public static boolean verify(byte[] srcData, byte[] digestbytes) {
        boolean flag = false;
        byte[] newHash = digest(srcData);
        if (Arrays.equals(newHash, digestbytes))
            flag = true;
        return flag;
    }

    public static void main(String[] args) throws Exception {
        File f = new File("src/BcSm3Test.java");
        FileInputStream inputStream = new FileInputStream(f);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String plaintext =new String(bytes,"utf-8");

        //String plaintext = "科学和技术test plaintext";
        //String oldfingerprint = "945f375395c32d3a0c4ea7ea0e4ddaff70eeb418e7c71c944891135a79eff7a4";
        //boolean flag = false;
        byte[] plainbytes = plaintext.getBytes("utf-8");
        byte[] newfingerprint = digest(plainbytes);
        System.out.println("\n  plaintext:");
        System.out.println(plaintext);
        System.out.println("  sm3 digest fingerprint:" + Hex.toHexString(newfingerprint));
    }
}