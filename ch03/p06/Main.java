package com.company.p06;

/**
 * Getter, Setter
 * 멤버 변수를 간접적으로 다룰 수 있게 해주는 메소드
 * 멤버 변수의 캡슐화(Encapsulation)에 도움 됨.
 *           -> 정보의 은닉/ 보호
 * 멤버 변수의 값을 제한해야 할 때 유용.
 */
public class Main {
    int x,y;
    String z;
    float w;

//    public Main(int x, int y, String z, float w) { //생성하면 디폴트 컨스트럭터가 사라짐.
//        this.x = x;
//        this.y = y;
//        this.z = z;
//        this.w = w;
//    }

    //멤버변수를 만들어야 생성가능.
    public int getX() { //경우에 따라 구현되지 않을 수 있다. 출력하지 않으면 사용 안하는 것.
        return x;
    }

    public void setX(int x) {
        if(x > 0 && x <= 1000){
            this.x = x;
        }else{
            System.out.println("x should be 1 < x <= 1000!");
            System.out.println("however, you put in x = "+x);
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}

class MainTest{
    public static void main(String[] args) {
        Main m = new Main();
        m.x=10; //x에 private를 넣으면 직접 값을 변경할 수 없음.
        System.out.println(m.x);

        m.setX(20);
        System.out.println(m.getX()); //권장된 처리 방식.

        m.x = 20; // 권장하지 않는 멤버 변수 처리 방식

        m.setX(11111);
        System.out.println(m.getX());

    }
}