public class Trie {
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[26];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;
        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < 26; i++)
                children[i] = null;
        }
    };
    private static TrieNode root;
    private static int charToIndex(char c){
        return c >= 'A' && c <= 'Z'? c - 'A' : c - 'a';
    }
    private static void insert(TrieNode root, String word[]){
        for(int i = 0; i < word.length; i++){
            insert(root, word[i],0, word[i].length());
        }
    }
    private static void insert(TrieNode root, String word, int start, int end){
        if(start == end){
            root.isEndOfWord = true;
            return;
        }
        int cIndex = charToIndex(word.charAt(start));
        if(root.children[cIndex] == null){
            root.children[cIndex] = new TrieNode();
        }
        root = root.children[cIndex];
        insert(root, word,start+1,end);
    }
    private static boolean isPresent(TrieNode root, String word){
        return isPresent(root, word, 0, word.length());
    }
    private static boolean isPresent(TrieNode root, String word, int start, int end){
        if(start == end){
            return root != null && root.isEndOfWord;
        }
        int cIndex = charToIndex(word.charAt(start));
        if(root.children[cIndex] == null){
            return false;
        }
        root = root.children[cIndex];
        return isPresent(root, word,start+1,end);
    }

    private static void deleteWord(TrieNode root, String word[]){
      for(int i = 0; i < word.length; i++){
          delete(root, word[i], 0, word[i].length());
      }
    }
    private static boolean isFree(TrieNode root){
      for (int i = 0; i < 26; i++)
      {
        if(root.children[i] != null){
           return false;
        }
      }
      return true;
    }
    private static boolean delete(TrieNode root, String word, int start, int len){
      if(start == len){
        if(root.isEndOfWord){
            root.isEndOfWord = false;
            if(isFree(root)){
              return true;
            }
        }
        return false;
      }
      int cIndex = charToIndex(word.charAt(start));
      if(delete(root.children[cIndex],word,start+1,len)){
        root.children[cIndex] = null;
        return !root.isEndOfWord && isFree(root);
      }
      return false;
    }


    public static void main(String args[]) {
        String word[] = {"apple","amazon","grapes"};
        root = new TrieNode();
        insert(root,word);
        String wordsToBeDeleted[] = {"amazon","grapes"};
        deleteWord(root, wordsToBeDeleted);
        System.out.print(isPresent(root,"grapes")+" ");
    }
}
