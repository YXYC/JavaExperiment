package chap06;

public abstract class Disk {  //������Disk
//����ʲô���̣����߱����۸񡢳��ҡ��ͺš��⼸�����ԣ�����ֱ�Ӽ̳м���
	protected Float price; // �۸�
	protected String manufacturer; // ����
	protected String model; // �ͺ�
//��ͬ�Ĵ��̣���д��ʽ���ܴ���������Ϊabstract��ǿ����������д
	abstract void read();
	abstract void write();
//���·����Ǹ��ִ���ͨ�õģ�ֱ�Ӽ̳м��ɣ������д
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
