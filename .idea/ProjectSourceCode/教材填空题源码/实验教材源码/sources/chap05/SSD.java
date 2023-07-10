package chap06;

public class SSD extends Disk {
	SSD() {
		this.manufacturer = "Seagate";
		this.model = "ssd";
		this.price = (float) 500.5;
	}

	@Override
	void read() {
		System.out.println("SSD read, very fast");
	}

	@Override
	void write() {
		System.out.println("SSD write, very fast");
	}
}
