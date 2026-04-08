[2ed Part](#2ed-part)

Inheritance is one of the **four pillars of Object-Oriented Programming (OOP)**. It allows a class to **reuse the properties and methods of another class**, making code more reusable and organized.

In Java, inheritance is implemented using the **`extends`** keyword.

---

# What is Inheritance?

## Definition

**Inheritance is a mechanism where one class acquires the properties (variables) and behaviors (methods) of another class.**

### Terminology

| Term         | Meaning                  |
| ------------ | ------------------------ |
| Parent Class | Superclass / Base class  |
| Child Class  | Subclass / Derived class |
| `extends`    | Keyword used to inherit  |

### Basic Syntax

```java
class Parent {
    // properties and methods
}

class Child extends Parent {
    // inherits Parent properties
}
```

---

# Types of Inheritance in Java

There are **5 types of inheritance** conceptually:

1. [**Single Inheritance**](#1-single-inheritance)
2. [**Multilevel Inheritance**](#2-multilevel-inheritance)
3. [**Hierarchical Inheritance**](#3-hierarchical-inheritance)
4. [**Multiple Inheritance**](#4-multiple-inheritance)
5. [**Hybrid Inheritance**](#5-hybrid-inheritance)

But in **Java with classes**, only these are directly supported:

* Single
* Multilevel
* Hierarchical

Multiple and Hybrid are achieved using **interfaces**.

---

# 1. Single Inheritance

## Definition

Single inheritance means **one child class inherits from one parent class**.

```
Parent
   |
 Child
```

### Example

```java
class Animal {

    void eat(){
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal {

    void bark(){
        System.out.println("Dog barks");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.eat();   // inherited method
        d.bark();  // own method
    }
}
```

### Output

```
Animal eats food
Dog barks
```

### Explanation

`Dog` inherits `eat()` from `Animal`.

---

# 2. Multilevel Inheritance

## Definition

In multilevel inheritance, **a class inherits from a class which is already inherited from another class**.

```
Grandparent
     |
  Parent
     |
   Child
```

### Example

```java
class Animal {

    void eat(){
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {

    void bark(){
        System.out.println("Dog barks");
    }
}

class Puppy extends Dog {

    void weep(){
        System.out.println("Puppy weeps");
    }
}

public class Main {

    public static void main(String[] args) {

        Puppy p = new Puppy();

        p.eat();
        p.bark();
        p.weep();
    }
}
```

### Output

```
Animal eats
Dog barks
Puppy weeps
```

### Explanation

`Puppy` inherits methods from both:

* Dog
* Animal

---

# 3. Hierarchical Inheritance

## Definition

In hierarchical inheritance, **multiple child classes inherit from the same parent class**.

```
        Animal
       /     \
     Dog     Cat
```

### Example

```java
class Animal {

    void eat(){
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {

    void bark(){
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {

    void meow(){
        System.out.println("Cat meows");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
        Cat c = new Cat();

        d.eat();
        d.bark();

        c.eat();
        c.meow();
    }
}
```

### Output

```
Animal eats
Dog barks
Animal eats
Cat meows
```

---

# 4. Multiple Inheritance

## Definition

Multiple inheritance means **one class inherits from multiple parent classes**.

```
Parent1    Parent2
     \      /
      Child
```

### Problem: Diamond Problem

If both parent classes have the same method, Java cannot decide **which one to inherit**.

Because of this **Java does NOT support multiple inheritance with classes**.

---

## Multiple Inheritance Using Interface

Java supports it via **interfaces**.

### Example

```java
interface A {

    void show();
}

interface B {

    void display();
}

class Test implements A, B {

    public void show(){
        System.out.println("Interface A method");
    }

    public void display(){
        System.out.println("Interface B method");
    }
}

public class Main {

    public static void main(String[] args) {

        Test t = new Test();

        t.show();
        t.display();
    }
}
```

### Output

```
Interface A method
Interface B method
```

---

# 5. Hybrid Inheritance

## Definition

Hybrid inheritance is a **combination of two or more inheritance types**.

Example combination:

* Multilevel
* Hierarchical
* Multiple

Java supports hybrid inheritance **using interfaces**.

---

### Example

```java
interface Animal {

    void eat();
}

interface Pet {

    void play();
}

class Dog implements Animal, Pet {

    public void eat(){
        System.out.println("Dog eats");
    }

    public void play(){
        System.out.println("Dog plays");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.eat();
        d.play();
    }
}
```

---

# Inheritance Keywords in Java

| Keyword      | Purpose             |
| ------------ | ------------------- |
| `extends`    | Inherit class       |
| `implements` | Implement interface |
| `super`      | Access parent class |
| `this`       | Current object      |

---

# Example Using `super`

```java
class Animal {

    Animal(){
        System.out.println("Animal constructor");
    }
}

class Dog extends Animal {

    Dog(){
        super();
        System.out.println("Dog constructor");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
    }
}
```

Output

```
Animal constructor
Dog constructor
```

---

# Summary Table

| Inheritance Type | Description                | Supported in Java    |
| ---------------- | -------------------------- | -------------------- |
| Single           | One parent → One child     | Yes                  |
| Multilevel       | Chain inheritance          | Yes                  |
| Hierarchical     | One parent → Many children | Yes                  |
| Multiple         | Multiple parents           | No (use interfaces)  |
| Hybrid           | Combination                | Only with interfaces |

---

# Real Example in Software

Example: **Vehicle System**

```
Vehicle
   |
   |---- Car
   |---- Bike
   |---- Truck
```

Vehicle provides common methods:

* start()
* stop()
* accelerate()

Child classes provide **specific behavior**.

---

💡 **Interview Tip**

Most asked inheritance questions:

* Difference between **Inheritance vs Composition**
* What is **super keyword**
* Why **Java doesn't support multiple inheritance**
* **Method overriding rules**

---

If you want, I can also explain **very advanced inheritance concepts used in real Java development**:

* **Method Overriding Rules (10 important rules)**
* **Constructor inheritance**
* **super vs this**
* **Runtime polymorphism with inheritance**
* **Diamond Problem deeply**
* **Covariant return types**
* **Java Object class inheritance hierarchy**

These are **very important for Java interviews and deep OOP understanding**.

# 2ed Part

These topics are **advanced Java inheritance concepts** and are very important for **interviews and real-world Java development**. We will explain them **deeply with examples**.

Topics covered:

1. [**Constructor Inheritance**](#1-constructor-inheritance)
2. [**`super` vs `this` keyword**](#2-super-vs-this)
3. [**Runtime Polymorphism with Inheritance**](#3-runtime-polymorphism-with-inheritance)

---

# 1. Constructor Inheritance

## Important Rule

⚠️ **Constructors are NOT inherited in Java.**

But when a child object is created:

1. Parent constructor executes first
2. Then child constructor executes

This happens automatically through **`super()`**.

---

## Constructor Execution Flow

When we create an object:

```
Child object created
        ↓
Parent constructor executes
        ↓
Child constructor executes
```

---

## Example 1 — Basic Constructor Flow

```java
class Animal {

    Animal(){
        System.out.println("Animal constructor called");
    }
}

class Dog extends Animal {

    Dog(){
        System.out.println("Dog constructor called");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
    }
}
```

### Output

```
Animal constructor called
Dog constructor called
```

### Explanation

When `Dog` object is created:

1️⃣ Parent constructor runs
2️⃣ Then child constructor runs

Even if we don't write `super()`, **Java automatically adds it**.

---

## Example 2 — Parent Constructor With Parameters

```java
class Animal {

    Animal(String name){
        System.out.println("Animal name: " + name);
    }
}

class Dog extends Animal {

    Dog(){
        super("Dog");
        System.out.println("Dog constructor executed");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
    }
}
```

### Output

```
Animal name: Dog
Dog constructor executed
```

Here we **explicitly call parent constructor using `super()`**.

---

## Constructor Chaining

Constructor chaining means **calling one constructor from another constructor**.

There are two types:

| Type                     | Keyword   |
| ------------------------ | --------- |
| Within same class        | `this()`  |
| Parent class constructor | `super()` |

---

# 2. `super` vs `this`

Both keywords refer to **objects**, but they have different purposes.

---

# `this` Keyword

## Definition

`this` refers to the **current object of the class**.

### Uses of `this`

1. Refer to current class variables
2. Call current class constructor
3. Pass current object as parameter
4. Return current object

---

## Example 1 — Access Current Class Variables

```java
class Student {

    String name;

    Student(String name){
        this.name = name;
    }

    void display(){
        System.out.println(name);
    }
}

public class Main {

    public static void main(String[] args) {

        Student s = new Student("Rahul");
        s.display();
    }
}
```

Output

```
Rahul
```

Here:

```
this.name = name
```

Current object variable = parameter value.

---

## Example 2 — Constructor Chaining with `this()`

```java
class Test {

    Test(){
        this(10);
        System.out.println("Default constructor");
    }

    Test(int x){
        System.out.println("Parameterized constructor " + x);
    }
}

public class Main {

    public static void main(String[] args) {

        Test t = new Test();
    }
}
```

Output

```
Parameterized constructor 10
Default constructor
```

---

# `super` Keyword

## Definition

`super` refers to the **parent class object**.

### Uses of `super`

1. Call parent constructor
2. Access parent method
3. Access parent variable

---

## Example — Access Parent Method

```java
class Animal {

    void eat(){
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {

    void eat(){
        super.eat();
        System.out.println("Dog eats meat");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
        d.eat();
    }
}
```

Output

```
Animal eats
Dog eats meat
```

---

# Difference Between `this` and `super`

| Feature          | `this`         | `super`       |
| ---------------- | -------------- | ------------- |
| Refers to        | Current object | Parent object |
| Used in          | Same class     | Child class   |
| Constructor call | `this()`       | `super()`     |
| Access variables | Current class  | Parent class  |
| Access methods   | Current class  | Parent class  |

---

# 3. Runtime Polymorphism with Inheritance

## Definition

Runtime polymorphism occurs when **method overriding is resolved at runtime instead of compile time**.

It is also called:

* **Dynamic Method Dispatch**
* **Late Binding**

---

## Key Concept

Parent reference can point to **child object**.

```
Parent obj = new Child();
```

But method executed depends on **actual object type**, not reference type.

---

## Example

```java
class Animal {

    void sound(){
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {

    void sound(){
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {

    void sound(){
        System.out.println("Cat meows");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal a;

        a = new Dog();
        a.sound();

        a = new Cat();
        a.sound();
    }
}
```

---

## Output

```
Dog barks
Cat meows
```

---

## Explanation

```
Animal a = new Dog();
```

Reference type → Animal
Object type → Dog

Java decides method **during runtime**.

---

# Method Overriding Rules

1. Method name must be same
2. Parameters must be same
3. Return type must be same or covariant
4. Access modifier cannot be more restrictive
5. Cannot override `final` methods
6. Static methods cannot be overridden (only hidden)

---

# Real-World Example (Payment System)

```java
class Payment {

    void pay(){
        System.out.println("Generic payment");
    }
}

class CreditCard extends Payment {

    void pay(){
        System.out.println("Payment using credit card");
    }
}

class UPI extends Payment {

    void pay(){
        System.out.println("Payment using UPI");
    }
}

public class Main {

    public static void main(String[] args) {

        Payment p;

        p = new CreditCard();
        p.pay();

        p = new UPI();
        p.pay();
    }
}
```

Output

```
Payment using credit card
Payment using UPI
```

This is **runtime polymorphism used in real payment systems**.

---

# Complete Flow of Inheritance + Polymorphism

```
Object Creation
      ↓
Parent Reference → Child Object
      ↓
Method Overridden in Child
      ↓
Method Decided at Runtime
```

---

# Summary

| Concept                 | Key Idea                             |
| ----------------------- | ------------------------------------ |
| Constructor Inheritance | Parent constructor runs before child |
| `this`                  | Refers to current object             |
| `super`                 | Refers to parent object              |
| Runtime Polymorphism    | Method decided during runtime        |
| Mechanism               | Method Overriding                    |

---

✅ These concepts are **very important in Java interviews**, especially for roles involving **Spring Boot, backend development, and system design**.

---

If you want, I can also explain **even deeper advanced OOP topics used by senior developers**:

* **Covariant Return Types**
* **Method Hiding vs Method Overriding**
* **Diamond Problem in Java**
* **Java Object Class Hierarchy**
* **Abstract Class vs Interface (very deep)**
* **Composition vs Inheritance (important design question)**.
