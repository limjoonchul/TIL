package pholymorphism3;

public class BeanFactory {
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
