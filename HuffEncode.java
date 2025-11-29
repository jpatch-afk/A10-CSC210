import java.util.*;
import java.io.*;

/**
 * Encodes a message based on a file
 */
public class HuffEncode {

    /**
     * Main method, runs the program to encode a message
     * @param args arguments 
     */
    public static void main(String[] args) {
        
        String filename = "DefaultTree.txt";

        HashMap<String, String> dict = new HashMap<String, String>();

        try(Scanner scanner = new Scanner(new File(filename)); ){
            
            while(scanner.hasNextLine()){
                String temp = scanner.nextLine();
                int index = 0;
                for(int i = 0; i < temp.length(); i++){
                    if(temp.charAt(i) == ' '){
                        String value = temp.substring(0, index);
                        String key = temp.substring(index + 1, temp.length());
                        dict.put(key, value);
                        break;
                    }
                    
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error: " + e);
        }

        String result = "";
        String input = "";

        try(Scanner reader = new Scanner(new File(input))){
            while(reader.hasNextLine()){
                input = reader.nextLine();
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found: "+ e);
        }

        for(char c: input.toCharArray()){
            String decode = dict.get(String.valueOf(c));
            result += decode;
        }

        System.out.println(result);
    }
}
