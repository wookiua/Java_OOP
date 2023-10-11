package src.main.java.lib1;

public class stringcalculator {
    public int add (String numbers) {
        

        if (numbers.length() == 0){
            return 0;
        }

        int sum = 0;

        if (numbers.contains(",,") || numbers.contains(",\n") || numbers.contains("\n,") ||numbers.contains("\n\n")){
            throw new IllegalArgumentException("Error: impossible case");
        }

        String[] output = numbers.split("[,\n]+");
     
        for (String part : output){
            int num = Integer.parseInt(part);
            sum += num;
        }
        return sum;
    }
}
