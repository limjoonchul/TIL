package pholymorphism3;

//������ = ��� + �޼ҵ� Overriding + ����ȯ(����������� �������� ����) ���������� ����! �� �������̽� ��Ƽ�� ���ø������̼�
//�ڽİ�ü�� �θ�Ÿ������ �Ǵ°� ������ ����ȯ
//
public class TvUser {

	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		Tv tv = (Tv) factory.getBean(args[0]);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		// �̰� Ŭ���̾�Ʈ���� �������� �༭ tv�� �۵��϶�� �ϴ� �Ͱ� ����.
		// tv�� �ٲ��� �������� �ȹٲ�ϱ� �ϳ��� ���������� �������� tv�� �۵���ų �� �ִ� �̰��� ������.
		// Ŭ���̾�Ʈ�� �Ȱǵ帮�� tv�� �ٲ� �� ������. --> ������ ������ �����ϸ� �ȴ�.

	}

}
