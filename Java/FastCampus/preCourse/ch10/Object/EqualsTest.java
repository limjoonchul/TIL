package com.company.ch10.Object;
class Student{
    int studentNum;
    String studentName;

    public Student(int studentNum, String studentName){
        this.studentName = studentName;
        this.studentNum = studentNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            Student std = (Student)obj;
            if(this.studentNum == std.studentNum){
                //이건 객체의 변수가 같은걸 비교하는 것이다
                //lee == won은 객체자체를 비교하는 것
                /**
                 * 객체의 변수비교하는 것과 객체 자체를 비교하는건데
                 * 왜 같은걸 비교하고 있지라고 멍청한 생각을 하고 있었다..
                 * 쉐엣
                 * */
                return true;
            }
            else return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return studentNum;
    }
}
public class EqualsTest {
    public static void main(String[] args) {
//        String str1 = new String("abc");
//        String str2 = new String("abc");
//        System.out.println(str1 == str2); //메모리 주소가 같은가 를 확인
//        System.out.println(str1.equals(str2));//두개의 문자열이 같은가를 확인

        Student lee = new Student(100,"이상원");
        Student lee2 =lee;
        Student won = new Student(100,"이상원");

        System.out.println(lee == lee2); //true
        System.out.println(lee == won);//false
        System.out.println(lee.equals(won)); //false

        System.out.println(lee.hashCode());
        System.out.println(won.hashCode());

        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1.equals(i2));
        System.out.println(i1.hashCode());
        System.out.println(i2.hashCode());

        System.out.println(System.identityHashCode(i1));
        System.out.println(System.identityHashCode(i2));

    }
}
