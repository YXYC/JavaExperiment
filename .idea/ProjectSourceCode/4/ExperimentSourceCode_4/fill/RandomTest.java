package ExperimentSourceCode_4.fill;

import java.io.*;

public class RandomTest {
    public static void main(String args[]) throws Exception{
        File f = new File("src\\ExperimentSourceCode_4\\fill\\RandomTest.java");
        try {
            RandomAccessFile random = new RandomAccessFile(f, "r");
            long l = random.length();
            char ch;
            for (long i = l; i >= 0; i--) {
                random.seek(i);
                ch = (char) random.read();
                System.out.print(ch);
            }
            random.close();
        } catch (Exception e) {
            System.out.println("IOError!");
        }
    }
}

