package com.company.ch12.Lambda;

public class IntComImpl implements IntConcat{
    @Override
    public int Concat(int x, int y) {
        return x>=y ? x: y;
    }
}
