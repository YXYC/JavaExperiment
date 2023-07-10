import java.io.*;
class LineCopy{
  public static void main(String args[]){
    int i=1;
    try{
       FileReader fis=new FileReader(args[0]);
       FileWriter fout=new FileWriter(args[1]);
       BufferedReader reader = new BufferedReader(fis);
       BufferedWriter bout=new BufferedWriter(fout);
       String s;
       while( (s = reader.readLine()) != null ){  
            bout.write(""+(i++)+": "+s);
            bout.newLine();
       }
       reader.close();
       fis.close();
       bout.close();
       fout.close();
    }catch(IOException e){
       System.out.println(e);
    }
  }
}