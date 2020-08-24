package com.company.ch09.DoitCoding;

import java.io.IOException;
import java.util.ArrayList;

public class SortTest {
    public static void main(String[] args) throws IOException {


        System.out.println("정렬방식을 선택하세요");
        System.out.println("B : BubbleSort ");
        System.out.println("H : HeapSort");
        System.out.println("Q : QuickSort");

        int ch = System.in.read();
        Sort sort = null;
        if(ch == 'B' || ch == 'b'){
            sort =new BubbleSort();

        }else if(ch == 'H' || ch == 'h'){
            sort =new HeapSort();

        }
        else if(ch == 'Q' || ch == 'q'){
            sort =new QuickSort();

        }else{
            System.out.println("지원하지 않는 기능입니다.");
            return;
        }
        sort.ascending(new int[]{1, 2, 3, 4, 5});
        sort.descending(new int[]{1, 2, 3, 4, 5});
        sort.description();


    }
}
