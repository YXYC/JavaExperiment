import java.util.ArrayList;
class Wolf {
    public String toString(){
        return "Wolf";
    }
}
class Sheep {
    public String toString(){
        return "Sheep";
    }
}
class Cabbage {
    public String toString(){
        return "Cabbage";
    }
}
class Riverside {
  private String name;
  private ArrayList alist;
  private boolean farmerExisted; 
  public Riverside(String n){
    name=n;
    alist=new ArrayList();
  }
  public String getName(){
    return name;
  }
  public void setName(String n){
    name=n;
  }
  public boolean isFarmerExisted() {return farmerExisted;}
  public void setFarmerExisted(boolean b){farmerExisted=b;}
  
  public boolean isSafe(){
      if(isFarmerExisted()) return true;
      else {
          int size=alist.size();
          if(size<=1 || size==3) {
              return true;
          }else {
              Object obj1=alist.get(0);
              Object obj2=alist.get(1);          
              if((obj1 instanceof Wolf & obj2 instanceof Cabbage) | (obj2 instanceof Wolf & obj1 instanceof Cabbage)) {
                  return true;
              } else {
                  return false;
              }
          }       
      }
  }
  public ArrayList getList(){return alist;}
  public void display(){
    System.out.println("----------------------------------");
    System.out.println("      "+name+"       ");
    System.out.println("----------------------------------");
    for(int i=0;i<alist.size();i++){
        Object obj=alist.get(i);
        if(obj instanceof Wolf) System.out.println("Wolf");
        else if(obj instanceof Sheep) System.out.println("Sheep");
        else System.out.println("Cabbage");
    }
    System.out.println("**********************************"); 
  }
  public void add(Object obj){
      alist.add(obj);
  }
}
class Boat {
    private Object obj;
    private Riverside currentSide,otherSide;
    public Boat(Riverside c,Riverside o){
        currentSide=c;otherSide=o;
    }
    public Riverside getCurrentSide(){return currentSide;}
    public void setCurrentSide(Riverside r){currentSide=r;}
    public Object getObj(){return obj;}
    public void setObj(Object o){obj=o;}
    public Riverside getOtherSide(){return otherSide;}
    public void setOtherSide(Riverside r){otherSide=r;}
    public void go(){
        if(obj==null) {
            System.out.println("Boat from "+currentSide.getName()+" to "+otherSide.getName()+".");
            Riverside tmp=currentSide;   //The boat arrived at the otherside
            currentSide=otherSide;
            otherSide=tmp;
            currentSide.setFarmerExisted(true);
            otherSide.setFarmerExisted(false);         
        }else{
            System.out.print("Boat from "+currentSide.getName()+" to "+otherSide.getName()+". Loading ");
            if(obj instanceof Wolf) System.out.println("Wolf");
            else if(obj instanceof Sheep) System.out.println("Sheep");
            else System.out.println("Cabbage");            
            otherSide.add(obj);
            obj=null;
            Riverside tmp=currentSide;   //The boat arrived at the otherside
            currentSide=otherSide;
            otherSide=tmp;
            currentSide.setFarmerExisted(true);
            otherSide.setFarmerExisted(false);
        }
    }
}
class Farmer {
    Riverside rs1,rs2;
    Wolf w=new Wolf();
    Sheep s=new Sheep();
    Cabbage c=new Cabbage();
    Boat boat;
    public Farmer(){
        System.out.println("初始化数据：河岸A有狼、羊、白菜各一,河岸B空！");
        rs1=new Riverside("Riverside A");
        rs2=new Riverside("Riverside B");
        rs1.add(w);rs1.add(s);rs1.add(c);
        boat=new Boat(rs1,rs2);
    }
    public void debug(){
        rs1.display();
        rs2.display();
    }
    public boolean safeCheck(Object obj){
        if(boat.getCurrentSide().isSafe()){
            Riverside rs=boat.getOtherSide();
            rs.setFarmerExisted(true);
            rs.add(obj);
            if(rs.isSafe()){
                rs.getList().remove(obj);
                return true;
            }else{
                rs.getList().remove(obj);
                return false;
            }
        }else{
            return false;
        }  
    }
    public void move(Object obj){
        boat.setObj(obj);
        boat.go();
    }
    public void transport(){
        Object obj;
        /**********************************/
        while(true){  
            debug();
            if(boat.getCurrentSide().getName().equals("Riverside A")){ //Riverside A
                while(boat.getCurrentSide().getList().size()>0){
                    obj=boat.getCurrentSide().getList().remove(0);
                    if(safeCheck(obj)){
                         move(obj);
                         break;
                    }else{
                         boat.getCurrentSide().add(obj);
                    }
                }
            }else{  //Riverside B
                if(boat.getCurrentSide().getList().size()==3) break;
                boat.getCurrentSide().setFarmerExisted(false);
                if(boat.getCurrentSide().isSafe()){
                    move(null);
                }else{
                    if(boat.getCurrentSide().getList().size()>0){
                        while(true){
                            obj=boat.getCurrentSide().getList().remove(0);
                            if(safeCheck(obj)){
                                 move(obj);
                                 break;
                            }else{
                                 boat.getCurrentSide().add(obj);
                            }                        
                        }
                    }
                } 
            }
        }           
    }
    public static void main(String[] args){
        Farmer myapp=new Farmer();
        myapp.transport();
    }
}