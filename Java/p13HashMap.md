Here’s a **complete, in-depth guide to `HashMap` in Java** — covering **types, all methods, internal working, and examples**.

---

# 🔷 1. What is `HashMap`?

`HashMap` is a **key-value data structure** based on **hashing**.

📦 Package:

```java
import java.util.HashMap;
```

👉 Example:

```java
HashMap<Integer, String> map = new HashMap<>();
```

---

# 🔷 2. Key Features

* Stores data as **(key, value)**
* Keys are **unique**
* Values can be duplicate
* Allows **one null key**, multiple null values
* Not synchronized (not thread-safe)
* No guaranteed order

---

# 🔷 3. Types of Map in Java (Important 🔥)

---

## 🔹 3.1 `HashMap`

```java
HashMap<Integer, String> map = new HashMap<>();
```

* Unordered
* Fastest (O(1))

---

## 🔹 3.2 `LinkedHashMap`

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
```

* Maintains insertion order

---

## 🔹 3.3 `TreeMap`

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

* Sorted order (Red-Black Tree)
* O(log n)

---

## 🔹 3.4 `Hashtable` (Legacy)

```java
Hashtable<Integer, String> map = new Hashtable<>();
```

* Thread-safe
* Slower

---

## 🔹 3.5 `ConcurrentHashMap`

```java
ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
```

* Thread-safe (modern)

---

# 🔷 4. Constructors of HashMap

```java
HashMap<>();
HashMap<>(int initialCapacity);
HashMap<>(int initialCapacity, float loadFactor);
HashMap<>(Map m);
```

---

# 🔷 5. Core Methods (Most Important 🔥)

---

## 🔹 5.1 Put Methods

```java
map.put(1, "A");              // add/update
map.putIfAbsent(2, "B");      // only if key not present
```

---

## 🔹 5.2 Get Methods

```java
map.get(1);                   // returns value
map.getOrDefault(2, "X");     // default value
```

---

## 🔹 5.3 Remove Methods

```java
map.remove(1);                // remove by key
map.remove(1, "A");           // remove if match
map.clear();                  // remove all
```

---

## 🔹 5.4 Check Methods

```java
map.containsKey(1);
map.containsValue("A");
map.isEmpty();
```

---

## 🔹 5.5 Size

```java
map.size();
```

---

# 🔷 6. Traversal Methods

---

## 🔹 6.1 keySet()

```java
for(Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## 🔹 6.2 values()

```java
for(String value : map.values()) {
    System.out.println(value);
}
```

---

## 🔹 6.3 entrySet() (Best Method 🔥)

```java
for(Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " " + entry.getValue());
}
```

---

## 🔹 6.4 forEach (Java 8)

```java
map.forEach((k, v) -> System.out.println(k + " " + v));
```

---

# 🔷 7. Advanced Methods (Very Important 🔥)

---

## 🔹 7.1 replace()

```java
map.replace(1, "New");
map.replace(1, "Old", "New");
```

---

## 🔹 7.2 compute()

```java
map.compute(1, (k, v) -> v + "X");
```

---

## 🔹 7.3 computeIfAbsent()

```java
map.computeIfAbsent(3, k -> "Value");
```

---

## 🔹 7.4 computeIfPresent()

```java
map.computeIfPresent(1, (k, v) -> v + "Updated");
```

---

## 🔹 7.5 merge()

```java
map.merge(1, "X", (oldVal, newVal) -> oldVal + newVal);
```

---

# 🔷 8. Internal Working (Very Important 🔥🔥)

👉 HashMap uses:

```java
Node<K,V>[] table;
```

---

## 🔹 Step-by-step

1. **Hashing**

```java
int hash = key.hashCode();
```

2. **Index Calculation**

```java
index = hash % arraySize;
```

3. **Collision Handling**

* Linked List (before Java 8)
* Balanced Tree (after Java 8 if > 8 nodes)

---

## 🔹 Treeify Condition

* If bucket size > 8 → convert to **Red-Black Tree**
* If < 6 → back to linked list

---

## 🔹 Load Factor

```java
default = 0.75
```

👉 Resize when:

```java
size > capacity * loadFactor
```

---

## 🔷 9. Time Complexity

| Operation | Complexity |
| --------- | ---------- |
| put       | O(1) avg   |
| get       | O(1) avg   |
| remove    | O(1) avg   |
| worst     | O(log n)   |

---

# 🔷 10. Example Code

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Mango");

        System.out.println(map.get(2));

        map.remove(3);

        for(Map.Entry<Integer, String> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        map.putIfAbsent(4, "Orange");

        System.out.println(map);
    }
}
```

---

# 🔷 11. HashMap vs Other Maps

| Feature   | HashMap | LinkedHashMap | TreeMap |
| --------- | ------- | ------------- | ------- |
| Order     | No      | Insertion     | Sorted  |
| Speed     | Fast    | Medium        | Slow    |
| Structure | Hash    | Hash + List   | Tree    |

---

# 🔷 12. Real Use Cases 🔥

* Frequency counting
* Caching
* Database indexing
* JSON parsing
* Graph representation

---

# 🔥 Final Summary

👉 `HashMap` is:

* Fast (O(1))
* Unordered
* Uses hashing + buckets

👉 Most Important Methods:

* `put()`, `get()`, `remove()`
* `compute()`, `merge()`
* `entrySet()` for traversal

---

If you want next:
✅ **HashMap interview questions (top 50)**
✅ **Internal working with diagrams deeply**
✅ **Practice problems (LeetCode level)**
