package com.company.ch09.DoitCoding;

public class QuickSort implements Sort{

    @Override
    public void ascending(int[] arr) {
        System.out.println("QuickSort ascending");
    }

    @Override
    public void descending(int[] arr) {
        System.out.println("QuickSort descending");
    }

    @Override
    public void description() {
        Sort.super.description();
        System.out.println("Quicksort 입니다.");
    }
}
