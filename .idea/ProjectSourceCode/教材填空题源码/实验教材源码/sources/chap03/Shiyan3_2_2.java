class Shiyan3_2_2 {
    public static void main(String[] args){
        int n=100;
        Sieve s=new Sieve();
        s.executeFilter(n);
        System.out.println("С��"+n+"�������У�");
        s.outFilter();
    }
}
class Counter{    //���ֲ�����
    private int value;  //���ֲ������ĳ�ֵ
    Counter(int val){value=val;}
    public int getValue(){return value;}
    public void next(){value++;}  //������һ������
}
class Sieve{   //ɸ��
    final int Max=100;  //�趨�����������ֵ
    private int filterCount=0;
    private int[] f;   //�洢���������ݵ�����
    public Sieve(){f=new int[Max];filterCount=0;}
    public void executeFilter(int n){  //ִ�й��ˣ�����2��n֮������
        Counter c=new Counter(2);  
        for(;c.getValue()<n;c.next()){
            passFilter(c);
        }    
    }
    public void passFilter(Counter c){
        for(int i=0;i<filterCount/2;i++)
            if(c.getValue()%f[i]==0) return;
            addElementIntoFilter(c.getValue());
    }
    private void addElementIntoFilter(int x){
        f[filterCount]=x;
        filterCount++;
    }
    public void outFilter(){
        for(int i=0;i<filterCount;i++){
            System.out.printf("%4d",f[i]);
            if((i+1)%10==0)System.out.println();
        }
    }
}
    
