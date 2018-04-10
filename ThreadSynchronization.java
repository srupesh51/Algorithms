class printDemo {
  public void printSequence(){
    try {
      for(int i = 1; i < 10;i++){
        System.out.println(i+" ");
      }
    } catch(Exception e){
      System.out.println("Thread interrupted");
    }
  }
}

class ThreadPrint extends Thread {
  private Thread t;
  private String threadName;
  printDemo pd;
  ThreadPrint(String t, printDemo p){
    threadName = t;
    pd = p;
  }
  public void run(){
    synchronized(pd){
      pd.printSequence();
      System.out.println("The Thread"+threadName+" exiting");
    }
  }
  public void start(){
    System.out.println("Starting " +  threadName );
    if(t == null){
      t = new Thread(this, threadName);
      t.start();
    }
  }
}

public class ThreadSynchronization {

  public static void main(String args[]) {
      printDemo pd = new printDemo();
      ThreadPrint tp1 = new ThreadPrint("T - 1", pd);
      ThreadPrint tp2 = new ThreadPrint("T - 2", pd);
      tp1.start();
      tp2.start();
      try {
        tp1.join();
        tp2.join();
      }catch(Exception e){
        System.out.println("Interrupted");
      }
  }

}
