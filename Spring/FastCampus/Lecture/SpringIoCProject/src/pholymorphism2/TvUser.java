package pholymorphism2;

//다형성 = 상속 + 메소드 Overriding + 형변환(절차지향언어에서 지원하지 않음) 유지보수가 편함! 원 인터페이스 멀티플 인플리멘테이션
//자식객체가 부모타입으로 되는걸 묵시적 형변환
//
public class TvUser {

	public static void main(String[] args) {
        Tv tv = new SamsungTv();
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        // 이건 클라이언트에게 리모콘을 줘서 tv를 작동하라고 하는 것과 같다.
        // tv는 바껴도 리모콘은 안바뀌니깐 하나의 리모콘으로 여러개의 tv를 작동시킬 수 있다 이것이 다형성.
        // 클라이언트를 안건드리고 tv를 바꿀 수 있을까. --> 디자인 패턴을 적용하면 된다.

	}

}
