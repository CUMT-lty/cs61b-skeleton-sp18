/**
 * doubled linked list implemented with linked list
 */
public class LinkedListDeque<T> {

    private static class ListNode<T> {
        public T value;
        public ListNode<T> prev;
        public ListNode<T> next;

        public ListNode() {
            this.value = null;
            this.prev = null;
            this.next = null;
        }

        public ListNode(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public ListNode<T> sentinel;
    public int size;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque () {
        this.size = 0;
        this.sentinel = new ListNode<>(); // sentinel will never be null
        this.sentinel.value = null;
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }


    public void addFirst(T item) {
        ListNode<T> node = new ListNode<>(item);
        node.next = this.sentinel.next;
        node.next.prev = node;
        node.prev = this.sentinel;
        this.sentinel.next = node;
        this.size++;
    }

    public void addLast(T item) {
        ListNode<T> node = new ListNode<>(item);
        node.prev = this.sentinel.prev;
        node.prev.next = node;
        node.next = this.sentinel;
        this.sentinel.prev = node;
        this.size++;
    }

    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        ListNode<T> iterator = this.sentinel.next;
        while (iterator != this.sentinel) {
            if (iterator.prev != this.sentinel) {
                System.out.print(" ");
            }
            System.out.printf("%s", iterator.value);
            iterator = iterator.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        ListNode<T> rm = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        rm.next = null;
        rm.prev = null;
        this.size--;
        return rm.value;
    }

    public T removeLast() {
        ListNode<T> rm = this.sentinel.prev;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        rm.prev = null;
        rm.next = null;
        this.size--;
        return rm.value;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        ListNode<T> itr = this.sentinel.next;
        for (int i = 0; i < index; i++) {
            itr = itr.next;
        }
        return itr.value;
    }

    /**
     *  Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return _getRecursive(this.sentinel.next, index);
    }

    public T _getRecursive(ListNode<T> ptr, int index) {
        if (ptr == this.sentinel) {
            return null;
        }
        if (index == 0) {
            return ptr.value;
        }
        return _getRecursive(ptr.next, index - 1);
    }
}
