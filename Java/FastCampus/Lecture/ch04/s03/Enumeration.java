package com.company.s03;


/**
 * 열거형 enumeration
 * enum 키워드로 표현
 * '이넘' 이라고 부름
 * 내부적인 구현은 enum ->java.lang.Enum 클래스를 상속 하는 것
 * enum은 다른 클래스를 상속하지 못함 이미 상속하는 클래스(java.lang.Enum) 가 있기 때문에
 *
 * 열거형은 다른 클래스를 상속하지 못하지만, 인터페이스 구현은 가능
 * 열거형 타입에는 열거형 상수와 null 값 할당 가능
 */

// 클래스의 일종 자료형으로 쓰일 수 있음.
enum Job{//실제 값은 0인데 자바에서는 숫자 개념으로 사용하지 말라는 것이 권고이다. 각 상수는 0부터 숫자를 가지지만, 심볼로만 사용하고 숫자는 사용하지 않음.
    STUDENT, MARKETING, DEVELOPER, CEO, CHIEF_EXECUTIONAL_OFFICER //열거형 상수 대문자 단어 구문은 _ 로 표현
}

class Foo{
    enum Symbol{ // 특정 범위 안에서 사용가능
        ONE,TWO,THREE;
    }
}

// 열거형 내부에서 메소드 구현
enum Symbol{
    ONE,TWO,THREE,FOUR;
    public Symbol nextSymbol(){
        if(this.equals(ONE)){ // 클래스 멤버변수 과 마찬가지로 바로 사용 가능
            //각자가 객체로 사용됨
            return TWO;
        }else if(this.equals(TWO)){
            return THREE;
        }else if(this.equals(THREE)){
            return FOUR;
        }else{
            return ONE;
        }
    }
}
// 열거형 생성자를 이용한 enum 상수 초기화

enum Family{
    FATHER("아버지"), MATHER("어머니"), SON("아들"), DAUGHTER("딸"); //열거형 상수(객체)
    // 여기서 객체가 생성되는 것임. 처음한번만 생성되는 거임


    private String koreanWord; //멤버변수(객체에 속하는 변수)

    public String getKoreanWord() {
        return koreanWord;
    }

    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;
    }

    //private는 생략 가능 public 안됨 강제 private  / 안에서 생성되기 때문에
    private Family(String koreanWord){ //객체의 생성자는 string을 입력받게 되어있는데 각각 만들때 각 객체에 값을 넣어줘야한다. FATHER("아버지")
        this.koreanWord = koreanWord;
    }

}

public class Enumeration {
    public static void main(String[] args) {
        Job job = Job.CHIEF_EXECUTIONAL_OFFICER; // 클래스 변수와 유사하게 사용

        if(job == Job.CHIEF_EXECUTIONAL_OFFICER){
            System.out.println("사장님 안녕하세요?");
        }

        char c = 'A';
        switch (c){
            case 'A':
                break;
            case 'B':
                break;
            case 'C':
                break;
            default:
        }

        switch (job){ // switch ~ case 문에는 열거형 자료형을 생략 열거형 상수만 사용
            case STUDENT:
                System.out.println("you will be a great one");
                break;
            case MARKETING:
                System.out.println("you sells");
                break;
            case DEVELOPER:
                System.out.println("you make things");
                break;
            case CHIEF_EXECUTIONAL_OFFICER:
                System.out.println("you choose");
                break;
            default:
                System.out.println("who are you?");
        }

        System.out.println(Foo.Symbol.ONE); //이넘이 그대로 출력됨 결과값 : ONE; 출력을하면 자동으로 toString()으로 변환되서 스트링으로 출력됨.
        // 숫자로서 사용되는게 굉장히 제한됨. 최대한 막혀 있음


        // 열거형 메소드
        Symbol sym = Symbol.ONE; //애는 객체
        Symbol nextSym = sym.nextSymbol(); //객체는 메소드를 실행할 수 있어서 사용가능 열거형 상수는 객체이다 .그래서 메소드를 실행할 수 있다.
        System.out.println(nextSym); //TWO
        nextSym = nextSym.nextSymbol();
        System.out.println(nextSym);


        // 열거형 생성자와 멤버 변수 활용
        Family fam = Family.SON;
        System.out.println(fam.getKoreanWord());
        fam.setKoreanWord("버린 자식");
        System.out.println(fam.getKoreanWord());
        System.out.println(Family.SON.getKoreanWord()); //처음한번만 생성되는 거임
    }
}
