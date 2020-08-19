package com.company.ch05.StaticEx;

import java.util.Calendar;

public class CompanyTest {
    public static void main(String[] args) {
        Company company1 = Company.getInstance();

        Company company2 = Company.getInstance();
        System.out.println(company1);
        System.out.println(company2);

        Calendar calendar = Calendar.getInstance(); //애도 싱글턴 패턴으로 되어있음.



    }
}
