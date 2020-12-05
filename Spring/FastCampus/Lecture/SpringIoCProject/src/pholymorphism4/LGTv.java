package pholymorphism4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv")
public class LGTv implements Tv {

	@Autowired
	private SonySpeaker speaker; // ��Ÿ���� ��ü�� �ݵ�� �޸𸮿� �־�� �Ѵ� ������ �뼭ġ�ͼ����� �߻���.

	private int price =13000;

	public LGTv() {
		System.out.println("===> LGTv ����");
		
	}

//	public LGTv(Speaker speaker) {
//		System.out.println("===> LGTv(2) ����");
//		this.speaker = speaker;
//	}
//
//
//	public LGTv(Speaker speaker, int price) {
//		System.out.println("===> LGTv(3) ����");
//		this.speaker = speaker;
//		this.price = price;
//	}

	@Override
	public void powerOn() {
		System.out.println("LGTv---���� �Ҵ�. " + price);

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
		System.out.println("LGTv---���� ����.");

	}

	@Override
	public void volumeUp() {
		System.out.println("LGTv---�Ҹ� �ø���.");
		speaker.volumeUp();

	}

	@Override
	public void volumeDown() {
		System.out.println("LGTv---�Ҹ� ������.");
		speaker.volumeDown();

	}

}
