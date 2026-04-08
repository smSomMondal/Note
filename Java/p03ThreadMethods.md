[2ed Part](#2ed-part)

In Java, the **`Thread` class** (from `java.lang.Thread`) provides many methods to **create, control, pause, coordinate, and manage threads**. Below is a **comprehensive guide to the most important Thread methods**, with explanations and Java code examples.

---

# 1. `start()`

## Purpose

Starts a new thread and internally calls the `run()` method.

### Example

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();   // starts new thread
    }
}
```

**Important:**
Calling `run()` directly does **not** create a new thread.

---

# 2. `run()`

## Purpose

Contains the code executed by the thread.

### Example

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread executed");
    }
}
```

If `start()` is called, Java automatically calls `run()` in a **separate thread**.

---

# 3. `sleep(long milliseconds)`

## Purpose

Pauses the thread for a specified time.

### Example

```java
class Test extends Thread {
    public void run() {
        try {
            for(int i=1;i<=5;i++){
                System.out.println(i);
                Thread.sleep(1000); // 1 second delay
            }
        } catch(Exception e){}
    }
}

public class Main {
    public static void main(String[] args) {
        new Test().start();
    }
}
```

---

# 4. `join()`

## Purpose

Makes the current thread wait until another thread finishes.

### Example

```java
class Test extends Thread {
    public void run() {
        for(int i=1;i<=3;i++){
            System.out.println("Child thread "+i);
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Test t = new Test();
        t.start();

        t.join(); // wait for child thread

        System.out.println("Main thread finished");
    }
}
```

---

# 5. `yield()`

## Purpose

Temporarily pauses the current thread and allows other threads to run.

### Example

```java
class Test extends Thread {
    public void run() {
        for(int i=1;i<=3;i++){
            Thread.yield();
            System.out.println("Thread running");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Test().start();
    }
}
```

---

# 6. `interrupt()`

## Purpose

Interrupts a thread that is sleeping or waiting.

### Example

```java
class Test extends Thread {
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Test t = new Test();
        t.start();
        t.interrupt();
    }
}
```

---

# 7. `isAlive()`

## Purpose

Checks whether a thread is still running.

### Example

```java
class Test extends Thread {
    public void run(){
        System.out.println("Thread running");
    }
}

public class Main {
    public static void main(String[] args){
        Test t = new Test();

        System.out.println(t.isAlive());

        t.start();

        System.out.println(t.isAlive());
    }
}
```

---

# 8. `setName()` and `getName()`

## Purpose

Set or get the name of a thread.

### Example

```java
class Test extends Thread {
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args){

        Test t = new Test();

        t.setName("WorkerThread");

        t.start();
    }
}
```

---

# 9. `currentThread()`

## Purpose

Returns the currently executing thread.

### Example

```java
public class Main {
    public static void main(String[] args) {

        Thread t = Thread.currentThread();

        System.out.println(t.getName());
    }
}
```

Output usually:

```
main
```

---

# 10. `setPriority()` and `getPriority()`

## Purpose

Set or get thread priority.

Range:

```
1 → MIN_PRIORITY
5 → NORM_PRIORITY
10 → MAX_PRIORITY
```

### Example

```java
class Test extends Thread {
    public void run(){
        System.out.println("Priority: " + getPriority());
    }
}

public class Main {
    public static void main(String[] args){

        Test t = new Test();

        t.setPriority(Thread.MAX_PRIORITY);

        t.start();
    }
}
```

---

# 11. `isDaemon()` and `setDaemon()`

## Purpose

Daemon threads run in the background.

Example:

* Garbage Collector

### Example

```java
class Test extends Thread {
    public void run(){
        if(Thread.currentThread().isDaemon()){
            System.out.println("Daemon thread");
        } else {
            System.out.println("User thread");
        }
    }
}

public class Main {
    public static void main(String[] args){

        Test t = new Test();

        t.setDaemon(true);

        t.start();
    }
}
```

---

# 12. `stop()` (Deprecated ⚠)

Stops a thread forcefully.

❌ Not recommended.

```java
t.stop();
```

Reason: Can cause **data inconsistency**.

---

# 13. `suspend()` and `resume()` (Deprecated ⚠)

```java
t.suspend();
t.resume();
```

These methods were removed due to **deadlock issues**.

---

# 14. `getId()`

Returns thread ID.

### Example

```java
class Test extends Thread {
    public void run(){
        System.out.println("Thread ID: "+Thread.currentThread().getId());
    }
}

public class Main {
    public static void main(String[] args){
        new Test().start();
    }
}
```

