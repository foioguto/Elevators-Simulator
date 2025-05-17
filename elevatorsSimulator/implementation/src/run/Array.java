package run;

public class Array<T> {
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public Array(int maxSize) {
        // Use of Object and we do a cast
        elements = (T[]) new Object[maxSize];
        size = 0;
    }

    public void append(T element) {
        if (size >= elements.length) {
            throw new RuntimeException("Max size excedeed");
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return elements[index];
    }

    public int tamanho() {
        return size;
    }

    public void imprimirTodos() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }
}

