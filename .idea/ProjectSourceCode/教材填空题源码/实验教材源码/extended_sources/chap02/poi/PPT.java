package chap09.poi;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PPT {
    //直接抽取幻灯片的全部内容
    public static String readDoc1(InputStream is) throws IOException{
        PowerPointExtractor extractor=new PowerPointExtractor(is);
        return extractor.getText();
    }

    //一张幻灯片一张幻灯片地读取
    public static void readDoc2(InputStream is) throws IOException{
        SlideShow ss=new SlideShow(new HSLFSlideShow(is));
        Slide[] slides=ss.getSlides();
        for(int i=0;i<slides.length;i++){
            //读取一张幻灯片的标题
            String title=slides[i].getTitle();
            System.out.println("标题:"+title);
            //读取一张幻灯片的内容(包括标题)
            TextRun[] runs=slides[i].getTextRuns();
            for(int j=0;j<runs.length;j++){
                System.out.println(runs[j].getText());
            }
        }
    }

    public static void main(String[] args){
        File file = new File("/home/orisun/2.ppt");
        try{
            FileInputStream fin=new FileInputStream(file);
            String cont=readDoc1(fin);
            System.out.println(cont);
            fin.close();
            fin=new FileInputStream(file);
            readDoc2(fin);
            fin.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}