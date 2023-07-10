import ______________________;
import java.util.LinkedList;
public class TestQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("Hello");
        queue.offer("World!");
        queue.________________("你好！");
        System.out.println(___________________);//打印出队列中对象的个数
        String str;
        while((str=queue.________)!=null){
            System.out.print(str);
        }
        System.out.println();
        System.out.println(queue.size());
    }
}