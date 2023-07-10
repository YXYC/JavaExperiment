import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
class MyNotepad extends JFrame {
    private JTextArea editText;
    File fOpen=null;
    String clipBoard="";//ճ����
    public MyNotepad(){
        super("�ҵļ򵥼��±�");
        setSize(600,400);
        Container cp=getContentPane();

        JMenuBar mb=new JMenuBar(); //����һ���˵�������
        setJMenuBar(mb);  
        editText=new JTextArea();
        editText.setTabSize(4);//����Tab������λ
        editText.setEditable(true); //�����ı�����ɱ༭
        JScrollPane scroll=new JScrollPane(editText);  //���ı����м��������
        scroll.setAutoscrolls(true);
        cp.add(scroll);
        JMenu fileMenu=new JMenu("�ļ�");
        JMenu editMenu=new JMenu("�༭");
        JMenu colorMenu=new JMenu("�ı���ɫ"); 
        JMenu helpMenu=new JMenu("����");
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(colorMenu);
        mb.add(helpMenu);
        JMenuItem mFile1=new JMenuItem("��");
        JMenuItem mFile2=new JMenuItem("����");
        JMenuItem mFile3=new JMenuItem("���Ϊ");
        JMenuItem mFile4=new JMenuItem("�˳�");
        JMenuItem mEdit1=new JMenuItem("����");
        JMenuItem mEdit2=new JMenuItem("����");
        JMenuItem mEdit3=new JMenuItem("ճ��");
        JMenuItem menuRed=new JMenuItem("��ɫ");
        JMenuItem menuGreen=new JMenuItem("��ɫ");
        JMenuItem menuBlue=new JMenuItem("��ɫ");
        JMenuItem menuCust=new JMenuItem("�Զ���");
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
             if(e.getActionCommand().equals("�Զ���")) {
                  Color newColor=JColorChooser.showDialog(null,"��ɫ��",editText.getForeground());
                  editText.setForeground(newColor); 
             }
             if(e.getActionCommand().equals("��ɫ")){
                  editText.setForeground(Color.red);
             }
             if(e.getActionCommand().equals("��ɫ")){
                  editText.setForeground(Color.blue);
             }
             if(e.getActionCommand().equals("��ɫ")){
                  editText.setForeground(Color.green);
             }
        }
    }
    class OpenListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fileSelection=new JFileChooser(); //����һ���ļ�ѡ��������
            int res=fileSelection.showOpenDialog(null);//��ʾ���򿪡��Ի���
            if(res==JFileChooser.CANCEL_OPTION){
                return;   //����û�ȡ���Ի����򷵻�
            }else{
                try {
                    fOpen=fileSelection.getSelectedFile(); //�����û�ѡ����ļ���Ϣ
                    FileReader fr=new FileReader(fOpen); //���ļ�
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
            int res=fileSelection.showSaveDialog(null); //�򿪡����桱�Ի���
            if(res==JFileChooser.CANCEL_OPTION){
                return ;
            }else{
                try {
                    File fSaveAs=fileSelection.getSelectedFile();
                    FileWriter fw=new FileWriter(fSaveAs);
                    PrintWriter out=new PrintWriter(fw);
                    editText.selectAll();  //ѡ���ı����������ı�
                    String str=editText.getSelectedText();
                    out.print(str);
                    fOpen=fSaveAs;  //����Ϊ��ǰ���ļ�
                    out.close();
                }catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    }
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(fOpen!=null){ //���õ�ǰ���ļ�����
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
            int st=editText.getSelectionStart();  //����ѡ���ı��Ŀ�ʼλ��
            int end=editText.getSelectionEnd();  //����ѡ���ı��Ľ�βλ��
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
