//���湫ʽŷ������ exp(ix)=cos(x)+i*sin(x)չʾ
public class Shiyan2_3_3 {

	public static void main(String[] args) {
		//�������[0,PI/2]�ڵ�ŷ������ֵ���ýǶȣ�����1��
        for(int x=0;x<=90;x++) {
        	System.out.printf("exp(i*%d)=%.4f+i*%.4f\n",x,Math.cos(Math.PI/180*x),Math.sin(Math.PI/180*x));
        }
	}

}
