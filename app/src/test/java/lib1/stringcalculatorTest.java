package src.test.java.lib1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import src.main.java.lib1.stringcalculator;

public class stringcalculatorTest {

    stringcalculator st = new stringcalculator();

   
    @Test
    public void testStep1() {
        assertEquals(0, st.add(""));
        assertEquals(1, st.add("1"));      
        assertEquals(9, st.add("9"));            
        assertEquals(16, st.add("11,5")); 
        assertEquals(36, st.add("11,5,8,12"));
    }

}