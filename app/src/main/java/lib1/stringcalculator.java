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
                delimiter = numbers.substring(2, delimiterIndex);

                if(delimiter.length() > 1){
                    throw new IllegalArgumentException("Error: delimiter length more than 1");
                }

                else{
                    numbers = numbers.substring(delimiterIndex + 1);
                }
                
            }

            else{
                throw new IllegalArgumentException("Error: impossible setting delimiter");
            }

        }

        if (numbers.contains(",,") || numbers.contains(",\n") || numbers.contains("\n,") ||numbers.contains("\n\n")){
            throw new IllegalArgumentException("Error: impossible case");
        }

        String[] output = numbers.split("[,\n" + delimiter + "]+");

        ArrayList <Integer> negativeNumbers = new ArrayList<>();
     
        for (String part : output){
            int num = Integer.parseInt(part);
            if(num < 0){
                negativeNumbers.add(num);
            }
            sum += num;
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
    System.out.println(st.add("//;\n-1;-22,-55,6"));
}


}
