[2ed Part](#2ed-part)

Object-Oriented Programming (OOP) is a programming paradigm that organizes software around **objects** rather than functions. An **object** represents a real-world entity and contains **data (variables)** and **behavior (methods)**.

The **four pillars of OOP** are:

1. **Encapsulation**
2. **Abstraction**
3. **Inheritance**
4. **Polymorphism**

These principles make software **modular, reusable, maintainable, and secure**.

---

# 1. Encapsulation (Data Hiding)

## Definition

**Encapsulation** is the process of **wrapping data (variables) and methods (functions) into a single unit (class)** and **restricting direct access** to the internal data.

It is also called **Data Hiding**.

Instead of accessing variables directly, we use **getter and setter methods**.

### Why Encapsulation?

* Protects data from unauthorized access
* Improves maintainability
* Allows controlled modification
* Makes code more secure

### Real-Life Example

A **capsule medicine**:

* Medicine inside = data
* Capsule cover = protection
* You cannot access medicine directly

---

## Encapsulation in Java

Steps:

1. Declare variables **private**
2. Provide **public getter and setter methods**

### Example

```java
class BankAccount {

    // private variable (hidden)
    private double balance;

    // setter method
    public void setBalance(double balance){
        if(balance > 0){
            this.balance = balance;
        }
    }

    // getter method
    public double getBalance(){
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {

        BankAccount acc = new BankAccount();

        acc.setBalance(5000);

        System.out.println(acc.getBalance());
    }
}
```

### Output

```
5000
```

Here:

* `balance` cannot be accessed directly
* Access only through methods

---

# 2. Abstraction

## Definition

**Abstraction means hiding implementation details and showing only essential features.**

User interacts with the **interface**, not the **internal working**.

### Real-Life Example

**Car Driving**

You use:

* steering
* brake
* accelerator

But you don't know:

* engine mechanism
* fuel injection system
* transmission

---

## Types of Abstraction in Java

1. **Abstract Class (0–100% abstraction)**
2. **Interface (100% abstraction)**

---

## Abstract Class Example

```java
abstract class Animal {

    abstract void sound();

    void sleep(){
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal {

    void sound(){
        System.out.println("Dog barks");
    }
}

public class Main {

    public static void main(String[] args) {

        Dog d = new Dog();
        d.sound();
        d.sleep();
    }
}
```

### Output

```
Dog barks
Animal is sleeping
```

Here:

* `sound()` is abstract
* Implementation is provided by child class

---

## Interface Example

```java
interface Vehicle {

    void start();
}

class Car implements Vehicle {

    public void start(){
        System.out.println("Car starts with key");
    }
}

public class Main {

    public static void main(String[] args) {

        Car c = new Car();
        c.start();
    }
}
```

---

# 3. Inheritance

## Definition

**Inheritance allows one class to acquire the properties and methods of another class.**

It helps in **code reusability**.

### Terminology

| Term       | Meaning                      |
| ---------- | ---------------------------- |
| Superclass | Parent class                 |
| Subclass   | Child class                  |
| extends    | Keyword used for inheritance |

---

### Real-Life Example

**Father → Son**

Son inherits:

* surname
* properties
* characteristics

---

## Java Inheritance Example

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

        d.eat();
        d.bark();
    }
}
```

### Output

```
Animal eats food
Dog barks
```

---

## Types of Inheritance in Java

| Type         | Supported      |
| ------------ | -------------- |
| Single       | Yes            |
| Multilevel   | Yes            |
| Hierarchical | Yes            |
| Multiple     | No (via class) |
| Hybrid       | No (via class) |

Multiple inheritance is possible using **interfaces**.

---

# 4. Polymorphism

## Definition

**Polymorphism means "many forms".**

One method can perform **different behaviors** depending on the situation.

---

## Types of Polymorphism

| Type         | Also Called        | When Happens       |
| ------------ | ------------------ | ------------------ |
| Compile-time | Method Overloading | During compilation |
| Runtime      | Method Overriding  | During execution   |

---

# Method Overloading (Compile Time)

Same method name but **different parameters**.

```java
class MathOperations {

    int add(int a, int b){
        return a + b;
    }

    int add(int a, int b, int c){
        return a + b + c;
    }
}

public class Main {

    public static void main(String[] args) {

        MathOperations obj = new MathOperations();

        System.out.println(obj.add(2,3));
        System.out.println(obj.add(2,3,4));
    }
}
```

Output

```
5
9
```

---

# Method Overriding (Runtime)

Child class provides **specific implementation** of parent method.

```java
class Animal {

