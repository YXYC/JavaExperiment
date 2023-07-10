import java.text.DecimalFormat;
import java.time.Year;
import java.util.Scanner;

class Matrix{
    private int row,col;
    double [][]Mat;
    public  Matrix(int r,int l,double [][]M){
            if(r>=0&&l>=0) {
                this.row = r;
                this.col = l;
                this.Mat = M;
            }
            else
                System.out.println("行列大小错误，请重试");
    }

    public double getMat(int r,int l){
        if(r>row||l>col) {
            System.out.println("行列大小错误，请重试");
            return 0.0;
        }
        else
            return Mat[r-1][l-1];
    }
    public double[][] getMat() {
        if(row==0||col==0){
            System.out.println("矩阵未初始化！");
            return null;
        }
        else
            return Mat;
    }

    public void setMat(int r, int l, double v){
        if(r>row||l>col)
            System.out.println("行列大小错误，请重试");
        else
            this.Mat[r-1][l-1]=v;
    }
    public void setMat(double [][]M){
        this.Mat=M;
    }

    public int getCol() {
        return col;
    }
    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        if(col<=0)
            System.out.println("行列大小错误，请重试");
        else
            this.col = col;
    }
    public void setRow(int row) {
        if(row<=0)
            System.out.println("行列大小错误，请重试");
        else
            this.row = row;
    }

    boolean equals(double [][]M){
        if(M.length!=Mat.length)
        return false;
        else{
            for(int i=0;i<Mat.length;i++){
                if(M[i].length!=Mat[i].length){
                    return false;
                }
                else{
                    for(int j=0;j<Mat[i].length;j++){
                        if(M[i][j]!=Mat[i][j])
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0");
        String result = "";

        for(int i = 0;i<this.row;i++) {
            result += df.format(Mat[i][0]);
            for(int j = 1;j<this.col;j++) {
                result += " " + df.format(Mat[i][j]);
            }
            result +=  "\n";
        }
        return result;
    }

    public Matrix add(Matrix b) {
        if(this.row!= b.getRow() && this.col!= b.getCol()) {
            return null;
        }
        double add[][] = new double[this.row][this.col];
        for(int i = 0;i<col;i++) {
            for(int j = 0;j<row;j++) {
                add[i][j] = this.Mat[i][j] + b.Mat[i][j];
            }
        }
        Matrix next = new Matrix(this.col,this.row,add);
        System.out.println("加法完成");
        return next;
    }

    public Matrix sub(Matrix b) {
        if(this.row!= b.getRow() && this.col!= b.getCol()) {
            return null;
        }
        double add[][] = new double[this.row][this.col];
        for(int i = 0;i<col;i++) {
            for(int j = 0;j<row;j++) {
                add[i][j] = this.Mat[i][j] - b.Mat[i][j];
            }
        }
        Matrix next = new Matrix(this.col,this.row,add);
        System.out.println("减法完成");
        return next;
    }

}

public class MatrixCal {
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        int row = key.nextInt();
        int col = key.nextInt();
        double Mat[][] = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                double d = key.nextDouble();
                Mat[i][j] = d;
            }
        }
        Matrix a = new Matrix(row, col, Mat);
        int r = key.nextInt();
        int c = key.nextInt();
        double M[][] = new double[r][c];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                double d = key.nextDouble();
                M[i][j] = d;
            }
        }
        Matrix b = new Matrix(r, c, M);
        Matrix ad = a.add(b);
        System.out.println(ad.toString());
        Matrix su = a.sub(b);
        System.out.println(su.toString());
    }
}
