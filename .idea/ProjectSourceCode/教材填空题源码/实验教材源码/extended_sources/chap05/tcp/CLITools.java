package net.tcp;

import java.util.Scanner;

public class CLITools {
    public static void pause() {
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("#��ʹ��netstat -an����鿴tcp����״̬�������س�������������");
    	scanner.hasNextLine();
    }
}
