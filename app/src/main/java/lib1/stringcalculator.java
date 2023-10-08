package src.main.java.lib1;

public class stringcalculator {
    public int add (String numbers) {
        

        if (numbers.length() == 0){
            return 0;
        }

        int sum = 0;

        String[] output = numbers.split(",");

        for (String part : output){
            int num = Integer.parseInt(part);
            sum += num;
        }
        return sum;
    }
}
