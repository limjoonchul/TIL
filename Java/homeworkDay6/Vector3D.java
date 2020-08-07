package com.company;


public class Vector3D {
    float x;
    float y;
    float z;

    Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;

    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }


    public Vector3D add(Vector3D v2) {

        return new Vector3D(this.x + v2.x,this.y + v2.y,this.z + v2.z);
    }
    public Vector3D add(float f) {

        return new Vector3D(this.x + f,this.y + f,this.z + f);
    }

    public Vector3D sub(Vector3D v1) {

        return new Vector3D(this.x - v1.x,this.y - v1.y,this.z - v1.z);

    }
    public Vector3D sub(float f) {

        return new Vector3D( this.x - f,this.y - f,this.z - f);

    }

    public float inner(Vector3D v2) {
        float num = this.x * v2.x + this.y * v2.y + this.z * v2.z;

        return num;
    }

    public Vector3D mult(float v) {
        this.x *= v;
        this.y *= v;
        this.z *= v;

        return this;
    }

    public float mag() {
        float f = (float) Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
        return f;
    }

    public void print(){

        System.out.printf("%.3f,%.3f,%.3f\n",x,y,z);
    }

}

class Vector3DTest{
    public static void main(String[] args) {
        Vector3D v1 = new Vector3D(0.5f, 0.2f, 0.9f);
        Vector3D v2 = new Vector3D(0.8f, 0.1f, 1.3f);


        v1.add(v2).print();
        v2.sub(v1).print();
        v1.add(0.2f).print();
        v2.sub(0.05f).print();
        System.out.println(v1.inner(v2));
        v1.mult(1.2f).print();
        System.out.println(v2.mag());

}
}