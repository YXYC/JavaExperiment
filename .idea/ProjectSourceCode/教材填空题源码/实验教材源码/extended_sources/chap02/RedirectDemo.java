import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
  public class RedirectDemo {
     public static void main(String[] args) throws FileNotFoundException {
         // 保存最原始的输入输出流
         InputStream in = System.in;
         PrintStream out = System.out;
         // 将标准输入流重定向至 in.txt
         System.setIn(new FileInputStream("in.txt"));
         System.setOut(new PrintStream("out.txt"));
        // 将 in.txt中的数据输出到 out.txt中
		Scanner scanner = new Scanner(System.in);
         // 将标准输出流重定向至 out.txt
         while (scanner.hasNextLine()) {
             String str = scanner.nextLine();
             System.out.println(str);
         }
         // 将标准输出流重定向至控制台
         System.setIn(in);
         // 将标准输出流重定向至控制台
         System.setOut(out);
         scanner = new Scanner(System.in);
		System.out.println("输入输出流已经恢复，请输入字符串:");
         String string = scanner.nextLine();
         System.out.println(string);
		
     }
 }