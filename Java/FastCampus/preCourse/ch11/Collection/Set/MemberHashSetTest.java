package com.company.ch11.Collection.Set;

public class MemberHashSetTest {
    public static void main(String[] args) {
        MemberHashSet manger= new MemberHashSet();
        Member memberLee = new Member(100,"LEE");
        Member memberKim = new Member(200,"Kim");
        Member memberPark = new Member(300,"Park");
        Member memberPark2 = new Member(300,"Park2");
        /**
         * 원래 set은 중복을 허용하지 않아서 memberpark과 park2의 memberID가
         * 같은 값을 넣었을 때 입력이 되지 않아야 하는데, 여기서는 입력이 된다  그 이유는
         * HashSetTest클래스에서 String은 jvm에서 이미 만들어져있어서 프로그램자체적으로
         * 동일한 값을 구별할 수 있는데 여기서 member는 두개의 객체가 다르다고 정의가 되어 있지 않아서
         * 구별을 하지 못함. member클래스에서 구현을 해야함
         *
         * HashSet을 사용할 때 이게 관리할 오브젝트가 논리적으로 같다라고 정의가 되어있는지
         * 확인을 해야한다. 안되어있으면 구현해야한다.
         * Integer나 String같은 경우는 이미 정의가 되어있기 때문에 잘 돌아간다.
         */


        manger.addMember(memberLee);
        manger.addMember(memberKim);
        manger.addMember(memberPark);
        manger.addMember(memberPark2);

        manger.showAllMember();

        manger.removeMember(100);
        manger.removeMember(200);

        manger.showAllMember();


    }
}
