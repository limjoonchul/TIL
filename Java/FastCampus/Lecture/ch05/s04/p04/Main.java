package com.company.s04.p04;

/**
 * 로컬 내부 클래스
 * 클래스 영역이 아닌 로컬 영역에 선언된 클래스
 * 아우터클래스라는게 없는 상태이다
 */

class LocalInnerClass{
    int x = 1;
    public Object methodA(){
        final int y1 =2;
        int y2 = 2;
        // 이 메소드안에서만 존재한다 내부에서만 쓰임 외부에서는 쓰일 수 없다 클래스가
        // 외부 클래스에 영향을 주지 않음음
        // 로컬 내부클래스는 스택에 저장이됨
        class LocalInner{ //메소드밖에서 아무의미가 없어짐 로컬변수처럼
            // 정적 변수를 가질 수 없다. 이유는 스택메모리에 클래스가 생기므로
            // 스태틱변수는 스태틱영역에 생겨야 하므로 static final만 사용 가능 하다
            // final은 상수풀에 저장되어 사용하므로

            // static int z = 4 불가능
            // static final  int z =4 는 가능 스태틱파이널은 생명주기가 존재하지 않는다. 생명주기가 존재할 필요가 없음
            // 왜냐하면 이건 코드가 작성하고 프로그램을 실행할 때 어딘가에 이미 적혀있기 때문에

            /**
             * 정리
             * 메소드는 객체에 속해있어서 메소드콜이되면 메소드콜스택에 쌓여 올라가니깐
             * 메소드 내부에서 사용하는 모든 로컬변수,로컬클래스는 스택에 쌓여올라감
             * 메소드가 시작할 때 메모리에 위치하게되고 끝나면 없어지게 된다.
             * 그런데 static 변수는 클래스가 존재하면 영속하는 변수여서 localInner는 메소드가 호출할 때
             * 생성되고 없어지면 사라지므로 localInner안에 static이 있어도 영속적으로 존재할 수 없게 되므로
             * 잇을 수 없다.
             *
             * final 은 이게 붙으면 수정되지 않는 상수가 됨 특별히 허용한 케이스이다,
             * 왜냐하면 따로 메모리를 잡을 필요가 없기 때문에 리터럴을 저장하기 위해서 어떤 메모리는 이미 코드영역에 잡혀잇기 때문에
             * final은 상수풀에서 값을 가져오기 때문에 복사해서
             * 이미 메모리가 잡고 있다. 구현상 메모리를 사용하지 않아서
             * static은 메모리가 필요하다
             * LocalInner는 원래 클래스영역에 있어야하는데 스택영역에 있다.
             * 원래 static 변수는 LocalInner와 같이 클래스영역에 잡혀야 하는데 LocalInner가 스택영역에
             * 잡혀있어서 static 변수는 사용 불가
             */
            void methodA(){
                System.out.println(x);
                System.out.println(y1);//1.7에서는 final인경우에만 가능했다.


                System.out.println(y2); // 1.8에서 final이 아닌경우에도 접근 가나ㅡㅇ
                // 대신 값을 수정할 수는 없게 되어 있다. final이 아닌데 final 처럼 사용됨

//                y2++;

            }
        }
        //4:50 진짜 뭔소린지 하나도 못알아먹음 다시 들어야됌*******
        LocalInner inner = new LocalInner(); // 애는 힙영역
        inner.methodA();
        /**
         * 로컬이너클래스가 외부 클래스를 상속할 때
         * 가상메소드콜로 methodA가 호출이 될 수 있다다
         *
         * 힙에있는 놈이 스택에 있는 변수들을 참조할 수없는데
         * 여기서는 메소드가 끝난후에도 접근이 가능하게 됫다
         * 그래서 로컬변수답게 하기 위해 final만 사용하게 되었는데
         * 불편하니 final없이 사용하게 한다.
         */
        return  (Object)inner;
    }
   static{
        int y =2;
        class LocalInner{
            void methodA(){
                System.out.println("a");
            }
        }

    }

    {//인스턴스 초기화블록
        int y =2;
        class LocalInner{
            void methodA(){
                System.out.println(x);
            }
        }
    }

    public static void main(String[] args) {
        int y =2;
        class LocalInner{
            void methodA(){
                System.out.println("a");
            }
        }

    }
}


public class Main {
}
