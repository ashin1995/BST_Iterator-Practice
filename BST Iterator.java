/*

  BST.java
  
  Binary Search Tree Exercises
  COSC 102, Colgate University

  Practice building in-order/pre-order BST traversals
  
*/


import java.util.*;

public class BST
{

    // component node class
    private static class Node
    {
        int key;
        Node left;
        Node right;
        
        Node() { super(); }
        
        Node(int key) { super(); this.key = key; }
    }
    
    //Enumerated type to determine what sort of traversal pattern
    //The iterator uses
    public enum IteratorType{
      INORDER, PREORDER
    }
    // keeps track of root and size
    private Node root;
    private int size;    
    private IteratorType iterType;
    
    private final static IteratorType DEFAULT_ITER = IteratorType.INORDER;
    

    // default constructor    
    public BST() 
    { 
      this(DEFAULT_ITER);
    }
    
    
    public BST(IteratorType iterType)
    {
      super();
      this.iterType = iterType;
    }
    
    // method to access number of keys in the tree
    public int size()
    {
        return size;
    }
    
    // standard BST insert -- non-balancing
    public boolean insert (int key)
    {
        if (root == null) {
            root = new Node(key);
            size = 1;
            return true;
        }
            
        return insert(root, key);    
    }
    
    // recursive helper for insert
    private boolean insert (Node n, int key)    
    {
        if (n.key == key)
            return false;
        
        if (key > n.key) {
            if (n.right == null) {
                n.right = new Node(key);
                size++;
                return true;
            }
            else
                return insert(n.right, key);
        }
        else {  // key < n.key
            if (n.left == null) {
                n.left = new Node(key);
                size++;
                return true;
            }
            else
                return insert(n.left, key);
        }
    }
    
    
    // prints a preorder traversal, with X for null pointers
    public void preorder()
    {
        preorder(root);
        System.out.println();
    }
    
    // recursive helper for preorder traversal
    private void preorder(Node root)
    {
        if (root == null) {
            System.out.print("X ");
            return;
        }
            
        System.out.print(root.key);
        System.out.print(' ');
        
        preorder(root.left);
        preorder(root.right);
    }

    // Above code was given by Colgate University CS Department
    // Below code is mine

    private class InOrderIterator implements Iterator<Integer>{

        Stack<Node> stack = new Stack<Node>();

        public InOrderIterator(){
            Node current = root;
            while (current != null){
                stack.push(current);
                current = current.left;
            }
        }

        public Integer next(){
            Node popped = stack.pop();
            Node temp = popped.right;
            while (temp != null){
                stack.push(temp);
                temp = temp.left;
            }
            return popped.key;
        }

        public boolean hasNext(){
            if (stack.empty())
                return false;
            return true;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    private class PreOrderIterator implements Iterator<Integer>{

        Stack<Node> stack = new Stack<Node>();

        public PreOrderIterator(){
            stack.push(root);
        }

        public Integer next(){
            Node popped = stack.pop();
            if (popped.right != null){
                stack.push(popped.right);
            }
            if (popped.left != null){
                stack.push(popped.left);
            }
            return popped.key;
        }

        public boolean hasNext(){
            return !(stack.empty());
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Integer> iterator(){
        if (iterType == IteratorType.PREORDER)
            return new PreOrderIterator();
        else
            return new InOrderIterator();

    }

    public static void main(String[] args){
        BST tree = new BST(IteratorType.INORDER);
        tree.insert(8);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(10);
        tree.insert(14);
        tree.insert(13);
        tree.preorder();
        Iterator<Integer> inOrd = tree.iterator();
        while (inOrd.hasNext()){
            System.out.println(inOrd.next());
        }
        BST tree2 = new BST(IteratorType.PREORDER);
        tree2.insert(8);
        tree2.insert(3);
        tree2.insert(1);
        tree2.insert(6);
        tree2.insert(4);
        tree2.insert(7);
        tree2.insert(10);
        tree2.insert(14);
        tree2.insert(13);
        Iterator<Integer> preOrd = tree2.iterator();
        while (preOrd.hasNext()){
            System.out.println(preOrd.next());
        }

    }
    
    
    
}