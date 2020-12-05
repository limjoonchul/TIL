package pholymorphism4;

import org.springframework.stereotype.Component;

@Component("aa")
public class AaSpeaker {

	public AaSpeaker() {
		System.out.println("===> AaSpeaker 积己");
	}

	public void volumeUp() {
		System.out.println("AaSpeaker---家府 棵赴促.");
	}

	public void volumeDown() {
		System.out.println("AaSpeaker---家府 郴赴促.");
	}

}
