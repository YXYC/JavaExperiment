package ExperimentSourceCode_4.MatrixIO;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix_File extends Matrix {

    private int row;//行
    private int col;//列
    private double[][] M;

    //文件初始化
    @Override
    public void Matrix_IO(File fp) throws IOException {
        Scanner read = null;
        try{
            read = new Scanner(fp);
            row = read.nextInt();
            col = read.nextInt();
            M = new double[row][col];
            for(int i=0;i<row;i++){
                for (int j=0;j<col;j++){
                    M[i][j] = read.nextDouble();
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("未找到该文件！");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("文件内矩阵数据数量有误！");
        }catch (ArithmeticException e){
            System.out.println("文件内矩阵数据内容有误！");
        }finally {
            read.close();
        }//关闭文件流
    }

    //直接初始化
    @Override
    public void Matrix_IO(int row, int col,double[][] m) {
        this.col=col;
        this.row=row;
        M = new double[row][col];
        M = m;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public double[][] getM() {
        return M;
    }

    //为整个矩阵赋值
    public void setM(double[][] m) {
        M = m;
    }

    //修改矩阵中某个值
    public void setN(int a, int b, int N) {
        M[a][b] = N;
    }

    @Override
    //输出整个矩阵
    public String toString() {
        return "NO1.Matrix{" +
                Arrays.deepToString(M) +
                '}';
    }

    //输出矩阵中的某个数
    public double getN(int a, int b) {
        return M[a - 1][b - 1];
    }

    //加法
    public double[][] matrixAdd(Matrix_File N) {
        double[][] K = new double[row][col];
        try {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    K[i][j] = M[i][j] + N.getM()[i][j];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("矩阵运算规则错误！");
        }//两数组行列数不同->会发生数组下标越界的错误
        return K;
    }

    //减法
    public double[][] matrixSub(Matrix_File N) {
        double[][] K = new double[row][col];
        try {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    K[i][j] = M[i][j] - N.getM()[i][j];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("矩阵运算规则错误！");
        }
        return K;
    }

    //判断矩阵是否相等
    public boolean equals(Matrix_File matrix) {
        if (matrix.getCol() == this.col && matrix.getRow() == this.row && Arrays.deepEquals(matrix.getM(), this.M))
            return true;
        else return false;
    }
}

class MatrixIOTest{
    public static void main(String[] args) {

        double[][] C = {{1, 1}, {1, 1}};
        double[][] D = {{1, 2}, {1, 2}};
        double[][] E = {{1,2,6},{1,5,7},{0,9,7}};
        double[][] F = {{6,7,8},{-1,2,5},{9,7,6}};

        Matrix_File A = new Matrix_File();
        A.Matrix_IO(2,2,C);
        Matrix_File B = new Matrix_File();
        B.Matrix_IO(2,2,C);
        Matrix_File K = new Matrix_File();
        K.Matrix_IO(2,2,D);
        Matrix_File T = new Matrix_File();
        T.Matrix_IO(3,3,E);
        Matrix_File U = new Matrix_File();
        U.Matrix_IO(3,3,F);

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
        if(A.matrixSub(B)!=null) System.out.println(Arrays.deepToString(A.matrixSub(B)));
        System.out.print("T-U=");
        if(T.matrixSub(U)!=null) System.out.println(Arrays.deepToString(T.matrixSub(U)));
        System.out.println("T、U是否相等："+T.equals(U));
        System.out.println("A、B是否相等：" + A.equals(B));
        System.out.println("A、K是否相等：" + A.equals(K));
    }
}

class MatrixIOTest_File{
    public static void main(String[] args) throws IOException {

        File fp =null;
        Matrix_File A = new Matrix_File();
        fp = new File("src\\ExperimentSourceCode_4\\FileData\\MatrixDataC.txt");
        A.Matrix_IO(fp);

        Matrix_File B = new Matrix_File();
        fp = new File("src\\ExperimentSourceCode_4\\FileData\\MatrixDataC.txt");
        B.Matrix_IO(fp);

        Matrix_File K = new Matrix_File();
        fp = new File("src\\ExperimentSourceCode_4\\FileData\\MatrixDataD.txt");
        K.Matrix_IO(fp);

        Matrix_File T = new Matrix_File();
        fp = new File("src\\ExperimentSourceCode_4\\FileData\\MatrixDataE.txt");
        T.Matrix_IO(fp);

        Matrix_File U = new Matrix_File();
        fp = new File("src\\ExperimentSourceCode_4\\FileData\\MatrixDataF.txt");
        U.Matrix_IO(fp);

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
        if(A.matrixSub(B)!=null) System.out.println(Arrays.deepToString(A.matrixSub(B)));
        System.out.print("T-U=");
        if(T.matrixSub(U)!=null) System.out.println(Arrays.deepToString(T.matrixSub(U)));
        System.out.println("T、U是否相等："+T.equals(U));
        System.out.println("A、B是否相等：" + A.equals(B));
        System.out.println("A、K是否相等：" + A.equals(K));
    }
}