//Computer.java
public class Computer {       //抽象组合后的简易计算器
   private CPU cpu;         //组合子对象cpu处理计算
   private Memory memory;  //组合子对象memory处理存储
   private Keyboard keyboard; //组合子对象keyboard处理输入
   private Screen screen;     //组合子对象screen处理输出
   public Computer() {
       memory=new Memory();
       cpu=new CPU(memory);
       keyboard=new Keyboard();
       screen=new Screen();
  }
public Computer(CPU cpu, Memory memory, Keyboard keyboard, Screen screen) {
		super();
		this.cpu = cpu;
		this.memory = memory;
		this.keyboard = keyboard;
		this.screen = screen;
	}
    public void doWork() {//模拟计算机开机工作方法
    	    screen.print("第一个操作数：");
    	    memory.setFirstnum(keyboard.inputDouble());
    	    screen.print("运算符：");
    	    cpu.setInstruct(keyboard.inputString());
    	    screen.print("第二个操作数：");
    	    memory.setSecondnum(keyboard.inputDouble());
    	    cpu.calculate();
    	    screen.println("计算结果："+memory.getResult());
    }
}
