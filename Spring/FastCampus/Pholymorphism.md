# 다형성
## 디폴트 생성자가 하는 역할
* 원래 생성자의 역할은 객체가 생성이 될 때 멤버변수의 값을 의미있는 값으로 초기화 할 때 
Primitive Type은 그 타입의 디폴트 값으로 초기화가 되고, Reference Type은 null로 초기화가 된다.
* 생략해도되고, 기본적으로 있다고해서 디폴트 생성자가 아니라 멤버 변수를 디폴트 값으로 초기화 시키기 때문에 디폴트 생성자라고 부른다.

## 다형성을 적용하지 않은 예제
* 예를 들어, 삼성티비와 엘지티비의 클래스를 따로따로 만든다고 했을 때, 다형성을 적용하지 않은 상태에서 
개발자에게 맡기게 되면 각각 자신의 생각대로 메소드명도 정하고 마음대로 클래스를 만들게 된다.
그래서 통일되지 않은 상태이게 되고 삼성티비와 엘지티비를 강제할 수단이 없기 때문에
각각 메소드를 호출 할 때마다 삼성티비와 엘지티비에 구현되어있는 메소드명으로 바꿔서 호출을 해줘야 한다.

* 이러한 예는 유지보수가 어렵고, 객체 지향 프로그래밍이 아닌 절차 지향 프로그래밍이다.

```java
public class TvUser {
    public static void main(String[] args) {
        SamsungTv tv = new SamsungTv();
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        // 객체지향스럽지 못함 유지보수가 어려워서
    }
}
```
```java
package com.company.pholymorphism1;

public class SamsungTv {
    public SamsungTv() {
        System.out.println("===> Samsung TV 생성");
    }

    public void powerOn(){
        System.out.println("SamsungTv---전원 켠다.");
    }

    public void powerOff(){
        System.out.println("SamsungTv---전원 끈다.");
    }

    public void volumeUp(){
        System.out.println("SamsungTv---소리 올린다.");
    }

    public void volumeDown(){
        System.out.println("SamsungTv---소리 내린다.");
    }
}
```
```java
package com.company.pholymorphism1;

public class LGTv {
    public LGTv() {
        System.out.println("===> LGTv 생성");
    }

    public void turnOn(){
        System.out.println("LGTv---전원 켠다.");
    }

    public void turnOff(){
        System.out.println("LGTv---전원 끈다.");
    }

    public void soundUp(){
        System.out.println("LGTv---소리 올린다.");
    }

    public void soundDown(){
        System.out.println("LGTv---소리 내린다.");
    }
}
```

## 다형성을 적용한 얘제
* 다형성 = 상속 + 메소드 Overriding + 형변환(절차 지향 언어에서 지원하지 않음) 유지보수가 편함! 원 인터페이스 멀티플 인플리멘테이션
* 자식 객체가 부모 타입으로 되는걸 묵시적 형변환

```java
package com.company.pholymorphism2;

public interface Tv {
    void powerOn();

    void powerOff();

    void volumeUp();

    void volumeDown();
}
```
```java
package com.company.pholymorphism2;

public class SamsungTv implements Tv {
    public SamsungTv() {
        System.out.println("===> Samsung TV 생성");
    }

    @Override
    public void powerOn(){
        System.out.println("SamsungTv---전원 켠다.");
    }

    @Override
    public void powerOff(){
        System.out.println("SamsungTv---전원 끈다.");
    }

    @Override
    public void volumeUp(){
        System.out.println("SamsungTv---소리 올린다.");
    }

    @Override
    public void volumeDown(){
        System.out.println("SamsungTv---소리 내린다.");
    }
}

```
```java
package com.company.pholymorphism2;

public class LGTv implements Tv{
    public LGTv() {
        System.out.println("===> LGTv 생성");
    }

    @Override
    public void powerOn() {
        System.out.println("LGTv---전원 켠다.");
    }

    @Override
    public void powerOff() {
        System.out.println("LGTv---전원 끈다.");
    }

    @Override
    public void volumeUp() {
        System.out.println("LGTv---소리 올린다.");
    }

    @Override
    public void volumeDown() {
        System.out.println("LGTv---소리 내린다.");
    }
}
```
```java
package com.company.pholymorphism2;


public class TvUser {
    public static void main(String[] args) {
        Tv tv = new SamsungTv();
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        // 이건 클라이언트에게 리모콘을 줘서 tv를 작동하라고 하는 것과 같다.
        // tv는 바껴도 리모콘은 안바뀌니깐 하나의 리모콘으로 여러개의 tv를 작동시킬 수 있다 이것이 다형성.
        // TvUser를 안건드리고 tv를 바꿀 수 있을까. --> 디자인 패턴을 적용하면 된다.

    }
}
```
* 이렇게 했을 때 객체를 생성해서 호출할 때 생성자만 바꿔주면 그에 맞는 메소드들이 호출되기 때문에
따로 메소드명을 일일히 바꿔주지 않아도 된다.
* 여기서 더 나아가서 TvUser 클래스를 건드리지 않고도 Tv를 바꿀 수 있는 방법은 뭐가 있을까 했을 때
디자인 패턴을 활용하는 것이다.

