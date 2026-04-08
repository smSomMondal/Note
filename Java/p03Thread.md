Java **Threads** are a core part of **concurrent programming**. They allow multiple tasks to run **simultaneously (or appear to run simultaneously)** within a single program. Threads are heavily used in **servers, web applications, game engines, and large backend systems**.

---

# 1. What is a Thread in Java?

## Definition

A **Thread** is the **smallest unit of execution inside a process**.

A Java program always has **at least one thread**, called the **Main Thread**.

Example:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread running");
    }
}
```

Here the **main() method runs in the main thread**.

---

## Process vs Thread

| Feature       | Process             | Thread                           |
| ------------- | ------------------- | -------------------------------- |
| Definition    | Independent program | Unit of execution inside process |
| Memory        | Separate memory     | Shared memory                    |
| Communication | Slow                | Fast                             |
| Creation cost | High                | Low                              |

Example:

```text
Process (Java Program)
   |
   |---- Thread 1
   |---- Thread 2
   |---- Thread 3
```

---

# 2. Why Multithreading is Needed

Without threads:

```text
Task1 → Task2 → Task3
```

Sequential execution.

With threads:

```text
Task1
Task2   → running simultaneously
Task3
```

Advantages:

* Better **CPU utilization**
* Faster **program execution**
* Used in **servers, games, GUI apps**

Example uses:

* Web servers
* Download managers
* Real-time systems
* Video streaming

---

# 3. Ways to Create Threads in Java

There are **two main ways**:

1️⃣ Extending **Thread class**
2️⃣ Implementing **Runnable interface**

---

# Method 1 — Extending Thread Class

## Example

```java
class MyThread extends Thread {

    public void run() {
        System.out.println("Thread is running");
    }
}

public class Main {

    public static void main(String[] args) {

        MyThread t = new MyThread();

        t.start();
    }
}
```

Output

```text
Thread is running
```

---

### Important Rule

❌ Do NOT call `run()` directly

```java
t.run();
```

✔ Always call

```java
t.start();
```

Because:

* `start()` creates a **new thread**
* `run()` executes in **same thread**

---

# Method 2 — Implementing Runnable Interface

This is the **most recommended approach**.

```java
class MyRunnable implements Runnable {

    public void run() {
        System.out.println("Thread using Runnable");
    }
}

public class Main {

    public static void main(String[] args) {

        MyRunnable r = new MyRunnable();

        Thread t = new Thread(r);

        t.start();
    }
}
```

---

### Why Runnable is Better

| Thread                      | Runnable                 |
| --------------------------- | ------------------------ |
| Uses inheritance            | Uses interface           |
| Cannot extend another class | Can extend other classes |
| Less flexible               | More flexible            |

---

# 4. Thread Life Cycle

A thread goes through **five states**.

```text
NEW
 ↓
RUNNABLE
 ↓
RUNNING
 ↓
WAITING / BLOCKED
 ↓
TERMINATED
```

Explanation:

| State      | Meaning              |
| ---------- | -------------------- |
| NEW        | Thread created       |
| RUNNABLE   | Ready to run         |
| RUNNING    | Executing            |
| WAITING    | Waiting for resource |
| TERMINATED | Execution finished   |

---

# 5. Important Thread Methods

Java provides many **Thread class methods**.

---

# 1. `start()`

Starts a thread and calls `run()` internally.

```java
Thread t = new Thread();
t.start();
```

---

# 2. `run()`

Contains code executed by the thread.

```java
public void run() {
    System.out.println("Thread running");
}
```

---

# 3. `sleep()`

Pauses thread execution.

```java
Thread.sleep(milliseconds);
```

Example:

```java
class Test extends Thread {

    public void run() {

        try {

            for(int i=1;i<=5;i++) {

                System.out.println(i);

                Thread.sleep(1000);
            }

        } catch(Exception e) {}
    }
}

public class Main {

