package pholymorphism3;

public class GoogleTv implements Tv {
	 public GoogleTv() {
	        System.out.println("===> GoogleTv ����");
	    }


	@Override
	public void powerOn() {
		System.out.println("GoogleTv---���� �Ҵ�.");
		
	}

	@Override
	public void powerOff() {
		System.out.println("GoogleTv---���� ����.");
		
	}

	@Override
	public void volumeUp() {
		System.out.println("GoogleTv---�Ҹ� �ø���.");
		
	}

	@Override
	public void volumeDown() {
		System.out.println("GoogleTv---�Ҹ� ������.");
	}

}
