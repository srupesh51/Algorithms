public class HashTableNode {

  static class HashTable {
    int data;
    int key;
    HashTable next;
    int size;
    int count;
    HashTable(){}
  };
  static HashTable table[];
  private static void rehash(HashTable h){
    int oldSize = h.size;
    h.size *= 2;
    HashTable table1[] = new HashTable[oldSize];
    table1 = table;
    table = new HashTable[h.size];
    for(int i = 0; i < oldSize; i++){
      HashTable tbl = table1[i];
      while(tbl != null){
        int index = hash(h, tbl.key);
        table[index] = new HashTable();
        table[index].key = tbl.key;
        table[index].data = tbl.data;
        tbl = tbl.next;
     }
    }
  }
  private static int hash(HashTable h,int key){
    return key % h.size;
  }
  private static void createTable(HashTable h, int size){
    h.size = size;
    table = new HashTable[h.size];
    for(int i = 0; i < h.size; i++){
      table[i] = null;
    }
  }
  private static void insert(HashTable h, int key, int value){
    if(h.count == h.size){
      rehash(h);
    }
    int index = hash(h,key);
    HashTable tbl = new HashTable();
    tbl.key = key;
    tbl.data = value;
    if(table[index] == null){
      table[index] = tbl;
      h.count++;
      return;
    }
    tbl.next = table[index];
    table[index] = tbl;
  }
  private static boolean search(HashTable h, int key, int value){
    int index = hash(h,key);
    HashTable tbl = table[index];
    while(tbl != null){
      if(tbl.data == value){
        return true;
      }
      tbl = tbl.next;
    }
    return false;
  }
  private static void delete(HashTable h,int key, int value){
    int index = hash(h,key);
    HashTable tbl = table[index];
    HashTable prev = null;
    boolean flag = false;
    while(tbl != null){
      if(tbl.data == value){
        flag = true;
        break;
      }
      prev = tbl;
      tbl = tbl.next;
    }
    if(flag){
      if(prev != null){
        prev.next = tbl.next;
      } else if(tbl != null){
          table[index] = tbl.next;
      }
    }
  }
  public static void main(String args[]){
    HashTable h = new HashTable();
    createTable(h, 4);
    insert(h,1,2);
    insert(h,2,3);
    insert(h,3,4);
    insert(h,11,24);
    insert(h,4,26);
    insert(h,9,223);
    System.out.print(search(h,3,4)+" ");
    delete(h, 3, 4);
    delete(h, 11, 24);
    delete(h, 4, 26);
    delete(h, 9, 223);
    System.out.print(search(h,4,26)+" ");
    System.out.print(search(h,3,4)+" ");
    System.out.print(search(h,9,223)+" ");
    System.out.print(search(h,1,2)+" ");
  }
}
