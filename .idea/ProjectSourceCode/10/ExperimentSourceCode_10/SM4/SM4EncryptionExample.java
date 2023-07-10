package ExperimentSourceCode_10.SM4;

import java.io.*;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

public class SM4EncryptionExample {
    private static final int BLOCK_SIZE = 16; // SM4 分组大小为 16 字节

    public static void main(String[] args) {
        try {
            // 读取原始的 Java 源程序文件
            File inputFile = new File("src/main/java/ExperimentSourceCode_10/SM4/SM4EncryptionExample.java");
            byte[] inputBytes = readFileAsBytes(inputFile);

            // 生成随机的密钥和 IV
            byte[] key = generateRandomBytes(BLOCK_SIZE);
            byte[] iv = generateRandomBytes(BLOCK_SIZE);

            // 加密文件内容
            byte[] encryptedBytes = encrypt(inputBytes, key, iv);

            // 将加密后的内容写入文件
            File encryptedFile = new File("src/main/java/ExperimentSourceCode_10/SM4/data/EncryptedFile.txt");
            writeBytesToFile(encryptedBytes, encryptedFile);

            // 将加密后的 key、iv、密文写入文件
            File encryptedInfoFile = new File("src/main/java/ExperimentSourceCode_10/SM4/data/EncryptedInfo.txt");
            writeEncryptionInfoToFile(key, iv, encryptedBytes, encryptedInfoFile);

            // 解密文件内容
            byte[] decryptedBytes = decrypt(encryptedBytes, key, iv);

            // 将解密后的内容写入文件
            File decryptedFile = new File("src/main/java/ExperimentSourceCode_10/SM4/data/DecryptedFile.txt");
            writeBytesToFile(decryptedBytes, decryptedFile);

            // 将解密后的 key、iv、原始数据写入文件
            File decryptedInfoFile = new File("src/main/java/ExperimentSourceCode_10/SM4/data/DecryptedInfo.txt");
            writeDecryptionInfoToFile(key, iv, inputBytes, decryptedInfoFile);

            System.out.println("Encryption and decryption completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取文件内容为字节数组
    private static byte[] readFileAsBytes(File file) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, length);
        }
        fis.close();
        return bos.toByteArray();
    }

    // 将字节数组写入文件
    private static void writeBytesToFile(byte[] bytes, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    // 将加密的 key、iv、密文写入文件
    private static void writeEncryptionInfoToFile(byte[] key, byte[] iv, byte[] encryptedBytes, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("Key: ".getBytes());
        fos.write(Hex.encode(key));
        fos.write(System.lineSeparator().getBytes());
        fos.write("IV: ".getBytes());
        fos.write(Hex.encode(iv));
        fos.write(System.lineSeparator().getBytes());
        fos.write("Encrypted Data: ".getBytes());
        fos.write(Hex.encode(encryptedBytes));
        fos.close();
    }

    // 将解密的 key、iv、原始数据写入文件
    private static void writeDecryptionInfoToFile(byte[] key, byte[] iv, byte[] decryptedBytes, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("Key: ".getBytes());
        fos.write(Hex.encode(key));
        fos.write(System.lineSeparator().getBytes());
        fos.write("IV: ".getBytes());
        fos.write(Hex.encode(iv));
        fos.write(System.lineSeparator().getBytes());
        fos.write("Decrypted Data: ".getBytes());
        fos.write(decryptedBytes);
        fos.close();
    }

    // 生成指定长度的随机字节数组
    private static byte[] generateRandomBytes(int length) {
        byte[] randomBytes = new byte[length];
        new java.security.SecureRandom().nextBytes(randomBytes);
        return randomBytes;
    }

    // 加密数据
    private static byte[] encrypt(byte[] input, byte[] key, byte[] iv) {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new SM4Engine()));
        cipher.init(true, new ParametersWithIV(new KeyParameter(key), iv));
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int processedBytes = cipher.processBytes(input, 0, input.length, output, 0);
        try {
            cipher.doFinal(output, processedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    // 解密数据
    private static byte[] decrypt(byte[] input, byte[] key, byte[] iv) {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new SM4Engine()));
        cipher.init(false, new ParametersWithIV(new KeyParameter(key), iv));

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int processedBytes = cipher.processBytes(input, 0, input.length, output, 0);
        try {
            cipher.doFinal(output, processedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}