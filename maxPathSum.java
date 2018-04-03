public class maxPathSum {

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
  private static int res = Integer.MIN_VALUE;
  private static int maxPathSum(BinaryTree root){
    if(root == null){
      return 0;
    }
    int leftSum = maxPathSum(root.left);
    int rightSum = maxPathSum(root.right);
    int ms = Math.max(Math.max(leftSum, rightSum)+ root.data, root.data);
    int max = Math.max(ms, leftSum+rightSum+root.data);
    res = Math.max(res, max);
    return ms;
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
    maxPathSum(root);
    System.out.print(res+" ");
  }
}
