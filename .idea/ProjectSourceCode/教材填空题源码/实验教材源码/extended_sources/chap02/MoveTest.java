import java.io.*;
import java.nio.file.*;
class MoveTest {
    public static void main(String... args){
        Path source=Paths.get("./horsebak.jpg");
        Path target=Paths.get("f:/tmp/horsebak.jpg");
        try {
            Files.move(source,target,StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("The file horse.jpg has moved to f:/tmp/horsebak.jpg!");
    }
}