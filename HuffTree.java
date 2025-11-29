import java.util.*;
import java.io.*;

/**
 * Creates a HuffTree, extension of a Binary Tree
 */
public class HuffTree extends BinaryTree<Character>{

    /**
     * Constructor for a tree 
     * @param e value for a root
     */
    public HuffTree(Character e){
        super(e);
    }

    /**
     * Constructor for a node
     * @param data value of node
     * @param left node for left child
     * @param right node for right child
     */
    public HuffTree(Character data, HuffTree left, HuffTree right){
        super(data, left, right);
    }

    /**
     * Set value for left child of an existing node
     * @param left node for left child
     */
    public void setLeft(HuffTree left){
        if(left instanceof HuffTree){
            super.setLeft(left);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Set value for right child of an existing node
     * @param right node for right child
     */
    public void setRight(HuffTree right){
        super.setRight(right);
    }

    /**
     * Get left child of an exisiting node
     * @return left child
     * 
     */
    public HuffTree getLeft(){
        return (HuffTree) super.getLeft();
    }

    /**
     * Get right child of an existing node
     * @return right child
     */
    public HuffTree getRight(){
        return (HuffTree) super.getRight();
    }

    /**
     * Get data of an existing node
     * @return data of a node with a Character type
     */
    public Character getData(){
        if (super.getData() instanceof Character) {
            return super.getData();
        } else {
            throw new UnsupportedOperationException("The data entered is of the wrong type.");
        }
    }

    /**
     * Transverses a tree based on an input path
     * @param input path that the method follows
     * @return node that the path lands on
     */
    public HuffTree followPath(String input){

        char[] path = input.toCharArray();
        HuffTree tree = this; 

        for(char s: path){
            if(s == ' '){
                return tree;
            }
            else if(s == '0'){
                if(tree.getLeft() != null) {
                    tree = (HuffTree) tree.getLeft();
                }
            }
            else if(s == '1'){
                if(tree.getRight() != null) {
                    tree = (HuffTree) tree.getRight();
                }
            }
            else {
                throw new IllegalArgumentException("Invalid Input: " + s);
            }
            if(tree == null){
                throw new IllegalArgumentException("Invalid Input. Node doesn't exist.");
            }
        }
        return tree;
    }

    /**
     * Reads a file into a HuffTree
     * @param filename file to be made into a tree
     * @return the resulting HuffTree
     */
    public static HuffTree readHuffTree(String filename){

        HuffTree root = new HuffTree((char)0); //initializes the root as 0 

        try {
            Scanner scanner = new Scanner(new File(filename)); 

            while(scanner.hasNextLine()){
                String temp = scanner.nextLine();

                root.writeHuffTree(temp, root);
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error: " + e);

        }

        return root;
    }

    /**
     * Writes the HuffTree recursively based on the given line in a file
     * @param line line to be turned into a tree
     * @param root tree that the line gets added to
     * @return the tree made from the line
     */ 
    public HuffTree writeHuffTree(String line, HuffTree root){

        char[] arr = line.toCharArray();

        if(arr[0] == ' '){ //base case 
            int num = Integer.parseInt(line.trim()); 

            char charNum = (char) num;

            this.setData(charNum); //sets the value of the node
        }
        else if(arr[0] == '0'){ //goes left
            if(this.getLeft() == null){
                this.setLeft(new HuffTree ((char) 0)); //creates a new node and initializes it at 0
            }
            this.getLeft().writeHuffTree(line.substring(1), root); //recursively moves to the next number

        }
        else if(arr[0] == '1'){
            if(this.getRight() == null){
                this.setRight(new HuffTree((char) 0)); //creates a new node and initializes it at 0
            }
            this.getRight().writeHuffTree(line.substring(1), root);
        }
        return root; 
    }

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args) {
        String filename = "Default.txt";

        readHuffTree(filename);
    }

}