# Java Design Patterns — Deep Dive

A complete reference to the 23 classic Gang of Four (GoF) design patterns, grouped into **Creational**, **Structural**, and **Behavioral** categories. Each pattern includes: what problem it solves, when to use it, a full working Java example, and the output you'd see when running it.

---

## Table of Contents

1. [Creational Patterns](#creational-patterns)
   - [Singleton](#1-singleton)
   - [Factory Method](#2-factory-method)
   - [Abstract Factory](#3-abstract-factory)
   - [Builder](#4-builder)
   - [Prototype](#5-prototype)
2. [Structural Patterns](#structural-patterns)
   - [Adapter](#6-adapter)
   - [Bridge](#7-bridge)
   - [Composite](#8-composite)
   - [Decorator](#9-decorator)
   - [Facade](#10-facade)
   - [Flyweight](#11-flyweight)
   - [Proxy](#12-proxy)
3. [Behavioral Patterns](#behavioral-patterns)
   - [Chain of Responsibility](#13-chain-of-responsibility)
   - [Command](#14-command)
   - [Interpreter](#15-interpreter)
   - [Iterator](#16-iterator)
   - [Mediator](#17-mediator)
   - [Memento](#18-memento)
   - [Observer](#19-observer)
   - [State](#20-state)
   - [Strategy](#21-strategy)
   - [Template Method](#22-template-method)
   - [Visitor](#23-visitor)
4. [Quick Comparison Table](#quick-comparison-table)
5. [How to Choose a Pattern](#how-to-choose-a-pattern)

---

## Creational Patterns

Creational patterns deal with **object creation mechanisms**, trying to create objects in a manner suitable to the situation, hiding the creation logic instead of instantiating objects directly with `new`.

### 1. Singleton

**Problem it solves:** Ensure a class has only one instance and provide a global point of access to it (e.g., a configuration manager, logger, or connection pool).

**Key idea:** Private constructor + static instance + static accessor method.

**Use case:** DB connection 

```java
public class Singleton {

    private static volatile Singleton instance;
    private int counter;

    // Private constructor prevents external instantiation
    private Singleton(int data) {
        this.counter = data;
    }

    public static Singleton getInstance(int data) {

        Singleton result = instance;  // Memory effecient

        if(result == null){  // Double lock
            synchronized(Singleton.class){ // Thread safe
                if(reault == null){  // Create only one
                    result = instance = new Singleton(data);
                }
            }            
        }

        return result;
    }

}

```

**Output:**
```
Singleton instance created
a == b : true
Counter (shared state): 2
```

**Notes / Pitfalls:**
- The "initialization-on-demand holder" idiom above is thread-safe without needing `synchronized`, because the JVM guarantees class-loading is atomic.
- An `enum` singleton (`enum Singleton { INSTANCE; }`) is another common, even simpler, thread-safe approach and also protects against reflection/serialization attacks.
- Overuse of Singleton is considered an anti-pattern because it introduces global state, making unit testing harder.

---

### 2. Factory Method

**Problem it solves:** Define an interface for creating an object, but let subclasses decide which class to instantiate. Useful when a class can't anticipate the type of objects it needs to create.

```java
// --- Burger Interface ---
interface Burger {
    void prepare();
}

// --- Concrete Burger Implementations ---
class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger with bun, patty, and ketchup!");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Burger with bun, patty, cheese, and lettuce!");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Burger with gourmet bun, premium patty, cheese, lettuce, and secret sauce!");
    }
}

// --- Burger Factory ---
class BurgerFactory {
    public Burger createBurger(String type) {
        if (type.equalsIgnoreCase("basic")) {
            return new BasicBurger();
        } else if (type.equalsIgnoreCase("standard")) {
            return new StandardBurger();
        } else if (type.equalsIgnoreCase("premium")) {
            return new PremiumBurger();
        } else {
            System.out.println("Invalid burger type!");
            return null;
        }
    }
}

```

**Notes:** Client code depends only on the abstract `NotificationFactory`/`Notification`, not concrete classes — this satisfies the Open/Closed Principle: new notification types can be added without touching existing code.

---

### 3. Abstract Factory

**Problem it solves:** Provide an interface for creating **families of related objects** without specifying their concrete classes (a "factory of factories").

```java
// ==========================================
// 1. ABSTRACT PRODUCTS (Interfaces)
// ==========================================

interface Burger {
    void prepare();
}

interface GarlicBread {
    void prepare();
}

// ==========================================
// 2. CONCRETE PRODUCTS - Regular Variants (Singh Burger Family)
// ==========================================

class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Regular Basic Burger (Bun + Patty + Ketchup)");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Regular Standard Burger (Bun + Patty + Cheese + Lettuce)");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Regular Premium Burger (Gourmet Bun + Patty + Secret Sauce)");
    }
}

class BasicGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing Regular Basic Garlic Bread (Butter + Garlic)");
    }
}

class CheeseGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing Regular Cheese Garlic Bread (Butter + Garlic + Extra Cheese)");
    }
}

// ==========================================
// 3. CONCRETE PRODUCTS - Wheat Variants (King Burger Family)
// ==========================================

class BasicWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Wheat Basic Burger (Wheat Bun + Patty + Ketchup)");
    }
}

class StandardWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Wheat Standard Burger (Wheat Bun + Patty + Cheese + Lettuce)");
    }
}

class PremiumWheatBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Wheat Premium Burger (Gourmet Wheat Bun + Patty + Secret Sauce)");
    }
}

class BasicWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing Wheat Basic Garlic Bread (Wheat Bread + Butter + Garlic)");
    }
}

class CheeseWheatGarlicBread implements GarlicBread {
    @Override
    public void prepare() {
        System.out.println("Preparing Wheat Cheese Garlic Bread (Wheat Bread + Butter + Garlic + Extra Cheese)");
    }
}

// ==========================================
// 4. ABSTRACT FACTORY INTERFACE
// ==========================================

interface MealFactory {
    Burger createBurger(String type);
    GarlicBread createGarlicBread(String type);
}

// ==========================================
// 5. CONCRETE FACTORIES
// ==========================================

// Factory producing Regular (Non-Wheat) Items
class SinghBurgerFactory implements MealFactory {
    @Override
    public Burger createBurger(String type) {
        return switch (type.toLowerCase()) {
            case "basic" -> new BasicBurger();
            case "standard" -> new StandardBurger();
            case "premium" -> new PremiumBurger();
            default -> {
                System.out.println("Invalid Regular Burger type: " + type);
                yield null;
            }
        };
    }

    @Override
    public GarlicBread createGarlicBread(String type) {
        return switch (type.toLowerCase()) {
            case "basic" -> new BasicGarlicBread();
            case "cheese" -> new CheeseGarlicBread();
            default -> {
                System.out.println("Invalid Regular Garlic Bread type: " + type);
                yield null;
            }
        };
    }
}

// Factory producing Wheat-based Items
class KingBurgerFactory implements MealFactory {
    @Override
    public Burger createBurger(String type) {
        return switch (type.toLowerCase()) {
            case "basic" -> new BasicWheatBurger();
            case "standard" -> new StandardWheatBurger();
            case "premium" -> new PremiumWheatBurger();
            default -> {
                System.out.println("Invalid Wheat Burger type: " + type);
                yield null;
            }
        };
    }

    @Override
    public GarlicBread createGarlicBread(String type) {
        return switch (type.toLowerCase()) {
            case "basic" -> new BasicWheatGarlicBread();
            case "cheese" -> new CheeseWheatGarlicBread();
            default -> {
                System.out.println("Invalid Wheat Garlic Bread type: " + type);
                yield null;
            }
        };
    }
}

// ==========================================
// 6. MAIN DEMO CLASS
// ==========================================

public class AbstractFactoryDemo {

    // Helper method: Client depends ONLY on MealFactory interface, not concrete implementations
    private static void prepareOrder(MealFactory factory, String burgerType, String breadType) {
        Burger burger = factory.createBurger(burgerType);
        GarlicBread garlicBread = factory.createGarlicBread(breadType);

        if (burger != null) burger.prepare();
        if (garlicBread != null) garlicBread.prepare();
    }

    public static void main(String[] args) {
        System.out.println("=== Order 1: Regular Meal (Singh Burger) ===");
        MealFactory regularMealFactory = new SinghBurgerFactory();
        prepareOrder(regularMealFactory, "basic", "cheese");

        System.out.println("\n=== Order 2: Healthier Wheat Meal (King Burger) ===");
        MealFactory wheatMealFactory = new KingBurgerFactory();
        prepareOrder(wheatMealFactory, "premium", "basic");
    }
}

```
**Output:**
```
Rendering a Mac-style button
Rendering a Mac-style checkbox
```

**Factory Method vs Abstract Factory:** Factory Method creates **one** product via inheritance (a single overridden method). Abstract Factory creates **families** of related products via composition (an injected factory object with multiple creation methods).

---

### 4. Builder

**Problem it solves:** Separate the construction of a complex object from its representation, so the same construction process can create different representations. Ideal when a constructor would otherwise need many optional parameters ("telescoping constructor" problem).

```java
public class Computer {
    // Required
    private final String cpu;
    private final String ram;
    // Optional
    private final String storage;
    private final boolean hasGraphicsCard;
    private final boolean hasBluetooth;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
        this.hasBluetooth = builder.hasBluetooth;
    }

    @Override
    public String toString() {
        return "Computer{cpu='" + cpu + "', ram='" + ram + "', storage='" + storage +
                "', graphicsCard=" + hasGraphicsCard + ", bluetooth=" + hasBluetooth + '}';
    }

    // Static nested Builder class
    public static class Builder {
        private final String cpu;
        private final String ram;
        private String storage = "256GB SSD"; // sensible default
        private boolean hasGraphicsCard = false;
        private boolean hasBluetooth = false;

        public Builder(String cpu, String ram) { // required fields go in the builder's constructor
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this; // enables fluent chaining
        }

        public Builder graphicsCard(boolean value) {
            this.hasGraphicsCard = value;
            return this;
        }

        public Builder bluetooth(boolean value) {
            this.hasBluetooth = value;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

class BuilderDemo {
    public static void main(String[] args) {
        Computer gamingPc = new Computer.Builder("Intel i9", "32GB")
                .storage("2TB NVMe SSD")
                .graphicsCard(true)
                .bluetooth(true)
                .build();

        Computer officePc = new Computer.Builder("Intel i5", "16GB").build();

        System.out.println(gamingPc);
        System.out.println(officePc);
    }
}
```

**Output:**
```
Computer{cpu='Intel i9', ram='32GB', storage='2TB NVMe SSD', graphicsCard=true, bluetooth=true}
Computer{cpu='Intel i5', ram='16GB', storage='256GB SSD', graphicsCard=false, bluetooth=false}
```

**Notes:** This is the classic "Effective Java" builder pattern (item 2). The built object (`Computer`) is immutable — all fields are `final`.

---

### 5. Prototype

**Problem it solves:** Create new objects by copying an existing object (a "prototype") rather than creating from scratch — useful when object creation is expensive or complex.

```java
import java.util.ArrayList;
import java.util.List;

// Prototype interface
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

class Circle implements Shape {
    private int radius;
    private String color;

    public Circle(int radius, String color) {
        this.radius = radius;
        this.color = color;
        // Imagine this constructor does expensive work, e.g., loading resources
        simulateExpensiveSetup();
    }

    private void simulateExpensiveSetup() {
        System.out.println("Performing expensive setup for a new Circle...");
    }

    // Copy constructor used internally for cloning
    private Circle(Circle source) {
        this.radius = source.radius;
        this.color = source.color;
    }

    @Override
    public Circle clone() {
        return new Circle(this); // cheap copy, no expensive setup re-run
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle of radius " + radius);
    }
}

class PrototypeDemo {
    public static void main(String[] args) {
        Circle original = new Circle(10, "red");   // expensive to create
        original.draw();

        // Clone instead of re-running expensive construction
        Circle clone1 = original.clone();
        clone1.setColor("blue");
        clone1.draw();

        Circle clone2 = original.clone();
        clone2.setColor("green");
        clone2.draw();

        original.draw(); // original is unaffected
    }
}
```

**Output:**
```
Performing expensive setup for a new Circle...
Drawing a red circle of radius 10
Drawing a blue circle of radius 10
Drawing a green circle of radius 10
Drawing a red circle of radius 10
```

**Notes:** Java's built-in `Object.clone()` + `Cloneable` can be used too, but it's notoriously finicky (shallow copy by default, checked exceptions, no constructor call). Many real-world codebases prefer explicit copy constructors, as shown above.

---

## Structural Patterns

Structural patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient.

### 6. Adapter

**Problem it solves:** Convert the interface of a class into another interface clients expect, letting incompatible interfaces work together (like a power-plug adapter).

```java
// Target interface the client expects
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee - an existing, incompatible class
class AdvancedMediaPlayer {
    void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
    void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter bridges MediaPlayer to AdvancedMediaPlayer
class MediaAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Client-facing class
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName); // native support
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            MediaAdapter adapter = new MediaAdapter();
            adapter.play(audioType, fileName); // delegate via adapter
        } else {
            System.out.println("Unsupported format: " + audioType);
        }
    }
}

class AdapterDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "documentary.vlc");
        player.play("avi", "clip.avi");
    }
}
```

**Output:**
```
Playing mp3 file: song.mp3
Playing mp4 file: movie.mp4
Playing vlc file: documentary.vlc
Unsupported format: avi
```

---

### 7. Bridge

**Problem it solves:** Decouple an abstraction from its implementation so the two can vary independently (avoids a combinatorial explosion of subclasses, e.g. `RedCircle`, `BlueCircle`, `RedSquare`...).

```java
// Implementor
interface Color {
    void applyColor();
}

class RedColor implements Color {
    public void applyColor() { System.out.println("applying red color"); }
}
class BlueColor implements Color {
    public void applyColor() { System.out.println("applying blue color"); }
}

// Abstraction
abstract class Shape2 {
    protected Color color; // bridge to the implementor

    protected Shape2(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// Refined Abstractions
class Square extends Shape2 {
    public Square(Color color) { super(color); }
    public void draw() {
        System.out.print("Square drawn with ");
        color.applyColor();
    }
}
class Triangle extends Shape2 {
    public Triangle(Color color) { super(color); }
    public void draw() {
        System.out.print("Triangle drawn with ");
        color.applyColor();
    }
}

class BridgeDemo {
    public static void main(String[] args) {
        Shape2 redSquare = new Square(new RedColor());
        Shape2 blueTriangle = new Triangle(new BlueColor());

        redSquare.draw();
        blueTriangle.draw();
    }
}
```

**Output:**
```
Square drawn with applying red color
Triangle drawn with applying blue color
```

**Bridge vs Adapter:** Adapter is applied **after the fact** to make unrelated classes work together. Bridge is designed **up front** so abstraction and implementation can evolve independently.

---

### 8. Composite

**Problem it solves:** Compose objects into tree structures to represent part-whole hierarchies, letting clients treat individual objects and compositions uniformly (files vs folders, UI widgets vs containers).

```java
import java.util.ArrayList;
import java.util.List;

// Component
interface FileSystemItem {
    void showDetails(String indent);
    int getSize();
}

// Leaf
class File implements FileSystemItem {
    private final String name;
    private final int size; // in KB

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void showDetails(String indent) {
        System.out.println(indent + "File: " + name + " (" + size + "KB)");
    }

    public int getSize() {
        return size;
    }
}

// Composite
class Folder implements FileSystemItem {
    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    public void showDetails(String indent) {
        System.out.println(indent + "Folder: " + name + " (" + getSize() + "KB total)");
        for (FileSystemItem child : children) {
            child.showDetails(indent + "  ");
        }
    }

    public int getSize() {
        int total = 0;
        for (FileSystemItem child : children) {
            total += child.getSize(); // recursively sums leaves and sub-folders
        }
        return total;
    }
}

class CompositeDemo {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder documents = new Folder("documents");
        Folder photos = new Folder("photos");

        documents.add(new File("resume.pdf", 120));
        documents.add(new File("notes.txt", 5));

        photos.add(new File("vacation.jpg", 2500));
        photos.add(new File("family.png", 1800));

        root.add(documents);
        root.add(photos);
        root.add(new File("readme.md", 2));

        root.showDetails("");
    }
}
```

**Output:**
```
Folder: root (4427KB total)
  Folder: documents (125KB total)
    File: resume.pdf (120KB)
    File: notes.txt (5KB)
  Folder: photos (4300KB total)
    File: vacation.jpg (2500KB)
    File: family.png (1800KB)
  File: readme.md (2KB)
```

---

### 9. Decorator

**Problem it solves:** Attach additional responsibilities to an object dynamically without altering its class — a flexible alternative to subclassing (e.g., `java.io` streams).

```java
// Component
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Coffee"; }
    public double getCost() { return 2.00; }
}

// Base Decorator
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() { return decoratedCoffee.getDescription(); }
    public double getCost() { return decoratedCoffee.getCost(); }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return decoratedCoffee.getDescription() + " + Milk"; }
    public double getCost() { return decoratedCoffee.getCost() + 0.50; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return decoratedCoffee.getDescription() + " + Sugar"; }
    public double getCost() { return decoratedCoffee.getCost() + 0.25; }
}

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) { super(coffee); }
    public String getDescription() { return decoratedCoffee.getDescription() + " + Whipped Cream"; }
    public double getCost() { return decoratedCoffee.getCost() + 0.75; }
}

class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.printf("%s : $%.2f%n", coffee.getDescription(), coffee.getCost());

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new WhippedCreamDecorator(coffee); // stacking decorators at runtime

        System.out.printf("%s : $%.2f%n", coffee.getDescription(), coffee.getCost());
    }
}
```

**Output:**
```
Coffee : $2.00
Coffee + Milk + Sugar + Whipped Cream : $3.50
```

---

### 10. Facade

**Problem it solves:** Provide a unified, simplified interface to a set of interfaces in a complex subsystem, making the subsystem easier to use.

```java
// Complex subsystem classes
class CPU {
    void freeze() { System.out.println("CPU: freezing"); }
    void jump(long position) { System.out.println("CPU: jumping to " + position); }
    void execute() { System.out.println("CPU: executing"); }
}

class Memory {
    void load(long position, byte[] data) {
        System.out.println("Memory: loading data at position " + position);
    }
}

class HardDrive {
    byte[] read(long lba, int size) {
        System.out.println("HardDrive: reading " + size + " bytes from sector " + lba);
        return new byte[size];
    }
}

// Facade
class ComputerFacade {
    private final CPU cpu = new CPU();
    private final Memory memory = new Memory();
    private final HardDrive hardDrive = new HardDrive();

    public void start() {
        System.out.println("--- Booting computer ---");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("--- Boot complete ---");
    }
}

class FacadeDemo {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start(); // client doesn't need to know about CPU/Memory/HardDrive details
    }
}
```

**Output:**
```
--- Booting computer ---
CPU: freezing
HardDrive: reading 1024 bytes from sector 0
Memory: loading data at position 0
CPU: jumping to 0
CPU: executing
--- Boot complete ---
```

---

### 11. Flyweight

**Problem it solves:** Use sharing to support large numbers of fine-grained objects efficiently, by separating **intrinsic** (shared, immutable) state from **extrinsic** (unique, passed-in) state.

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight - intrinsic state (character glyph shape) is shared
class CharacterGlyph {
    private final char symbol;    // intrinsic
    private final String fontFamily; // intrinsic

    public CharacterGlyph(char symbol, String fontFamily) {
        this.symbol = symbol;
        this.fontFamily = fontFamily;
        System.out.println("Creating new glyph for '" + symbol + "' in " + fontFamily);
    }

    // extrinsic state (position, size, color) passed in at render time
    public void render(int x, int y, int fontSize) {
        System.out.println("Rendering '" + symbol + "' (" + fontFamily + ") at (" + x + "," + y + ") size " + fontSize);
    }
}

// Flyweight Factory - ensures glyphs are shared/reused
class GlyphFactory {
    private static final Map<String, CharacterGlyph> cache = new HashMap<>();

    public static CharacterGlyph getGlyph(char symbol, String fontFamily) {
        String key = symbol + "_" + fontFamily;
        return cache.computeIfAbsent(key, k -> new CharacterGlyph(symbol, fontFamily));
    }

    public static int cacheSize() {
        return cache.size();
    }
}

class FlyweightDemo {
    public static void main(String[] args) {
        String text = "HELLO";
        int x = 0;

        for (char c : text.toCharArray()) {
            CharacterGlyph glyph = GlyphFactory.getGlyph(c, "Arial"); // reused for repeated letters
            glyph.render(x, 0, 12);
            x += 10;
        }

        System.out.println("Total distinct glyph objects created: " + GlyphFactory.cacheSize());
    }
}
```

**Output:**
```
Creating new glyph for 'H' in Arial
Rendering 'H' (Arial) at (0,0) size 12
Creating new glyph for 'E' in Arial
Rendering 'E' (Arial) at (10,0) size 12
Creating new glyph for 'L' in Arial
Rendering 'L' (Arial) at (20,0) size 12
Rendering 'L' (Arial) at (30,0) size 12
Creating new glyph for 'O' in Arial
Rendering 'O' (Arial) at (40,0) size 12
Total distinct glyph objects created: 4
```

Note only 4 glyph objects were created for 5 characters — the second `'L'` was reused from the cache.

---

### 12. Proxy

**Problem it solves:** Provide a surrogate or placeholder for another object to control access to it (lazy loading, access control, logging, caching, remote proxies).

```java
// Subject
interface Image {
    void display();
}

// Real Subject - expensive to create
class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName + " from disk (expensive I/O)");
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy - controls access, defers creation until actually needed
class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage; // not created until needed

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // lazy initialization
        }
        realImage.display();
    }
}

class ProxyDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("large_photo.jpg");

        System.out.println("Image object created, but not loaded yet.");
        System.out.println("Calling display() for the first time:");
        image.display(); // triggers real loading

        System.out.println("Calling display() again:");
        image.display(); // no reload, already cached
    }
}
```

**Output:**
```
Image object created, but not loaded yet.
Calling display() for the first time:
Loading large_photo.jpg from disk (expensive I/O)
Displaying large_photo.jpg
Calling display() again:
Displaying large_photo.jpg
```

---

## Behavioral Patterns

Behavioral patterns are concerned with algorithms and the assignment of responsibilities between objects — how objects communicate and interact.

### 13. Chain of Responsibility

**Problem it solves:** Pass a request along a chain of handlers; each handler decides to process it or pass it to the next (e.g., middleware pipelines, approval workflows).

```java
abstract class Approver {
    protected Approver next;

    public Approver setNext(Approver next) {
        this.next = next;
        return next; // enables fluent chain-building
    }

    public abstract void approve(int amount);
}

class TeamLead extends Approver {
    public void approve(int amount) {
        if (amount <= 1000) {
            System.out.println("TeamLead approved expense of $" + amount);
        } else if (next != null) {
            next.approve(amount);
        }
    }
}

class Manager extends Approver {
    public void approve(int amount) {
        if (amount <= 10000) {
            System.out.println("Manager approved expense of $" + amount);
        } else if (next != null) {
            next.approve(amount);
        }
    }
}

class Director extends Approver {
    public void approve(int amount) {
        if (amount <= 100000) {
            System.out.println("Director approved expense of $" + amount);
        } else if (next != null) {
            next.approve(amount);
        } else {
            System.out.println("Expense of $" + amount + " requires board approval");
        }
    }
}

class ChainDemo {
    public static void main(String[] args) {
        Approver teamLead = new TeamLead();
        Approver manager = new Manager();
        Approver director = new Director();

        teamLead.setNext(manager).setNext(director); // build the chain

        teamLead.approve(500);
        teamLead.approve(5000);
        teamLead.approve(50000);
        teamLead.approve(500000);
    }
}
```

**Output:**
```
TeamLead approved expense of $500
Manager approved expense of $5000
Director approved expense of $50000
Expense of $500000 requires board approval
```

---

### 14. Command

**Problem it solves:** Encapsulate a request as an object, letting you parameterize clients with queues, requests, and operations, and supports undo/redo.

```java
import java.util.Stack;

// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver
class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void append(String str) {
        text.append(str);
    }

    public void delete(int length) {
        text.delete(text.length() - length, text.length());
    }

    public String getText() {
        return text.toString();
    }
}

// Concrete Command
class AppendTextCommand implements Command {
    private final TextEditor editor;
    private final String textToAppend;

    public AppendTextCommand(TextEditor editor, String textToAppend) {
        this.editor = editor;
        this.textToAppend = textToAppend;
    }

    public void execute() {
        editor.append(textToAppend);
    }

    public void undo() {
        editor.delete(textToAppend.length());
    }
}

// Invoker
class CommandManager {
    private final Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}

class CommandDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandManager manager = new CommandManager();

        manager.executeCommand(new AppendTextCommand(editor, "Hello, "));
        manager.executeCommand(new AppendTextCommand(editor, "World!"));
        System.out.println("Text: " + editor.getText());

        manager.undoLast(); // undoes "World!"
        System.out.println("After undo: " + editor.getText());
    }
}
```

**Output:**
```
Text: Hello, World!
After undo: Hello, 
```

---

### 15. Interpreter

**Problem it solves:** Given a language, define a representation for its grammar along with an interpreter that uses the representation to interpret sentences (e.g., simple expression evaluators, rule engines).

```java
import java.util.Map;
import java.util.HashMap;

// Abstract Expression
interface Expression {
    int interpret(Map<String, Integer> context);
}

// Terminal Expression - a variable
class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    public int interpret(Map<String, Integer> context) {
        return context.getOrDefault(name, 0);
    }
}

// Terminal Expression - a literal number
class Number implements Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public int interpret(Map<String, Integer> context) {
        return value;
    }
}

// Non-terminal Expressions
class Add implements Expression {
    private final Expression left, right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) + right.interpret(context);
    }
}

class Multiply implements Expression {
    private final Expression left, right;

    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) * right.interpret(context);
    }
}

class InterpreterDemo {
    public static void main(String[] args) {
        // Represents the expression: (x + 5) * y
        Expression expression = new Multiply(
                new Add(new Variable("x"), new Number(5)),
                new Variable("y")
        );

        Map<String, Integer> context = new HashMap<>();
        context.put("x", 3);
        context.put("y", 4);

        int result = expression.interpret(context);
        System.out.println("(x + 5) * y  where x=3, y=4  =  " + result);
    }
}
```

**Output:**
```
(x + 5) * y  where x=3, y=4  =  32
```

---

### 16. Iterator

**Problem it solves:** Provide a way to access elements of a collection sequentially without exposing its underlying representation.

```java
import java.util.Iterator;
import java.util.NoSuchElementException;

// Custom collection
class BookShelf implements Iterable<String> {
    private final String[] books;
    private int count = 0;

    public BookShelf(int capacity) {
        books = new String[capacity];
    }

    public void addBook(String title) {
        if (count < books.length) {
            books[count++] = title;
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new BookShelfIterator();
    }

    // Custom Iterator implementation
    private class BookShelfIterator implements Iterator<String> {
        private int index = 0;

        public boolean hasNext() {
            return index < count;
        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return books[index++];
        }
    }
}

class IteratorDemo {
    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(5);
        shelf.addBook("Clean Code");
        shelf.addBook("Effective Java");
        shelf.addBook("Design Patterns");

        // Because BookShelf implements Iterable, it works in a for-each loop
        for (String title : shelf) {
            System.out.println("Book: " + title);
        }
    }
}
```

**Output:**
```
Book: Clean Code
Book: Effective Java
Book: Design Patterns
```

---

### 17. Mediator

**Problem it solves:** Define an object that encapsulates how a set of objects interact, promoting loose coupling by preventing objects from referring to each other directly (e.g., chat rooms, air-traffic control).

```java
import java.util.ArrayList;
import java.util.List;

// Mediator
interface ChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) { // don't echo back to sender
                user.receive(message, sender.getName());
            }
        }
    }
}

// Colleague
class User {
    private final String name;
    private final ChatMediator mediator;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this); // never talks to other Users directly
    }

    public void receive(String message, String senderName) {
        System.out.println(name + " receives from " + senderName + ": " + message);
    }
}

class MediatorDemo {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User alice = new User("Alice", chatRoom);
        User bob = new User("Bob", chatRoom);
        User charlie = new User("Charlie", chatRoom);

        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);

        alice.send("Hi everyone!");
    }
}
```

**Output:**
```
Alice sends: Hi everyone!
Bob receives from Alice: Hi everyone!
Charlie receives from Alice: Hi everyone!
```

---

### 18. Memento

**Problem it solves:** Capture and externalize an object's internal state without violating encapsulation, so it can be restored later (undo mechanisms, snapshots).

```java
import java.util.Stack;

// Memento - stores state
final class EditorMemento {
    private final String content;

    EditorMemento(String content) {
        this.content = content;
    }

    String getContent() {
        return content; // package-private access, only Originator/Caretaker can read it
    }
}

// Originator
class Editor {
    private String content = "";

    public void write(String text) {
        content += text;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content); // creates a snapshot
    }

    public void restore(EditorMemento memento) {
        content = memento.getContent();
    }
}

// Caretaker
class History {
    private final Stack<EditorMemento> snapshots = new Stack<>();

    public void push(EditorMemento memento) {
        snapshots.push(memento);
    }

    public EditorMemento pop() {
        return snapshots.pop();
    }
}

class MementoDemo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.write("Hello");
        history.push(editor.save()); // snapshot after "Hello"

        editor.write(", World");
        history.push(editor.save()); // snapshot after "Hello, World"

        editor.write("!!!");
        System.out.println("Current: " + editor.getContent());

        editor.restore(history.pop()); // back to "Hello, World"
        System.out.println("After undo 1: " + editor.getContent());

        editor.restore(history.pop()); // back to "Hello"
        System.out.println("After undo 2: " + editor.getContent());
    }
}
```

**Output:**
```
Current: Hello, World!!!
After undo 1: Hello, World
After undo 2: Hello
```

---

### 19. Observer

**Problem it solves:** Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically (event systems, pub/sub, `java.util.Observer` predecessor, reactive frameworks).

```java
import java.util.ArrayList;
import java.util.List;

// Observer
interface Subscriber {
    void update(String event);
}

// Subject
interface Publisher {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String event);
}

// Concrete Subject
class NewsPublisher implements Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(String event) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(event);
        }
    }

    public void publishNews(String headline) {
        System.out.println("\n[Publisher] Breaking news: " + headline);
        notifySubscribers(headline);
    }
}

