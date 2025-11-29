import java.util.*;
import java.io.*;

public class HuffTree extends BinaryTree<Character>{

    //Character data;

    //HuffTree left;

    //HuffTree right;

    public HuffTree(Character e){
        super(e);
    }

    public HuffTree(Character data, HuffTree left, HuffTree right){
        super(data, left, right);
    }

    public void setLeft(HuffTree left){
        if(left instanceof HuffTree){
            super.setLeft(left);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    public void setRight(HuffTree right){
        super.setRight(right);
    }

    public HuffTree getLeft(){
        return (HuffTree) super.getLeft();
    }

    public HuffTree getRight(){
        return (HuffTree) super.getRight();
    }

    public Character getData(){
        if (super.getData() instanceof Character) {
            return super.getData();
        } else {
            throw new UnsupportedOperationException("The data entered is of the wrong type.");
        }
    }

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

    public static void main(String[] args) {
        String filename = "Default.txt";

        readHuffTree(filename);
    }

}