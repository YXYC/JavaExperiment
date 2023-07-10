package red.star;
import java.io.*;
public class ComputerCount {
    int number=0; 
    public synchronized void addCount() {
        number++;
    } 
    public int getNumber(){
        return number;
    }
}
