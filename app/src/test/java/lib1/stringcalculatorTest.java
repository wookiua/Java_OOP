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
   public void TryStep2() {
        assertEquals(46, st.add("11,5\n8,12\n10"));
    }

    @Test
    public void TryExeption() {
        Exception exception = assertThrows(IllegalArgumentException.class,()-> st.add("11,,5,\n6"));
    }

    }

