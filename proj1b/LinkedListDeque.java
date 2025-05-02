public class LinkedListDeque<T> implements Deque<T> {

    private static class ListNode<T> {
        private T value;
        private ListNode<T> prev;
        private ListNode<T> next;

        private ListNode() {
            this.value = null;
            this.prev = null;
            this.next = null;
        }

        private ListNode(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private ListNode<T> sentinel;
    private int size;

    public LinkedListDeque () {
        this.size = 0;
        this.sentinel = new ListNode<>(); // sentinel will never be null
        this.sentinel.value = null;
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    @Override
    public void addFirst(T item) {
        ListNode<T> node = new ListNode<>(item);
        node.next = this.sentinel.next;
        node.next.prev = node;
        node.prev = this.sentinel;
        this.sentinel.next = node;
        this.size++;
    }

    @Override
    public void addLast(T item) {
        ListNode<T> node = new ListNode<>(item);
        node.prev = this.sentinel.prev;
        node.prev.next = node;
        node.next = this.sentinel;
        this.sentinel.prev = node;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
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

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        ListNode<T> rm = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        rm.next = null;
        rm.prev = null;
        this.size--;
        return rm.value;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        ListNode<T> rm = this.sentinel.prev;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        rm.prev = null;
        rm.next = null;
        this.size--;
        return rm.value;
    }

    @Override
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

    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return _getRecursive(this.sentinel.next, index);
    }

    private T _getRecursive(ListNode<T> ptr, int index) {
        if (ptr == this.sentinel) {
            return null;
        }
        if (index == 0) {
            return ptr.value;
        }
        return _getRecursive(ptr.next, index - 1);
    }
}
