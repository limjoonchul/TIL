package pholymorphism3;

public class SamsungTv implements Tv{

	  public SamsungTv() {
	        System.out.println("===> Samsung TV ����");
	    }

	    public void powerOn(){
	        System.out.println("SamsungTv---���� �Ҵ�.");
	    }

	    public void powerOff(){
	        System.out.println("SamsungTv---���� ����.");
	    }

	    public void volumeUp(){
	        System.out.println("SamsungTv---�Ҹ� �ø���.");
	    }

	    public void volumeDown(){
	        System.out.println("SamsungTv---�Ҹ� ������.");
	    }
}
