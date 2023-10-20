package src.main.java.lib1;

import java.util.ArrayList;

public class stringcalculator {
    public int add (String numbers) {
        

        if (numbers.length() == 0){
            return 0;
        }

        int sum = 0;
        ArrayList<String> delimiters = new ArrayList<String>();
   
        if (numbers.startsWith("//")){
  
            int delimiterIndex = numbers.indexOf("\n");

            if (delimiterIndex != -1) {
    
                String delimiter_block = numbers.substring(2, delimiterIndex);
                if (delimiter_block.startsWith("[") && delimiter_block.endsWith("]")) {

                    for (String part : delimiter_block.split("[\\[\\]]+")) {
                        if (part.length() > 0) {
                            delimiters.add(part);
                            if (delimiters.size() > 1){
                                if(part.length() > 1){
                                    throw new IllegalArgumentException("Error: delimeter length more than 1");
                                }
                            }
                        }
                    }

                    if (delimiters.size() == 0) {
                        throw new IllegalArgumentException("Error: impossible setting delimiter");
                    }   
                } else {

                    delimiter_block = numbers.substring(2, delimiterIndex);

                    if(delimiter_block.length() > 1) {
                        throw new IllegalArgumentException("Error: delimiter length more than 1");
                    }
                    delimiters.add(delimiter_block);
                }

                numbers = numbers.substring(delimiterIndex + 1);
            } else {

                throw new IllegalArgumentException("Error: impossible setting delimiter");
            }
        }

        if (numbers.contains(",,")  || numbers.contains(",\n") || 
            numbers.contains("\n,") || numbers.contains("\n\n")) {
            throw new IllegalArgumentException("Error: impossible case");
        }

        numbers = numbers.replace('\n',',');
        for (String part : delimiters) {
            numbers = numbers.replace(part,",");
        }

        String[] output = numbers.split("[,]+");


        ArrayList <Integer> negativeNumbers = new ArrayList<>();

        for (String part : output) {

            if (!part.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("Error: illegal delimiter");
            }
            if (part.length()>0) {
                int num = Integer.parseInt(part);
                if(num < 0){
                    negativeNumbers.add(num);
                }

                if (num <= 1000){
                    sum += num;
                }   
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Error: Impossible case of negative numbers: " + negativeNumbers);
        }

        return sum;

    }

    
    public static void main(String args[]){
        stringcalculator st = new stringcalculator();
        System.out.println(st.add("//[*][!][&]\n1\n2,10*15!10&10"));
    }

}
