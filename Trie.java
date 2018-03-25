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
            insert(root, word[i], 0, word[i].length());
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


    public static void main(String args[]) {
        String word[] = {"apple","orange","grapes"};
        root = new TrieNode();
        insert(root,word);
        System.out.print(isPresent(root,"orange")+" ");
    }
}
