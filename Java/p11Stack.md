Here’s a **complete, in-depth guide to `Stack` in Java** — including **types, all important methods, internal working, and examples**.

---

# 🔷 1. What is `Stack` in Java?

`Stack` is a **LIFO (Last In First Out)** data structure.

👉 Example:

* Push: A → B → C
* Pop: C → B → A

📦 Package:

```java
import java.util.Stack;
```

👉 `Stack` **extends `Vector`**, so it inherits many methods.

---

# 🔷 2. Types of Stack in Java

Java has **multiple ways to implement stack** 👇

---

## 🔹 2.1 `Stack` Class (Legacy)

```java
Stack<Integer> stack = new Stack<>();
```

👉 Features:

* Synchronized (thread-safe)
* Slower than modern alternatives

---

## 🔹 2.2 `Deque` (Recommended Modern Stack)

```java
Deque<Integer> stack = new ArrayDeque<>();
```

👉 Use instead of Stack:

* Faster
* Not synchronized
* Preferred in interviews

---

## 🔹 2.3 Custom Stack (Using Array)

```java
class MyStack {
    int arr[] = new int[100];
    int top = -1;
}
```

---

## 🔹 2.4 Custom Stack (Using LinkedList)

```java
class Node {
    int data;
    Node next;
}
```

---

# 🔷 3. Constructors of Stack

```java
Stack<Integer> s = new Stack<>();
```

👉 Only default constructor (inherits from `Vector`)

---

# 🔷 4. Core Stack Methods (Must Know 🔥)

---

## 🔹 4.1 push()

```java
stack.push(10);
```

👉 Adds element to top

---

## 🔹 4.2 pop()

```java
stack.pop();
```

👉 Removes & returns top element
❗ Throws `EmptyStackException` if empty

---

## 🔹 4.3 peek()

```java
stack.peek();
```

👉 Returns top without removing

---

## 🔹 4.4 empty()

```java
stack.empty();
```

👉 Returns `true` if empty

---

## 🔹 4.5 search()

```java
stack.search(10);
```

👉 Returns position from top (1-based)
👉 Returns -1 if not found

---

# 🔷 5. Methods Inherited from Vector (Very Important)

Since `Stack extends Vector`, it has many methods:

---

## 🔹 5.1 Add Methods

```java
stack.add(10);
stack.addElement(20);
stack.addAll(list);
```

---

## 🔹 5.2 Remove Methods

```java
stack.remove(0);
stack.removeElement(10);
stack.clear();
```

---

## 🔹 5.3 Access Methods

```java
stack.get(0);
stack.firstElement();
stack.lastElement();
```

---

## 🔹 5.4 Size & Capacity

```java
stack.size();
stack.capacity();
stack.isEmpty();
```

---

## 🔹 5.5 Iteration

```java
for(Integer i : stack) {
    System.out.println(i);
}
```

---

## 🔹 5.6 Other Important Methods

```java
stack.contains(10);
stack.indexOf(10);
stack.set(0, 100);
stack.clone();
```

---

# 🔷 6. Deque Stack Methods (Recommended)

Using `ArrayDeque`:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

### Equivalent methods:

| Stack Method | Deque Method |
| ------------ | ------------ |
| push()       | push()       |
| pop()        | pop()        |
| peek()       | peek()       |

Extra methods:

```java
stack.offerFirst(10);
stack.pollFirst();
stack.peekFirst();
```

---

# 🔷 7. Internal Working of Stack

👉 Since it extends `Vector`:

```java
Object[] elementData;
```

### ✔ Growth:

* Default capacity = 10
* Doubles when full

### ✔ Thread Safety:

* All methods are synchronized

---

# 🔷 8. Time Complexity

| Operation | Complexity |
| --------- | ---------- |
| push      | O(1)       |
| pop       | O(1)       |
| peek      | O(1)       |
| search    | O(n)       |

---

# 🔷 9. Example Code (Stack)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top: " + stack.peek());

        System.out.println("Removed: " + stack.pop());

        System.out.println("Stack: " + stack);

        System.out.println("Position of 10: " + stack.search(10));
    }
}
```

---

# 🔷 10. Example Using Deque (Best Practice)

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
```

---

# 🔷 11. Stack vs ArrayList

| Feature     | Stack    | ArrayList     |
| ----------- | -------- | ------------- |
| Structure   | LIFO     | Dynamic array |
| Access      | Top only | Random access |
| Thread-safe | Yes      | No            |

---

# 🔷 12. Real Use Cases 🔥

* Undo/Redo operations
* Expression evaluation (Infix → Postfix)
* Recursion handling (Call Stack)
* Backtracking (DFS)
* Browser history

---

# 🔥 Final Summary

👉 Use:

* `Stack` → simple & legacy
* `ArrayDeque` → modern & faster ✅

👉 Key operations:

* `push()`, `pop()`, `peek()` = core of stack

---

If you want next:
✅ **Queue all types & methods (like this)**
✅ **Stack interview questions (top 50)**
✅ **Implement stack problems (DSA level)**
