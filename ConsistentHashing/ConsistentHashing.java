import java.util.*;
class Request {
  int requestId;
  Request(int Id){
    this.requestId = Id;
  }
}
class DLL {
  LinkedList<Request> r;
  int copyID;
  DLL prev;
  DLL next;
  boolean failOver;
  DLL(){}
  DLL(LinkedList<Request> t, int cID){
    this.r = t;
    this.copyID = cID;
    this.failOver = false;
    this.prev = null;
    this.next = null;
  }
}
class LoadBalancer {
  private LinkedList<Request> list;
  private HashMap<Integer, LinkedList<Request>>map;
  private int n;
  private int m;
  private DLL Machines;
  LoadBalancer(){
  }
  LoadBalancer(int n, int m){
    this.n = n;
    this.m = m;
    list = new LinkedList<Request>();
    map = new HashMap<Integer, LinkedList<Request>>();
  }
  public void setRequests(int n){
    this.n = n;
  }
  public void setMachines(int m){
    this.m = m;
  }
  public int getRequests(){
    return n;
  }
  public int getMachines(){
    return m;
  }
  public void addRequest(Request r){
    if(list.isEmpty()){
      list.add(r);
      return;
    }
    Request t = null;
    int index = -1;
    for(int i = 0; i < list.size(); i++){
      if(r.requestId <= list.get(i).requestId){
        t = list.get(i);
        index = i;
        break;
      }
    }
    if(index != -1){
      list.remove(index);
    }
    list.add(r);
    if(t != null){
      list.add(t);
    }
  }
  public void addMachine(){
    int k = n/m;
    int ID = 2;
    int num = k;
    int num1 = m;
    int num2 = -1;
    int count = 0;
    for(int i = 0; i < n; i++){
      LinkedList<Request> r1 = null;
      if(count < k || !map.containsKey(ID)){
        r1 = !map.containsKey(ID) ?
        new LinkedList<Request>() : map.get(ID);
        r1.add(list.get(i));
        map.put(ID,r1);
        map.put(ID-1,r1);
        count++;
      }
      if(count == k) {
        k = (n - (i+1));
        num1--;
        if(num1 == 0){
          break;
        }
        num2 = (k)/(num1);
        k = num2;
        ID += 2;
        count = 0;
      }
    }
  }
  public void print(){
    for(int i = 1; i <= map.size(); i++){
      LinkedList<Request> t = map.get(i);
      for(int j = 0; j < t.size(); j++){
        System.out.print(t.get(j).requestId+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
  public void balanceLoads(){
    for(int i = 2; i <= map.size(); i += 2){
      LinkedList<Request> t = !map.containsKey(i) ?
      map.get(i-1) : map.get(i);
      int cID = !map.containsKey(i) ? -1 : i-1;
      if(Machines == null){
        Machines = new DLL(t,cID);
        Machines.prev = Machines;
        Machines.next = Machines;
      } else {
        DLL m2 = new DLL(t,cID);
        DLL t1 = Machines;
        DLL t2 = t1.prev;
        t2.next = m2;
        m2.prev = t2;
        t1.prev = m2;
        m2.next = t1;
      }
    }
  }
  public void setFailOver(int n){
    DLL m3 = Machines;
    int ID = 2;
    int count = 0;
    for(int i = 2; i <= map.size(); i += 2){
      if(i == ID){
        LinkedList<Request> t = map.get(m3.copyID);
        m3.r = t;
        if(!m3.failOver){
          m3.failOver = true;
          map.remove(i);
          m3.copyID = -1;
        }
        ID += 2;
        count++;
      }
      if(count == n){
        break;
      }
      m3 = m3.next;
    }
  }
  public void rollBackFailOver(int n){
    DLL m3 = Machines;
    int ID = 2;
    int count = 0;
    for(int i = 1; i <= map.size(); i += 2){
      if(i == ID-1){
        LinkedList<Request> t = map.get(i);
        m3.r = t;
        if(m3.failOver){
          m3.failOver = false;
          map.put(ID, m3.r);
          m3.copyID = i;
        }
        ID += 2;
        count++;
      }
      if(count == n){
        break;
      }
      m3 = m3.next;
    }
  }
  public void printLoadDistribution(){
    DLL m3 = Machines;
    DLL m4 = m3.prev;
    DLL m5 = m3.prev.next;
    LinkedList<Request> t1 = m4.r;
    System.out.print(m4.copyID+" ");
    for(int i = 0; i < t1.size(); i++){
      System.out.print(t1.get(i).requestId+" ");
    }
    LinkedList<Request> t2 = m5.r;
    System.out.print(m5.copyID+" ");
    for(int i = 0; i < t2.size(); i++){
      System.out.print(t2.get(i).requestId+" ");
    }
    int count = 1;
    while(m3 != null){
      LinkedList<Request> t = m3.r;
      System.out.print(m3.copyID+" ");
      for(int i = 0; i < t.size(); i++){
        System.out.print(t.get(i).requestId+" ");
      }
      System.out.println();
      if(count == m){
        break;
      }
      count++;
      m3 = m3.next;
    }
    System.out.println();
  }
}
public class ConsistentHashing {
  public static void main(String args[]){
    LoadBalancer b1 = new LoadBalancer(7,4);
    Request r[] = {new Request(1), new Request(3), new Request(2),
    new Request(4), new Request(6), new Request(5), new Request(7)};
    for(int i = 0; i < r.length; i++){
      b1.addRequest(r[i]);
    }
    b1.addMachine();
    b1.print();
    b1.balanceLoads();
    b1.setFailOver(2);
    b1.rollBackFailOver(2);
    b1.printLoadDistribution();
  }
}
