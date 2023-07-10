public class DemoOverloading {
	public void disp(char c)	    {
	         System.out.println(c);
	    }
	public void disp(char c, int num){
	         for(int i=1;i<=num;i++) System.out.print(c);
	         System.out.println();
	    }
	public void disp(String s){
	    	System.out.println(s.toUpperCase().charAt(1));
	    }
	publicvoid disp(String s,int num) {
	    	for(int i=1;i<=num;i++) System.out.print(s+" ");
	    	System.out.println();
	    }
	public static void main(String[] args){
	    	DemoOverloading obj=new DemoOverloading();
	    	obj.disp('*');
	    	obj.disp('=',10);
	    	obj.disp("abcdefg");
	    	obj.disp("abcdefg",10);
	    }
}