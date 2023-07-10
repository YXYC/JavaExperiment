public class EulerTriangle {
    
    public static void main(String[] args) {
		double radian,real,image;
        for( int degree =0;degree<=90;degree++){
		    radian = degree*3.14159/180;
		    image=Math.sin(radian);
            real=Math.cos(radian);
            System.out.printf("exp(i*%d)=%6.4f+i*%6.4f\n",degree,real,image);
			//System.out.println();
		}
    }
}