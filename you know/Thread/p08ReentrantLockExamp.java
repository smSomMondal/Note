import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Example {

    final Lock lock = new ReentrantLock();

    void outerFunc(){
        try {
            lock.lock(); //lock main door as Thread 1
            System.out.println("outer function get lock in main door");
            innerFunc();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }finally{
            lock.unlock();//unlock main door as Thread 1
            System.out.println("outer function get unlock in main door");
        }
    }

    void innerFunc(){
        try {
            lock.lock();//lock toilet door as Thread 1
            System.out.println("inner function get lock toilet door");
            System.out.println("****love from inner function****");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }finally{
            lock.unlock();//unlock toilet door as Thread 1
            System.out.println("inner function get unlock toilet door");
        }
    }
    
}

public class p08ReentrantLockExamp {
    public static void main(String[] args) {
        Example obj = new Example();
        obj.outerFunc();
    }
}
