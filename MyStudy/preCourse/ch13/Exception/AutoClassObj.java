package com.company.ch13.Exception;

public class AutoClassObj implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("close()가 호출되었습니다.");
    }
}
