package com.company.ch09.BookShelf;

public interface Queue {
    void enQueue(String title);
    String deQueue();

    int getSize();
}
