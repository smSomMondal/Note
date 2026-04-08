// package Thread;
class A extends Thread{
    @Override
    public void run() {
        System.out.println(this.getState());
    }
}

class C {

    void print() {
        for (int i = 0; i < 100; i++) {
            System.out.println("hi");
        }
    }
}
class B extends C implements Runnable {
    @Override
    public void run() {
        
        System.out.println(this.getClass());
        print();
    }
}

public class p02TypeOfThreadImp {
    public static void main(String[] args) {
        A ob1 = new A();
        Thread ob2 = new Thread(new B());
        System.out.println(ob1.getState());
        System.out.println(ob2.getState());
                
    }
}
