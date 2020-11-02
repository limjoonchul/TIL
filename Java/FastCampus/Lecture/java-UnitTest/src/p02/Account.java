package com.company.p02;

// POJO : Plain old java object 자바클래스를 의미하는 것인데 클래스파일하나가 이런 자바만으로 이루어진 파일이 있을 수 있고 스프링이 묻어있는 애들이 있을 수 있어서
// 순수자바만 이용된걸 포조라고 부른다.
// - 이렇게 불러야 사람들이 그럴듯해 보여서 자바 클래스를 쓰더라
// 이런언어들은 사람들의 인지적인 관점을 적용하려 애쓴다. 그래서 이러한 용어들을 만들어내고 하는 것이다. 어이없지만 생각보다 중요한 것들이다.
public class Account {
    private  int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw(int value){
        balance -= value;
    }

    public void deposit(int value){
        balance += value;
    }

    public boolean isMinus(){
        return balance < 0;
    }

    public void throwExcept(){
        throw new ArithmeticException();
    }
}
