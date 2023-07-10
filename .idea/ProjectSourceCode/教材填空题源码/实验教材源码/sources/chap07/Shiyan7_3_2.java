import java.util.Scanner;
import java.io.*;
class Shiyan7_3_2 {
    public static void main(String[] args){        
        Matrix mymatrix=new Matrix();
        mymatrix.initValue("matrix.dat");
        System.out.print(mymatrix.toString());
    }
}
class Matrix {
    private int m,n;
    private double v[][];
    public Matrix(){m=0;n=0;}
    public Matrix(int k,int l){
        m=Math.abs(k);n=Math.abs(l); //过滤数据
        if(m==0||n==0) m=n=1;
        v=new double[m][n];
        initZero();
    }
    public int getM(){return m;}
    public int getN(){return n;}
    public double[][] getV(){ return v;}
    public double getElement(int i,int j){return v[i][j];}
    
    public void setMN(int k,int l){
        m=Math.abs(k);n=Math.abs(l);
        if(m==0||n==0) m=n=1;
        v=new double[m][n];
        initZero();    
    }
    private void initZero(){
        for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               v[i][j]=0.0;
           }
        }
    }
    public boolean equals(Matrix another){
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               if(v[i][j]!=another.getElement(i,j)) return false;
           }
        }
        return true;
    }
    public void initValue(){  
        Scanner keyin=new Scanner(System.in);  
        System.out.println("请输入"+(m*n)+"个浮点数,用来初始化"+m+"*"+n+"的矩阵.");
        for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               v[i][j]=keyin.nextDouble();
           }
        }    
    }

    public void initValue(String fname){
        try {
           Scanner filein=new Scanner(new File(fname)).useDelimiter("\\s*,\\s*|\\s+"); 
            m=filein.nextInt();
            n=filein.nextInt();
            v=new double[m][n];
            System.out.println("m="+m+",n="+n);
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    v[i][j]=filein.nextDouble();
//                    System.out.print("  "+v[i][j]);
                }
            }
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
    public void writeToFile(String fname){
        try {
            BufferedWriter bout=new BufferedWriter(new FileWriter(fname));
            bout.write(""+m+" "+n);
            bout.newLine();
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    bout.write(""+v[i][j]+" ");
                }
                bout.newLine();
            }
        }catch(IOException e1){e1.printStackTrace();}    
    }
    public String toString(){
        StringBuilder str=new StringBuilder();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
               str.append(v[i][j]).append("\t");
            }
            str.append("\n");
        }
        return str.toString();
    }
}