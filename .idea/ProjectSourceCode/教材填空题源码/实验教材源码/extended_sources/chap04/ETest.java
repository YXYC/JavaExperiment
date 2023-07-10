import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class ETest extends JFrame implements ActionListener,ItemListener
{  
   String str[]=new String[6];
   String s;
   FileReader file; 
   BufferedReader in;  
   JButton restart,next;
   JRadioButton ansButton[];
   JTextArea  testContent;
   JTextField scoreShowing;
   int score=0; 
   ETest()
   {  
      super("�������");
      scoreShowing=new JTextField(10);
      testContent=new JTextArea(6,10);
      testContent.setLineWrap(true);
      restart=new JButton("������ϰ");
      restart.addActionListener(this);// ����ǰ����ע��Ϊrestart��ActionEvent�¼�������
      next=new JButton("��һ��Ŀ");
      next.addActionListener(this); // ����ǰ����ע��Ϊnext ��ActionEvent�¼�������
      ansButton=new JRadioButton[4];
      for(int i=0;i<=3;i++)
       {  
          ansButton[i]=new  JRadioButton("***");
          ansButton[i].addItemListener(this);     // ����ǰ����ע��ΪansButton[i]��ItemEvent�¼�������
       } 
      try
       {  
          file=new FileReader("English.txt");
          in=new BufferedReader(file);
       }
      catch(IOException e){}   
      Box boxV=Box.createVerticalBox();
      boxV.add(new JLabel("��Ŀ:"));
      boxV.add(new JScrollPane(testContent));
      Box boxH1=Box.createHorizontalBox();
      boxH1.add(new JLabel("ѡ��")); 
      for(int i=0;i<=3;i++)
           {  
              boxH1.add(ansButton[i]);
           } 
      Box boxH2=Box.createHorizontalBox();
      boxH2.add(new JLabel("���ĵ÷�:"));
      boxH2.add(scoreShowing);
      Box boxH3=Box.createHorizontalBox();
      boxH3.add(restart); 
      boxH3.add(next);
      Box boxBase=Box.createVerticalBox();
      boxBase.add(boxV); 
      boxBase.add(boxH1);
      boxBase.add(boxH2);
      boxBase.add(boxH3);
      Container con=getContentPane();
      con.add(boxBase,BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100,100,400,240); 
      setVisible(true);
      validate();
      reading();
   } 
   public void reading()
   {  
       int i=0; 
       try { 
             s=in.readLine().trim();
             if(!(s.startsWith("endend")))
                 { 
                    StringTokenizer tokenizer=new StringTokenizer(s,"#"); 
                    while(tokenizer.hasMoreTokens())
                        { 
                           str[i]=tokenizer.nextToken();
                           i++;
                        }
                     testContent.setText(str[0]);
                     for(int j=1;j<=4;j++)
                       {  
                         ansButton[j-1].setText(str[j]);
                       } 
                  }
              else if(s.startsWith("endend"))
                  {  
                     testContent.setText("ѧϰ���"); 
                     for(int j=0;j<4;j++)
                       {  
                         // ansButton[j].setText("***"); 
                          ansButton[j].setVisible(false);
                          in.close();
                          file.close();
                       } 
                  }
            }
         catch(Exception exp)
            {
              testContent.setText("�������ļ�") ; 
           } 
   }
  public void actionPerformed(ActionEvent e)     // ActionListener�ӿڷ���������
   {  
       if(e.getSource()==restart)        // �ж��¼�Դ��restart
         {  
            score=0;
            scoreShowing.setText("�÷֣� "+score);
            for(int j=0;j<4;j++){
              ansButton[j].setVisible(true);
            }
            try {  
                   file=new FileReader("English.txt");
                   in=new BufferedReader(file);
                }
            catch(IOException ee){}  
            reading(); 
         }
      if(e.getSource()==next)        // �ж��¼�Դ��next
         {  
            for(int j=0;j<4;j++)
             { 
                 ansButton[j].setEnabled(true);
                 ansButton[j].removeItemListener(this);
                 ansButton[j].setSelected(false);
                 ansButton[j].addItemListener(this);
             }
           reading();
         }
   }
  public void itemStateChanged(ItemEvent e)  // ItemListener�ӿڷ���������
   { 
     for(int j=0;j<4;j++)
        { 
          if(ansButton[j].getText().equals(str[5])&&ansButton[j].isSelected())
             {  
                score++;
             }
          scoreShowing.setText("�÷֣� "+score);
          ansButton[j].setEnabled(false); 
        }
   }
   public static void main(String args[])
   {  
       ETest w=new ETest();
   }
}
