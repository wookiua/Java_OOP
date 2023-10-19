package src.main.java.lib1;

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
     
        for (String part : output){
            int num = Integer.parseInt(part);
            sum += num;
        }
        return sum;
    }
}
