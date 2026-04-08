Here’s a **complete, in-depth guide to Queue & PriorityQueue in Java** — covering **types, all methods, internal working, and examples**.

---

# 🔷 1. What is Queue in Java?

A **Queue** follows **FIFO (First In First Out)**.

👉 Example:

* Insert: A → B → C
* Remove: A → B → C

📦 Package:

```java
import java.util.*;
```

---

# 🔷 2. Types of Queue in Java

Java provides multiple implementations 👇

---

## 🔹 2.1 `Queue` Interface

```java
Queue<Integer> q = new LinkedList<>();
```

👉 Cannot instantiate directly (interface)

---

## 🔹 2.2 `LinkedList` (Most Common Queue)

```java
Queue<Integer> q = new LinkedList<>();
```

👉 Features:

* Doubly linked list
* Allows null
* Good for frequent insert/delete

---

## 🔹 2.3 `PriorityQueue` (Heap-based)

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

👉 Features:

* Elements sorted automatically
* Default = **min heap**

---

## 🔹 2.4 `ArrayDeque` (Best for Queue)

```java
Deque<Integer> dq = new ArrayDeque<>();
```

👉 Features:

* Faster than LinkedList
* No null allowed
* Can act as queue + stack

---

## 🔹 2.5 `Deque` (Double Ended Queue)

```java
Deque<Integer> dq = new ArrayDeque<>();
```

👉 Insert/remove from both ends

---

## 🔹 2.6 BlockingQueue (Advanced / Multithreading)

```java
BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(10);
```

👉 Used in producer-consumer problems

---

# 🔷 3. Queue Methods (Core Interface)

---

## 🔹 3.1 Insert Methods

```java
q.add(10);     // throws exception if full
q.offer(20);   // returns false if fails
```

---

## 🔹 3.2 Remove Methods

```java
q.remove();    // removes head, exception if empty
q.poll();      // removes head, returns null if empty
```

---

## 🔹 3.3 Access Methods

```java
q.element();   // head, exception if empty
q.peek();      // head, null if empty
```

---

# 🔷 4. Important Queue Methods (Collection)

```java
q.size();
q.isEmpty();
q.contains(10);
q.clear();
```

---

# 🔷 5. Deque Methods (Double Ended Queue)

---

## 🔹 Insert

```java
dq.addFirst(10);
dq.addLast(20);
dq.offerFirst(30);
dq.offerLast(40);
```

---

## 🔹 Remove

```java
dq.removeFirst();
dq.removeLast();
dq.pollFirst();
dq.pollLast();
```

---

## 🔹 Access

```java
dq.getFirst();
dq.getLast();
dq.peekFirst();
dq.peekLast();
```

---

## 🔹 Stack-style (LIFO)

```java
dq.push(10);
dq.pop();
```

---

# 🔷 6. PriorityQueue (Very Important 🔥)

---

## 🔹 6.1 Declaration

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

👉 Default: **Min Heap**

---

## 🔹 6.2 Max Heap

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```

---

## 🔹 6.3 Custom Comparator

```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
```

---

## 🔹 6.4 Methods

```java
pq.add(10);
pq.offer(20);

pq.remove();
pq.poll();

pq.peek();
pq.element();
```

---

## 🔹 6.5 Other Methods

```java
pq.size();
pq.contains(10);
pq.clear();
```

---

# 🔷 7. Internal Working

---

## 🔹 Queue (LinkedList)

* Uses **doubly linked list**
* Fast insertion/removal

---

## 🔹 ArrayDeque

* Uses **circular array**
* Faster than LinkedList

---

## 🔹 PriorityQueue

* Uses **Binary Heap**

### Heap Structure:

```
        10
       /  \
     20    30
    /  \
   40  50
```

---

# 🔷 8. Time Complexity

| Operation     | Queue | PriorityQueue |
| ------------- | ----- | ------------- |
| add / offer   | O(1)  | O(log n)      |
| remove / poll | O(1)  | O(log n)      |
| peek          | O(1)  | O(1)          |

---

# 🔷 9. Example (Queue)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Queue<Integer> q = new LinkedList<>();

        q.add(10);
        q.add(20);
        q.add(30);

        System.out.println(q.peek());
        System.out.println(q.poll());

        System.out.println(q);
    }
}
```

---

# 🔷 10. Example (PriorityQueue)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(30);
        pq.add(10);
        pq.add(20);

        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
```

👉 Output:

```
10 20 30
```

---

# 🔷 11. Queue vs PriorityQueue

| Feature   | Queue      | PriorityQueue    |
| --------- | ---------- | ---------------- |
| Order     | FIFO       | Sorted           |
| Structure | List       | Heap             |
| Access    | Head only  | Smallest/Largest |
| Use Case  | Scheduling | Dijkstra, Heap   |

---

# 🔥 12. Real Use Cases

### Queue:

* CPU scheduling
* Printer queue
* BFS traversal

### PriorityQueue:

* Dijkstra algorithm
* Task scheduling with priority
* Top K elements problems

---

# 🔥 Final Summary

👉 **Queue Types**

* LinkedList → simple
* ArrayDeque → fastest ✅
* BlockingQueue → multithreading

👉 **PriorityQueue**

* Default → Min Heap
* Custom → Max Heap / comparator

👉 Core Methods:

* `add()`, `offer()`
* `remove()`, `poll()`
* `peek()`

---

If you want next:
✅ **Deque vs Queue vs Stack deep comparison**
✅ **Heap problems (Top K, Median, etc.)**
✅ **Interview questions (50+ with answers)**
