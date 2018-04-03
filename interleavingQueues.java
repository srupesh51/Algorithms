import java.util.*;
public class interleavingQueues {

  private static Queue<Integer> interleaveQueues(Queue<Integer> q){
    if(q.isEmpty()){
      return null;
    }
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = new LinkedList<Integer>();
    int n = q.size();
    int hf = n/2;
    int i = 1;
    while(i <= hf){
      q1.add(q.remove());
      i++;
    }
    while(i <= n){
      q2.add(q.remove());
      i++;
    }
    while(!q1.isEmpty() && !q2.isEmpty()){
        q.add(q1.remove());
        q.add(q2.remove());
    }
    return q;
  }

  public static void main(String args[]){
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(11);
    q.add(12);
    q.add(13);
    q.add(14);
    q.add(15);
    q.add(16);
    q.add(17);
    q.add(18);
    q.add(19);
    q.add(20);
    q = interleaveQueues(q);
    System.out.print(q+" ");
  }

}
