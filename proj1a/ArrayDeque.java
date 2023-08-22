public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }
    private void resize(int capacity) {
        int p = nextFirst;
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            p = circularIncrease(p);
            newItems[i] = items[p];
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private int circularIncrease(int x, int step, int circLength) {
        x += step;
        x %= circLength;
        if (x < 0) {
            x += circLength;
        }
        return x;
    }

    private int circularIncrease(int x) {
        return circularIncrease(x, 1);
    }

    private int circularIncrease(int x, int step) {
        return circularIncrease(x, step, items.length);
    }

    private int circularDecrease(int x) {
        return circularIncrease(x, -1);
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = circularDecrease(nextFirst);
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = circularIncrease(nextLast);
        size += 1;
    }

    public T get(int index) {
        if (index < size / 2) {
            return items[(nextFirst + index + 1) % items.length];
        } else {
            return items[(nextLast - (size - index) + items.length) % items.length];
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = circularDecrease(nextLast);
        T x = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = circularIncrease(nextFirst);
        T x = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = nextFirst;
        for (int i = 0; i < size; i++) {
            p = circularIncrease(p);
            System.out.print(items[p] + " ");
        }
    }
}
