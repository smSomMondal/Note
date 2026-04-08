interface Animal {

    void makeSound();
}

class Dog implements Animal{

    @Override
    public void makeSound() {
        System.out.println("bho bho");
    }

    public void jump(){
        System.out.println("jump upon the sky");
    }
}

class A{

    void job(){
        System.out.println("job of A");
    }
}

class B extends A{
    @Override
    void job() {
        System.out.println("job of B");
    }

    void job2(){
        System.out.println("job2 of B");
    }
}

public class test {
    public static void main(String[] args) {
        
        /* Annonymus Class */
        /*Animal ob = new Animal() {
            @Override
            public void makeSound() {
                System.out.println("just creat sound");
            }
        };
        ob.makeSound();

        Dog ob1 = new Dog();
        ob1.makeSound();
        ob1.jump();

        Animal ob2 = new Dog();
        ob2.makeSound();
        //ob2.jump();*/

        A ob = new A();
        ob.job();

        B ob1 = new B();
        ob1.job();

        A obB = new B();
        obB.job();
        // obB.job2();

        /*
        //DOWN CASTING NOT ALLOWED IN JAVA
        B obA = (B)new A();
        obA.job();
        */
    }
}
