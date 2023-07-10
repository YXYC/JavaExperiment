package ExperimentSourceCode_9.BANK.BankClient.Client.File;
//https://link.csdn.net/?target=http%3A%2F%2Fitextpdf.com%2Fbook%2Fexamples.php
//https://link.csdn.net/?target=http%3A%2F%2Fitextpdf.com%2F
//https://github.com/itext/itext7

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.report;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ExportPdf {
    public static final String RESULT = "d://temp//year.pdf";
    public static void main(String[] args)
            throws Exception {
        new ExportPdf().createPdf(RESULT);
    }

    public void createPdf(String filename) throws Exception {
        Request request = new Request();
        C_Connect connect = new C_Connect();
        request.setA("get_pdf");
        request=connect.in_out(request);
        List<report> list =new ArrayList<>();
        list =request.getReports();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        for(int i=0;i< list.size();i++)
        {
            document.add(new Paragraph("Current year:"+list.get(i).getYear()));
            document.add(new Paragraph("Total number of accounts:"+list.get(i).getCount()));
            document.add(new Paragraph("Total balance:"+list.get(i).getAll_balance()));
            document.add(new Paragraph("Total number of accounts opened:"+list.get(i).getNewuser()));
            document.add(new Paragraph("Total number of account cancellation:"+list.get(i).getDieuser()));
        }
        Date time=new Date();
        Paragraph p=new Paragraph("The time is:"+time);
        document.add(p);
        document.close();
    }
}


