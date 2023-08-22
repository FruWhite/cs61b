public class LinkedListDeque<T> {
    private TNode sentinel;
    private TNode last;
    private int size;

    private class TNode{
        public T item;
        public TNode next;
        public TNode prev;
        public TNode(T i, TNode n, TNode p){
            item = i;
            next = n;
            prev = p;
        }

    }
    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public LinkedListDeque(T x){
        sentinel = new TNode(null, new TNode(x, null, null), null);
        sentinel.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        sentinel.next.next = sentinel;
        size = 1;
    }
    public void addFirst(T x){
        sentinel.next = new TNode(x, sentinel.next, sentinel);
        size += 1;
    }
    public int size(){
        return size;
    }
    public void addLast(T x){
        sentinel.prev = new TNode(x, sentinel, sentinel.prev);
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void printDeque(){
        TNode p = sentinel;
        while (p.next != sentinel){
            p = p.next;
            System.out.print(p.item + " ");
        }
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        TNode first = sentinel.next;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first.item;
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        TNode last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last.item;
    }
    public T get(int index){
        if (index >= size && index < 0){
            return null;
        }
        else if(index < size / 2){
            TNode p = sentinel;
            for(int i = 0;i <= index; i++){
                p = p.next;
            }
            return p.item;
        }
        else{
            TNode p = sentinel;
            for(int i = 0;i < size - index; i++){
                p = p.prev;
            }
            return p.item;
        }
    }
    /**Helper method for getRecursive(int index) */
    private TNode getNextNItem(TNode start, int n){
        if(n == 0){
            return start;
        }
        else if (n > 0) {
            return getNextNItem(start.next, n - 1);
        }
        else return getNextNItem(start.prev, n + 1);
    }
    public T getRecursive(int index){
        if (index >= size && index < 0){
            return null;
        }
        else if(index < size / 2){
            return getNextNItem(sentinel.next, index).item;
        }
        else return getNextNItem(sentinel, index - size).item;
    }
}
