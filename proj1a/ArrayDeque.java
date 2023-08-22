public class ArrayDeque<T> {
    private T[] items;
    private int next_first;
    private int next_last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        next_first = items.length - 1;
        next_last = 0;
    }
    /** resize the array, with new NEXT_FIRST at index LENGTH - 1*/
    private void resize(int capacity) {
       int p = next_first;
       T[] new_items = (T[]) new Object[capacity];
       for (int i = 0; i<size; i++) {
           p = circularIncrease(p);
           new_items[i] = items[p];
       }
       items = new_items;
       next_first = items.length - 1;
       next_last = size;
    }

    private int circularIncrease(int x, int step, int circ_length) {
        x += step;
        x %= circ_length;
        if (x < 0){
            x += circ_length;
        }
        return x;
    }
    private int circularIncrease(int x) {
        return circularIncrease(x, 1);
    }
    private int circularIncrease(int x, int step){
        return circularIncrease(x, step, items.length);
    }
    private int circularDecrease(int x) {
        return circularIncrease(x, -1);
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[next_first] = x;
        next_first = circularDecrease(next_first);
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[next_last] = x;
        next_last = circularIncrease(next_last);
        size += 1;
    }

    public T get(int index) {
        if (index < size / 2) {
            return items[(next_first + index + 1) % items.length];
        } else {
            return items[(next_last - (size - index) + items.length) % items.length];
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = circularDecrease(next_last);
        T x = items[last];
        items[last] = null;
        next_last = last;
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
        int first = circularIncrease(next_first);
        T x = items[first];
        items[first] = null;
        next_first = first;
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
        int p = next_first;
        for (int i = 0; i < size; i++) {
            p = circularIncrease(p);
            System.out.print(items[p] + " ");
        }
    }
}
