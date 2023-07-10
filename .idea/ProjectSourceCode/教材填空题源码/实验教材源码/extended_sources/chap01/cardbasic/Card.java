import java.awt.*;
public class Card {	// һ���˿��Ƶĳ���
	private int suit;	// ��ɫ��ȡֵ��ΧΪ0��4���ֱ�����顢÷�������ҡ����ҡ�����
	private int face;	// ������ȡֵ��ΧΪ0��12���ֱ����2��3��...��10��J��Q��K��A����ȡֵ0��1��
        private Image image;
        static Toolkit  mytool=Toolkit.getDefaultToolkit();
	// ���췽��������s��f�ֱ��ʾ�ƵĻ�ɫ�����
	public Card(int s, int f,String imagefile) {
		suit = s;
		face = f;
		image=mytool.getImage(imagefile);
	}
        public void drawme(Graphics g,int x,int y,int w,int h){
             g.drawImage(image,x,y,w,h,null);
        }
	public String display() {// �������ַ�������������
		String suitString = "";
		if (suit == 0)  suitString = "����";
		else if (suit == 1)  suitString = "÷��";
		else if (suit == 2)  suitString = "����";
		else if (suit==3) suitString = "����";
		else suitString="��";
		String faceString = "";
		if(suit!=4){
			if (face >= 0 && face <= 8)  faceString = "" + (face + 2);
			else if (face == 9)  faceString = "J";
			else if (face == 10)  faceString = "Q";
			else  if (face == 11)  faceString = "K";
			else  faceString = "A";
			return  (suitString + faceString);
		}else{
		    	if(face==0) return "С"+suitString;
		    	else return "��"+suitString;
		}
	}
        public String toString(){return display();}
	public int getIndex() {	// ������������������Ĵ���
		return  (suit * 13 + face);
	}
}