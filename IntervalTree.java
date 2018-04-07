class Interval {
  int low;
  int high;
  Interval(){}
  Interval(int l,int h){
    this.low = l;
    this.high = h;
  }
}
class IntervalTreeNode {
  Interval i;
  IntervalTreeNode left;
  IntervalTreeNode right;
  int max;
  IntervalTreeNode(){}
  IntervalTreeNode(Interval x, int m){
    this.i = x;
    this.max = m;
  }
}
public class IntervalTree {
  static IntervalTreeNode node;
  private static IntervalTreeNode insert(IntervalTreeNode root, Interval i){
    IntervalTreeNode t = new IntervalTreeNode(i,i.high);
    if(root == null){
       root = t;
       return root;
    }
    if(root.i.low >= i.low){
      root.left = insert(root.left, i);
    } else if(root.i.low <= i.low){
      root.right = insert(root.right, i);
    }
    if(root.max < i.high){
      root.max = i.high;
    }
    return root;
  }

  static void inorder(IntervalTreeNode root){
    if(root == null){
      return;
    }
    inorder(root.left);
    System.out.print(root.i.low+" "+root.i.high+" "+root.max+" ");
    inorder(root.right);
  }

  static boolean isOverlap(IntervalTreeNode root, Interval x){
      if(root == null){
        return false;
      }
      if(root.i.low <= x.high && x.low <= root.i.high){
        return true;
      }
      if(root.i.low >= x.low){
        return isOverlap(root.left, x);
      } else if(root.i.low <= x.low){
        return isOverlap(root.right, x);
      }
      return false;
  }

  public static void main(String args[]){
    node = null;
    int arr1[] = {15,10,17,5,12,30};
    int arr2[] = {20,30,19,20,15,40};
    for(int i = 0; i < arr1.length; i++){
      Interval t = new Interval(arr1[i], arr2[i]);
      node = insert(node, t);
    }
    inorder(node);
    System.out.print(isOverlap(node, new Interval(6,1))+" ");
  }

}
