package hello.core.singleton;

public class StatefulService
{
    //private int price;//싱글톤으로 사용할 객체는 상태저장을 하면 안된다
    public int order(String name, int price)
    {
        System.out.println("name =" + name + " price = " + price);
        //this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
