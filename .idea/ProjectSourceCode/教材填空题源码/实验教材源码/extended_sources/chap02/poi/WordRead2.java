package chap09.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;

public class WordRead2 {
	   public static void readDoc2(InputStream is) throws IOException {
	        HWPFDocument doc=new HWPFDocument(is);
	        Range r=doc.getRange();
	        for(int x=0;x<r.numSections();x++){
	            Section s=r.getSection(x);
	            for(int y=0;y<s.numParagraphs();y++){
	                Paragraph p=s.getParagraph(y);
	                for(int z=0;z<p.numCharacterRuns();z++){
	                    CharacterRun run=p.getCharacterRun(z);
	                    String text=run.text();
	                    System.out.print(text);
	                }
	            }
	        }
	    }
	   public static void main(String[] args) {
	        File file = new File("d://temp//majun.doc");
	        try {
	            FileInputStream fin = new FileInputStream(file);
	            fin = new FileInputStream(file);
	            readDoc2(fin);
	            fin.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
