//TestUnion.java
public class TestUnion {
    public static void main(String[] args) {
    	Computer1 mycomputer=new Computer1("联系昭阳450电脑");
    	Printer myprinter=new Printer("Brother DCP-7057打印机");
    	Camera mycamera=new Camera("奥尼剑影摄像头");
    	SoundBox mysound= new SoundBox("好牧人V8音箱");//创建一个"好牧人V8音箱"
    	Microphone mymc=new Microphone("飞利浦麦克风");
        mycomputer.playMusic(mysound);  // 通过音箱播放音乐	
    	mycomputer.saveData(mycamera. getData());//将摄像头输入的数据写到磁盘中
    	mycomputer.print(myprinter, "摄像头输入的数据被保存到磁盘上了！");
    	mycomputer.saveData(mymc.getData()); 
    	mycomputer.print(myprinter, "麦克风输入的数据也被保存到磁盘上了！");	
    }
}
