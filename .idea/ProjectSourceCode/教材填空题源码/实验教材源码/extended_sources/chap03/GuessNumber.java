import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
class GuessNumber extends JFrame implements ActionListener { 
    int number;
    JTextField inputNumber;
    JLabel  feedBack;
    JButton buttonGetNumber,buttonEnter; 
    GuessNumber(String s) { 
      super(s);
      buttonGetNumber=new JButton("�õ�һ�������");
      feedBack=new JLabel ("�޷�����Ϣ",JLabel.CENTER);
      feedBack.setBackground(Color.green);
      inputNumber=new JTextField("0",5); 
      buttonEnter=new JButton("ȷ��"); 
      buttonEnter.addActionListener(this);    // ��ťbuttonEnter����ActionEvent�¼���������������Ϊ��ǰ����
      buttonGetNumber.addActionListener(this);   // ��ťbuttonGetNumber����ActionEvent�¼���������������Ϊ��ǰ����
      JPanel p=new JPanel();
      p.setLayout(new GridLayout(4,2));
      p.add(new JLabel("��ȡ1-100֮��������:"));
      p.add(buttonGetNumber);
      p.add(new JLabel("�������Ĳ²�:")); 
      p.add(inputNumber);
      p.add(new JLabel("����ȷ����ť:")); 
      p.add(buttonEnter);
      p.add(new JLabel("������Ϣ:"));
      p.add(feedBack);
      Container con=getContentPane();
      con.add(p); 
      con.validate();
      setBounds(120,125,270,200);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100,100,150,150);
      setVisible(true);
      validate();
    }
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==buttonGetNumber){  // �ж��¼�Դ�Ƿ���buttonGetNumber
         number=(int)(Math.random()*100)+1;
      }else if(e.getSource()==buttonEnter){ // �ж��¼�Դ�Ƿ���buttonEnter
         int guess=0;
         try { 
                 guess=Integer.parseInt(inputNumber.getText()); //����inputNumber�е��ı�
                 if(guess==number){
                    feedBack.setText("�¶���");//feedBack������ʾ�ı����¶��ˣ���
                 }else if(guess>number){
                    feedBack.setText("�´���"); //feedBack������ʾ�ı����´��ˣ���
                    inputNumber.setText(null); 
                 }else if(guess<number){
                    feedBack.setText("��С��"); //feedBack������ʾ�ı�����С�ˣ���
                    inputNumber.setText(null); 
                 }
               }
           catch(NumberFormatException event){
                 feedBack.setText("�����������ַ�"); //feedBack������ʾ�ı��������������ַ���
               } 
        }
    } 
}
class GuessExample {
    public static void main(String args[]) { 
      new GuessNumber("������С��Ϸ");
    }
}
