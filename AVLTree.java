public class AVLTree {
  static class AVLNode {
    int data;
    int height;
    AVLNode left;
    AVLNode right;
    AVLNode(){}
    AVLNode(int d){
      this.data = d;
      this.height = 1;
      this.left = null;
      this.right = null;
    }
  };
  static AVLNode root;
  private static int balance(AVLNode root) {
    return getHeight(root.left) - getHeight(root.right);
  }
  private static int getHeight(AVLNode root){
    if(root == null){
      return 0;
    }
    return root.height;
  }
  private static AVLNode rotateLeft(AVLNode z, AVLNode y){
    int bf = balance(y);
    if(y.height < 0 && bf < -1){
      y = LeftRight(y, y.right);
    }
    z =  LeftLeft(z, y);
    return z;
  }

  private static AVLNode rotateRight(AVLNode z, AVLNode y){
    int bf = balance(y);
    if(y.height > 0 && bf > 1){
      y = RightLeft(y, y.left);
    }
    z  = RightRight(z,y);
    return z;
  }
  private static AVLNode LeftLeft(AVLNode z, AVLNode y){
    z.left = y.right;
    y.right = z;
    z.height = Math.max(getHeight(z.left), getHeight(z.right))+1;
    y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
    return y;
  }

  private static AVLNode LeftRight(AVLNode y, AVLNode x){
    y.right = x.left;
    x.left = y;
    y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
    return x;
  }
  private static AVLNode RightLeft(AVLNode y, AVLNode x){
    y.left = x.right;
    x.right = y;
    y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
    return x;
  }

  private static AVLNode RightRight(AVLNode z, AVLNode y){
    z.right = y.left;
    y.left = z;
    z.height = Math.max(getHeight(z.left), getHeight(z.right))+1;
    y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
    return y;
  }
  public static AVLNode insert(AVLNode root, int d){
      if(root == null){
        root = new AVLNode(d);
        return root;
      }
      if(root.data > d){
        root.left =  insert(root.left, d);
      } else if(root.data < d){
        root.right = insert(root.right, d);
      }
      root.height = Math.max(getHeight(root.left), getHeight(root.right))+1;
      int bf = balance(root);
      if(bf > 1){
        return rotateLeft(root, root.left);
      } else if(bf < -1){
        return rotateRight(root, root.right);
      }
      return root;
  }
  private static int getMin(AVLNode root){
    if(root != null && root.left == null){
      return root.data;
    }
    AVLNode ptr = root.left;
    while(ptr != null && ptr.left != null){
      ptr = ptr.left;
    }
    return ptr.data;
  }
  public static AVLNode delete(AVLNode root, int d){
      if(root == null){
        return null;
      }
      if(root.data > d){
        root.left =  delete(root.left, d);
      } else if(root.data < d){
        root.right = delete(root.right, d);
      } else if(root.data == d) {
          if(root.left != null && root.right != null){
            int min = getMin(root.right);
            root.data = min;
            root.right = delete(root.right, root.data);
          } else if(root.left == null){
            return root.right;
          } else if(root.right == null){
            return root.left;
          }
      }
      root.height = Math.max(getHeight(root.left), getHeight(root.right))+1;
      int bf = balance(root);
      if(bf > 1){
        return rotateLeft(root, root.left);
      } else if(bf < -1){
        return rotateRight(root, root.right);
      }
      return root;
  }
  public static void preorder(AVLNode root){
    if(root == null){
      return;
    }
    System.out.print(root.data+" ");
    preorder(root.left);
    preorder(root.right);
  }
  public static void main(String args[]){
    int arr[] = {6,5,1,11,10,9,8,7,2,3,4};
    for(int i = 0; i < arr.length; i++){
      root = insert(root, arr[i]);
    }
    preorder(root);
    delete(root, 10);
    preorder(root);
  }
}
