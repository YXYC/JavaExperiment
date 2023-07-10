package ExperimentSourceCode_10.SM3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

/**
 * 该类使用SM3算法计算Java源代码的消息摘要。
 */
public class JavaSourceCodeDigest {
    static {
        // 如果尚未添加Bouncy Castle提供者，则添加
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 计算Java源代码文件的消息摘要。
     *
     * @param filePath Java源代码文件的路径
     * @return 计算得到的摘要作为字节数组
     * @throws IOException 读取文件时发生I/O错误
     */
    public static byte[] calculateDigest(String filePath) throws IOException {
        StringBuilder sourceCode = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sourceCode.append(line);
            }
        }

        byte[] input = sourceCode.toString().getBytes("UTF-8");
        SM3Digest sm3Digest = new SM3Digest();
        // 按照指定的字节来更新摘要
        sm3Digest.update(input, 0, input.length);
        byte[] digest = new byte[sm3Digest.getDigestSize()];
        // 形成摘要信息ret
        sm3Digest.doFinal(digest, 0);
        return digest;
    }

    /**
     * 主方法，用于计算Java源代码文件的摘要并进行验证。
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/ExperimentSourceCode_10/SM3/SM3Util.java";
            String expectedDigest = "1359555e2850c21f17630a2a433d75d4b43acd6b8e87a3070352a5a79b4f4106";
            byte[] digest = calculateDigest(filePath);
            System.out.println("Java源代码摘要: " + Hex.toHexString(digest));
            // 验证计算得到的摘要是否与预期的摘要值相同
            System.out.println("验证结果: " + Hex.toHexString(digest).equals(expectedDigest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}