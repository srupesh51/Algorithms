public class dequeDLL {

  static class deque {
    int data;
    deque prev;
    deque next;
    deque front;
    deque rear;
    deque(){}
    deque(int d){
      this.data = d;
      this.next = null;
      this.prev = null;
    }
  };
  static deque deq;

  private static boolean isEmpty(deque q){
    return q.front == null;
  }

  private static void addFront(deque q, int d){
    if(q.front == null){
      q.front = new deque(d);
      if(q.rear == null){
        q.rear = q.front;
      }
    } else {
      deque t = q.front;
      deque t1 = new deque(d);
      t1.next = t;
      t.prev = t1;
      q.front = t1;
    }
  }

  private static void addRear(deque q, int d){
    if(q.rear == null){
      q.rear = new deque(d);
      if(q.front == null){
        q.front = q.rear;
      }
    } else {
      deque t = q.rear;
      deque t1 = new deque(d);
      t.next = t1;
      t1.prev = t;
      q.rear = t1;
    }
  }

  private static void deleteFront(deque q){
    if(isEmpty(q)){
      return;
    }
    q.front = q.front.next;
    if(q.rear == q.front){
      q.rear.prev = null;
    } else if(q.front == null){
      q.front = null;
    }
  }

  private static void deleteRear(deque q){
    if(isEmpty(q)){
      return;
    }
    q.rear = q.rear.prev;
    if(q.rear == q.front){
      q.front.next = null;
    } else if(q.rear == null){
      q.front = null;
    }
  }

  private static int getFront(deque q){
    if(isEmpty(q)){
      return Integer.MAX_VALUE;
    }
    return q.front.data;
  }

  private static int getRear(deque q){
    if(isEmpty(q)){
      return Integer.MAX_VALUE;
    }
    return q.rear.data;
  }

  public static void main(String args[]){
    deq = new deque();
    addFront(deq, 10);
    addFront(deq, 20);
    addFront(deq, 30);
    addFront(deq, 40);
    addRear(deq, 50);
    addRear(deq, 60);
    addRear(deq, 70);
    addRear(deq, 80);
    System.out.print(getRear(deq)+" ");
    deleteFront(deq);
    deleteFront(deq);
    deleteFront(deq);
    deleteFront(deq);
    deleteFront(deq);
    deleteFront(deq);
    deleteFront(deq);
    deleteRear(deq);
    System.out.print(getRear(deq)+" ");
    System.out.print(getFront(deq)+" ");
  }

}
