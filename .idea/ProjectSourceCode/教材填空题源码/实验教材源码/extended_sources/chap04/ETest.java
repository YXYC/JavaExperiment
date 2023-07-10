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
      super("完型填空");
      scoreShowing=new JTextField(10);
      testContent=new JTextArea(6,10);
      testContent.setLineWrap(true);
      restart=new JButton("重新练习");
      restart.addActionListener(this);// 将当前窗口注册为restart的ActionEvent事件监视器
      next=new JButton("下一题目");
      next.addActionListener(this); // 将当前窗口注册为next 的ActionEvent事件监视器
      ansButton=new JRadioButton[4];
      for(int i=0;i<=3;i++)
       {  
          ansButton[i]=new  JRadioButton("***");
          ansButton[i].addItemListener(this);     // 将当前窗口注册为ansButton[i]的ItemEvent事件监视器
       } 
      try
       {  
          file=new FileReader("English.txt");
          in=new BufferedReader(file);
       }
      catch(IOException e){}   
      Box boxV=Box.createVerticalBox();
      boxV.add(new JLabel("题目:"));
      boxV.add(new JScrollPane(testContent));
      Box boxH1=Box.createHorizontalBox();
      boxH1.add(new JLabel("选择：")); 
      for(int i=0;i<=3;i++)
           {  
              boxH1.add(ansButton[i]);
           } 
      Box boxH2=Box.createHorizontalBox();
      boxH2.add(new JLabel("您的得分:"));
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
                     testContent.setText("学习完毕"); 
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
              testContent.setText("无试题文件") ; 
           } 
   }
  public void actionPerformed(ActionEvent e)     // ActionListener接口方法的声明
   {  
       if(e.getSource()==restart)        // 判断事件源是restart
         {  
            score=0;
            scoreShowing.setText("得分： "+score);
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
      if(e.getSource()==next)        // 判断事件源是next
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
  public void itemStateChanged(ItemEvent e)  // ItemListener接口方法的声明
   { 
     for(int j=0;j<4;j++)
        { 
          if(ansButton[j].getText().equals(str[5])&&ansButton[j].isSelected())
             {  
                score++;
             }
          scoreShowing.setText("得分： "+score);
          ansButton[j].setEnabled(false); 
        }
   }
   public static void main(String args[])
   {  
       ETest w=new ETest();
   }
}
