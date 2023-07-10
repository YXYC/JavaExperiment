//CPU.java
public class CPU { //抽象的计算控制模块
	private double ax, bx;
	private String instruct;
     private Memory memo;
	public CPU(Memory memo) {
		ax = bx = 0; instruct = "+";	this.memo=memo;}
	public String getInstruct() {
		return instruct;	}
	public void setInstruct(String instruct) {
		this.instruct = instruct;	}
	public void calculate() {
		ax=memo.getFirstnum();
		bx=memo.getSecondnum();
		switch (instruct) {
		case "+":	ax = ax + bx;	break;
		case "-":	ax = ax - bx;	break;
		case "*":	ax = ax * bx;	break;
		case "/":	ax = ax / bx;	break;
		default:	ax=9999999.999;	}
		memo.setResult(ax);
}}
