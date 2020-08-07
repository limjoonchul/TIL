package com.company;

import java.util.Arrays;
import java.util.Vector;

class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        // write codes here

        return String.format("%f, %f",x,y);
    }
}

class RectCore {
    protected Vector2D pos;
    protected float w, h;

    public RectCore(float x, float y, float w, float h) {
        this.pos = new Vector2D(x, y);
        this.w = w;
        this.h = h;
    }

    public String toString() {
        // write codes here
        return String.format("%f, %f, %f",pos.toString(),w,h);
    }
}
public class Rect extends RectCore {

    Vector2D [] v2 = new Vector2D[4];
    {
        v2[0] = pos;
        v2[1] = new Vector2D(pos.x,pos.y+h);
        v2[2] = new Vector2D(pos.x+w,pos.y+h);
        v2[3] = new Vector2D(pos.x+w,pos.y);
    }

    public Rect(float x, float y, float w, float h) {
        super(x, y, w, h);
    }
    public float getArea() {
        // write codes here
        float area = w * h;

        return area;
    }

    public Vector2D getCenterOfMass() {
        // write codes here
        float a = (float) (this.pos.x + (w * 0.5));
        float b = (float) (this.pos.y + (h * 0.5));

        return new Vector2D(a, b);
    }

    public Vector2D [] getAllPoints() {

        return v2;
    }

    public void rot90(Vector2D pivot) {
        // write codes here
        Vector2D [] v3 = new Vector2D[4];
        float x1 = (float) ((float)pivot.x - 0.5*h);
        float x2 = (float) ((float)pivot.x + 0.5*h);
        float y1 = (float) ((float)pivot.y - 0.5*w);
        float y2 = (float) ((float)pivot.y + 0.5*w);


        v3[0] = new Vector2D(x1,y1);
        v3[1] = new Vector2D(x1,y2);
        v3[2] = new Vector2D(x2,y2);
        v3[3] = new Vector2D(x2,y1);

        for (int i =0; i<v2.length; i++){
            System.out.println(v3[i]);
        }

    }

    public String toString() {
        // write codes here
        return v2[0].toString() + " "+ v2[1].toString() + " "
                +v2[2].toString() + " " + v2[4].toString();

    }
}

class RectTest{
    public static void main(String[] args) {
        Rect rect = new Rect(0.5f, 0.7f, 1.5f, 2.3f);
        System.out.println("Area: " + rect.getArea());
        System.out.println("CoM: " + rect.getCenterOfMass());
        System.out.println("All Points: " + Arrays.toString(rect.getAllPoints()));

        rect.rot90(new Vector2D(0.4f, 0.2f));
        System.out.println("Rotated rect: " + rect);
    }
}
