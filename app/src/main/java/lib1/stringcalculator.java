package src.main.java.lib1;

import java.util.ArrayList;

public class stringcalculator {
    public int add (String numbers) {
        

        if (numbers.length() == 0){
            return 0;
        }

        int sum = 0;
        String delimiter = ",";

        if (numbers.startsWith("//")){

            int delimiterIndex = numbers.indexOf("\n");

            if (delimiterIndex != -1){

                String delimiter_block = numbers.substring(2, delimiterIndex);             

                if(delimiter_block.startsWith("[") && delimiter_block.endsWith("]")){
                    delimiter = delimiter_block.substring(1, delimiter_block.length() - 1);

                    if(delimiter.isEmpty()){
                        throw new IllegalArgumentException("Error: impossible setting delimiter");
                    }
                }
                
                else{
                    delimiter = numbers.substring(2, delimiterIndex);

                    if(delimiter.length() > 1){
                        throw new IllegalArgumentException("Error: delimiter length more than 1");
                    }
                }
                numbers = numbers.substring(delimiterIndex + 1);
                
            }

            else{
                throw new IllegalArgumentException("Error: impossible setting delimiter");
            }

        }


        if (numbers.contains(",,") || numbers.contains(",\n") || numbers.contains("\n,") ||numbers.contains("\n\n")){
            throw new IllegalArgumentException("Error: impossible case");
        }

        numbers = numbers.replace('\n',',');
        numbers = numbers.replace(delimiter,",");

        String[] output = numbers.split("[,]+");

        ArrayList <Integer> negativeNumbers = new ArrayList<>();

        for (String part : output){
            if (!part.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("Error: illegal delimiter");
            }
            if (part.length()>0) {
                int num = Integer.parseInt(part);
                if(num < 0){
                    negativeNumbers.add(num);
                }
                // Skip numbers more than 1000
                if (num <= 1000){
                    sum += num;
                }   
            }
        
        }

        if (!negativeNumbers.isEmpty()){
            throw new IllegalArgumentException("Error: Impossible case of negative numbers: " + negativeNumbers);
        }
        else{
            return sum;
        }

    }

    
    public static void main(String args[]){
        stringcalculator st = new stringcalculator();
        System.out.println(st.add("//[!!!]\n!!!22,55,6"));
    }

}
