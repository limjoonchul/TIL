package pholymorphism3;

public class LGTv implements Tv{

	 public LGTv() {
	        System.out.println("===> LGTv 생성");
	    }

	@Override
	public void powerOn() {
		System.out.println("LGTv---전원 켠다.");
		
	}

	@Override
	public void powerOff() {
		System.out.println("LGTv---전원 끈다.");
		
	}

	@Override
	public void volumeUp() {
		System.out.println("LGTv---소리 올린다.");
		
	}

	@Override
	public void volumeDown() {
		System.out.println("LGTv---소리 내린다.");
		
	}


}
