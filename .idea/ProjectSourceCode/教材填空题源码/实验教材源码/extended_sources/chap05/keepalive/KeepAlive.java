package net.keepalive;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//ά�����ӵ���Ϣ������������
public class KeepAlive implements Serializable{
   @Override
   public String toString() {
       return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\tά�����Ӱ�";
   }

}