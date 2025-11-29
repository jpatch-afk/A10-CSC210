import java.util.*;
import java.io.*;

public class HuffDecode {

    public static void main(String[] args) {    
    
        //Reads in a file 
        String filename = "DefaultTree.txt";

        //Converts to a tree
        HuffTree tree = HuffTree.readHuffTree(filename);
 
        //Creates values  
        String result = "";
        String encoded = "";
        String enc_path = "encoded.txt";

        try {
            Scanner scanner = new Scanner(new File(enc_path)); 

            while(scanner.hasNextLine()){
                encoded = scanner.nextLine();
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found: " + e);
        }

        //make a queue representation of the code
        Queue<String> queue = new LinkedList<String>();

        for(char c: encoded.toCharArray()){
            queue.add(String.valueOf(c));
        }

        String temp = ""; //temporary string to store the potential string
        while(!queue.isEmpty()){
            
            temp += queue.remove(); //takes the first element

            HuffTree temp_tree = tree; //temporary tree to traverse freely

            HuffTree node = temp_tree.followPath(temp);

            if(node.isLeaf()){
                result += node.getData();
                temp = ""; //reset temp to for next line
            }
        }
        System.out.println(result);
    }
}
