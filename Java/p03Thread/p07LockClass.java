import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

class Banker {

    int balance = 100;

    final Lock lock = new ReentrantLock();

    void withdrawal(int amount) {
        System.out.println(Thread.currentThread().getName() + " try acess withdrawl ");
        try {
            if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {
                try {
                    if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " try to with drwal " + amount);
                    Thread.sleep(30000);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " has to with drwal " + amount);
                    System.out.println(Thread.currentThread().getName() + " has complete with balance " + balance);
                } else {
                    System.out.println(Thread.currentThread().getName() + " has insuffisient balance");
                }
                } catch (Exception e) {
                    // TODO: handle exception
                    Thread.currentThread().interrupt(); // catch any intarupt
                }finally{
                    lock.unlock();
                }
                
            } else {
                System.out.println(Thread.currentThread().getName() + " fail to acure lock ");

            }

        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " error happend ");
            Thread.currentThread().interrupt(); // catch any intarupt
        }

        if (Thread.currentThread().isInterrupted()) {
            //if any intarupt happend by any process do work
        }
    }

}

public class p07LockClass {
    public static void main(String[] args) {
        Banker ob = new Banker();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                ob.withdrawal(50);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(ob.balance);
    }
}
