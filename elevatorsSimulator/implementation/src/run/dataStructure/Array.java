package run.dataStructure;

/**
 * A generic, fixed-size array implementation that provides basic array operations.
 * This class handles element storage, retrieval, and manipulation while maintaining
 * type safety through generics.
 *
 * @param <T> the type of elements stored in this array
 */
public class Array<T> {
    private T[] elements;
    private int size;
    private final int capacity;

    /**
     * Constructs a new Array with the specified maximum capacity.
     *
     * @param maxSize the maximum number of elements the array can hold (must be positive)
     * @throws IllegalArgumentException if maxSize is zero or negative
     */
    @SuppressWarnings("unchecked")
    public Array(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.elements = (T[]) new Object[maxSize];
        this.size = 0;
        this.capacity = maxSize;
    }

    /**
     * Appends an element to the end of the array.
     *
     * @param element the element to append (cannot be null)
     * @throws IllegalArgumentException if element is null
     * @throws IllegalStateException if the array is already full
     */
    public void append(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot append null element");
        }
        if (size >= capacity) {
            throw new IllegalStateException("Array capacity exceeded");
        }
        elements[size++] = element;
    }

    /**
     * Sets the element at the specified position in the array.
     *
     * @param index the position to set (0-based)
     * @param element the element to store (cannot be null)
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException if element is null
     */
    public void set(int index, T element) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException("Index " + index + 
                " out of bounds for capacity " + capacity);
        }
        if (element == null) {
            throw new IllegalArgumentException("Cannot set null element");
        }
        
        elements[index] = element;
        if (index >= size) {
            size = index + 1;
        }
    }

    /**
     * Returns the element at the specified position in the array.
     *
     * @param index the position of the element to return (0-based)
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements[index];
    }

    /**
     * Removes the element at the specified position and shifts subsequent elements left.
     *
     * @param index the position of the element to remove (0-based)
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        // Shift elements left
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        
        // Clear last element and decrement size
        elements[--size] = null;
    }

    /**
     * Returns the current number of elements in the array.
     *
     * @return the number of elements currently stored
     */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum capacity of the array.
     *
     * @return the maximum number of elements the array can hold
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Checks if the array is empty.
     *
     * @return true if the array contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the array is full.
     *
     * @return true if the array has reached its maximum capacity, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Clears all elements from the array, resetting its size to zero.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Checks if the array contains the specified element.
     *
     * @param element the element to search for (null elements will always return false)
     * @return true if the element is found, false otherwise
     */
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a new array containing all elements in this array.
     *
     * @return a new array containing all elements
     */
    public T[] getAllElements() {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = elements[i];
        }
        return copy;
    }

    /**
     * Prints all elements in the array to standard output, one per line.
     */
    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }
}