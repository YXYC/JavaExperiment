package chap09.poi;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
//https://www.w3cschool.cn/apache_poi_word/
public class WordRead1 {
    // 直接抽取全部内容
    public static String readDoc1(InputStream is) throws IOException {
        WordExtractor extractor = new WordExtractor(is);
        return extractor.getText();
    }

    public static void main(String[] args) {
        File file = new File("d://temp//majun.doc");
        try {
            FileInputStream fin = new FileInputStream(file);
            String cont = readDoc1(fin);
            System.out.println(cont);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}