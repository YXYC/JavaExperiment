public class shiyan6_2_3{
    public static void main(String[] args){
        StringBuilder s1=new StringBuilder();
        System.out.println("StringBuilder�����Ĭ�Ͽռ��СΪ��"+s1.capacity());
        StringBuilder s2=new StringBuilder("abcd1234");
        System.out.println("��ǰ�ַ���Ϊ��"+s2.toString()+",����Ϊ��"+s2.capacity()+",����Ϊ��"+s2.length());
        System.out.println("s2.delete(3,6)="+s2.delete(3,6).toString());
        System.out.println("s2.reverse()="+s2.reverse().toString());
        System.out.println("s2.append(20.2)="+s2.append(20.2).toString());
        s2=s2.insert(2,"1234567890");
        System.out.print("��ǰ�ַ�����"+s2.toString()+",����Ϊ��"+s2.capacity());
        s2=s2.insert(3,"1234567890");
        System.out.print("��ǰ�ַ�����"+s2.toString()+",����Ϊ��"+s2.capacity());
    }
}