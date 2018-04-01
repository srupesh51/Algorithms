import java.util.*;
public class addOne {

  private static ArrayList<Integer> MSB(ArrayList<Integer> A){
    ArrayList<Integer> B = new ArrayList<Integer>();
    return MSB(A, A.size() - 1, B, 0);
  }

  private static ArrayList<Integer> MSB(ArrayList<Integer> A, int len, ArrayList<Integer> B,
  int carry){
    if(len < 0){
      return null;
    }
    int value = A.get(len);
    if(len == A.size() - 1){
      carry = (value+1 == 10)? 1 : 0;
    } else {
      if(carry == 1){
        carry = value + 1 == 10? 1 : 0;
        value = carry == 1 ? 0 : value + 1;
      } else {
        carry = 0;
      }
    }
    MSB(A, len-1, B, carry);
    if(carry == 1){
      if(len == A.size() - 1){
        B.add(0);
      } else if(len > 0){
        B.add(0);
      } else {
        B.add(1);
        B.add(0);
      }
    } else {
      if(len == A.size() - 1){
            B.add(value+1);
        } else {
            B.add(value);
        }
    }
    return B;
  }


  public static void main(String args[]){

    ArrayList<Integer> A = new ArrayList<Integer>();
    A.add(0);
    A.add(6);
    A.add(0);
    A.add(6);
    A.add(4);
    A.add(8);
    A.add(8);
    A.add(1);
    A =  MSB(A);
    int i = 0;
    while(i < A.size()){
      if(A.get(i) > 0){
        break;
      }
      i++;
    }
    i--;
    while(i >= 0){
      A.remove(i);
      i--;
    }
    System.out.print(A+" "+i);
  }


}
