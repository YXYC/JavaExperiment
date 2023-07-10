package ExperimentSourceCode_1;

import java.util.Arrays;

public class Matrix {
    private int row;//行
    private int col;//列
    private double[][] M;
    //构造方法
    public Matrix(int row,int col) {
        this.col=col;
        this.row=row;
        M = new double[row][col];
        //初始将矩阵内元素全部赋值为0
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                M[i][j] = 0;
            }
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow(){
        return row;
    }

    public double[][] getM() {
        return M;
    }

    //为整个矩阵赋值
    public void setM(double[][] m) {
        M = m;
    }

    //为某个矩阵赋值
    public void setN(int a,int b,int N){
        M[a][b]= N;
    }

    @Override
    //输出整个矩阵
    public String toString() {
        return "NO1.Matrix{" +
                Arrays.deepToString(M)+
                '}';
    }

    //输出矩阵中的某个数
    public double getN(int a,int b){
        return M[a-1][b-1];
    }

    public double[][] matrixAdd(Matrix N){
        if(N.getCol()!=this.getCol()||N.getRow()!=this.getRow()) {
            System.out.println("error!");
            return null;
        }
        double[][] K = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                K[i][j] = M[i][j]+N.getM()[i][j];
            }
        }
        return K;
    }
    //判断矩阵是否相等
    public double[][] matrixMultiy(Matrix N){
        if(N.getCol()!=this.getCol()||N.getRow()!=this.getRow()) {
            System.out.println("error!");
            return null;
        }
        double[][] K = new double[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                K[i][j] = M[i][j]-N.getM()[i][j];
            }
        }
        return K;
    }
    public boolean equals(Matrix matrix){
        if(matrix.getCol()==this.col&&matrix.getRow()==this.row&&Arrays.deepEquals(matrix.getM(), this.M)) return true;
        else return false;
    }
}

class MatrixTest{
    public static void main(String[] args) {

        double[][] C = {{1, 1}, {1, 1}};
        double[][] D = {{1, 2}, {1, 2}};
        double[][] E = {{1,2,6},{1,5,7},{0,9,7}};
        double[][] F = {{6,7,8},{-1,2,5},{9,7,6}};

        Matrix A = new Matrix(2, 2);
        Matrix B = new Matrix(2, 2);
        Matrix K = new Matrix(2, 2);
        Matrix T = new Matrix(3,3);
        Matrix U =new Matrix(3,3);

        A.setM(C);
        B.setM(C);
        K.setM(D);
        T.setM(E);
        U.setM(F);

        System.out.println("A="+A.toString());
        System.out.println("B="+B.toString());
        System.out.println("K="+K.toString());
        System.out.println("T="+T.toString());
        System.out.println("U="+U.toString());
        //这里的矩阵用数组进行表示，直接调用数组的toString方法
        System.out.print("A+B=");
        if(A.matrixAdd(B)!=null) System.out.println(Arrays.deepToString(A.matrixAdd(B)));
        System.out.print("A+U=");
        if(A.matrixAdd(U)!=null) System.out.println(Arrays.deepToString(A.matrixAdd(U)));
        System.out.print("T+U=");
        if(T.matrixAdd(U)!=null) System.out.println(Arrays.deepToString(T.matrixAdd(U)));
        System.out.print("A-B=");
        if(A.matrixMultiy(B)!=null) System.out.println(Arrays.deepToString(A.matrixMultiy(B)));
        System.out.print("T-U=");
        if(T.matrixMultiy(U)!=null) System.out.println(Arrays.deepToString(T.matrixMultiy(U)));
        System.out.println("T、U是否相等："+T.equals(U));
        System.out.println("A、B是否相等：" + A.equals(B));
        System.out.println("A、K是否相等：" + A.equals(K));
    }
}
