package pholymorphism4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		// 1. ������ IoC �����̳ʸ� ����(����)�Ѵ�.
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

	    // 2. ������IoC �����̳ʷκ��� ��ü�� �˻�(lookup) �Ѵ�. 

		// Tv tv = (Tv) container.getBean("pholymorphism4.LGTv"); // "pholymorphism4.LGTv#0" ������ ��ü�� �߿� �˻��ѿ�û�� ���� ���������  ID�� �־ �˻�
		Tv tv = (Tv) container.getBean("tv");

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. ������ IoC �����̳ʸ� �����Ѵ�.(�����̳ʴ� ����Ǳ� ������ �ڽ��� �����ϴ� ��� ��ü�� �����Ѵ�.) �׷��� destory �޼ҵ尡 ȣ���.
		container.close();
	

	}

}
