package com.company.ch09.BookShelf;

public class BookShelf extends Shelf implements Queue{
    @Override
    public void enQueue(String title) { //배열에 요소추가
        shelf.add(title);
    }

    @Override
    public String deQueue() { //맨 처음 요소를 배열에서 삭제하고 반환
        return shelf.remove(0);
    }

    @Override
    public int getSize() { //배열 요소 개수 반환
        return getCount();
    }
}
