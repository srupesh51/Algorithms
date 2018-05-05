import java.util.*;
class Request {
  int requestId;
  Request(int Id){
    this.requestId = Id;
  }
}
class DLL {
  LinkedList<Request> r;
  DLL prev;
  DLL next;
  DLL(){}
  DLL(LinkedList<Request> t){
    this.r = t;
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
    int ID = 1;
    int num = k;
    int num1 = m;
    int num2 = -1;
    for(int i = 0; i < n; i++){
      LinkedList<Request> r1 = null;
      if(i == k) {
        k = (n - (i+1));
        num1--;
        num2 = (k)/(num1);
        k = i + num2 + 1;
        ID++;
      }
      if(i < k && !map.containsKey(ID)){
        r1 = new LinkedList<Request>();
        r1.add(list.get(i));
        map.put(ID,r1);
      } else if(i < k){
        r1 = map.get(ID);
        r1.add(list.get(i));
        map.put(ID,r1);
      }
    }
    for(int i = ID+1; i <= m*2; i++){
      if(map.containsKey(ID)){
        LinkedList<Request> r1 = map.get(ID);
        map.put(i, r1);
        ID--;
      }
    }
  }
  public void print(){
    for(int i = 1; i < map.size()+1; i++){
      LinkedList<Request> t = map.get(i);
      for(int j = 0; j < t.size(); j++){
        System.out.print(t.get(j).requestId+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
  public void balanceLoads(){
    DLL t2 = null;
    for(int i = map.size(); i > 0; i--){
      LinkedList<Request> t = map.get(i);
      if(Machines == null){
        Machines = new DLL(t);
        t2 = Machines;
      } else {
        DLL m2 = new DLL(t);
        DLL t1 = Machines;
        m2.next = t1;
        t1.prev = m2;
        Machines = m2;
      }
    }
  }
  public void printLoadDistribution(){
    DLL m3 = Machines;
    int count = 1;
    while(m3 != null){
      LinkedList<Request> t = m3.r;
      for(int i = 0; i < t.size(); i++){
        System.out.print(t.get(i).requestId+" ");
      }
      System.out.println();
      if(count == map.size()){
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
    LoadBalancer b1 = new LoadBalancer(6,4);
    Request r[] = {new Request(1), new Request(3), new Request(2),
    new Request(4), new Request(6), new Request(5)};
    for(int i = 0; i < r.length; i++){
      b1.addRequest(r[i]);
    }
    b1.addMachine();
    b1.print();
    b1.balanceLoads();
    b1.printLoadDistribution();
  }
}
