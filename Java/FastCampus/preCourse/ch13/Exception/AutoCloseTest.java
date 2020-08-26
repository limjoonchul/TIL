package com.company.ch13.Exception;

public class AutoCloseTest {
    public static void main(String[] args) {
        try (AutoClassObj obj = new AutoClassObj()) {
            throw new Exception();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
