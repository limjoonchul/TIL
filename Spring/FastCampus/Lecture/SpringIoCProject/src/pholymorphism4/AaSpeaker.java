package pholymorphism4;

import org.springframework.stereotype.Component;

@Component("aa")
public class AaSpeaker {

	public AaSpeaker() {
		System.out.println("===> AaSpeaker ����");
	}

	public void volumeUp() {
		System.out.println("AaSpeaker---�Ҹ� �ø���.");
	}

	public void volumeDown() {
		System.out.println("AaSpeaker---�Ҹ� ������.");
	}

}