// Concrete Observers
class EmailSubscriber implements Subscriber {
    private final String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    public void update(String event) {
        System.out.println("Emailing " + email + ": " + event);
    }
}

class AppSubscriber implements Subscriber {
    private final String username;

    public AppSubscriber(String username) {
        this.username = username;
    }

    public void update(String event) {
        System.out.println("Push notification to " + username + ": " + event);
    }
}

class ObserverDemo {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        Subscriber emailSub = new EmailSubscriber("alice@example.com");
        Subscriber appSub = new AppSubscriber("bob_app_user");

        publisher.subscribe(emailSub);
        publisher.subscribe(appSub);

        publisher.publishNews("Design Patterns are essential!");

        publisher.unsubscribe(emailSub);
        publisher.publishNews("Alice unsubscribed and won't see this");
    }
}
```

**Output:**
```
[Publisher] Breaking news: Design Patterns are essential!
Emailing alice@example.com: Design Patterns are essential!
Push notification to bob_app_user: Design Patterns are essential!

[Publisher] Breaking news: Alice unsubscribed and won't see this
Push notification to bob_app_user: Alice unsubscribed and won't see this
```

---

### 20. State

**Problem it solves:** Allow an object to alter its behavior when its internal state changes — the object appears to change its class (e.g., a traffic light, a document workflow, a vending machine).

```java
// State interface
interface OrderState {
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getName();
}