---

# 15. `getState()`

Returns thread state.

Example states:

```
NEW
RUNNABLE
BLOCKED
WAITING
TIMED_WAITING
TERMINATED
```

### Example

```java
class Test extends Thread {
    public void run(){
        System.out.println("Running");
    }
}

public class Main {
    public static void main(String[] args){

        Test t = new Test();

        System.out.println(t.getState());

        t.start();

        System.out.println(t.getState());
    }
}
```

---

# Summary Table

| Method          | Purpose                  |
| --------------- | ------------------------ |
| start()         | Starts thread            |
| run()           | Thread logic             |
| sleep()         | Pause thread             |
| join()          | Wait for thread          |
| yield()         | Give CPU to other thread |
| interrupt()     | Interrupt thread         |
| isAlive()       | Check running            |
| setName()       | Set thread name          |
| getName()       | Get thread name          |
| currentThread() | Current thread           |
| setPriority()   | Set priority             |
| getPriority()   | Get priority             |
| setDaemon()     | Background thread        |
| isDaemon()      | Check daemon             |
| getId()         | Thread ID                |
| getState()      | Thread state             |

---

✅ These methods form the **core API for Java multithreading** and are widely used in **backend servers, frameworks like Spring Boot, and concurrent systems**.

---

If you want, I can also explain **very advanced thread concepts used in real production systems**:

* **Thread Synchronization (`synchronized`)**
* **wait(), notify(), notifyAll()**
* **Thread Pools (ExecutorService)**
* **Callable vs Runnable**
* **Future and CompletableFuture**
* **Deadlock, Race Conditions**
* **Locks (`ReentrantLock`)**

These are **critical for high-level Java concurrency programming**.

# 2ed Part

These topics belong to **Java Concurrency (Multithreading Control)**. They are used to **safely coordinate multiple threads** working on shared data in real-world systems like **servers, databases, and microservices**.

We will explain deeply:

1. **Thread Synchronization (`synchronized`)**
2. **`wait()`, `notify()`, `notifyAll()`**
3. **Thread Pools (`ExecutorService`)**
4. **`Callable` vs `Runnable`**
5. **`Future` and `CompletableFuture`**
6. **Deadlock & Race Condition**
7. **Locks (`ReentrantLock`)**

---

# 1. Thread Synchronization (`synchronized`)

## Problem: Race Condition

When multiple threads modify **shared data**, incorrect results can occur.

Example problem:

```text
Thread1 → count++
Thread2 → count++
```

Expected result: `2`
Actual result sometimes: `1`

This is called a **Race Condition**.

---

## Solution: Synchronization

`synchronized` ensures **only one thread executes a critical section at a time**.

---

## Example Without Synchronization

```java
class Counter {

    int count = 0;

    void increment() {
        count++;
    }
}
```

Two threads may update `count` at the same time.

---

## Example With Synchronization

```java
class Counter {

    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for(int i=0;i<1000;i++) c.increment();
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<1000;i++) c.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.count);
    }
}
```

Output:

```
2000
```

---

## Types of Synchronization

| Type                   | Description           |
| ---------------------- | --------------------- |
| Method Synchronization | Entire method locked  |
| Block Synchronization  | Specific block locked |
| Static Synchronization | Lock at class level   |

Example block synchronization:

```java
synchronized(this){
   count++;
}
```

---

# 2. `wait()`, `notify()`, `notifyAll()`

These methods allow **threads to communicate**.

They belong to the **Object class**, not Thread.

| Method        | Purpose                        |
| ------------- | ------------------------------ |
| `wait()`      | Thread releases lock and waits |
| `notify()`    | Wakes one waiting thread       |
| `notifyAll()` | Wakes all waiting threads      |

---

## Producer–Consumer Example

```java
class Shared {

    synchronized void produce() throws Exception {

        System.out.println("Producing...");
        wait();

        System.out.println("Resumed production");
    }

    synchronized void consume() {

        System.out.println("Consuming...");
        notify();
    }
}

public class Main {

    public static void main(String[] args) {

        Shared obj = new Shared();

        new Thread(() -> {
            try { obj.produce(); }
            catch(Exception e){}
        }).start();

        new Thread(() -> {
            obj.consume();
        }).start();
    }
}
```

---

# 3. Thread Pools (`ExecutorService`)

Creating threads repeatedly is **expensive**.

Thread pools reuse threads.

Java provides **Executor Framework**.

---

