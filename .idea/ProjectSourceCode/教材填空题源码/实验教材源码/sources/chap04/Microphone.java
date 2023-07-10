//Microphone.java
public class Microphone {
      private String brand;
      public Microphone(String brand) {
         this.brand=brand;
     }
	public byte[] getData() {
		String msg="在"+brand+"麦克风上获取的音频数据流";
		return msg.getBytes();
	}
}
