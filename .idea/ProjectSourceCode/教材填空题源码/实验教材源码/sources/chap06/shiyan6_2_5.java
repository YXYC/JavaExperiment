import java.util.Arrays;
public class shiyan6_2_5 {
    public static void main(String[] args){
        int[] a={5,1,3,2,4,8,7,9,10,6};
        System.out.print("����ǰ��");
        for(int i=0;i<a.length;i++) System.out.print("  "+a[i]);
        Arrays.sort(a);
        System.out.print("\n�����");
        for(int i=0;i<a.length;i++) System.out.print("  "+a[i]);  
        System.out.print("\n����Ԫ��ֵ4");  
        int k=Arrays.binarySearch(a,4);
        if(k<0) System.out.print("\nû���ҵ�Ԫ��ֵ4");
        else System.out.print("\nԪ��ֵ4�������е��±�Ϊ��"+k);
        int[] c=new int[10]; int[] d=new int[10];
        Arrays.fill(c,4); Arrays.fill(d,4);
        System.out.print("\n����c��");
        for(int i=0;i<c.length;i++) System.out.print("  "+c[i]); 
        System.out.println("\nequals(a,c)="+Arrays.equals(a,c));
        System.out.println("equals(c,d)="+Arrays.equals(c,d));
    }
}        
        