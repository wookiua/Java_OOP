package matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import matrix.Matrix;

public class matrixTest {

   

    @Test
    public void step1EmptyMatrix() {
        Matrix m = new Matrix();
        assertEquals(0, m.GetSize());
    }    

    @Test
    public void step1CreateMatrix() {
        Matrix m = new Matrix(5,3);
        assertEquals(15, m.GetSize());
    }   
    
    @Test
    public void step1CopyMatrix() {
        Matrix m1 = new Matrix(5,3);
        Matrix m2 = new Matrix(m1);
        assertEquals(15, m2.GetSize());
    }    
    
    @Test
    public void step1DrucEmpty() {
        Matrix m1 = new Matrix(0,0);
        assertEquals("[]\n", m1.Druc());
    }  

    @Test
    public void step1Druc2x2() {
        Matrix m1 = new Matrix(2,2);
        assertEquals("[ 0.0 0.0 ]\n[ 0.0 0.0 ]\n", m1.Druc());
    }  

    // Step 3 ----------------

    @Test
    public void step2WrongFillCell() {
        Matrix m1 = new Matrix(2,2);
        Exception exception = assertThrows(IllegalArgumentException.class,()-> m1.fillCell(-1, 1, 1.5));
    }      

    @Test
    public void step2CorrectFillCell() {
        Matrix m1 = new Matrix(2,2);
        m1.fillCell(1, 1, 1.5);
        assertEquals("[ 1.5 0.0 ]\n[ 0.0 0.0 ]\n",m1.Druc());
    }  

    @Test
    public void step2FillRow() {
        Matrix m1 = new Matrix(4,2);
        m1.fillX(1, 1.1, 1.2, 1.3, 1.4);
        assertEquals("[ 1.1 1.2 1.3 1.4 ]\n[ 0.0 0.0 0.0 0.0 ]\n",m1.Druc());
    }  
    
    @Test
    public void step2FillColumn() {
        Matrix m1 = new Matrix(4,2);
        m1.fillY(1, 2.1, 2.2);
        assertEquals("[ 2.1 0.0 0.0 0.0 ]\n[ 2.2 0.0 0.0 0.0 ]\n",m1.Druc());
    }  

    // Step 4 ----------------

    @Test
    public void step3GetCell() {
        Matrix m1 = new Matrix(4,2);
        m1.fillCell(2, 1, 2.1);
        assertEquals(2.1,m1.getCell(2, 1));
    }  

    @Test
    public void step3GetX() {
        Matrix m1 = new Matrix(4,2);
        m1.fillCell(2, 1, 2.1);
        double[] reference = new double [] {0.0, 2.1, 0.0, 0.0}; 
        assertArrayEquals(reference,m1.getX(1));
    }  

    @Test
    public void step3GetY() {
        Matrix m1 = new Matrix(4,2);
        m1.fillCell(2, 1, 2.1);
        double[] reference = new double [] {2.1, 0.0}; 
        assertArrayEquals(reference,m1.getY(2));
    } 
    
    // Step 5 ----------------

    @Test
    public void Rozm() {
        Matrix m1 = new Matrix(4,2);
        m1.fillY(1, 2.1, 2.2);
        assertEquals("4 x 2\n",m1.Rozm());
    } 

    // Step 6 ----------------

    @Test
    public void GetHash() {
        Matrix m1 = new Matrix(4,2);
        m1.fillY(1, 2.1, 2.2);
        assertEquals(-401279905,m1.getHash());
    } 

    @Test
    public void IsEqual() {
        Matrix m1 = new Matrix(4,2);
        m1.fillY(1, 2.1, 2.2);
        Matrix m2 = new Matrix(4,2);
        m2.fillY(1, 2.1, 2.2);
        assertEquals(true,m1.isEqual(m2));
    }

    // Step 7 ----------------

    @Test
    public void XSize() {
        Matrix m1 = new Matrix(4,2);
        assertEquals(4, m1.XSize());
    } 

    @Test
    public void YSize() {
        Matrix m1 = new Matrix(4,2);
        assertEquals(2, m1.YSize());
    } 

    @Test
    public void CreateImmutable() {
        Matrix m = new Matrix(4,2);
        IMatrix im = new IMatrix(m);
        assertEquals(m.getHash(), im.getHash());
    } 

    @Test
    public void ChangeImmutable() {
        IMatrix im = new IMatrix(4,2);
        Exception exception = assertThrows(IllegalArgumentException.class,()-> im.fillCell(1, 1, 1.5));
    } 

}