    void sound(){
        System.out.println("Animal makes sound");
    }
}

class Cat extends Animal {

    void sound(){
        System.out.println("Cat meows");
    }
}

public class Main {

    public static void main(String[] args) {

        Animal a = new Cat();

        a.sound();
    }
}
```

Output

```
Cat meows
```

This is **runtime polymorphism**.

---

# Summary of Four Pillars

| Pillar        | Purpose             | Example                  |
| ------------- | ------------------- | ------------------------ |
| Encapsulation | Data hiding         | Private variables        |
| Abstraction   | Hide implementation | Abstract class           |
| Inheritance   | Code reuse          | extends                  |
| Polymorphism  | Multiple behavior   | Overloading / Overriding |

---

# Real-World Example Combining All Four

```java
abstract class Vehicle {

    private int speed;

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

    abstract void start();
}

class Car extends Vehicle {

    void start(){
        System.out.println("Car starts with button");
    }
}

public class Main {

    public static void main(String[] args) {

        Car c = new Car();

        c.setSpeed(120);

        System.out.println(c.getSpeed());

        c.start();
    }
}
```

This program uses:

* **Encapsulation** → private `speed`
* **Abstraction** → abstract class
* **Inheritance** → `Car extends Vehicle`
* **Polymorphism** → `start()` overridden

---

✅ **In interviews**, OOP pillars are one of the **most frequently asked Java questions**.

---

If you want, I can also explain:

* **OOP pillars with one complete real-world project example (Banking System)**
* **Difference between Abstract Class vs Interface (very important for interviews)**
* **Advanced OOP concepts (Composition, Aggregation, Association, SOLID principles)**.


# 2ed Part
Advanced OOP concepts go **beyond the four pillars** and describe **relationships between classes and design principles for scalable software**.

The most important advanced concepts are:

1. **Association**
2. **Aggregation**
3. **Composition**
4. **SOLID Principles**

These are very important for **system design, clean code, and large applications**.

---

# 1. Association

## Definition

**Association is a relationship between two independent classes where objects communicate with each other.**

Both objects **can exist independently**.

### Key Characteristics

* Loose relationship
* No ownership
* Objects have their own lifecycle
* Can be **one-to-one, one-to-many, many-to-many**

### Real-Life Example

**Teacher ↔ Student**

* Teacher can exist without student
* Student can exist without teacher

---

## Java Example

```java
class Student {

    String name;

    Student(String name){
        this.name = name;
    }
}

class Teacher {

    String name;

    Teacher(String name){
        this.name = name;
    }

    void teach(Student s){
        System.out.println(name + " teaches " + s.name);
    }
}

public class Main {

    public static void main(String[] args) {

        Student s = new Student("Rahul");
        Teacher t = new Teacher("Mr. Sharma");

        t.teach(s);
    }
}
```

Output

```
Mr. Sharma teaches Rahul
```

Here:

* Teacher uses Student
* Both are **independent**

---

# 2. Aggregation

## Definition

**Aggregation is a special type of association that represents a "HAS-A" relationship.**

The **child object can exist independently** even if the parent object is destroyed.

### Example

**Department → Employees**

* Employees exist without department
* Department has employees

---

## Java Example

```java
class Employee {

    String name;

    Employee(String name){
        this.name = name;
    }
}

class Department {

    String deptName;
    Employee emp;

    Department(String deptName, Employee emp){
        this.deptName = deptName;
        this.emp = emp;
    }

    void show(){
        System.out.println(emp.name + " works in " + deptName);
    }
}

public class Main {

    public static void main(String[] args) {

        Employee e = new Employee("Amit");

        Department d = new Department("IT", e);

        d.show();
    }
}
```

Output

```
Amit works in IT
```

Here:

* Department **HAS-A** Employee
* Employee still exists independently

---

# 3. Composition

## Definition

**Composition is a strong "HAS-A" relationship where the child object cannot exist without the parent object.**

If parent is destroyed → child is destroyed.

### Example

**House → Rooms**

If house is destroyed → rooms are destroyed.

---

## Java Example

```java
class Engine {

    void start(){
        System.out.println("Engine started");
    }
}

class Car {

    private Engine engine;

    Car(){
        engine = new Engine(); // created inside
    }

    void startCar(){
        engine.start();
        System.out.println("Car started");
    }
}

