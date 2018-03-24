/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import java.util.*;

/**
 *
 * @author Rupesh Kumar
 */

class heap {
    int data;
    LinkedList<heap> list = new LinkedList<heap>(); 
    heap min;
    heap child;
    heap parent;
    heap left;
    heap right;
    heap next;
    heap prev;
    int degree;
    int X = 1;
    int maxDegree;
    int size;
    heap head;
    boolean isMarked;
    boolean isValid = false;
    heap(){}
    heap(int d){
        this.data = d;
    }
    
    /*insert: adds the element to priority Queue
    and updates minimum accordingly */
    void insert(int d){
        heap n = new heap(d);
        n.left = n;
        n.right = n;
        size++;
        if(min == null){
            head = n;
            min = n;
        } else {
        if(min.prev == null){
            if(isValid){
                isValid = false;
            }
            head = null;
            min.prev = n;
            n.next = min;
        } else {
            heap t = min.prev;
            min.prev = n;
            n.next = min;
            t.next = n;
            n.prev = t;
        }
        if(head == null){
            head = n;
        }
        }
        list.add(n);
        if(n.data <= min.data){
            min = n;
        }
        if(X == size){
            X *= 2;
            maxDegree++;
        }
    }
    
    void print(){
        heap p = head;
        while(p != null){
            System.out.print(p.data+"--->");
            p = p.next;
        }
        
    }
    
    boolean isEmpty(){
        return this.min == null;
    }
    
    
    int getMin(){
        if(isEmpty()){
            return Integer.MIN_VALUE;
        }
        return this.min.data;
    }
    
    /*removeMin: Deletes the minimum element
    And performs consolidation on the elements of priority Queue based on their degrees */
    void removeMin(){
        
        if(isEmpty()){
            return;
        }
        
        if(min.prev == null && min.next == null && min.child == null){ 
            size = 0;
            min = null;
            head = null;
            return;
        }
        if(!list.isEmpty()){
            list.removeFirst();
        }
        heap pre = min.prev;
        heap ne = min.next;
        if(min.child != null){
            heap k1 = min.child;
            k1.parent = null;
            if(isValid){
                head = k1.left;
                isValid = false;
            } else {
                if(min == head){
                    heap r1 = head.next;
                    if(r1 != null){
                        r1.prev = k1.left.right;
                    }
                    k1.left.right.next = r1;
                    head = k1.left;
                } else {
                    if(pre != null){
                        pre.next = k1.left;
                        k1.left.prev = pre;
                    }
                    if(ne != null){
                        ne.prev = k1.left.right;
                    }
                    k1.left.right.next = ne;
                }
            }
            min = k1;
            k1.left = k1;
            k1.left.right = k1;
        } else {
            heap k = null;
            if(min == head){    
            head = head.next;
            k = head;
          } else {     
          if(pre != null){
              k = pre;
              pre.next = ne;
          }
          if(ne != null){
              ne.prev = pre;
              k = ne;
          }
         }
         min = k;   
        }
        if(size == X/2){
            X /= 2;
            maxDegree--;
        }
        size--;
        consolidate();
    }
    
    void decreaseKey(heap x, int k){
        if(isEmpty() || x == null){
            return;
        }
        heap t1 = x;
        heap t2 = (t1.child != null) ? t1.child : null;
        if(x != min){
        heap pre = x.prev;
        heap ne = x.next;
        if(pre == null && ne != null){
            heap k1 = x;
            heap t = k1.left.right;
            x = x.next;
            x.prev = null;
            t.left = x;
            x.right = t;
        } else if(ne == null && pre != null){
            heap k1 = x;
            heap t = k1.left;
            x = x.prev;
            x.next = null;
            k1.child = x;
            x.left = t;
            t.right = x;
        } else if(pre != null && ne != null){
            pre.next = ne;
            ne.prev = pre;
        }
        t1.left = t1;
        t1.left.right = t1;
        cascadingCut(t1);
        if(t1.isMarked){
            t1.isMarked = false;
        }
        if(t1.parent != null){
            if(t1.parent.data > k){
                cut(t1.parent);
            }
        }
        }
        t1.data = k;
        if(t2 != null){
            if(t2.data < t1.data){
                int tmp = t2.data;
                t2.data = t1.data;
                t1.data = tmp;
            }
        }
        if(t1.data <= min.data){
            min = t1;
        }
    }
    
    void cascadingCut(heap x){
        if(head != null){
            if(head.prev == null){
                if(isValid){
                    isValid = false;
                }
                head.prev = x;
                x.next = head;
                head = x;
            } else {
                heap k = head.prev;
                x.next = head;
                head.prev = x;
                k.next = x;
                x.prev = k;
            }
        }
    }
    
    /*decreaseKey: reduces the element of priority Queue to some Value
    And deletes it from their parent and updates head pointer */
    void decreaseKey(int k){
        if(list.isEmpty()){
            return;
        }
        heap z = list.removeFirst();
        decreaseKey(z, k);
    }
    
