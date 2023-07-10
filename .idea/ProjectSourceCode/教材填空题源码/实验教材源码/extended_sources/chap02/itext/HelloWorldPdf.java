package chap09.itext;
//https://link.csdn.net/?target=http%3A%2F%2Fitextpdf.com%2Fbook%2Fexamples.php
//https://link.csdn.net/?target=http%3A%2F%2Fitextpdf.com%2F
//https://github.com/itext/itext7
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class HelloWorldPdf {
    public static final String RESULT = "d://temp//hello.pdf";
    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new HelloWorldPdf().createPdf(RESULT);
    }

    public void createPdf(String filename)	throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.add(new Paragraph("Hello World!"));
        document.add(new Paragraph("sqrt(2.0)="+Math.sqrt(2.0)));
        Date time=new Date();
        Paragraph p=new Paragraph("The time is:"+time);
        document.add(p);
        document.close();
    }
}
