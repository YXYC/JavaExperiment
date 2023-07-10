package red.star;
import java.io.*; 
public class Play {
    String pictureName[];  //存放全部图片文件名字的数组。
    String showImage;     //存放当前要显示的图片。
    String webDir="";     //web服务目录的名字，例如ch5
    String tomcatDir;     //tomcat的安装目录,例如apache-tomcat-9.0.26。
    int index = 0;        //存放图片文件的序号
    public Play() {
        File f= new File(""); //该文件认为在Tomcat服务器启动的目录中即bin目录中。
        String path = f.getAbsolutePath();
        int index = path.indexOf("bin");//bin是tomcat的安装目录下的子目录。
        tomcatDir = path.substring(0,index); //得到tomcat的安装目录的名字。
    }
    public void setWebDir(String s) {
        webDir = s;
        File dirImage = new File(tomcatDir+"/webapps/"+webDir+"/image"); 
        pictureName = dirImage.list();
    }
    public String getShowImage() {
       showImage = pictureName[index];
       return showImage; 
    } 
    public void setIndex(int i) {
       index = i; 
       if(index>=pictureName.length)
          index = 0;
       if(index < 0)
          index = pictureName.length-1;
    }
    public int getIndex() {
        return  index ; 
    }  
}
