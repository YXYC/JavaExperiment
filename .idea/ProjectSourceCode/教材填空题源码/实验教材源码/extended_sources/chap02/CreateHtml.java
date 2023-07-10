package chap09;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateHtml {

	public static String repeat(String ch,int m) {
		StringBuffer sb=new StringBuffer();
		for(int i=1;i<=m;i++) {
			sb.append(ch);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriter fw = null;
		BufferedWriter bw = null;
		Scanner keyin=new Scanner(System.in);
		System.out.print("请输入要显示菱形的行数：");
		int row=keyin.nextInt();
		try {
			fw = new FileWriter("d://temp//lingxing.html");
			bw = new BufferedWriter(fw);
			bw.write("<html><body><h1><center>菱形</center</h1><hr><h3>");
		     for(int i=1;i<=(int)(row/2.0+0.5);i++)
		      {
		         bw.write(repeat("&nbsp;",row/2+1));
		         bw.write(repeat("*",2*i-1));
		         bw.write("<br>");
		      }
		      for(int i=row/2;i>=1;i--)
		      {
			         bw.write(repeat("&nbsp;",row/2+1));
			         bw.write(repeat("*",2*i-1));
			         bw.write("<br>");
		      }
			bw.write("</h3></body></html>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("lingxing.html生成完毕！");

	}

}
