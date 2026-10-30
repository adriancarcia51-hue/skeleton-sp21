package deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(T item,Node prev,Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
        public T getnode(int index){
            if (index == 0)
                return item;
            return this.next.getnode(index - 1);
        }
    }
    private Node head;//哨兵节点，next指向第一个节点，prev指向最后一个节点，第一节点pre指向head,最后结点next指向head
    private int size;//存储大小
    //创建一个空链表
    public LinkedListDeque(){
        size = 0;
        head = new Node(null, null, null);
        head.prev=head;
        head.next=head;
    }
    @Override
    /**
    将item加入首位
    @param item 要加入的对象
    */ 
    public void addFirst(T item){
        size += 1;
        Node A = new Node(item, head, head.next);
        head.next.prev = A;
        head.next = A;
    }
    @Override 
    /**
    尾部插入
    @param item 要加入的对象
    */
    public void addLast(T item){
        size += 1;
        Node A=new Node(item, head.prev, head);
        head.prev.next = A;
        head.prev = A;
    }
    // @Override 
    // /**@return 空链表返回true,非空返回false */
    // public boolean isEmpty(){
    //     return size == 0;
    // }
    @Override 
    /*@return 返回链表的项数 */
    public int size(){
        return size;
    }
    @Override 
    /*从前往后打印deque中的项，用空格分隔。所有项打印完毕后，输出一个换行符 */
    public void printDeque(){
        if (isEmpty()){
            System.out.println();
            return ;
        }
        Node A = head.next;
        while (A != head) {
            System.out.print(A.item.toString() + " ");
            A=A.next;
        }
        System.out.println();
    }
    @Override 
    /**移除并返回第一项，若不存在返回null 
    @return 若不为空，返回被移除的项，否则返回null*/
    public T removeFirst(){
        if (isEmpty())
            return null;
        T A = head.next.item;
        head.next = head.next.next;
        size--;
        head.next.prev = head;
        return A;
    }
    @Override 
    /**移除并返回deque后端的项。如果不存在该项，返回null 
    @return 若不为空，返回被移除的项，否则返回null*/
    public T removeLast(){
        if (isEmpty())
            return null;
        T A = head.prev.item;
        size--;
        head.prev = head.prev.prev;
        head.prev.next = head;
        return A;
    }
    @Override 
    /** 获取给定索引处的项，其中0是前端，1是下一项，以此类推。如果不存在该项，返回null。不改变deque
    @return index处的项*/
    public T get(int index){
        if (index < 0 || index >= size)
            return null;
        Node A;
        if (index < size / 2){
            A = head.next;
            for (int i = 0; i < index; i++){
                A = A.next;
            }
        } else {
            A = head.prev;
            for (int i = 0; i < size - 1 - index; i++){
                A = A.prev;
            }
        }
        return A.item;
    }
    @Override 
    //我们要创建的Deque对象是可迭代的（即Iterable<T>），所以我们必须提供此方法来返回一个迭代器
    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node cursor = head.next;
        @Override
        public boolean hasNext() {
            return cursor != head;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T item = cursor.item;
            cursor = cursor.next;
            return item;
        }
    }
    @Override
    /**    (non-Javadoc)
     * 返回参数o是否等于此Deque。如果o是一个Deque，并且它包含相同的内容（由泛型T的equals方法决定）且顺序相同，则o被认为是相等的
     * @see java.lang.Object#equals(java.lang.Object)
     * @param o 比较对象
     * @return 相等返回true 否则返回false
     */ 
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
    /**
     * 作用同get使用递归
     * @param index 目标位置
     * @return
     */
    public T getRecursive(int index){
        Node curNode = head.next;
        if (index < 0 || index >= size)
            return null;
        return  curNode.getnode(index);
    }
}
