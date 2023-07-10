package chap19;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import java.security.Security;
import java.util.Arrays;
import java.io.UnsupportedEncodingException;
	// ����SM3ժҪ�㷨������
public class SM3Util {
    static{
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null)
        Security.addProvider(new BouncyCastleProvider());
    }
    //����SM3�㷨Ĭ����Կ����ժҪ����Ϊ����ֵ
    public static byte[] digest(byte[] input){
		//����һ��sm3�㷨�Ķ���
        SM3Digest sm3Digest = new SM3Digest();
         //����ָ�����ֽ�������ժҪ
        sm3Digest.update(input, 0, input.length);
        byte[] ret = new byte[sm3Digest.getDigestSize()];
		//�γ�ժҪ��Ϣret 
        sm3Digest.doFinal(ret, 0);
        return ret;
    }
	/**
     * �ж�Դ��������������Ƿ�һ��
     * @param srcData     �����ֽ�����
     * @param cipherbytes �ֹ��ṩ��ժҪ�ֽ����飨16�������飩
     * @return ��֤���
     * @explain ��֤�������ɵ�hash�������ֹ�������ժҪ�����Ƿ���ͬ
     */
    public static boolean verify(byte[] srcData, byte[] cipherbytes) {
        boolean flag = false;
        byte[] newHash = digest(srcData);
        if (Arrays.equals(newHash, cipherbytes)) 
				flag = true;
		return flag;
    }
    public static void main(String[] args) throws Exception {
      String plaintext = "��ѧ�ͼ���test plaintext";
		String oldfingerprint = "945f375395c32d3a0c4ea7ea0e4ddaff70eeb418e7c71c944891135a79eff7a4"; 
		//String oldfingerprint = "945f375395c32d3a0c4ea7ea0e4ddaff60eeb418e7c71c944891135a79eff7a4";
		boolean flag = false;
		byte[] plainbytes = plaintext.getBytes("utf-8");
	    byte[] newfingerprint = digest(plainbytes);
     System.out.println("sm3 digest fingerprint:" + Hex.toHexString(newfingerprint));
	    flag=verify( plainbytes,  Hex.decode(oldfingerprint));
		System.out.println("verify result:"+flag);
	}
}