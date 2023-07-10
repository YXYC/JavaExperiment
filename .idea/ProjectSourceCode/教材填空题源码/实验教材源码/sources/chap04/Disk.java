//Disk.java
import java.io.FileOutputStream;
import java.io.IOException;
public class Disk {
    private String brand;
    public Disk(String brand) {
    	this.brand=brand;
    }
	public void saveData(byte[] data) {
		try {
			FileOutputStream fout=new FileOutputStream(""+brand+".dat",true);
			fout.write(data);
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
