package pholymorphism4;


public class SamsungTv implements Tv {
	
	private Speaker speaker; // ��ü�� ���������� ������ nullpointException�� �߻�����...
	private int price;

	public SamsungTv() {
		System.out.println("===> Samsung TV(1) ����");

	}

	public void powerOn() {
		System.out.println("SamsungTv---���� �Ҵ�." + price);
	}

	public void powerOff() {
		System.out.println("SamsungTv---���� ����.");
	}

	public void volumeUp() {

		speaker.volumeUp();
	}

	public void volumeDown() {

		speaker.volumeDown();
	}
}
