package practice;

import java.util.Stack;

/**
 * AbstractStack
 * -> stack
 *
 * is_empty() : 비어있는지 확인
 * push( ): 넣는것
 * pop() : 빼는것
 * peek() : 최고윗부분만 확인.
 *
 */


// abstract class AbstractStack{ 만 놓고 혼자서 복귀해 봐야함 이런걸 해봐야함.
abstract class AbstractStack{
    protected int capacity; //총 용량
    protected int top;

    public AbstractStack(int capacity){
        this.capacity = capacity;
        this.top = 0;
    }

    public boolean is_empty(){
        return this.top == 0; //top 이 0이면 비어있는것임.
    }

    public abstract void push(int value);
    public abstract int pop();
    public abstract int peek();
}

class ArrayStack extends AbstractStack{
    private int [] array;

    public ArrayStack(int capacity){
        super(capacity);
        array = new int[capacity];
    }

    @Override
    public void push(int value) {
        if(top == capacity){//capacity가부족하면 용량이 두배 증가함.
            int [] new_array = new int[capacity * 2];
            System.arraycopy(array, 0, new_array, 0, array.length);
            array = new_array;
            capacity *=2;
//            for (int i = 0; i<array.length; i++){
//                new_array[i] = array[i];
//            }
        }
        array[top++] = value;
        //top++;
    }

    @Override
    public int pop() {
        if(is_empty()){
            return -1;
        }
        return array[--top]; //값이 들어오면 탑은 +1되기때문에 pop은 거기서 하나빼고 출력해야함.
    }

    @Override
    public int peek() {
        if(is_empty()){
            return -1;
        }
        return array[top-1];
    }
}

public class AbstractClassPractice {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        for (int i=0; i<15; i++){
            stack.push(i);
        } //입력 0부터 9
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        for (int i=0; i<20; i++){
            System.out.println(stack.pop());
        }//출력 9~0

    }
}
