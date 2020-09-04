package com.company;

public class SpeedConverter {
    public static long toMilesPerHour(double kilometersPerHour){
        if(kilometersPerHour < 0){
            return -1;
        }else{
            return Math.round(kilometersPerHour/1.60934);
        }
    }
    public static void main(String[] args) {
        System.out.println(toMilesPerHour(14.3));
    }
}
