package deque;
import java.util.Iterator;
public interface Deque<T> extends Iterable<T>{
    public void addFirst(T item);//在deque的前端添加一个类型为T的项。你可以假设item永远不为null。
    public void addLast(T item);//在deque的后端添加一个类型为T的项。你可以假设item永远不为null。
    public default boolean isEmpty(){
        return size() == 0;
    }//如果deque为空则返回true，否则返回false。
    public int size();//返回deque中的项数。
    public void printDeque();//：从前往后打印deque中的项，用空格分隔。所有项打印完毕后，输出一个换行符。
    public T removeFirst();//：移除并返回deque前端的项。如果不存在该项，返回null。
    public T removeLast();//：移除并返回deque后端的项。如果不存在该项，返回null。
    public T get(int index);//：获取给定索引处的项，其中0是前端，1是下一项，以此类推。如果不存在该项，返回null。不得改变deque！
    public Iterator<T> iterator();//我们要创建的Deque对象是可迭代的（即Iterable<T>），所以我们必须提供此方法来返回一个迭代器。
    public boolean equals(Object o);//返回参数o是否等于此Deque。如果o是一个Deque，并且它包含相同的内容（由泛型T的equals方法决定）且顺序相同，则o被认为是相等的。
}
