package com.company.s07.p03;

/**
 * 공변 반환 타입 (Covariant return type)
 */

class Foo{
    public Foo getInstance(){
        return this;
    }
}
class Bar extends Foo{
    @Override
    public Bar getInstance(){ //오버라이딩이지만, 리턴 타입이 달라질 수 있다. 여기서는 예외적으로 반환값이 달라진다. 공변 반환 타입
        return this;
        //return (Foo)this; 이거와 같다.
    }
}

public class Poly03 {
}