public class Main {

    public static void main(String[] args) {

        Car c = new Car();
        c.startCar();
    }
}
```

Output

```
Engine started
Car started
```

Here:

* Engine cannot exist without Car
* Engine is created **inside Car**

---

# Difference Between Association, Aggregation, Composition

| Feature              | Association     | Aggregation         | Composition    |
| -------------------- | --------------- | ------------------- | -------------- |
| Relationship         | Uses            | Has-A               | Strong Has-A   |
| Ownership            | No              | Weak                | Strong         |
| Lifecycle dependency | No              | No                  | Yes            |
| UML symbol           | Line            | Hollow Diamond      | Filled Diamond |
| Example              | Teacher-Student | Department-Employee | Car-Engine     |

---

# SOLID Principles

SOLID is a set of **5 object-oriented design principles** for writing **maintainable and scalable code**.

| Letter | Principle                       |
| ------ | ------------------------------- |
| S      | Single Responsibility Principle |
| O      | Open/Closed Principle           |
| L      | Liskov Substitution Principle   |
| I      | Interface Segregation Principle |
| D      | Dependency Inversion Principle  |

---

# 1. Single Responsibility Principle (SRP)

## Definition

A class should have **only one reason to change**.

### Bad Example

```java
class Report {

    void generateReport(){}

    void printReport(){}

    void saveToDatabase(){}
}
```

Problem:

* Too many responsibilities.

---

### Good Example

```java
class ReportGenerator {

    void generateReport(){}
}

class ReportPrinter {

    void printReport(){}
}

class ReportSaver {

    void saveToDatabase(){}
}
```

Now each class has **one responsibility**.

---

# 2. Open Closed Principle (OCP)

## Definition

Software should be:

* **Open for extension**
* **Closed for modification**

You should **add new functionality without changing existing code**.

---

### Example

```java
abstract class Shape {

    abstract double area();
}

class Circle extends Shape {

    double radius;

    Circle(double r){
        radius = r;
    }

    double area(){
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {

    double width, height;

    Rectangle(double w,double h){
        width=w;
        height=h;
    }

    double area(){
        return width*height;
    }
}
```

You can add new shapes **without modifying existing code**.

---

# 3. Liskov Substitution Principle (LSP)

## Definition

Objects of a subclass should **replace the parent class without breaking the program**.

---

### Example

Bad Design

```
Bird
  |
Penguin
```

If Bird has `fly()` method:

* Penguin cannot fly → violates LSP.

Better design

```
Bird
  |
FlyingBird
  |
Eagle
```

---

# 4. Interface Segregation Principle (ISP)

## Definition

Clients should **not be forced to implement interfaces they do not use**.

---

### Bad Example

```java
interface Worker {

    void work();
    void eat();
}
```

Robot must implement `eat()` → wrong.

---

### Good Example

```java
interface Workable {

    void work();
}

interface Eatable {

    void eat();
}
```

---

# 5. Dependency Inversion Principle (DIP)

## Definition

High-level modules should **not depend on low-level modules**.
Both should depend on **abstractions**.

---

### Bad Example

```java
class Keyboard {}

class Computer {

    Keyboard keyboard = new Keyboard();
}
```

Computer depends directly on Keyboard.

---

### Good Example

```java
interface InputDevice {}

class Keyboard implements InputDevice {}

class Computer {

    InputDevice device;

    Computer(InputDevice device){
        this.device = device;
    }
}
```

Now Computer can work with:

* Keyboard
* Mouse
* Touchscreen

---

# Complete OOP Relationship Hierarchy

```
OOP Concepts
│
├── Core Concepts
│   ├── Encapsulation
│   ├── Abstraction
│   ├── Inheritance
│   └── Polymorphism
│
├── Object Relationships
│   ├── Association
│   ├── Aggregation
│   └── Composition
│
└── Design Principles
    └── SOLID
        ├── SRP
        ├── OCP
        ├── LSP
        ├── ISP
        └── DIP
```

---

✅ **In real-world software (Spring Boot, Microservices, large systems)** these concepts are **used heavily**.

---

If you want, I can also explain **very important advanced OOP topics used in big tech interviews**:

* **Dependency Injection (DI)**
* **Design Patterns (Factory, Singleton, Builder, Strategy)**
* **Composition vs Inheritance (very important design question)**
* **Immutable Objects**
* **Domain Driven Design (DDD)**

These are **next-level OOP concepts used in real production systems.**
