package com.company.ch08.Abstract;

public class ComputerTest {
    public static void main(String[] args) {
        Computer computer = new DeskTop();
        computer.display();
        computer.turnOff();

        Computer mynote = new MyNoteBook();
        mynote.display();
        mynote.turnOff();
    }
}
