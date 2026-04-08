Here’s a **complete, in-depth guide to `Set` in Java** — covering **types, all methods, internal working, and examples**.

---

# 🔷 1. What is `Set` in Java?

`Set` is a **Collection that does NOT allow duplicate elements**.

📦 Package:

```java
import java.util.*;
```

👉 Example:

```java
Set<Integer> set = new HashSet<>();
```

---

# 🔷 2. Key Features of Set

* ❌ No duplicates
* ✔ Can store null (depends on implementation)
* ❌ No indexing
* ✔ Faster search (depending on type)

---

# 🔷 3. Types of Set in Java (Very Important 🔥)

---

## 🔹 3.1 `HashSet` (Most Used)

```java
Set<Integer> set = new HashSet<>();
```

👉 Features:

* No order
* Allows one null
* Uses **HashMap internally**
* O(1) average time

---

## 🔹 3.2 `LinkedHashSet`

```java
Set<Integer> set = new LinkedHashSet<>();
```

👉 Features:

* Maintains **insertion order**
* Slightly slower than HashSet

---

## 🔹 3.3 `TreeSet`

```java
Set<Integer> set = new TreeSet<>();
```

👉 Features:

* Sorted order
* Uses **Red-Black Tree**
* O(log n)
* ❌ No null allowed

---

## 🔹 3.4 `EnumSet`

```java
EnumSet<Day> set = EnumSet.of(Day.MON, Day.TUE);
```

👉 Features:

* Used with enums
* Very fast (bitwise operations)

---

## 🔹 3.5 `CopyOnWriteArraySet`

```java
CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
```

👉 Features:

* Thread-safe
* Used in concurrent applications

---

# 🔷 4. Set Interface Methods (Core Methods 🔥)

---

## 🔹 4.1 Add Methods

```java
set.add(10);
set.addAll(list);
```

---

## 🔹 4.2 Remove Methods

```java
set.remove(10);
set.removeAll(list);
set.retainAll(list);  // intersection
set.clear();
```

---

## 🔹 4.3 Check Methods

```java
set.contains(10);
set.containsAll(list);
set.isEmpty();
```

---

## 🔹 4.4 Size

```java
set.size();
```

---

# 🔷 5. Traversal Methods

---

## 🔹 5.1 For-each loop

```java
for(Integer i : set) {
    System.out.println(i);
}
```

---

## 🔹 5.2 Iterator

```java
Iterator<Integer> it = set.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}
```

---

## 🔹 5.3 forEach (Java 8)

```java
set.forEach(System.out::println);
```

---

# 🔷 6. Important Methods (Collection Interface)

```java
set.toArray();
set.equals(otherSet);
set.hashCode();
```

---

# 🔷 7. TreeSet Special Methods 🔥

---

## 🔹 Navigation Methods

```java
TreeSet<Integer> ts = new TreeSet<>();

ts.first();
ts.last();

ts.higher(10);   // >10
ts.lower(10);    // <10

ts.ceiling(10);  // >=10
ts.floor(10);    // <=10
```

---

## 🔹 Poll Methods

```java
ts.pollFirst();
ts.pollLast();
```

---

## 🔹 Subset Methods

```java
ts.subSet(10, 50);
ts.headSet(50);
ts.tailSet(10);
```

---

# 🔷 8. Internal Working

---

## 🔹 HashSet

* Uses `HashMap`
* Stores elements as keys:

```java
map.put(value, PRESENT);
```

---

## 🔹 LinkedHashSet

* HashSet + Linked List (order maintained)

---

## 🔹 TreeSet

* Uses **Red-Black Tree**
* Self-balancing BST

---

# 🔷 9. Time Complexity

| Operation | HashSet | LinkedHashSet | TreeSet  |
| --------- | ------- | ------------- | -------- |
| add       | O(1)    | O(1)          | O(log n) |
| remove    | O(1)    | O(1)          | O(log n) |
| contains  | O(1)    | O(1)          | O(log n) |

---

# 🔷 10. Example Code

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();

        set.add(10);
        set.add(20);
        set.add(10); // duplicate ignored

        System.out.println(set);

        set.remove(20);

        for(int i : set) {
            System.out.println(i);
        }
    }
}
```

---

# 🔷 11. Set vs List

| Feature    | Set            | List       |
| ---------- | -------------- | ---------- |
| Duplicates | Not allowed    | Allowed    |
| Order      | Not guaranteed | Maintained |
| Index      | No             | Yes        |

---

# 🔷 12. Real Use Cases 🔥

* Removing duplicates
* Membership checking
* Graph algorithms
* Unique elements storage

---

# 🔥 Final Summary

👉 `Set` Types:

* `HashSet` → fastest ✅
* `LinkedHashSet` → ordered
* `TreeSet` → sorted

👉 Core Methods:

* `add()`, `remove()`, `contains()`

👉 Special:

* `TreeSet` → navigation methods

---

If you want next:
✅ **Collection Framework full roadmap**
✅ **Comparison: List vs Set vs Map (deep)**
✅ **Top 100 Java interview questions**
