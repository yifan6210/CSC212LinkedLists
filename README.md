# CSC212Lists
List Data Structures via Testing 

### ListADT Methods to implement

Note: ``ListADT`` and friends are not actually in this repository.
- You can browse the [online Javadoc](https://javadoc.jitpack.io/com/github/jjfiv/CSC212ADT/1.3/javadoc/index.html).
- You can directly [import the code](https://github.com/jjfiv/CSC212ADT) if you want.

The most important portions of the interface are:

```java
public abstract class ListADT<T> ... {
  ...
  
  public T removeFront();
  public T removeBack();
  public T removeIndex(int index);
  
  public void addFront(T item);
  public void addBack(T item);
  public void addIndex(int index, T item);

  public T getFront();
  public T getBack();
  public T getIndex(int index);
  
  public void setIndex(int index, T value);

  public int size();
  public boolean isEmpty();
}
```

### Errors:

Where appropriate, you should throw my error classes instead of ``NullPointerException``s or ``ArrayIndexOutOfBoundsException``s. It is good programming practice to not expose those to your users, and it also demonstrates that you're reasoning about your code and its failure cases.

I provide errors for:
- ``TODOErr``, so I can quickly tell if you have not changed the starter code in a particular case. You will be deleting these errors, not adding them.
- ``EmptyListError``, when a remove or get operation is called on an empty list. See ``checkNotEmpty()``
- ``BadIndexError``, when a getIndex, addIndex or removeIndex operation is called with an index that does not exist. See ``checkExclusiveIndex`` and ``checkInclusiveIndex``.

Prefer the most precise error: ``EmptyListError`` rather than a ``BadIndexError`` when looking for the 5th thing in an empty list.

## No reflection or form this week.

This assignment is purely graded on test accuracy.

## Rubric (100)

Right now, when I run all the tests in this project, I get the following output:

```
Tests run: 60, Failures: 0, Errors: 46, Skipped: 0
```

So there are 24 passing tests, and 46 failing tests.

Your score on this assignment will range up to 100% -- the fraction of the tests that pass on your code (minus the tests that already pass), provided:

 1. there are no code compilation problems with your code (red lines or Problems in Eclipse).  
 2. you do not rename any methods or modify the tests in any way, and 
 3. you resolve any infinite loops in your code (better to comment out that method and have it crash than run forever -- it prevents other tests from running.

## What should I do first?

There are TODOErr methods in:

Start with ``SinglyLinkedList``: This will be very hard if you do not draw pictures.
- (9) ``src/main/java/edu/smith/cs/csc212/adtr/real/SinglyLinkedList.java``
- Start with everything that has the word ``Front`` in it. 
- ``getIndex`` and ``setIndex`` are a lot like ``size``.
- ``Front`` is easier than ``Back`` which is easier than ``Index``.
- ``get`` is easier than ``add`` which is easier than ``remove``, usually.

Then, work on ``DoublyLinkedList``: Some methods will be just like SinglyLinkedList (e.g., front methods) and some will be very different.
- (12) ``src/main/java/edu/smith/cs/csc212/adtr/real/DoublyLinkedList.java``
- ``*Front`` and ``*Back`` have symmetry. 
- Again, ``getIndex`` and ``setIndex`` are like size.
- Do all the front/back methods before doing any index methods here.

