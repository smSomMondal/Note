// package Thread;

// import java.lang.Thread;
class A extends Thread{


    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName()+" "+this.getPriority());

            try {
                sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
class B extends Thread{
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName()+" "+this.getPriority());
        }
    }
}
public class p03PriorityName {
    public static void main(String[] args) {
        A ob1 = new A();
        B ob2 = new B();
        ob1.setPriority(Thread.MAX_PRIORITY);
        ob1.setName("som");

        ob1.start();
        ob2.start();
        
    }
}
