// package Thread;

class myClass extends Thread {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 100; i++) {
            System.out.println("myClass"+this.getName());
        }
        
    }
}
public class p01Introduction {
    public static void main(String[] args) {
        myClass ob = new myClass();
        ob.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main");
        }
    }
}
