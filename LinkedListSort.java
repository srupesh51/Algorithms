class LinkedListSort {
  static class LinkedList {
      int data;
      LinkedList next;
      LinkedList(){}
      LinkedList(int d){
        this.data = d;
        this.next = null;
      }
  };
  static LinkedList head;

  private static LinkedList sort(LinkedList head){
    if(head == null || head.next == null){
      return head;
    }
    LinkedList middle = getMiddle(head);
    LinkedList nextOfMiddle = middle.next;
    middle.next = null;
    LinkedList left = sort(head);
    LinkedList right = sort(nextOfMiddle);
    return merge(left, right);
  }

  private static LinkedList merge(LinkedList left, LinkedList right){
    if(left == null){
      return right;
    }
    if(right == null){
      return left;
    }
    LinkedList result = null;
    if(left.data <= right.data){
      result = left;
      result.next = merge(left.next, right);
    } else {
      result = right;
      result.next = merge(left, right.next);
    }
    return result;
  }

  private static LinkedList getMiddle(LinkedList head){
    LinkedList slow = head;
    LinkedList fast = head;
    boolean flag = false;
    while(fast.next != null){
      if(!flag){
        fast = fast.next;
        flag = true;
      } else {
        slow = slow.next;
        fast = fast.next;
      }
    }
    return slow;
  }

  public static void main(String args[]){
    head = new LinkedList(2);
    head.next = new LinkedList(1);
    head.next.next = new LinkedList(4);
    head.next.next.next = new LinkedList(3);
    head = sort(head);
    LinkedList ptr = head;
    while(ptr != null){
      System.out.print(ptr.data+" ");
      ptr = ptr.next;
    }
  }

}
