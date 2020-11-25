package com.company.pholymorphism3;

public class BeanFactory { // 객체 공장 팩토리 패턴활용

    public Object getBean(String id){
        if (id.equals("lg")){
            return new LGTv();
        } else if(id.equals("samsung")){
            return new SamsungTv();
        } else if(id.equals("google")){
            return new GoogleTv();
        }
        return null;

    }
}
