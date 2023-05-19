# Study Guide
- by Connor Garmey
- Welcome to the study guide

# Java Arrays
- An **array** is a collection of variables of the same type

## Declaring an Array
- General Form: type[] arrayName;
- only creates a reference
- no array has  actually been created yet

## Creating an Array (Instantiation)
- General form:  arrayName = new type[numberOfElements];
- numberOfElements must be a positive Integer.
- Gotcha: Array size is not modifiable once instantiated.

# IO
- System.in - stdin
- connected to the system’s standard input stream (usually the keyboard)
- System.out - stdout
- connected to system’s standard output stream (usually some type of terminal)

### Why have both out and err?
- don't want to torment your user with error messages

## File I/O
- Files - usually stored on non-volatile memory
- Able to store data that lives beyond the current execution

# Testing

# Vectors
- Vectors act like resizable arrays

## Declaring a vector
- General Form: Vector<type> v = new Vector();
- type needs to be a valid reference type

## Adding an element to a vector
- v.add(object of type);
