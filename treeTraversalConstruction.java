import java.util.*;
public class treeTraversalConstruction {
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
  static Stack<BinaryTree> st = new Stack<BinaryTree>();
  static HashSet<BinaryTree> set = new HashSet<BinaryTree>();
  private static BinaryTree BuildTree(int pre[], int in[]){
    int i = 0;
    int index = 0;
    while(i < pre.length){
      BinaryTree node = null;
      do{
        node = new BinaryTree(pre[i]);
        if(root == null){
          root = node;
        }
        if(!st.isEmpty()){
          if(set.contains(st.peek())){
            set.remove(st.peek());
            st.pop().right = node;
          } else {
            st.peek().left = node;
          }
        }
        st.push(node);
      }while(pre[i++] != in[index] && i < pre.length);
      node = null;
      while(!st.isEmpty() && index < in.length && st.peek().data == in[index]){
        node = st.pop();
        index++;
      }
      if(node != null){
        set.add(node);
        st.push(node);
      }
    }
    return root;
  }

  static void inorder(BinaryTree root){
    if(root == null){
      return;
    }
    inorder(root.left);
    System.out.print(root.data+" ");
    inorder(root.right);
  }

  public static void main(String args[]){
    int in[] = {9, 8, 4, 2, 10, 5, 10, 1, 6, 3, 13, 12, 7};
    int pre[] = {1, 2, 4, 8, 9, 5, 10, 10, 3, 6, 7, 12, 13};
    root = BuildTree(pre, in);
    inorder(root);
  }

}
