//神奇公式欧拉函数 exp(ix)=cos(x)+i*sin(x)展示
public class Shiyan2_3_3 {

	public static void main(String[] args) {
		//输出区间[0,PI/2]内的欧拉函数值，用角度，步进1度
        for(int x=0;x<=90;x++) {
        	System.out.printf("exp(i*%d)=%.4f+i*%.4f\n",x,Math.cos(Math.PI/180*x),Math.sin(Math.PI/180*x));
        }
	}

}
