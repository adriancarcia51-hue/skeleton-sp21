package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {
    private T[] data;
    private int size;
    private int head; // 第一个元素在 data 中的物理下标
    private int tail; // 下一个可写位置在 data 中的物理下标

    private static final int INIT_CAPACITY = 8;

    /**
     * 创建空的双端队列
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        data = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    /**
     * 按逻辑顺序把元素复制到新容量数组中，并重置 head/tail
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        head = (head - 1 + data.length) % data.length;
        data[head] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }

    // @Override
    // public boolean isEmpty() {
    //     return size == 0;
    // }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[(head + i) % data.length].toString() + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removed = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        if (data.length > INIT_CAPACITY && size < data.length / 4) {
            resize(data.length / 2);
        }
        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail = (tail - 1 + data.length) % data.length;
        T removed = data[tail];
        data[tail] = null;
        size--;
        if (data.length > INIT_CAPACITY && size < data.length / 4) {
            resize(data.length / 2);
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return data[(head + index) % data.length];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = data[(cursor + head) % data.length];
            cursor++;
            return item;
        }
    }
    public boolean equals(Object o){
        if (o == this)
            return true;
        if (o instanceof Deque<?>){
            Deque<?> other = (Deque<?>) o;
            if (other.size() != this.size())
                return false;
            Iterator<?> this_Iterator = this.iterator();
            Iterator<?> other_Iterator = other.iterator();
            while (this_Iterator.hasNext()) {
                if (!this_Iterator.next().equals(other_Iterator.next()))
                    return false;
            }
            return true;
        }
        return false;
    } 
}