## Example

```java
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=1;i<=5;i++){

            int task = i;

            executor.submit(() -> {
                System.out.println("Task "+task+
                    " executed by "+Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
```

---

## Types of Thread Pools

| Method                      | Description     |
| --------------------------- | --------------- |
| `newFixedThreadPool()`      | Fixed threads   |
| `newCachedThreadPool()`     | Dynamic threads |
| `newSingleThreadExecutor()` | One thread      |
| `newScheduledThreadPool()`  | Scheduled tasks |

---

# 4. `Callable` vs `Runnable`

| Feature      | Runnable               | Callable    |
| ------------ | ---------------------- | ----------- |
| Return value | ❌ No                   | ✔ Yes       |
| Exception    | ❌ No checked exception | ✔ Can throw |
| Method       | `run()`                | `call()`    |

---

## Runnable Example

```java
Runnable task = () -> {
    System.out.println("Runnable running");
};

new Thread(task).start();
```

---

## Callable Example

```java
import java.util.concurrent.*;

Callable<Integer> task = () -> {
    return 10 + 20;
};

ExecutorService executor = Executors.newSingleThreadExecutor();

Future<Integer> result = executor.submit(task);

System.out.println(result.get());

executor.shutdown();
```

Output

```
30
```

---

# 5. `Future` and `CompletableFuture`

## Future

Represents **result of asynchronous computation**.

Example:

```java
Future<Integer> future = executor.submit(() -> {
    return 50;
});

System.out.println(future.get());
```

`get()` waits until computation finishes.

---

## CompletableFuture

Modern async API (Java 8).

Example:

```java
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> {
            return 10;
        })
        .thenApply(x -> x * 2)
        .thenAccept(System.out::println);
    }
}
```

Output

```
20
```

---

# 6. Deadlock & Race Condition

## Deadlock

Two threads waiting for each other forever.

Example:

```java
class A {
    synchronized void methodA(B b){
        b.last();
    }
    synchronized void last(){}
}

class B {
    synchronized void methodB(A a){
        a.last();
    }
    synchronized void last(){}
}
```

Thread1 locks A → waits for B
Thread2 locks B → waits for A

Program freezes.

---

## Race Condition

Occurs when:

* Multiple threads access shared variable
* No synchronization

Example:

```java
count++;
```

Two threads may read same value simultaneously.

---

# 7. Locks (`ReentrantLock`)

`ReentrantLock` is more powerful than `synchronized`.

Features:

* Manual lock control
* Try lock
* Fair locking
* Interruptible locks

---

## Example

```java
import java.util.concurrent.locks.*;

class Counter {

    int count = 0;

    Lock lock = new ReentrantLock();

    void increment(){

        lock.lock();

        try{
            count++;
        } finally {
            lock.unlock();
        }
    }
}
```

---

## Advantages Over `synchronized`

| Feature        | synchronized | ReentrantLock |
| -------------- | ------------ | ------------- |
| Manual control | ❌            | ✔             |
| tryLock        | ❌            | ✔             |
| Interruptible  | ❌            | ✔             |
| Fair locking   | ❌            | ✔             |

---

# Complete Java Concurrency Hierarchy

```
Multithreading
│
├── Thread Control
│   ├── synchronized
│   ├── Locks
│   └── wait/notify
│
├── Executors
│   ├── Thread Pools
│   └── Callable
│
├── Async Programming
│   ├── Future
│   └── CompletableFuture
│
└── Problems
    ├── Race Condition
    └── Deadlock
```

---

# Summary

| Topic             | Key Idea                   |
| ----------------- | -------------------------- |
| synchronized      | Prevent race conditions    |
| wait/notify       | Thread communication       |
| ExecutorService   | Thread pools               |
| Callable          | Task returning result      |
| Future            | Async result holder        |
| CompletableFuture | Advanced async programming |
| Deadlock          | Threads waiting forever    |
| ReentrantLock     | Advanced locking           |

---

✅ These are **core concurrency concepts used in high-performance Java systems** like:

* **Spring Boot servers**
* **Microservices**
* **High-frequency trading systems**
* **Big data processing**

---

If you want, I can also explain **the most advanced Java concurrency topics used by senior engineers**:

* **Java Memory Model (JMM)**
* **Volatile keyword**
* **Atomic variables (`AtomicInteger`)**
* **ForkJoinPool**
* **Parallel Streams**
* **Concurrent Collections (`ConcurrentHashMap`)**

These topics are **extremely important for high-level backend development and system design**.
