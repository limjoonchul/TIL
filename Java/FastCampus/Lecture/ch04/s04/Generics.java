package com.company.s04;

/**
 * 제네릭(Generics)
 * - 대상 객체의 타입을 입력받아서 사용하는 형식
 * - 미리 사용할 수 있는 타입을 명시해서 컴파일 타임에 체크 가능.
 *   - 일부 타입을 제한할 수 있는 기능이 있음.
 *   - 입력을 object로 할 수 있으나, 런타임에 instanceof로 객체를 체크해야 함.
 *     원하는 객체가 들어왔는지 계속확인해야함.
 *   - 제네릭을 사용할 경우 이러한 과정 없이 간결하게 코드 작성을 할 수 있다.
 *
 */
//제네릭 클래스

//일반 클래스
class Foo{

}


// 제네릭 클래스
// 클래스를 선언할 때에는 설정되지 않은 타입이 있으며, 이것을 타입 파라미터로 표현한다.
class GenericFoo<T> { // T: 타입 파라미터
    String name;
    T memberVar; // T를 자료형으로 사용.

    public GenericFoo(String name, T memberVar) {
        //생성자 생성 어떤타입의 자료형이 들어오는지 모르는데 구현 가능.
        this.name = name;
        this.memberVar = memberVar;
    }
}
//제네릭 인터페이스
interface GenericInterface<T>{ //인터페이스도 제네릭이 가능

}
class HashMap<k,w>{ // 여러개의 타입 파라미터로 쓸 수 있다.

}

//주의 해야할 문법들
class  GenericBar<T>{
    //제네릭은 객체를 생성할때 자료형을 입력받기때문에. 스태틱은 객체생성전에 자료형이 결정되어야 하기때문에 / 클래스에 속하기 때문에 t를사용하면 자료형 알 수 없음.
    // static은 클래스에 속하기 때문에 애초에 t가 쓸수 없음 문법적 문제
    // static T classVar; // 안된다.. 클래스의 스테틱변수는 객체와 관계없이 클래스에 속해있기 때문에 스태틱 변수가 생겨야하는 생서시점에는 t 자료형이 없기 때문에
    // 스태틱은 자료형마다 하나씩 있기 때문에 불가능?? 이건 모르겟음
    //static void method(T var){} //이것도 마찬가지로 불가능.

    // 문법적으로 문제가 없을 것 같으나, 안정성 문제로 금지. 암기해야 하는 것이다. 되게 할 수 있지만 실수가 날 확률이 있다.
    //T memberVar = new T(); t는 new 키워드를 쓸 수 없음. -  안정성 문제 때문에 불가능하다. 티라는 자료형이 결정되지 않기때문에 생성자가 어떻게 정의될지 모르기 때문에 불가능 자바에서 막혀있음.
    // 타입파라미터의 객체를 생성하는 것은 불가능 하다.
//    {
//        Object obj = new Object();
//        if(obj instanceof T){ // 불가능 어떤 객체가있을대 t에속하는지 확인하기 불가능 안정성 문제 때문에 그냥 막혀있음.
//
//        }
//    }
}


// 제네릭 타입의 상속

//제네릭클래스
class GFoo<T>{

}
//제네릭 인터페이스
interface IGFoo<D>{

}

// 이클래스도 제네릭클래스이다 T,D라는 타입파라미터를 사용 GFOO를 상속하는데 사용되고 IGFOO를 상속하는 경우에 사용됨.
class  GIGFoo<T,D> extends GFoo<T> implements IGFoo<D>{

}



// 만약 위의 타입 파라미터가 같을경우 다르게 넣어주면됨
//class GFoo<T>{
//}
// //만약 위의 타입 파라미터가 같을경우 다르게 넣어주면됨
//interface IGFoo<T>{
//}
//class  GIGFoo<T,D> extends GFoo<T> implements IGFoo<D>{
//}

// 타입 파라미터는 부모 제니릭의 타입 파라미터를 모두 채워 주어야 한다.
// 추가적인 타입 파라미터도 사용할 수 있다.
//class  GIGFoo<K,T,D> extends GFoo<T> implements IGFoo<D>{
//}

