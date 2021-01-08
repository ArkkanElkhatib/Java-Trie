//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
public class TrieMap implements TrieMapInterface{
  TrieMapNode root;
  
  public TrieMap(){
    root = new TrieMapNode();
  }
  
  //Indirectly recursive method to meet definition of interface
  public void put(String key, String value){
    put(root, key, value);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public void put(TrieMapNode current, String curKey, String value){
    if (curKey.length() == 0) {
      current.setValue(value);
      return;
    }
    if (!current.getChildren().containsKey(curKey.charAt(0))) {
      current.getChildren().put(curKey.charAt(0), new TrieMapNode());
    }
    put(current.getChildren().get(curKey.charAt(0)), curKey.substring(1), value);
  }
  
  //Indirectly recursive method to meet definition of interface
  public String get(String key){
    return get(root, key);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public String get(TrieMapNode current, String curKey){
    if (curKey.length() == 0) {
      return current.getValue();
    }
    if (current.getChildren().keySet().contains(curKey.charAt(0))) {
      return get(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
    return null;
  }
  
  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key){
    return containsKey(root, key);
  }
  
  //Recursive method
  //Note: arguments are only a suggestion, you may use your own if you devise a different recursive solution
  public boolean containsKey(TrieMapNode current, String curKey){
    if (curKey.length() == 0) {
      if (current.getValue() == null) {
        return false;
      } else {
        return true;
      }
    }
    if (current.getChildren().keySet().contains(curKey.charAt(0))) {
      return containsKey(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
    return false;
  }
  
  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix){
    return getSubtreeKeys(findNode(root, prefix), new ArrayList<String>());
  }
  
  //Recursive helper function to find node that matches a prefix
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public TrieMapNode findNode(TrieMapNode current, String curKey){
    if (curKey.length() == 0) {
      if (current != null) {
        return current;
      }
    }
    if (current.getChildren().keySet().contains(curKey.charAt(0))) {
      return findNode(current.getChildren().get(curKey.charAt(0)), curKey.substring(1));
    }
    return null;
  }
  
  //Recursive helper function to get all keys in a node's subtree
  //Note: only a suggestion, you may solve the problem is any recursive manner
  public ArrayList<String> getSubtreeKeys(TrieMapNode current, ArrayList<String> result) {
    if (current != null) {
      if (current.getValue() != null) {
        result.add(current.getValue());
      }
      for (char i : current.getChildren().keySet()) {
        getSubtreeKeys(current.getChildren().get(i), result);
      }
    }
    return result;
  }
  
  //Indirectly recursive method to meet definition of interface
  public void print(){
    print(root);
  }
  
  //Recursive method to print values in tree
  public void print(TrieMapNode current){
    if (current.getValue() != null) {
      System.out.println(current.getValue());
    }
    for (char i: current.getChildren().keySet()) {
      print(current.getChildren().get(i));
    }
  }
  
  public static void main(String[] args){
    //You can add some code in here to test out your TrieMap initially
    //The TrieMapTester includes a more detailed test
    TrieMap map = new TrieMap();
    map.put("ARE", "ARE");
    map.put("ART", "ART");
    map.put("BET", "BET");
    map.put("LIFE", "LIFE");
    map.put("ARTISTIC", "ARTISTIC");
    map.put("ARTICUNO", "ARTICUNO");
    map.put("ARTISTIC", "ARTISTIC");
    map.print();
    System.out.println(">> CONTAINS KEY <<");
    System.out.println(map.containsKey("ARTICUNO"));
    System.out.println(map.containsKey("NOT"));
    System.out.println(">> GETS <<");
    System.out.println(map.get("ART"));
    System.out.println(map.get("BET"));
    System.out.println(map.get("NOT"));
  }
}