package pholymorphism4;


public class SamsungTv implements Tv {
	
	private Speaker speaker; // 객체를 생성해주지 않으면 nullpointException이 발생하지...
	private int price;

	public SamsungTv() {
		System.out.println("===> Samsung TV(1) 생성");

	}

	public void powerOn() {
		System.out.println("SamsungTv---전원 켠다." + price);
	}

	public void powerOff() {
		System.out.println("SamsungTv---전원 끈다.");
	}

	public void volumeUp() {

		speaker.volumeUp();
	}

	public void volumeDown() {

		speaker.volumeDown();
	}
}
