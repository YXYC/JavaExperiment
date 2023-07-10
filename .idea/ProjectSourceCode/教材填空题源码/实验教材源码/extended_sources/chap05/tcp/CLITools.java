package net.tcp;

import java.util.Scanner;

public class CLITools {
    public static void pause() {
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("#请使用netstat -an命令查看tcp连接状态，并按回车键继续。。。");
    	scanner.hasNextLine();
    }
}