## 디자인 패턴을 활용한 다형성 예
* 각 티비 클래스는 비슷한 형태이니 생략하고 추가된 부분과 변경된 코드만 작성했다.
* 팩토리 디자인 패턴을 이용한 형태처럼 BeanFactory 클래스를 만들어 값이 입력될 때마다
입력 값에 맞는 객체들이 반환되서 TvUser 클래스의 Tv 객체에 대입 되게 만들어졌다. 
```java
package com.company.pholymorphism3;

public class BeanFactory { // 객체 공장 팩토리 패턴활용

    public Object getBean(String id){
        if (id.equals("lg")){
            return new LGTv();
        } else if(id.equals("samsung")){
            return new SamsungTv();
        } else if(id.equals("google")){
            return new GoogleTv();
        }
        return null;

    }
}
```
```java
package com.company.pholymorphism3;

public class TvUser {
    public static void main(String[] args) {

        BeanFactory factory = new BeanFactory();
        Tv tv = (Tv) factory.getBean(args[0]);

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
    }
}
```

### 프로그램 발전
* 프로그램 개발 발전은 복사/붙여넣기로 시작이 되었다. 반복되는 코드들을 복사붙여넣기하니깐 너무많아 유지보수가 어려워서 생긴게
* 함수가 생김 함수를 기반으로 절차지향언어 c가 생김 반복적으로 사용하는 코드를 함수로 모듈화 시켜놓으면 그 코드가 필요하면 함수만 호출하면되니깐
* 반복되는 코드를 줄여나가게 되었고. 알고리즘만 재사용하니깐 재사용성이 좋아지면 좋겠다 해서 나온게 클래스이다.
* 클래스는 단순히 함수만 있는게 아니라 함수가 동작할 때 필요한 변수까지 가지고 있어서 재사용성이 점점 좋아지는 쪽으로 발전함.

* 클래스를 통째로 바꿔치기하면서 재사용을 하려면 다형성이라는게 필요하다! 그래서 객체지향언어의 핵심은 다형성이다.
* 다형성을 사용하지 않을꺼면 복잡한 객체지향언어를 사용할 필요가 없다.

* 다형성을 사용했더니 클라이언트가 소스수정이 필요하다 그래서 이 다형성을 한단계 뛰어넘는게 디자인 패턴이다.
* 팩토리 패턴을 사용하면 공장으로부터 원하는 것을 얻어낸다. 클라이언트는 유지 보수과정에서 수정되지 않는다.
* 클라이언트(main)만 봐서는 무슨 tv가 생성되는지 알수 없다. 클라이언트는 이렇게 작성되어야 한다.

* 구글 티비를 만들어서 그냥 프로그램 컨피규레이션에 google을 넣는다고 동작하지않음 빈팩토리에 분기를 만들어서 조건을 걸어줘야 동작이 된다.
* 소스를 수정하면 컴파일 다시 해야 하고 컴파일다시하면 서버에 다시 올려야한다. 소스수정을 최소화시키는 방향으로 생각해야한다.
* 다형성, 디자인 패턴를 써도 해결되지 않은 문제를 해결하는 것이 스프링프레임워크이다. *핵심은 유지보수할 때 자바소스를 안건드리는 것이다. 그래서 컴파일을 다시 할 필요가 없다.

