public class Cards {// һ���˿��Ƶĳ���
    static final int MAX = 54; // һ���ƹ���54����
    private Card[] deck = new Card[MAX];    
    public Cards() {// ���췽�������δ���ÿһ���˿���
        for (int suit = 0; suit < 4; suit++) {
            for (int face = 0; face < 13; face++) {
		deck[suit * 13 + face] = new Card(suit, face,""+suit+"\\"+getFname(face));
	    }
	}
	deck[52]=new Card(4,0,"4\\xw.jpg");
	deck[53]=new Card(4,1,"4\\dw.jpg");
    }
    public String getFname(int face){
        String faceString = "";
	if (face >= 0 && face <= 8)  faceString = "" + (face + 2);
	    else if (face == 9)  faceString = "J";
	    else if (face == 10)  faceString = "Q";
	    else  if (face == 11)  faceString = "K";
	    else  faceString = "A";
	    return  faceString+".jpg";
    }
    public void shuffle() {	// ϴ��
	for (int count = 0; count < 200; count++) {
	    int index1 = (int) (Math.random() * MAX);// ����������Ƶ�λ��
		int index2 = (int) (Math.random() * MAX);
		Card temp = deck[index1];//����
		deck[index1] = deck[index2];
		deck[index2] = temp;
	}
    }
    public Card getIndex(int i){return deck[i];}
    public void displays(int start,int end){
	for(int index=start;index<end;index++){
	    System.out.print(deck[index].display() + " ");
	}
    }
}