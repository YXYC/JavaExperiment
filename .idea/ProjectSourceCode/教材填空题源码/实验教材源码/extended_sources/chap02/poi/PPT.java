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
    //ֱ�ӳ�ȡ�õ�Ƭ��ȫ������
    public static String readDoc1(InputStream is) throws IOException{
        PowerPointExtractor extractor=new PowerPointExtractor(is);
        return extractor.getText();
    }

    //һ�Żõ�Ƭһ�Żõ�Ƭ�ض�ȡ
    public static void readDoc2(InputStream is) throws IOException{
        SlideShow ss=new SlideShow(new HSLFSlideShow(is));
        Slide[] slides=ss.getSlides();
        for(int i=0;i<slides.length;i++){
            //��ȡһ�Żõ�Ƭ�ı���
            String title=slides[i].getTitle();
            System.out.println("����:"+title);
            //��ȡһ�Żõ�Ƭ������(��������)
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