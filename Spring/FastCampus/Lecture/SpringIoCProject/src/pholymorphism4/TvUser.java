package pholymorphism4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		// 1. 스프링 IoC 컨테이너를 생성(구동)한다.
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

	    // 2. 스프링IoC 컨테이너로부터 객체를 검색(lookup) 한다. 

		// Tv tv = (Tv) container.getBean("pholymorphism4.LGTv"); // "pholymorphism4.LGTv#0" 생성한 객체들 중에 검색한요청한 것을 생성해줘라  ID를 넣어서 검색
		Tv tv = (Tv) container.getBean("tv");

		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. 스프링 IoC 컨테이너를 종료한다.(컨테이너는 종료되기 직전에 자신이 관리하던 모든 객체를 삭제한다.) 그래야 destory 메소드가 호출됨.
		container.close();
	

	}

}
