public class ArrayDeque<T> {
    T[] items;
    int next_first;
    int next_last;
    int size;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        next_first = items.length - 1;
        next_last = 0;
    }
    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, next_last);
        int new_next_first = a.length - (items.length - next_first);
        System.arraycopy(items, next_first + 1, a,  new_next_first+ 1, items.length - next_first - 1);
        items = a;
        next_first = new_next_first;
    }
    private int CircularIncrease(int x){
        x += 1;
        return x % items.length;
    }
    private int CircularDecrease(int x){
        x -= 1;
        return (x + items.length) % items.length;
    }
    public void addFirst(T x){
        if(size == items.length){
            resize(size * 2);
        }
        items[next_first] = x;
        next_first = CircularDecrease(next_first);
        size += 1;
    }
    public void addLast(T x){
        if(size == items.length){
            resize(size * 2);
        }
        items[next_last] = x;
        next_last = CircularIncrease(next_last);
        size += 1;
    }
    public T get(int index){
        if(index < size / 2){
            return items[(next_first + index + 1) % items.length];
        }
        else{
            return items[(next_last -(size - index) + items.length) % items.length];
        }
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        int last = CircularDecrease(next_last);
        T x = items[last];
        items[last] = null;
        next_last = last;
        size -= 1;
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        int first = CircularIncrease(next_first);
        T x = items[first];
        items[first] = null;
        next_first = first;
        size -= 1;
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
        return x;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void printDeque(){
        int p = next_first;
        for(int i = 0; i<size; i++){
            p = CircularIncrease(p);
            System.out.print(items[p] + " ");
        }
    }
}
