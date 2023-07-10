package chap06;

public class TestDisk {
	public static void main(String[] args) {
		Disk disk;
        //disk = new Disk(); //编译错误：Cannot instantiate the type Disk
		disk = new SSD();
		disk.read(); // OK, 最终调用的read(), 输出“SSD read, very fast”
		disk.write(); // OK, 最终调用write(), 输出“SSD write, very fast”
		System.out.println(disk.getManufacturer()); // OK, 最 终 调 用getManufacturer(), 输出“Seagate”
		System.out.println(disk.getModel()); // OK, 最终调用Disk::getModel(),输出“ssd”
		System.out.println(disk.getPrice()); // OK, 最终调用Disk::getPrice(),输出“500.5”
		disk = new SATA();
		disk.read(); // OK, 最终调用read(), 输出“SATA read, very slow”
		disk.write(); // OK, 最终调用write(), 输出“SATA write, very slow”
		System.out.println(disk.getManufacturer()); // OK, 最 终 调 用getManufacturer(), 输出“Maxtor”
		System.out.println(disk.getModel()); // OK, 最终调用getModel(),输出“sata”
		System.out.println(disk.getPrice()); // OK, 最终调用getPrice(),输出“100.5”
	}
}