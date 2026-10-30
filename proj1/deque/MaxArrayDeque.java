package deque;

import java.util.Comparator;

public class MaxArrayDeque<T>  extends ArrayDeque<T>{
    private Comparator<T> ArrayComparator;
    public MaxArrayDeque(Comparator<T> c){
        ArrayComparator = c;
    }
    public T max(){
        if (isEmpty())
            return null;
        T maxT = get(0);
        for(T i : this){
            if(ArrayComparator.compare(i, maxT) > 0)
                maxT = i;
        }
        return maxT;
    }
    public T max(Comparator<T> c){
        if (isEmpty())
            return null;
        T maxT = get(0);
        for(T i : this){
            if(c.compare(i, maxT) > 0)
                maxT = i;
        }
        return maxT;
    }
}
