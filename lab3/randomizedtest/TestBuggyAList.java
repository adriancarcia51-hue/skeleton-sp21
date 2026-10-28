package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test 
  public void testThreeAddThreeRemove(){
    BuggyAList<Integer> Bug=new BuggyAList<>();
    AListNoResizing<Integer> AL=new AListNoResizing<>();
    for(int i=1;i<=10;i++){
      Bug.addLast(i);
      AL.addLast(i);
    }
    for(int i=1;i<=3;i++){
      int b=Bug.removeLast();
      int a=AL.removeLast();
      assertEquals(b, a);
    }
  }
  @Test 
  public void randomizedTest(){
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> B=new BuggyAList<>();
    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        B.addLast(randVal);
        System.out.println("addLast(" + randVal + ")");
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        int size_b=B.size();
        assertEquals(size, size_b);
      } else if(operationNumber==2){
        if(L.size()==0)
          continue;
        int last_a=L.getLast();
        int last_b=B.getLast();
        assertEquals(last_a, last_b);
      } else if(operationNumber==3){
        if(L.size()==0)
          continue;
        int last=L.removeLast();
        int last_b=B.removeLast();
        assertEquals(last, last_b);
        System.out.println("last: "+ last+"size: "+L.size());
      }
    }
  }
}
