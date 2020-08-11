package practice;

class Calculator{
   private final ArithmaticOperator op; //포함관계
   public Calculator(ArithmaticOperator op){
       this.op = op;
   }

   public int run(int x, int y){
       return op.operate(x,y);
   }
    public long run(long x, long y){
        return op.operate(x,y);
    }
    public double run(double x, double y){
        return op.operate(x,y);
    }

}

class Runner{
    public static void run(ArithmaticOperator op, int x, int y){
        System.out.println(op.operate(x,y));
    }
}



public class Main {
    public static void main(String[] args) {
        // 다형성 예(1)
        ArithmaticOperator op;
        op = new Add(); //이게 다형성의 힘

        System.out.println(op.operate(10,20));

        // 다형성 예(2)
        ArithmaticOperator [] ops; // 다형성을 하는 이유  어레이를 쓰는이유 자료형이 하나만 쓸 수 있어서 다형성을 쓰면 부모 클래스 인터페이스 가지고 어레이를 만들면
        //자식 오버라이드 되는 특성을 이용해서 부모로받은
        //인터페이스는 상속의 개념이다 인터페이스는 객체가 될 수 없다. 클래스에서 new 해야 객체다 인터페이스는 객체를 만들 수 없기 때문에.
        // 인터페이스 같은 경우는 인터페이스는 멤버 변수가 ㅇ벗기고 메소드만 있기 때문에 객체로 생성될 수 없음.
        // 클래스 영역은 확실히 존재하긴 하는데 인터페이스에서 스태틱 메소드가 사용가능하기 때문에 메소드에 대한 정보는 클래스 영역 들어가 있음
        // 객체가 따로 생성되는 건 아니기때문에 클래스영역하나만 있고 같은것만 참조함.
        // 버쳐메소드콜과 오버라이드를 이용해서 다른 객체를 한번에 사용하는 것 다형성의 의미

        ops = new ArithmaticOperator[]{new Add(),new Multiply()};
        for (ArithmaticOperator o : ops){
            System.out.println(o.operate(5,2));

        }

        // 하나의 다형성 예(3)
        Calculator add = new Calculator(new Add());
        Calculator sub = new Calculator(new Substract());

        System.out.println(add.run(10,20));
        System.out.println(sub.run(20,10));

        // 다형성의 예(4) -> 여기서 한단계 나가면 람다가 나옴

        Runner.run(new Add(), 40, 50); //바로 입력을 받을 수 있는데 이걸 이해해야 람다를 잘 이해할 수 있음
        Runner.run(new Divide(), 40 , 20);

        //부모쪽에서 메소드 콜을 할 때 자식 메소드를 호출할 때(오버라이드 된 자식걸 사용할 때) 이걸 가상 메소드 콜이라고 부른다.

    }
}
