package com.company;

import java.util.ArrayList;

public class DomainReverse {
    public static void main(String[] args) {
        String str = "www.google.com";

        String []arr = str.split("\\.");

        for(int i=0; i<arr.length; i++){
            for (int j=arr[i].length()-1;j>=0; j--){
                System.out.print(arr[i].charAt(j));
            }
            if(i<arr.length-1){
                System.out.print(".");
            }
        }

    }
}