    void cut(heap x){
        if(x.parent != null){
            if(!x.isMarked){
                x.isMarked = true;
            } else {
                heap pre = x.prev;
                heap ne = x.next;
                if(pre == null && ne != null){
                    heap k1 = x;
                    heap t = k1.left.right;
                    x = x.next;
                    x.prev = null;
                    t.left = x;
                    x.right = t;
                } else if(ne == null && pre != null){
                    heap k1 = x;
                    heap t = k1.left;
                    x = x.prev;
                    x.next = null;
                    k1.child = x;
                    x.left = t;
                    t.right = x;
                } else if(pre != null && ne != null){
                    pre.next = ne;
                    ne.prev = pre;
                }
                x.left = x;
                x.left.right = x;
                cascadingCut(x);
                cut(x.parent);
            }
        }
    }
    
    
    void consolidate(){
        heap A[] = new heap[maxDegree];
        int max = Integer.MIN_VALUE;
        int g = Integer.MIN_VALUE; 
        HashMap<Integer, heap> map = new HashMap<Integer, heap>();
        heap tmp = head;
        int t = 0;
        while(tmp != null){
                while(tmp != null && A[tmp.degree] == null){
                    t = tmp.degree;
                    if(tmp.data <= min.data){
                        min = tmp;
                    }
                    if(t > 0){
                        if(!map.containsKey(t)){
                            map.put(t, tmp);
                        }
                        if(t > max){
                            max = t;
                        }
                    }
                    A[t] = tmp;
                    tmp = tmp.next;
                }
                heap c = tmp;
                heap y = null;
                if(c != null){
                    t = c.degree;
                    y = tmp;
                    y = y.next;
                }
                while(c != null && A[t] != null){
                    A[t].prev = null;
                    c.prev = null;
                    A[t].next = null;
                    c.next = null;
                    c = merge(c, A[t]);
                    if(map.containsKey(t)){
                        map.remove(t);
                    }
                    A[t] = null;
                    if(A[c.degree] == null){
                        A[c.degree] = c;
                        if(!isValid){
                            isValid = true;
                        }
                        if(c.degree > max){
                            max = c.degree;
                            map.put(max, c);
                        } else {
                            g = c.degree;
                            map.put(g, c);
                        }
                    } else {
                        t = c.degree;   
                    } 
                }
                
             tmp = y;
        }
        if(isValid){
        if(min.degree == 0){
            min.prev = null;
            min.next = null;
            isValid = false;
        }
        head = min;
        if(!map.isEmpty()){
        Set<Integer> s = (Set<Integer>)map.keySet();
        Iterator it = s.iterator();
        while(it.hasNext()){
            int y1 = (int)it.next();
            heap y = map.get(y1);
            if(y != null && y != min){
                if(min.degree == 0){
                    if(min.prev == null){
                        min.prev = y;
                        y.next = min;
                        head = y;
                    } else {
                        heap z = min.prev;
                        y.next = min;
                        min.prev = y;
                        z.next = y;
                        y.prev = z;
                    }
                } else {
                    y.parent = min;
                    if(y.degree == 1 && min.degree == 1){
                         min.child.prev = y;
                         y.next = min.child;
                         min.child.left = y;
                         y.right = min.child;
                    } else {
                        min.child.left.prev = y;
                         y.next = min.child.left;
                         min.child.left = y;
                         y.right = min.child;
                    }
                } 
            }
          }
        }
         if(A[0] != null && min != A[0]){
                 A[0].prev = null;
                 min.child.left.prev = A[0];
                 A[0].next = min.child.left;
                 min.child.left = A[0];
                 A[0].right = min.child;
            }   
       }
    }
    
    heap merge(heap x, heap y){
        if(x.data <= y.data){
            y.parent = x;
            if(x.child == null){
                x.child = y;
            }
            if(x.degree == 1 && y.degree == 1){
                x.child.prev = y;
                y.next = x.child;
                x.child.left = y;
                y.right = x.child;
            } else if(x.degree > 1 && y.degree > 1){
               x.child.left.prev = y;
               y.next = x.child.left;
               x.child.left = y;
               y.right = x.child;
            }
            if(x.data <= min.data){
                min = x;
            }
            x.degree = y.degree + 1;
            return x;
        } else {
            x.parent = y;
            if(y.child == null){
                y.child = x;
            }
            if(x.degree == 1 && y.degree == 1){
                y.child.prev = x;
                x.next = y.child;
                y.child.left = x;
                x.right = y.child;
            } else if(x.degree > 1 && y.degree > 1){
               y.child.left.prev = x;
               x.next = y.child.left;
               y.child.left = x;
               x.right = y.child;
            }
            if(y.data <= min.data){
                min = y;
            }
            y.degree = x.degree + 1;
            return y;
        }
    }
    
}

public class Fibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        heap k = new heap();
        k.insert(2);
        k.insert(3);
        k.insert(4);
        k.insert(5);
