package pholymorphism4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv")
public class LGTv implements Tv {

	@Autowired
	private SonySpeaker speaker; // 이타입의 객체가 반드시 메모리에 있어야 한다 없으면 노서치익셉션이 발생함.

	private int price =13000;

	public LGTv() {
		System.out.println("===> LGTv 생성");
		
	}

//	public LGTv(Speaker speaker) {
//		System.out.println("===> LGTv(2) 생성");
//		this.speaker = speaker;
//	}
//
//
//	public LGTv(Speaker speaker, int price) {
//		System.out.println("===> LGTv(3) 생성");
//		this.speaker = speaker;
//		this.price = price;
//	}

	@Override
	public void powerOn() {
		System.out.println("LGTv---전원 켠다. " + price);

	}

//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}

	@Override
	public void powerOff() {
		System.out.println("LGTv---전원 끈다.");

	}

	@Override
	public void volumeUp() {
		System.out.println("LGTv---소리 올린다.");
		speaker.volumeUp();

	}

	@Override
	public void volumeDown() {
		System.out.println("LGTv---소리 내린다.");
		speaker.volumeDown();

	}

}
