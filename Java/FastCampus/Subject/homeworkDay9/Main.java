package com.company;
/**
 * List 인터페이스를 구현하여 IntArrayList 클래스를 완성하시오.
 *
 * List는 순서가 있는 연속된 값으로, List 인터페이스에 선언되어 있는 메소드의 기능은 아래와 같다.
 *
 * - append(): List의 마지막에 value를 삽입한다.
 * - prepend(): List의 시작점에 value를 삽입한다. 기존의 데이터를 한 칸씩 뒤로 옮긴다.
 * - insert(): index에 value를 삽입한다. 기존 데이터를 한 칸씩 뒤로 옮긴다.
 * - remove(): index의 value를 삭제한다. 기존 데이터를 한 칸씩 앞으로 당긴다.
 * - get(): index의 value를 반환한다.
 * - length(): List의 길이를 출력한다.
 *
 * IntArrayList는 int []를 이용하여 List를 구현한다.
 * - 생성자에서는 capacity를 입력받으며, 배열의 크기가 부족할 때마다 2배씩 증가시킨다.
 */
interface List {
    public void append(int value);
    public void prepend(int value);
    public void insert(int index, int value);
    public void remove(int index);
    public int get(int index);
    public void length();
}

class IntArrayList implements List {
    private int capacity;
    private int []array;
    private int last;
    private int first;


    public boolean is_empty(){
        return this.last == 0;
    }
    public IntArrayList(int capacity){
        this.capacity = capacity;
        this.last = 0;
        this.first = 0;

        array = new int[capacity];
    }


    @Override
    public void append(int value) {

        if (last == capacity){
            int [] new_array = new int[capacity * 2];
            System.arraycopy(array,0,new_array,0,array.length);
            array = new_array;
            capacity = capacity *2;
        }
        array[last++] = value;

    }

    @Override
    public void prepend(int value) {
        if (last == capacity){
            int [] new_array = new int[capacity * 2];
            System.arraycopy(array,0,new_array,0,array.length);
            array = new_array;
            capacity = capacity *2;
        }

        for (int i=last+1; i>first; i--){
            array[i] = array[i-1];
        }
        array[first] = value;
        last++;

    }

    @Override
    public void insert(int index, int value) {
        if (last == capacity){
            int [] new_array = new int[capacity * 2];
            System.arraycopy(array,0,new_array,0,array.length);
            array = new_array;
            capacity = capacity *2;
        }
        for (int i=last+1; i>index; i--){
            array[i] = array[i-1];
        }
        array[index] = value;
        last++;

    }

    @Override
    public void remove(int index) {
        if (is_empty()){
            System.out.println("배열이 비어있습니다.");
        }
        for (int i=index; i<last; i++){
            array[i] = array[i+1];
        }
        array[last] = 0;
        last--;

    }

    @Override
    public int get(int index) {
        return array[index];
    }

    @Override
    public void length() {
        System.out.println(last);
    }
}
public class Main {

    public static void main(String[] args) {
	// write your code here

        IntArrayList ial = new IntArrayList(10);

        for (int i = 0; i<15; i++){
            ial.append(i);
        }

    }
}