    public static void main(String[] args) {

        Test t = new Test();

        t.start();
    }
}
```

Output

```text
1
2
3
4
5
```

(1 second delay)

---

# 4. `join()`

Makes one thread wait for another thread.

Example:

```java
class Test extends Thread {

    public void run() {

        for(int i=1;i<=3;i++) {

            System.out.println("Child thread " + i);
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Test t = new Test();

        t.start();

        t.join();

        System.out.println("Main thread finished");
    }
}
```

---

# 5. `yield()`

Temporarily pauses thread and allows other threads to execute.

```java
Thread.yield();
```

Example:

```java
class Test extends Thread {

    public void run() {

        for(int i=1;i<=3;i++) {

            Thread.yield();

            System.out.println("Thread running");
        }
    }
}
```

---

# 6. `isAlive()`

Checks if thread is still running.

```java
t.isAlive();
```

Example:

```java
System.out.println(t.isAlive());
```

---

# 7. `setName()` and `getName()`

Set or get thread name.

```java
t.setName("WorkerThread");

System.out.println(t.getName());
```

---

# 8. `setPriority()`

Sets thread priority.

Range:

```text
1 → MIN_PRIORITY
5 → NORM_PRIORITY
10 → MAX_PRIORITY
```

Example:

```java
t.setPriority(Thread.MAX_PRIORITY);
```

---

# 9. `interrupt()`

Interrupts a thread.

Example:

```java
t.interrupt();
```

Used for **stopping sleeping or waiting threads**.

---

# 6. Thread Synchronization

When multiple threads access **shared data**, problems may occur.

Example:

```text
Thread1 → update balance
Thread2 → update balance
```

Both accessing same variable.

Solution → **Synchronization**

---

## Example

```java
class Counter {

    int count = 0;

    synchronized void increment() {

        count++;
    }

    //or
    synchronized(this){
        void increment() {
            count++;
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Counter c = new Counter();

        Thread t1 = new Thread(() -> {

            for(int i=0;i<1000;i++)
                c.increment();
        });

        Thread t2 = new Thread(() -> {

            for(int i=0;i<1000;i++)
                c.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c.count);
    }
}
```

Output

```text
2000
```

---

# 7. Thread Communication

Threads communicate using:

* `wait()`
* `notify()`
* `notifyAll()`

Example:

```java
synchronized(obj){
    obj.wait();
}
```

---

# 8. Modern Thread Tools (Important)

Modern Java uses:

| Tool              | Purpose           |
| ----------------- | ----------------- |
| ExecutorService   | Thread pool       |
| CompletableFuture | Async programming |
| ForkJoinPool      | Parallel tasks    |

Example:

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

executor.submit(() -> {
    System.out.println("Thread running");
});

executor.shutdown();
```

---

# 9. Common Thread Problems

| Problem        | Description                                 |
| -------------- | ------------------------------------------- |
| Deadlock       | Threads waiting forever                     |
| Starvation     | Thread never gets CPU                       |
| Race Condition | Multiple threads modify data                |
| Livelock       | Threads keep responding but not progressing |

---

# Summary

| Topic             | Key Idea                            |
| ----------------- | ----------------------------------- |
| Thread            | Smallest unit of execution          |
| Multithreading    | Multiple threads run simultaneously |
| Creation          | Thread class / Runnable             |
| Important methods | start, run, sleep, join             |
| Synchronization   | Prevent race conditions             |
| Communication     | wait, notify                        |

---

✅ Threads are heavily used in **Spring Boot servers, high-performance systems, and large backend applications**.

---

If you want, I can also explain **very advanced Java concurrency topics** (used in real production systems):

* **Thread Pools and Executor Framework (very important)**
* **Callable vs Runnable**
* **Future and CompletableFuture**
* **Locks (ReentrantLock)**
* **Deadlock with real examples**
* **Parallel Streams in Java**
* **Java Memory Model**

These are **essential for becoming a strong Java backend developer**.
