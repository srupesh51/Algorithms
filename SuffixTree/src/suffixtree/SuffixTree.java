/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suffixtree;

/**
 *
 * @author Rupesh Kumar
 */

class SuffixTreeNode {
    SuffixTreeNode children[] = new SuffixTreeNode[256];
    SuffixTreeNode internalNode;
    SuffixTreeNode root;
    SuffixTreeNode suffixLink;
    int remaining = 0;
    int start = -1;
    int end = -1;
    int ActiveEdge = -1;
    void createSuffixTree(String text){
        root = new SuffixTreeNode();
        createSuffix(text);
        setSuffixIndexByDFS(text);
    }
    
    void createSuffix(String text){
        int i = 0;
        end = text.length() - 1;
        while(i < text.length()){
        remaining++;
        while(remaining > 0){    
        if(root.children[text.charAt(i)] == null){
            root.children[text.charAt(i)] = new SuffixTreeNode();
            root.children[text.charAt(i)].start = i;
            remaining--;
        } else {
            if(ActiveEdge == -1){
                ActiveEdge = root.children[text.charAt(i)].start;
                break;
            } else if(text.charAt(ActiveEdge + 1) == 
                    text.charAt(i)){
                ActiveEdge++;
                break;
            } else { 
               root.children[text.charAt(ActiveEdge)].internalNode = new SuffixTreeNode();
               root.children[text.charAt(ActiveEdge)].internalNode.start = i;
               remaining--;
            }
        } 
      }
      if(remaining == 0){
          ActiveEdge = -1;
      }
     i++;
     }
    }
    
    void setSuffixIndexByDFS(String text){
        for(int i = 0; i < root.children.length; i++){
            if(root.children[i] != null){
                System.out.print("--edge---");
                print(root.children[i].start, end, text);
                if(root.children[i].internalNode != null){
                    System.out.print("--IN---");
                    print(root.children[i].internalNode.start, end, text);
                }
            }
        }
    }
    
    void print(int start, int end, String text){
        for(int i = start; i <= end; i++){
           System.out.print(text.charAt(i)+" ");
        }
    }
    
}


public class SuffixTree {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        SuffixTreeNode sf = new SuffixTreeNode();
        String text = "abc";
        sf.createSuffixTree(text);
    }
    
}
