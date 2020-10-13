package com.company.s01.p02;

/**
 * 팩토리 패턴
 * - 구상 클래스 객체(추상 클래스를 구현한 것, Concrete class 추상의 반대의미 구상)를 전담하여 생성하는 클래스를 구현하는 패턴
 *
 */
interface Shape{
   void draw();
}

class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("Rectangle drawn");
    }
}

class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("Square drawn");
    }
}

class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Circle drawn");
    }
}

// 팩토리 메소드 패턴
// 팩토리 클래스가 다른 클래스에서 가지게 될 의존성을 모두 가져온다.
// 모든 의존성이 이 코드안에 모여있다.
class ShapeFactory{
    Shape getShape(String shapeType){
        Shape shape = null;
        // 기존 다형성 구현 방법 이렇게 되어있을 때 매번 직접 객체를 생성할 경우,
        // 새로운 구상 클래스가 만들어졌을 때 코드 수정이 불가피해진다.
        // 클래스가 구상 클래스에 의존하게 된다.
        // 구상클래스는 구체적인 구현이기때문에 내용이 자주바뀔수있다고 가정을하고해야한다 그래서 여기에 의존하지 않는게 좋다. 바람직하지 않음
        // 클래스는 추상클래스 혹은 인터페이스에 의존하는 것이 더 바람직하다 라는 기본 설계 철학이 있다. 그래서 만들어주는게 팩토리 메쏘드 패턴
        if (shapeType.equals("Circle")){
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")){
            shape = new Rectangle();
        } else if (shapeType.equals("Square")){
            shape = new Square();
        }
        return shape;
    }
}

// 추상 팩토리 패턴 (Abstract factory pattern)
abstract class AbstractFactory{
    abstract  Shape getShape(String shapeType);
}

class RoundedShapeFactory extends AbstractFactory{
    @Override
    Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("Circle")){
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")){
            shape = new RoundedRectangle();
        } else if (shapeType.equals("Square")){
            shape = new RoundedSquare();
        }
        return shape;
    }
}

class NormalShapeFactory extends AbstractFactory{

    @Override
    Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("Circle")){
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")){
            shape = new Rectangle();
        } else if (shapeType.equals("Square")){
            shape = new Square();
        }
        return shape;
    }
}

class RoundedRectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("RoundedRectangle drawn");
    }
}

class RoundedSquare implements Shape{

    @Override
    public void draw() {
        System.out.println("RoundedSquare drawn");
    }
}


class FactoryProducer{
    public static AbstractFactory getFactory(boolean rounded){
        if(rounded){
            return new RoundedShapeFactory();
        } else{
            return new NormalShapeFactory();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        String shapeType = "Circle";
        Shape shape;
        // 기존 다형성 구현 방법 이렇게 되어있을 때 매번 직접 객체를 생성할 경우,
        // 새로운 구상 클래스가 만들어졌을 때 코드 수정이 불가피해진다.
        // 클래스가 구상 클래스에 의존하게 된다.
        // 구상클래스는 구체적인 구현이기때문에 내용이 자주바뀔수있다고 가정을하고해야한다 그래서 여기에 의존하지 않는게 좋다. 바람직하지 않음
        // 클래스는 추상클래스 혹은 인터페이스에 의존하는 것이 더 바람직하다 라는 기본 설계 철학이 있다. 그래서 만들어주는게 팩토리 메쏘드 패턴
        if (shapeType.equals("Circle")){
            shape = new Circle();
        } else if (shapeType.equals("Rectangle")){
            shape = new Rectangle();
        } else if (shapeType.equals("Square")){
            shape = new Square();
        } else {
            shape = null;
        }

        if (shape != null){
            shape.draw();
        }

        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("Circle");
        shape1.draw();
        // 이렇게되면 구상클래스에 의존하게 되지 않는다.
        // 여기서 의존성이란 상속이라던지 has-a관계 를 의미한다
        // shape = new Circle()과 같은 건 생성자가 호출이 되어서 참조하고 있을 때 이 클래스들의 의존성을 가지게 된다라는 의미


        // 이미 많은 GUI 같은 것들이 이런식으로 활용을 하고 있다. 우리가 팩토리패턴을 이해하고 있다면 추상클래스와 구상클래스가 어떻게 되어있는지 알수 있다
        // 모르면 우리가 왜 이렇게 사용해야하는지 몰라서 이해하는게 좋다.
        // 해당 클래스의 독립성을 좋게 하기 위해서 사용한다.  설계 철학은 의존성을 줄이는 방법으로 했다
        Shape shape2 =  FactoryProducer.getFactory(true).getShape("Rectangle");
        shape2.draw();

    }
}
