class Counter{
    int count;

    public Counter(){
        count =0;
    }

    void incriment(){
        count++;
    }

    void getCount(){
        System.out.println("Count = "+count);
    }
}

class A extends Thread{

    Counter obj;
    public A(Counter obj){
        this.obj=obj;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            obj.incriment();
        }
        
    }
}
public class p04RaceCondition {
    public static void main(String[] args) {
        Counter ob = new Counter();
        A T1 = new A(ob);
        A T2 = new A(ob);

        T1.start();
        T2.start();

        try {
            T1.join();
            T2.join();
            //WAIT UNTILL T1 ANS T2 GET FENISHED
        } catch (Exception e) {
            // TODO: handle exception
        }

        ob.getCount();
    }
}
