package pholymorphism3;

public class LGTv implements Tv{

	 public LGTv() {
	        System.out.println("===> LGTv ����");
	    }

	@Override
	public void powerOn() {
		System.out.println("LGTv---���� �Ҵ�.");
		
	}

	@Override
	public void powerOff() {
		System.out.println("LGTv---���� ����.");
		
	}

	@Override
	public void volumeUp() {
		System.out.println("LGTv---�Ҹ� �ø���.");
		
	}

	@Override
	public void volumeDown() {
		System.out.println("LGTv---�Ҹ� ������.");
		
	}


}
