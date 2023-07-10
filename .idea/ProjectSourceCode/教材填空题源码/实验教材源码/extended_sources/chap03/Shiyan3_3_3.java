import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
class MyNotepad extends JFrame {
    private JTextArea editText;
    File fOpen=null;
    String clipBoard="";//粘贴版
    public MyNotepad(){
        super("我的简单记事本");
        setSize(600,400);
        Container cp=getContentPane();

        JMenuBar mb=new JMenuBar(); //创建一个菜单栏对象
        setJMenuBar(mb);  
        editText=new JTextArea();
        editText.setTabSize(4);//设置Tab键的列位
        editText.setEditable(true); //设置文本区域可编辑
        JScrollPane scroll=new JScrollPane(editText);  //在文本框中加入滚动条
        scroll.setAutoscrolls(true);
        cp.add(scroll);
        JMenu fileMenu=new JMenu("文件");
        JMenu editMenu=new JMenu("编辑");
        JMenu colorMenu=new JMenu("文本颜色"); 
        JMenu helpMenu=new JMenu("帮助");
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(colorMenu);
        mb.add(helpMenu);
        JMenuItem mFile1=new JMenuItem("打开");
        JMenuItem mFile2=new JMenuItem("保存");
        JMenuItem mFile3=new JMenuItem("另存为");
        JMenuItem mFile4=new JMenuItem("退出");
        JMenuItem mEdit1=new JMenuItem("复制");
        JMenuItem mEdit2=new JMenuItem("剪切");
        JMenuItem mEdit3=new JMenuItem("粘贴");
        JMenuItem menuRed=new JMenuItem("红色");
        JMenuItem menuGreen=new JMenuItem("绿色");
        JMenuItem menuBlue=new JMenuItem("蓝色");
        JMenuItem menuCust=new JMenuItem("自定义");
        fileMenu.add(mFile1);
        fileMenu.add(mFile2);
        fileMenu.add(mFile3);
        fileMenu.add(mFile4);
        editMenu.add(mEdit1);
        editMenu.add(mEdit2);
        editMenu.add(mEdit3);
        colorMenu.add(menuRed);
        colorMenu.add(menuGreen);
        colorMenu.add(menuBlue);
        colorMenu.add(menuCust);
        mFile1.addActionListener(new OpenListener());
        mFile2.addActionListener(new SaveListener());
        mFile3.addActionListener(new SaveAsListener());
        mFile4.addActionListener(new ExitListener());
        mEdit1.addActionListener(new CopyListener());
        mEdit2.addActionListener(new CutListener());
        mEdit3.addActionListener(new PasteListener());
        menuRed.addActionListener(new ColorListener());
        menuBlue.addActionListener(new ColorListener());
        menuGreen.addActionListener(new ColorListener());
        menuCust.addActionListener(new ColorListener());
        addWindowListener(new WindowDestroyer());
    }
    class ColorListener implements ActionListener{
        public void actionPerformed(ActionEvent e) { 
             if(e.getActionCommand().equals("自定义")) {
                  Color newColor=JColorChooser.showDialog(null,"调色板",editText.getForeground());
                  editText.setForeground(newColor); 
             }
             if(e.getActionCommand().equals("红色")){
                  editText.setForeground(Color.red);
             }
             if(e.getActionCommand().equals("蓝色")){
                  editText.setForeground(Color.blue);
             }
             if(e.getActionCommand().equals("绿色")){
                  editText.setForeground(Color.green);
             }
        }
    }
    class OpenListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fileSelection=new JFileChooser(); //创建一个文件选择器对象
            int res=fileSelection.showOpenDialog(null);//显示“打开”对话框
            if(res==JFileChooser.CANCEL_OPTION){
                return;   //如果用户取消对话框，则返回
            }else{
                try {
                    fOpen=fileSelection.getSelectedFile(); //返回用户选择的文件信息
                    FileReader fr=new FileReader(fOpen); //打开文件
                    BufferedReader in=new BufferedReader(fr);
                    String str;
                    editText.setText("");
                    str=in.readLine();
                    while(str!=null){
                        editText.append(str+"\n");
                        str=in.readLine();
                    }
                    in.close();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }
    class SaveAsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fileSelection=new JFileChooser();
            int res=fileSelection.showSaveDialog(null); //打开“保存”对话框
            if(res==JFileChooser.CANCEL_OPTION){
                return ;
            }else{
                try {
                    File fSaveAs=fileSelection.getSelectedFile();
                    FileWriter fw=new FileWriter(fSaveAs);
                    PrintWriter out=new PrintWriter(fw);
                    editText.selectAll();  //选择文本框中所有文本
                    String str=editText.getSelectedText();
                    out.print(str);
                    fOpen=fSaveAs;  //设置为当前打开文件
                    out.close();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    }
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(fOpen!=null){ //利用当前打开文件保存
                try {
                    FileWriter fw=new FileWriter(fOpen);
                    PrintWriter out=new PrintWriter(fw);
                    editText.selectAll();
                    String str=editText.getSelectedText();
                    out.print(str);
                    out.close();
                }catch(Exception e3){
                    e3.printStackTrace();
                }
            }
        }
    }
    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    class CutListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int st=editText.getSelectionStart();  //返回选择文本的开始位置
            int end=editText.getSelectionEnd();  //返回选择文本的结尾位置
            editText.replaceRange("",st,end);
        }
    }
    class CopyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            clipBoard=editText.getSelectedText();
        }
    }
    class PasteListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int pos=editText.getSelectionStart();
            editText.insert(clipBoard,pos);
        }
    }
}
public class Shiyan3_3_3{
    public static void main(String[] args){
        MyNotepad win=new MyNotepad();
        win.setVisible(true);
    }
}        
