package src.test.java.lib1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import src.main.java.lib1.stringcalculator;

public class stringcalculatorTest {

    stringcalculator st = new stringcalculator();

   
    @Test
    public void TryZero() {
        assertEquals(0, st.add(""));
    }


    @Test
    public void TryOne() {
        assertEquals(1, st.add("1"));
    }

    @Test
    public void TryNine() {
        assertEquals(9, st.add("9"));
    }

    @Test
    public void TrySumfromTwo() {
        assertEquals(16, st.add("11,5"));
    }

    @Test
   public void TrySumfromFive() {
        assertEquals(46, st.add("11,5,8,12,10"));
    }

    @Test
   public void TryStep3() {
        assertEquals(46, st.add("11,5\n8,12\n10"));
    }

    @Test
    public void TryExeption_Step3() {
        Exception exception = assertThrows(IllegalArgumentException.class,()-> st.add("11,,5,\n6"));
    }

    @Test
   public void TryStep4_1() {
        assertEquals(3, st.add("//;\n1;2"));
    }
    
    @Test
   public void TryStep4_2() {
        assertEquals(28, st.add("//;\n1;2,10\n15"));
    }
    
    @Test
    public void TryExeption_Step4() {
        Exception exception = assertThrows(IllegalArgumentException.class,()-> st.add("//!!\n1!!2"));
    }
    
    }