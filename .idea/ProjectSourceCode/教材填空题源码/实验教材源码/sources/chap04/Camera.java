//Camera.java
public class Camera {
    private String brand;
    public Camera(String brand) {
    	this.brand=brand;
    }
	public byte[] getData() {
		String data="从"+brand+"摄像头上获取的视频字节流";
		return data.getBytes(); //返回字符串data的字节数组
	} 
}
