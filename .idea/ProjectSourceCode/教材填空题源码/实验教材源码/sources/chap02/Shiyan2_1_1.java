public class Shiyan2_1_1 {
    public static void main(String[] args){
        System.out.println("�����Ѿ�֪������ƽ���뾶6370.856ǧ��");
        System.out.println("Ҳ֪�����ϵĵ�������Ϊ5.98��10^24 kg");
        System.out.println("���ǾͿ���ʹ����ѧ��ʽ��������ƽ���ܶ�Ϊ��");
        double radius=6.370856E6;  //����洢�뾶�ĸ����ͱ������ÿ�ѧ������
        double mass=5.98E24; //����洢�����ĸ����ͱ���
        double volume=4*Math.PI*Math.pow(radius,3)/3;  //��������ʽ��ע����ѧ��ʽ�ڳ����б任���õ���Math���г����ͷ�����
        double density=mass/volume; //����ƽ���ܶ�
        System.out.print(density+"(ǧ��/������)"); //�������
    }
}