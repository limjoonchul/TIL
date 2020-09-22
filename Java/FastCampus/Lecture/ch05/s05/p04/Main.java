package com.company.s05.p04;

/**
 * 람다식과 익명 클래스 객체가 동일한 것은 아니다라는 것의 증명
 */

/**
 * 표준 함수형 인터페이스 많이사용하는 것은 아님!
 * 5:21
 * 람다식은 정형화되어있어서 직접만들어 쓸 필요없이
 * 표준함수형인터페이스로 구현되어있는게 있다 그래서 이걸 잘 이해하고 활용하면
 * 편하게 사용할 수 있다.
 *
 * 지금까지는 문법을 배웠다면 이제부터는 함수형인터페이스를 어떻게 사용할 것이냐
 * 그냥 인터페이스라면(함수형 인터페이스라면) 추상메소드 하나만 의미가 있다고 배었는데, 두개 이상이면 안됨
 * 근데 디폴트 메소드가 구현이 될 수 있어서 추상메소드를 가지고 어떻게 활용할 것인지를 이해한다음에
 * 이걸 연계해서 디폴트메소드를 어떻게 쓸것인지
 *
 *
 * 컨슈며 입력을 받고 입력을 소비하는 출력을 하지 않는다
 * 서플라이어 입력은 받지 않고 출력만 하는 애다 무언가 출력해서 출력을 공급해줌 공급자
 * 펑션 뭔가를 적용하는 것? 적용해서 입력을 받아서 출력을 해주는데 입력과 출력의 타입이 다를 수 있다 (타입의 변형)
 * 오퍼레이션 입력받아 출력이되는데 입력받은 애를 뭔가변화를 해주는데 여전히 같은 타입으로 출력이 됨
 * 프레디케이트 입력이 들어왔을 때 이것이 참인지 거짓인지 판단을 해주는 것
 *
 * 정의역(domain)과 치역(range) 입력을 받을 때  입력이 들어왔을 때 어떤 함수로 치역을 가르키게 된는게 매핑?
 * 입력될 수 있는 자료타입 출력될 수 있는 자료타입을 연결(처리를 해주는) 것이 매핑
 *
 */
/**
 * 표준 함수형인터페이스의 종류
 * 컨슈며 계열 입력을 받는게 소비를 한다라고 표현한것
 * 반환을 하지 않는 대신 다른 방식으로 영향을 주는 메소드이다
 * 계열은 특별히 다른게 아니라 메소드의 이름이나 리턴타입이나 입력팔라미터 정도만 다를 수 있다 그외에는 없음
 * negate  == not
 *
 *
 * 람다식 메소드/ 생성자 사용 중요!
 * 스트링클래스에 정의가되어있는 comapreto한 인스턴스 메서드를 활용한다.
 * compareTo 멧도느는 obj.compareTo(String o) 이런식으로 정의되어있는 메소드인데
 * String에 들어와야하는 애는
 * comapre(T o1, T o2) -> obj.compareTo(String o)
 * o1 == obj , o2 == o 와 같다.
 *
 * 기존에 있는 메소드를 참조하는 형식식

 */

/**
 * 람다식은 내부에 클래스를 생성하지 않는데 어떻게 익명내부클래스랑 똑같이 동작을 할수있을까요
 * 함수형인터페이스여서 가능한 것이다 원래 이런 것이다.
 * 러버덕 주제 함수형 프로그래밍
 */
@FunctionalInterface
interface IFoo{
    String method();
}

public class Main {
    static void funtionalMethod(IFoo foo){
        System.out.println(foo.method());
    }

    void methodA(){
        // 그외에는 둘이 완전히 똑같다고 생각하면된다 this만 다르다고 생각하면 된다.
        funtionalMethod(()->{
            System.out.println("this"+ this); // 이 this가 왜 main의 객체?
            // 익명클래스와 다르게 람다식은 익명클래스와 달리 클래스가 만들어지지 않는다.
            // 함수형 인터페이스로 동작을 한다.
            System.out.println("OuterClass.this"+Main.this);
            return "Lambad expression used.";
        });
       // 위 아래 출력 값이 다르다. this의 출력값이 다르다
        funtionalMethod(new IFoo() {
            @Override
            public String method() {
                System.out.println("this"+ this); // 익명 클래스의 객체가 this가 된다.
                // 익명클래스를사용할 때 실제로 익명클래스가 만들어지고 사용이되서 넣어진다는게 증명이된다.
                System.out.println("OuterClass.this"+Main.this); //외부 클래스인 Main 객체의 this
                return "Anonymous local inner class used.";
            }
        });
    }

    public static void main(String[] args) {
        new Main().methodA();
    }
}
