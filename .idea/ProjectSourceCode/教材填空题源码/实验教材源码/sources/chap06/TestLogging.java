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
		log1.severe("严重");
		log1.warning("警告");
		log1.info("信息");
		log1.config("配置");
		log1.fine("良好");
		log1.finer("较好");
		log1.finest("最好");
		log2.severe("严重");
		log2.warning("警告");
		log2.info("信息");
		log2.config("配置");
		log2.fine("良好");
		log2.finer("较好");
		log2.finest("最好");
	}
}