// Concrete States
class PendingState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order is being processed.");
        context.setState(new ProcessingState());
    }
    public void cancel(OrderContext context) {
        System.out.println("Order cancelled from Pending.");
        context.setState(new CancelledState());
    }
    public String getName() { return "PENDING"; }
}

class ProcessingState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order has been shipped.");
        context.setState(new ShippedState());
    }
    public void cancel(OrderContext context) {
        System.out.println("Order cancelled during Processing.");
        context.setState(new CancelledState());
    }
    public String getName() { return "PROCESSING"; }
}

class ShippedState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order delivered.");
        context.setState(new DeliveredState());
    }
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel — order already shipped!");
    }
    public String getName() { return "SHIPPED"; }
}

class DeliveredState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Order already delivered — nothing further to do.");
    }
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel — order already delivered!");
    }
    public String getName() { return "DELIVERED"; }
}

class CancelledState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Cannot proceed — order is cancelled.");
    }
    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }
    public String getName() { return "CANCELLED"; }
}

// Context
class OrderContext {
    private OrderState state = new PendingState();

    public void setState(OrderState state) {
        this.state = state;
    }

    public void next() {
        state.next(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public String getStateName() {
        return state.getName();
    }
}

class StateDemo {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        System.out.println("State: " + order.getStateName());

        order.next(); // Pending -> Processing
        order.next(); // Processing -> Shipped
        order.cancel(); // rejected, already shipped
        order.next(); // Shipped -> Delivered

        System.out.println("Final state: " + order.getStateName());
    }
}
```

**Output:**
```
State: PENDING
Order is being processed.
Order has been shipped.
Cannot cancel — order already shipped!
Order delivered.
Final state: DELIVERED
```

---

### 21. Strategy

**Problem it solves:** Define a family of interchangeable algorithms, encapsulate each one, and make them interchangeable at runtime (e.g., different sorting/pricing/payment strategies).

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f using Credit Card ending in %s%n",
                amount, cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f using PayPal account %s%n", amount, email);
    }
}

class CryptoPayment implements PaymentStrategy {
    private final String walletAddress;

    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void pay(double amount) {
        System.out.printf("Paid $%.2f using Crypto wallet %s%n", amount, walletAddress);
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount); // delegates to whichever strategy is set
    }
}

class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234567812345678"));
        cart.checkout(150.00);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(75.50);

        cart.setPaymentStrategy(new CryptoPayment("0xA1b2C3..."));
        cart.checkout(200.00);
    }
}
```

**Output:**
```
Paid $150.00 using Credit Card ending in 5678
Paid $75.50 using PayPal account user@example.com
Paid $200.00 using Crypto wallet 0xA1b2C3...
```

**Strategy vs State:** Structurally almost identical! The difference is intent: Strategy lets the **client** choose an algorithm; State lets the **object itself** transition between behaviors based on internal triggers.

---

### 22. Template Method

**Problem it solves:** Define the skeleton of an algorithm in a base class, deferring some steps to subclasses without changing the algorithm's overall structure.

```java
// Abstract class defining the template method
abstract class DataProcessor {

    // Template method - final so subclasses can't change the overall algorithm
    public final void process() {
        readData();
        processData();
        if (shouldValidate()) { // hook - subclasses can influence flow
            validateData();
        }
        writeData();
    }

    protected abstract void readData();
    protected abstract void processData();
    protected abstract void writeData();

    // Hook with a default implementation - optional override
    protected boolean shouldValidate() {
        return true;
    }

    private void validateData() {
        System.out.println("Validating data (common step)");
    }
}

// Concrete implementation - CSV
class CsvDataProcessor extends DataProcessor {
    protected void readData() { System.out.println("Reading data from CSV file"); }
    protected void processData() { System.out.println("Parsing CSV rows into objects"); }
    protected void writeData() { System.out.println("Writing processed data to database"); }
}

// Concrete implementation - JSON, skips validation
class JsonDataProcessor extends DataProcessor {
    protected void readData() { System.out.println("Reading data from JSON API"); }
    protected void processData() { System.out.println("Deserializing JSON into objects"); }
    protected void writeData() { System.out.println("Writing processed data to cache"); }

    @Override
    protected boolean shouldValidate() {
        return false; // this subclass skips the validation step
    }
}

class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("--- CSV Processing ---");
        DataProcessor csvProcessor = new CsvDataProcessor();
        csvProcessor.process();

        System.out.println("\n--- JSON Processing ---");
        DataProcessor jsonProcessor = new JsonDataProcessor();
        jsonProcessor.process();
    }
}
```

**Output:**
```
--- CSV Processing ---
Reading data from CSV file
Parsing CSV rows into objects
Validating data (common step)
Writing processed data to database

--- JSON Processing ---
Reading data from JSON API
Deserializing JSON into objects
Writing processed data to cache
```

---

### 23. Visitor

**Problem it solves:** Represent an operation to be performed on elements of an object structure, letting you define a new operation without changing the classes of the elements it operates on (double dispatch).

```java
// Element interface
interface ShapeElement {
    void accept(ShapeVisitor visitor);
}

// Concrete Elements
class Circle2 implements ShapeElement {
    public final double radius;
    public Circle2(double radius) { this.radius = radius; }
    public void accept(ShapeVisitor visitor) { visitor.visit(this); }
}

class Rectangle implements ShapeElement {
    public final double width, height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public void accept(ShapeVisitor visitor) { visitor.visit(this); }
}

class Triangle2 implements ShapeElement {
    public final double base, height;
    public Triangle2(double base, double height) {
        this.base = base;
        this.height = height;
    }
    public void accept(ShapeVisitor visitor) { visitor.visit(this); }
}

// Visitor interface - one visit method per concrete element type
interface ShapeVisitor {
    void visit(Circle2 circle);
    void visit(Rectangle rectangle);
    void visit(Triangle2 triangle);
}

// Concrete Visitor - calculates area
class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0;

    public void visit(Circle2 circle) {
        double area = Math.PI * circle.radius * circle.radius;
        System.out.printf("Circle area: %.2f%n", area);
        totalArea += area;
    }

    public void visit(Rectangle rectangle) {
        double area = rectangle.width * rectangle.height;
        System.out.printf("Rectangle area: %.2f%n", area);
        totalArea += area;
    }

    public void visit(Triangle2 triangle) {
        double area = 0.5 * triangle.base * triangle.height;
        System.out.printf("Triangle area: %.2f%n", area);
        totalArea += area;
    }

    public double getTotalArea() {
        return totalArea;
    }
}

// Another Concrete Visitor - calculates perimeter/outline description (new operation, no changes to shapes!)
class DescriptionVisitor implements ShapeVisitor {
    public void visit(Circle2 circle) {
        System.out.println("This is a circle with radius " + circle.radius);
    }
    public void visit(Rectangle rectangle) {
        System.out.println("This is a rectangle " + rectangle.width + "x" + rectangle.height);
    }
    public void visit(Triangle2 triangle) {
        System.out.println("This is a triangle with base " + triangle.base + " and height " + triangle.height);
    }
}

class VisitorDemo {
    public static void main(String[] args) {
        ShapeElement[] shapes = {
            new Circle2(5),
            new Rectangle(4, 6),
            new Triangle2(3, 8)
        };

        System.out.println("--- Calculating areas ---");
        AreaCalculator areaCalculator = new AreaCalculator();
        for (ShapeElement shape : shapes) {
            shape.accept(areaCalculator); // double dispatch: accept() calls the right visit() overload
        }
        System.out.printf("Total area: %.2f%n", areaCalculator.getTotalArea());

        System.out.println("\n--- Describing shapes (new operation, shapes unchanged) ---");
        DescriptionVisitor descriptionVisitor = new DescriptionVisitor();
        for (ShapeElement shape : shapes) {
            shape.accept(descriptionVisitor);
        }
    }
}
```

**Output:**
```
--- Calculating areas ---
Circle area: 78.54
Rectangle area: 24.00
Triangle area: 12.00
Total area: 114.54

--- Describing shapes (new operation, shapes unchanged) ---
This is a circle with radius 5.0
This is a rectangle 4.0x6.0
This is a triangle with base 3.0 and height 8.0
```

---

## Quick Comparison Table

| Pattern | Category | Core Purpose | Real-World Java Example |
|---|---|---|---|
| Singleton | Creational | One instance, global access | `Runtime.getRuntime()` |
| Factory Method | Creational | Subclass decides what to instantiate | `Collection.iterator()` |
| Abstract Factory | Creational | Create families of related objects | `DocumentBuilderFactory` |
| Builder | Creational | Step-by-step construction of complex objects | `StringBuilder`, `StreamBuilder` |
| Prototype | Creational | Clone existing objects | `Object.clone()` |
| Adapter | Structural | Make incompatible interfaces work together | `Arrays.asList()` |
| Bridge | Structural | Decouple abstraction from implementation | JDBC driver architecture |
| Composite | Structural | Tree structures, part-whole hierarchies | `java.awt.Container` |
| Decorator | Structural | Add behavior dynamically | `java.io.BufferedReader` |
| Facade | Structural | Simplify a complex subsystem | `javax.faces.context.FacesContext` |
| Flyweight | Structural | Share fine-grained objects | `Integer.valueOf()` caching |
| Proxy | Structural | Control access to an object | `java.lang.reflect.Proxy` |
| Chain of Responsibility | Behavioral | Pass request along a handler chain | Servlet Filters |
| Command | Behavioral | Encapsulate a request as an object | `Runnable`, `javax.swing.Action` |
| Interpreter | Behavioral | Evaluate sentences in a grammar | `java.util.regex.Pattern` |
| Iterator | Behavioral | Sequential access without exposing internals | `java.util.Iterator` |
| Mediator | Behavioral | Centralize complex communications | `java.util.concurrent.ExecutorService` |
| Memento | Behavioral | Capture/restore internal state | Undo functionality in editors |
| Observer | Behavioral | One-to-many notification | `java.beans.PropertyChangeListener` |
| State | Behavioral | Behavior changes with internal state | `javax.faces.lifecycle.LifecycleFactory` |
| Strategy | Behavioral | Interchangeable algorithms | `java.util.Comparator` |
| Template Method | Behavioral | Skeleton algorithm, subclasses fill steps | `java.io.InputStream.read()` |
| Visitor | Behavioral | New operations without changing classes | `javax.lang.model.element.ElementVisitor` |

---

## How to Choose a Pattern

- **Need exactly one shared instance?** → Singleton
- **Need to hide which concrete class gets created?** → Factory Method / Abstract Factory
- **Constructor has too many optional params?** → Builder
- **Object creation is expensive, want fast copies?** → Prototype
- **Two interfaces don't match, need to bridge them?** → Adapter
- **Want to avoid a class explosion when combining variations?** → Bridge
- **Need to treat individual objects and groups uniformly?** → Composite
- **Want to add behavior without subclassing?** → Decorator
- **Complex subsystem needs a simple front door?** → Facade
- **Many similar objects eating memory?** → Flyweight
- **Need to control/delay/guard access to an object?** → Proxy
- **Multiple handlers might process a request, order matters?** → Chain of Responsibility
- **Want to queue, log, or undo operations?** → Command
- **Need to evaluate expressions in a small custom language?** → Interpreter
- **Need to traverse a collection without exposing its structure?** → Iterator
- **Objects are too tightly coupled to each other directly?** → Mediator
- **Need undo/rollback of state?** → Memento
- **One change should notify many dependents?** → Observer
- **Behavior should change based on internal status?** → State
- **Need to swap algorithms at runtime?** → Strategy
- **Same algorithm skeleton, different steps per subclass?** → Template Method
- **Need new operations on a stable class hierarchy without modifying it?** → Visitor

---

### Compiling and Running the Examples

Each demo class above has a `main` method and can be run independently. For example, to try the Singleton demo:

```bash
javac Singleton.java
java SingletonDemo
```

If you paste multiple examples into the same file, make sure there is only **one `public` top-level class** per `.java` file (Java requires the public class name to match the filename) — rename the `public` class or split classes into separate files as needed.
