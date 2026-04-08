class Banker {

    int balance = 100;

    void withdrawal(int amount) {
        synchronized (this) {
            try {
                if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " try to with drwal " + amount);
                    Thread.sleep(10000);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " has to with drwal " + amount);
                } else {
                    System.out.println(Thread.currentThread().getName() + " has insuffisient balance");
                }
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " reeor happend ");
            }
            System.out.println(Thread.currentThread().getName() + " has complete with balance " + balance);
        }
    }

}

public class p06AnonimusRunaClas {

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