public class LCA {

  static class BinaryTree {
    int data;
    BinaryTree left;
    BinaryTree right;
    BinaryTree(){
    }
    BinaryTree(int d){
      this.data = d;
      this.left = null;
      this.right = null;
    }

  };
  private static BinaryTree root;
  private static BinaryTree find(BinaryTree root, BinaryTree a){
    if(root == null){
      return null;
    }
    if(root == a){
      return root;
    }
    BinaryTree l1 = find(root.left, a);
    BinaryTree l2 = find(root.right, a);
    return l1 != null ? l1 : l2;
  }
  private static BinaryTree findLCAUtil(BinaryTree root, BinaryTree a,BinaryTree b){
    BinaryTree a1 = find(root, a);
    BinaryTree a2 = find(root, b);
    if(a1 != null && a2 != null){
      return findLCA(root, a1, a2);
    }
    return null;
  }
  private static BinaryTree findLCA(BinaryTree root, BinaryTree a,BinaryTree b){
    if(root == null){
      return null;
    }
    if(root == a || root == b){
      return root;
    }
    BinaryTree leftLCA = findLCA(root.left,a,b);
    BinaryTree rightLCA = findLCA(root.right,a,b);
    if(leftLCA != null && rightLCA != null){
      return root;
    }
    return leftLCA != null? leftLCA : rightLCA;
  }
  public static void main(String args[]){
    root = new BinaryTree(10);
    root.left = new BinaryTree(3);
    root.left.left = new BinaryTree(2);
    root.left.left.left = new BinaryTree(1);
    root.left.left.right = new BinaryTree(4);
    root.right = new BinaryTree(9);
    root.right.right = new BinaryTree(7);
    root.right.right.right = new BinaryTree(6);
    root.right.right.left = new BinaryTree(5);
    BinaryTree t1 = new BinaryTree(78);
    BinaryTree t = findLCAUtil(root, root.left, t1);
    if(t != null){
      System.out.print(t.data+" ");
    }
  }
}