//        k.insert(9);
//        k.insert(8);
//        k.insert(11);
//        k.insert(10);
//        k.insert(14);
//        k.insert(15);
//        k.insert(20);
//        k.insert(24);
//        k.insert(22);
//        k.insert(50);
//        k.insert(42);
//        k.insert(48);
//        k.insert(50);
//        k.insert(51);
//        k.insert(52);
//        k.insert(30);
//        k.insert(40);
//        k.insert(50);
//        k.insert(60);
//        k.insert(70);
//        k.insert(80);
//        k.insert(90);
//        k.insert(100);
//        k.insert(120);
//        k.insert(130);
//        k.insert(150);
//        k.insert(170);
//        k.insert(190);
//        k.insert(220);
//        k.insert(250);
//        k.insert(270);
//        k.insert(1000);
//        k.insert(1200);
//        k.insert(1300);
//        k.insert(1500);
//        k.insert(1700);
//        k.insert(1900);
//        k.insert(2200);
//        k.insert(2500);
//        k.insert(10000);
//        k.insert(12000);
//        k.insert(13000);
//        k.insert(15000);
//        k.insert(17000);
//        k.insert(19000);
//        k.insert(22000);
//        k.insert(25000);
//        k.insert(100000);
//        k.insert(120000);
//        k.insert(130000);
//        k.insert(150000);
//        k.insert(170000);
//        k.insert(190000);
//        k.insert(220000);
//        k.insert(250000);
//        k.insert(1000000);
//        k.insert(1200000);
//        k.insert(1300000);
//        k.insert(1500000);
//        k.insert(1700000);
//        k.insert(1900000);
//        k.insert(2200000);
//        k.insert(2500000);
//        k.insert(2700000);
//        k.insert(900);
//        k.insert(920);
//        k.insert(930);
//        k.insert(950);
//        k.insert(970);
//        k.insert(990);
//        k.insert(9200);
//        k.insert(9500);
//        k.insert(9700);
//        k.insert(9000);
//        k.insert(9200);
//        k.insert(9300);
//        k.insert(9500);
//        k.insert(9700);
//        k.insert(9900);
//        k.insert(9200);
//        k.insert(9500);
//        k.insert(90000);
//        k.insert(92000);
//        k.insert(93000);
//        k.insert(95000);
//        k.insert(97000);
//        k.insert(99000);
//        k.insert(92000);
//        k.insert(95000);
//        k.insert(900000);
//        k.insert(920000);
//        k.insert(930000);
//        k.insert(950000);
//        k.insert(970000);
//        k.insert(990000);
//        k.insert(920000);
//        k.insert(950000);
//        k.insert(9000000);
//        k.insert(9200000);
//        k.insert(9300000);
//        k.insert(9500000);
//        k.insert(9700000);
//        k.insert(9900000);
//        k.insert(92000000);
//        k.insert(95000000);
//        k.insert(97000000);
//        k.insert(88000000);
//        k.insert(85000000);
//        k.insert(87000000);
//        k.insert(85000000);
//        k.insert(88000000);
//        k.insert(87000000);
//        k.insert(9000000);
//        k.insert(9200000);
//        k.insert(9300000);
//        k.insert(9500000);
//        k.insert(9700000);
//        k.insert(9900000);
//        k.insert(92000000);
//        k.insert(95000000);
//        k.insert(97000000);
//        k.insert(88000000);
//        k.insert(85000000);
//        k.insert(87000000);
//        k.insert(85000000);
//        k.insert(88000000);
//        k.insert(87000000);
//        k.insert(-3);
//        k.insert(19);
//        k.insert(-1);
//        k.insert(-2);
//        k.removeMin();
//////        System.out.println("---l");
//        k.decreaseKey(-4);
//        k.removeMin();
//////        System.out.println("---l");
//        k.removeMin();
//        k.removeMin();
////        k.removeMin();
////        k.removeMin();
////        k.removeMin();
//////        System.out.print("---k");
//////        k.print();
////        k.removeMin();
////        k.removeMin();
////        k.removeMin();
////        k.removeMin();
////        k.removeMin();
////        k.removeMin();
//////        k.removeMin();
////        System.out.println("-----");
//        k.print();
//        if(k.min != null && k.min.child != null){
//                System.out.print(k.min.child.data+"QA");
//                heap l = k.min.child.left;
//                while(l != null){
//                    System.out.print(l.data+"QAS");
//                    l = l.next;
//                }
//        }
//        System.out.print(k.getMin()+"min");
//        System.out.println("-----AA");
//        boolean flag = false;
//        int count = 0;
//        int n = 4;
//        while(k.size > 0){
//            if(count == 2 && !flag){
//                k.insert(1);
//                k.insert(2);
//                k.insert(15);
//                k.insert(16);
//                flag = true;
//                k.print();
//            } else {
//                k.removeMin();
//            }
//            System.out.println("----------------p");
//            k.print();
//            System.out.println("------");
//            if(k.min != null && k.min.child != null){
//                System.out.print(k.min.child.data+"CH");
//                heap l = k.min.child.left;
//                while(l != null){
//                    System.out.print(l.data+"CH ele");
//                    l = l.next;
//                }
//            }
//            System.out.print("MIN"+k.getMin()+" ");
//            System.out.println("------finished----");
//            count++;
//       }
    }
    
}