// 부모 클래스/인터페이스들에 동일한 타입 파라미터를 넘겨 줄 수 있다.
class  IGGIGFoo<T> extends GFoo<T> implements IGFoo<T>{
}


// 타입 제한
//타입 제한을 하지 않으면 extends object와 동일하다.
class GenericNoTypeLimit<T extends Object>{
}

//extends를 이용해서 부모클래스를 제한 할 수 있음// 추상클래스를 상속하면서 인터페이스를 추가로 구현할 수 있음  추상클래스+인터페이스를 구현해야한다
class GenericTypeLimitation<T extends Number & Cloneable>{

}
// 와일드카드
class WildFoo{

}
class WildBar extends WildFoo{

}
class WildGeneric<T>{

}

// 와일드카드 ?는 메소드의  입력 타입에 제네릭이 쓰일 경우,
// 제네릭의 타입 변수를 제한할 수 있다. 그냥 ?만쓸경우 ~~고 extends를 쓸 경우 제한할 수 있음
class WildCard{
                                   //<>명시할 수도 있고 명시하지 않을 수 있고 ?를 사용할 수 있음.
     //extends 조상이하의 것들은 다사용할 수 있음.
    public void method1(WildGeneric<?> x){} //입력파라미터로 제네릭을 사용한다면 제네릭의 타입변수는 ?를사용해서 미리 정하지 않을 수 있다.여기선 아무거나 들어올 수 있다.
    public void method1_Eq(WildGeneric<? extends Object> x){} //object가 상한이고 -> 모든 클래스를 사용할 수 있음.(모든 클래스는 object를 상위클래스로 갖고있기 때문에)
    public void method2(WildGeneric<? extends WildFoo> x){} // 가장 조상이 어디까지인지 제한을 하는 것 / 여기선 wildfoo, wildbar 사용가능
    public void method3(WildGeneric<? super WildBar> x){} //object, wildfoo, wildbar 사용가능. 여기서 whildbar 이상의 것들 하한선이 whildbar
}
// 제네릭 메소드
class GenericMethod{
    //정적 메소드도 가능. 제네릭클래스의 타입파라미터와 제네릭 메소드의 타입 파라미터는 별개다. 메소드 자체의 타입변수이다. 클래스 제네릭 타입으로 제네릭변수와메소드 생성 불가능
    // t에 프리미티브 타입은 불가능.
    public static <T> T staticMethod (T t){ // T로 자료형이들어오면 알아서 결정이됨.
        return t;
    }


    public int method(int x){
        return x;
    }
          //타입 파라미터를 명시 할 수 있음.
    public <T> T method(T x){
        return x;
    }
}


public class Generics {
    public static void main(String[] args) {
        GenericFoo<String> foo = new GenericFoo<String>("name","var"); //**생성할 때 자료형을 정해주게 되어 있음.**
        // <>안에 스트링으로 넣어줬기 때문에 T도 스트링으로 된다.

        GenericFoo<String> foo1 = new GenericFoo<>("name","var");  //뒤에 <>생략가능

//        GenericFoo<int> 프리미티브 타입 불가능.

        System.out.println(foo.name);
        System.out.println(foo.memberVar);

        GenericFoo<Integer> foo2 = new GenericFoo<>("name1",10);
        System.out.println(foo2.name);
        System.out.println(foo2.memberVar);

        // 동적 바인딩이란 메소드가 생길때 자료형이랑 정의되어있어서 기계가동작하게 만들어지는게 정상적인데 자료형이 정해져있지않아서 들어오는거에따라 동작이 달라짐 실제로 런타임이 되지않으면
        //컴파일타임이 알 수 없음.
        // 동적 바인딩 -> 컴파일타임에는 동작이 완전히 정의가 되지 않음.
        // 런타임에 결정이 된다.
        GenericMethod.staticMethod(new String(""));
        GenericMethod.staticMethod("abcd");
        GenericMethod.staticMethod(12342);
    }
}
