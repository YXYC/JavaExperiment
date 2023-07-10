package chap06;

public class SATA extends Disk {
	SATA() {
		this.manufacturer = "Maxtor";
		this.model = "sata";
		this.price = (float) 100.5;
	}

	@Override
	void read() {
		System.out.println("SATA read, very slow");
	}

	@Override
	void write() {
		System.out.println("SATA write, very slow");
	}
}
