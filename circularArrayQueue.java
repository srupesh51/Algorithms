public class circularArrayQueue {

  static class Queue {
    int arr[];
    int front;
    int rear;
    int n;
    Queue(){}
    Queue(int s){
      this.n = s;
      this.arr = new int[s];
      this.front = -1;
      this.rear = -1;
    }
  };
  static Queue q;
  static int size(Queue q){
    return (q.front == -1 && q.rear == -1) ? 0 : q.rear - q.front + 1;
  }
  static boolean isFull(Queue q){
    return size(q) == q.n ? true : false;
  }

  static boolean isEmpty(Queue q){
    return q.front == -1;
  }

  static void enQueue(Queue q, int d){
    if(isFull(q)){
      return;
    }
    q.rear = (q.rear+1)%q.n;
    q.arr[q.rear] = d;
    if(q.front == -1){
      q.front = q.rear;
    }
  }
  static int deQueue(Queue q){
    if(isEmpty(q)){
      return Integer.MAX_VALUE;
    }
    int frontElement = q.arr[q.front];
    if(q.front == q.rear){
      q.front = -1;
    } else {
      q.front = (q.front+1)%q.n;
    }
    return frontElement;
  }
  public static void main(String args[]){
    q = new Queue(4);
    enQueue(q,10);
    enQueue(q,20);
    enQueue(q,30);
    enQueue(q,40);
    System.out.print(deQueue(q)+" ");
    System.out.print(deQueue(q)+" ");
    System.out.print(deQueue(q)+" ");
    System.out.print(deQueue(q)+" ");
    System.out.print(deQueue(q)+" ");
  }
}
