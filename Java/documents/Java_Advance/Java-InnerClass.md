# 내부 클래스(Inner Classes)

## 내부 클래스란
연관 있는 것을 그룹 지어 사용할 때 내부클래스,외부클래스가 주로 사용이됨.
* 클래스 내부에 선언하는 클래스로 중첩 클래스라고도 부름
* `HAS-A`관계에 있는 클래스가 해당 클래스에서만 사용될 경우 주로 사용.

* 내부 클래스에서 주로 사용하는 것은 익명 내부 클래스인데,
익명 내부 클래스를 사용하는 것이 람다식이여서 결국 람다식을 사용하기 위한 것
* 클래스 안에 클래스 안에 인스턴스변수와 클래스변수가 있을 수 있고,
중첩되면서 굉장히 복잡해질 것같다.

## 내부 클래스의 종류

* 클래스 영역에 선언
   * 인스턴스 내부 클래스 : 외부 클래스의 멤버 변수처럼 사용 가능.
   * 클래스 내부 클래스 : static이 붙은 정적인 내부 클래스
   
* 로컬 영역에 선언
   * 로컬 내부 클래스 : 선언된 영역의 Scope 내에서 사용 가능
   * 익명 내부 클래스 : 객체를 일회성으로 생성하기 위한 이름이 없는 클래스
      * new ----() : ----- 여기에 클래스이름을 사용했어야 했는데
        클래스를 한 번만 사용할거면 이름이 없어도 된다
        일회성으로 생성을 한다면 익명으로 만드는 것이 가능하다.
        이것들은 암기하는 영역이다.
    
## 다양한 내부 클래스

### 인스턴스 내부 클래스 (Instance Inner Class)
* 클래스 영역에 `static` 키워드 없이 선언되면 인스턴스 내부 클래스이다.
* static 클래스가 아닌 일반적인 클래스처럼 동작 한다.
* 외부 클래스의 private을 포함한 모든 멤버에 접근 가능
* 외부 클래스 객체를 통해서만 생성 가능
* static 멤버 변수는 사용할 수 없으나, `static final` 은 사용 가능
   * static 멤버 변수가 사용이 될려면 클래스가 먼저 있고 그 안에 static 멤버 변수가 존재하게 되는데,
     문제는 인스턴스 내부 클래스는 인스턴스가 만들어져야 외부 클래스의 인스턴스가 만들어져야 
     그 안에 내부 클래스가 생기는 것이기 때문에 static 멤버 변수가 있을 수 없다.
   *`static final`은 사용 가능 - 변하지 않는 상수이기 때문에 (상수 취급이 되기 때문에) 사용이 가능하다 이해는 할 수 있지만 따로 암기
* 외부 클래스의 객체를 만들고 객체 내에서만 사용이 가능하다 ->이해되면 이해 안되면 암기


```groovy
class Outer {
    class InstanceInner {
        int innerMemberVar = 1;
        //static int staticMemberVar = 1;
        static final int CONSTANT_VAR = 10;

        void innerMethod() {
            System.out.println(innerMemberVar);
            System.out.println(outerMemberVar);
        }
    }

  private int outerMemberVar = 2;

  void outerMethod() {
      IstanceInner obj = new InstanceInner();
      obj.innerMethod();
  }

  public static void main(String[] args) {
      Outer outer = new Outer();
      InstanceInner inner = outer.new InstanceInner();
      inner.innerMethod();
  }

}
```
* 클래스이기때문에 이외에는 클래스처럼 사용이 가능하다. 이너 메소드가 존재할 수 있다.
* outer 클래스의 멤버 변수도 inner 메소드에서 사용이가능 private이여도 접근이 가능하다.
* inner 클래스는 인스턴스에 속해 있기 때문에 속해 있는 인스턴스 변수에 접근이 가능하다.
* inner클래스를 outer 클래스를 통해서만 객체 생성이 가능하다는 것은
  * outerMethod는 static이 붙어있지 않기때문에 인스턴스 멤버 메소드이다. outerMethod 를 콜하기위해서 outer 인스턴스가 있다는 것이므로
  * outer 인스턴스에 속하는 메소드이기 때문에 inner 클래스를 바로 사용이 가능하다(생성 가능) 그런 다음 메소드 콜이 가능하다.

#### outerMethod 와 main method 비교
* 메인메소드는 static인데 static은 static 메소드이자 outer 클래스에 속한 메소드이기 때문에
  outerMethod는 객체.outerMethod로 선언이 되기 때문에 이미 객체가 있다는 전제하에 outerMethod 바디에 내부클래스를 생성할 수 있음
* 반면 메인메소드는 스태틱이므로 인스턴스가아니라 클래스 변수이기때문에 outer.new InnerClass를 사용해서 생성해야 한다.
   * new 이너클래스는 통으로 보면된다  통으로 생성자를 콜하는걸로 보면 된다 outer 안에 통으로 콜한다고 의미를 생각하면 됨. 외우면 직관적이 됨.
#### this 활용

* 내부클래스에서 this는 내부클래스 멤버변수를 가르키고,  outer.this.var해야지 외부크래스의 멤버변수를 가르킴

```groovy
class Outer {
    class InstanceInner {
        int var = 1;

        void innerMethod() {
            System.out.println(var);
            System.out.println(this.var);
            System.out.println(Outer.this.var);
        }
    }

  private int var = 2;

  public static void main(String[] args) {
      Outer outer = new Outer();
      InstanceInner inner = outer.new InstanceInner();
      inner.innerMethod();
  }

}
```

### 클래스 내부 클래스 (Class Inner Class)
* 클래스 영역에 `static` 키워드와 함께 선언된 내부 클래스
* 외부 클래스 객체가 없어도 생성 가능
* 외부 클래스의 private을 포함한 모든 멤버에 접근 가능
* static 멤버 변수를 가질 수 있음.
* 인스턴스 내부클래스와 상당히 유사하지만 객체를 있고,없고의 차이
스태틱을 사용할 수 있고 없고의 차이.

* 인스턴스에 속한 클래스가 아니기때문에 외부클래스의 특정인스턴스에 속한 애가아니기때문에 외부클래스에 접근하려면
  내부클래스에서 외브클래스를 생성을해야 접근이 가능하다.
  
* 외부클래스의 메소드에서 내부 클래스의 객체를 다 만들 수 있음. 
외부 클래스 내부 클래스는 독립적인 클래스가 맞긴한데 종속 독립의 개념보다 내부클래스는 외부클래스의 사정을 알고있는 클래스이다?
클래스내부클래스의 내부클래스에서 외부클래스의 객체를 만들면 외부클래스의 priavte에 접근이 가능하므로 독립된것처럼사용할 수 있지만 독립된건 아니다.
```groovy
class Outer {
    static class ClassInner {
        int innerVar = 1;
        static int staticInnerVar = 100;

        void innerMethod() {
            Outer outer = new Outer();
            System.out.println(outer.outerVar);
            System.out.println(innerVar);
        }
    }

    private int outerVar = 2;

    void outerMethod() {
        ClassInner inner = new ClassInner();
        inner.innerMethod();
    }

    public static void main(String[] args) {
        Static inner = new StaticInner();
        inner.innerMethod();
    }
}
```
* 메인에서 아우터 클래스로 객체를 만들 때 아우터 클래스 객체안에 이너 클래스 객체가 있는것이 아니다. 컴포지션이랑은 전혀 다른 개념이다
클래스는 있는데 객체는 없는 것이다.
* 아우터 클래스로 객체를 만들 때 이너 클래스의 메소드를 사용할 수 없음. 아우터클래스에서 스태틱만 접근이가능하다.