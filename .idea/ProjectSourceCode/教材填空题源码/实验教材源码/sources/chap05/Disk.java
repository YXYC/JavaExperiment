package chap06;

public abstract class Disk {  //抽象类Disk
//不管什么磁盘，都具备“价格、厂家、型号”这几个属性，子类直接继承即可
	protected Float price; // 价格
	protected String manufacturer; // 厂家
	protected String model; // 型号
//不同的磁盘，读写方式差别很大，所以声明为abstract，强制子类必须改写
	abstract void read();
	abstract void write();
//如下方法是各种磁盘通用的，直接继承即可，无需改写
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
}
