import java.io.*;
class FileMethods {
   public static void main(String args[]) {
      if(args.length==0) {
          System.out.println("�������ļ�����");
          System.exit(0);   //��������
      }
      File f1=new File(args[0]);  //���������в���
      System.out.println("�ļ�����"+f1.getName());
      System.out.println("·����"+f1.getPath());
      System.out.println("����·����"+f1.getAbsolutePath());
      System.out.println(f1.exists()?"�ļ�����" :"�ļ�������") ;
      System.out.println(f1.isDirectory()?"��Ŀ¼": "���ļ�") ;
      System.out.println(f1.isFile()?"����ͨ�ļ�": "�����������ܵ�");
      if(f1.canRead())  {
         System.out.println("���Զ�ȡ���ļ�") ;
      }else{
         System.out.println("���ܶ�ȡ���ļ�");
      }
      if(f1.canWrite()){
         System.out.println("����д����ļ�");
      }else{
         System.out.println("����д����ļ�");
      }
         System.out.println("���ļ�����޸�ʱ����1970��1��1�պ��"+f1.lastModified()+"��") ;
   }
}