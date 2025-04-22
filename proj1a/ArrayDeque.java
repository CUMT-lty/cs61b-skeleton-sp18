public class ArrayDeque<T> {

    private int capacity;
    private Object[] carray;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.capacity = 8;
        this.carray = new Object[this.capacity];
        this.size = 0;
        this.nextFirst = this.capacity / 2;
        this.nextLast = this.nextFirst + 1;
    }

    public void addFirst(T item) {
        increaseCapacity();
        this.carray[this.nextFirst] = item;
        this.nextFirst = (this.nextFirst - 1 + this.capacity) % this.capacity;
        this.size++;
    }

    public void addLast(T item) {
        increaseCapacity();
        this.carray[this.nextLast] = item;
        this.nextLast = (this.nextLast + 1) % this.capacity;
        this.size++;
    }

    private void increaseCapacity() {
        if (this.size == this.capacity) { // or size == capacity
            Object[] larger = new Object[this.capacity * 2];
            int tmp = (this.nextFirst + 1) % this.capacity;
            for (int i = 0; i < this.size; i++) {
                larger[i] = this.carray[tmp];
                tmp = (tmp + 1) % this.capacity;
            }
            this.capacity *= 2;
            this.carray = larger;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = (this.nextFirst + 1) % this.capacity;
             i < this.nextLast; i = (i + 1) % this.capacity) {
            if (i != (this.nextFirst + 1) % this.capacity) {
                System.out.print(" ");
            }
            System.out.printf("%s", this.carray[i]);
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        this.nextFirst = (this.nextFirst + 1) % this.capacity;
        T rm = (T) this.carray[this.nextFirst];
        this.size--;
        shrinkCapacity();
        return rm;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        this.nextLast = (this.nextLast - 1 + this.capacity) % this.capacity;
        T rm = (T) this.carray[this.nextLast];
        this.size--;
        shrinkCapacity();
        return rm;
    }

    private void shrinkCapacity() {
        if (this.capacity >= 16 && this.size < this.capacity / 4) {
            Object[] smaller = new Object[this.capacity / 2];
            int tmp = (this.nextFirst + 1) % this.capacity;
            for (int i = 0; tmp != this.nextLast; i++) {
                smaller[i] = this.carray[tmp];
                tmp = (tmp + 1) % this.capacity;
            }
            this.capacity /= 2;
            this.carray = smaller;
            this.nextFirst = this.capacity - 1;
            this.nextLast = this.size;
        }
    }

    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }
        return (T) this.carray[(this.nextFirst + 1 + index) % this.capacity];
    }
}
