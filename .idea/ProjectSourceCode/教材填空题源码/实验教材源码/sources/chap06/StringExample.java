class StringExample  {
    public static void main(String args[]) {
        String s1=new String("you are a student"),
             s2=new String("how are you");
        if (s1.equals(s2)) { // �ж�s1��s2�Ƿ���ͬ
            System.out.println("s1��s2��ͬ");
         } else {
           System.out.println("s1��s2����ͬ");  
         }
         String s3=new String("620102197210015331");
         if (s3.startsWith(620102)) {  // �ж�s3��ǰ׺�Ƿ��ǡ�620102��
               System.out.println("����ʡ�����֤");
         }
         String s4=new String("��"),
              s5=new String("��");
         if(s4.compareTo(s5)) {  
              System.out.println("���ֵ���s4����s5");
         } else {
             System.out.println("���ֵ���s4С��s5");
         }
         int position=0;
         String path="d:\\shiyan\\src\\shiyan3\\People.java";
         position=path.lastIndexOf("\\");//��ȡpath��������Ŀ¼�ָ����ŵ�λ��
         System.out.println("d:\\shiyan\\src\\shiyan3\\People.java��������\\��λ��:"+position); 
         String fileName=path.substring(position); //��ȡpath�С�People.java�����ַ���
         System.out.println("c:\\java\\jsp\\A.java�к��е��ļ���:"+fileName); 
         String s6=new String("100"), s7=new String("123.678");
         int n1=Integer.parseInt(s6);   // ��s6ת����int������
         double n2=Double.parseDouble(s7); // ��s7ת����double������
         double n=n1+n2;
         System.out.println(n);
         String s8=new String("ABCDEF");
         char a[]=s8.getBytes();       // ��s8��ŵ�����a��
         for(int i=a.length-1;i>=0;i--) {
             System.out.printf("%3c",a[i]);
          }
    }
}
