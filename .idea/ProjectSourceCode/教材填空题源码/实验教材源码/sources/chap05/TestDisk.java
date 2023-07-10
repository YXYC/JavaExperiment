package chap06;

public class TestDisk {
	public static void main(String[] args) {
		Disk disk;
        //disk = new Disk(); //�������Cannot instantiate the type Disk
		disk = new SSD();
		disk.read(); // OK, ���յ��õ�read(), �����SSD read, very fast��
		disk.write(); // OK, ���յ���write(), �����SSD write, very fast��
		System.out.println(disk.getManufacturer()); // OK, �� �� �� ��getManufacturer(), �����Seagate��
		System.out.println(disk.getModel()); // OK, ���յ���Disk::getModel(),�����ssd��
		System.out.println(disk.getPrice()); // OK, ���յ���Disk::getPrice(),�����500.5��
		disk = new SATA();
		disk.read(); // OK, ���յ���read(), �����SATA read, very slow��
		disk.write(); // OK, ���յ���write(), �����SATA write, very slow��
		System.out.println(disk.getManufacturer()); // OK, �� �� �� ��getManufacturer(), �����Maxtor��
		System.out.println(disk.getModel()); // OK, ���յ���getModel(),�����sata��
		System.out.println(disk.getPrice()); // OK, ���յ���getPrice(),�����100.5��
	}
}