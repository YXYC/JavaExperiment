package net.keepalive;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//维持连接的消息对象（心跳对象）
public class KeepAlive implements Serializable{
   @Override
   public String toString() {
       return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t维持连接包";
   }

}