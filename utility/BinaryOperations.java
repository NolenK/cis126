package utility;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.security.*;


public class BinaryOperations {
    // boolean arrays store the binary data with the index representing the bit position
    // the least significant bit starts at position 0
    // and increases as the significance increases
    // binaryData[0] is the least significant bit
    // binaryData[binaryData.length - 1] is the most significant bit

    public static boolean[] convertToArray(String binaryData) 
    {
         //create an output array based off length of imported string.
        boolean[] output = new boolean[binaryData.length()];
        
        //create a string array from string, using split to split 
        String [] parts = binaryData.split("");
        
        //for loop that irrterates for as many times as long as the string array parts is.
        for (int i = 0; i < parts.length; i++)
        {
            //if the char at the index point i = 1 the output at that index point is true.
            if(parts[i].equals("1"))
            {
               output[i] = true;
            }
            
            //else the output is false at that index.
            else
            {
               output[i] = false;
            }
            
        }
         
        return output;

    }

    public static boolean[] convertToArray(int binaryData, int numberOfBits) 
    {
        boolean[] output = new boolean[numberOfBits]; 
       
       //convert int to string.
       String binaryString = Integer.toString(binaryData);
       
       //formats the String to add 0s in binary, based on the number of user required bits.       
       binaryString = String.format("%"+Integer.toString(numberOfBits)+"s",Integer.toBinaryString(19)).replace(" ","0");
       
       //create a string to help the sorting process.        
       String [] parts = binaryString.split("");
              
       //for loop that irrterates for as many times as long as the string array parts is.
        for (int i = 0; i < parts.length; i++)
        {
            //if the char at the index point i = 1 the output at that index point is true.
            if(parts[i].equals("1"))
            {
               output[i] = true;
            }
            
            //else the output is false at that index.
            else
            {
               output[i] = false;
            }

        }

        return output;
    }

    public static int convertToInteger(String binaryData) 
    {
        //creates the int varible output, and parses the string value into the int. 
        int output = Integer.parseInt(binaryData);
         
        return output;

    }

    public static int convertToInteger(boolean[] binaryData) {
        int output = 0;

        for (int i = 0; i < binaryData.length; i++) {
            if (binaryData[i]) output = output | (1 << i);
        }
        return output;
    }

    public static String convertToString(int binaryData, int numberOfBits) {
        String output = Integer.toBinaryString(binaryData);

        output = output.substring(Math.max(0, output.length() - numberOfBits)); // trims if numberOfBits < string length
        while (output.length() < numberOfBits) {
            output = "0" + output;
        }
        return output;
    }

    public static String convertToString(boolean[] binaryData) {
        String output = "";

        for (boolean bit : binaryData) output = (bit ? "1" : "0") + output;
        return output;
    }
}
