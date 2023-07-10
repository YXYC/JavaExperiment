package ExperimentSourceCode_4.MatrixIO;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public abstract class Matrix {


    public Matrix() {
    }//构造方法

    //输入对应参数的方法，定义为抽象==>创建或初始化矩阵的方法
    public abstract void Matrix_IO(File fp) throws IOException;

    public abstract void Matrix_IO(int row, int col,double [][] m);

    public abstract int getCol();

    public abstract int getRow();

    public abstract double[][] getM();

    //为整个矩阵赋值
    public abstract void setM(double[][] m);

    //修改矩阵中某个值
    public abstract void setN(int a, int b, int N);

    @Override
    //输出整个矩阵
    public abstract String toString();

    //输出矩阵中的某个数
    public abstract double getN(int a, int b);

    //加法
    public abstract double[][] matrixAdd(Matrix_File N);

    //减法
    public abstract double[][] matrixSub(Matrix_File N);

    //判断矩阵是否相等
    public abstract boolean equals(Matrix_File matrix);
}
