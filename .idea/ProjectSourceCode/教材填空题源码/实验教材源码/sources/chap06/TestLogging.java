package chap07;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TestLogging {
	public static void main(String[] args) throws IOException {
		Logger log = Logger.getLogger("javasoft");
		log.setLevel(Level.INFO);
		Logger log1 = Logger.getLogger("javasoft");
		System.out.println(log == log1);// true
		Logger log2 = Logger.getLogger("javasoft.blog");
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINE);
		log1.addHandler(consoleHandler);
		FileHandler fileHandler = new FileHandler("d:/temp/testlog%g.log");
		log2.addHandler(fileHandler);
		log2.setLevel(Level.FINEST);
		log1.severe("����");
		log1.warning("����");
		log1.info("��Ϣ");
		log1.config("����");
		log1.fine("����");
		log1.finer("�Ϻ�");
		log1.finest("���");
		log2.severe("����");
		log2.warning("����");
		log2.info("��Ϣ");
		log2.config("����");
		log2.fine("����");
		log2.finer("�Ϻ�");
		log2.finest("���");
	}
}