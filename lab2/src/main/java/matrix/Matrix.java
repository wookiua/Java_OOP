package matrix;

import java.util.Arrays;

public class Matrix {
 
    private double[][] _data = null;
    private int _x=0, _y=0;

    // Step 1,2 ----------------

    public Matrix(int x, int y) {
        this._x = x;
        this._y = y;
        _data = new double [this._x][this._y];
    }

    public Matrix() {
        this._x = 0;
        this._y = 0;
        _data = new double [this._x][this._y];
    }    

    public Matrix(Matrix m_other) {
        this._x = m_other._x;
        this._y = m_other._y;        
        this._data = new double [this._x][this._y];
        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                this._data[i][j] = m_other._data[i][j];
    } 
    
    public void fillRandom(double min, double max) {   
        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                this._data[i][j] = min + Math.random()*(max-min);
    }

    public int GetSize() {
        return this._x * this._y;
    }

    public String Druc() {
        String output = "";
        if (this._x == 0 || this._y == 0) {
            output = "[]\n";
            return output;
        }
        for (int j=0; j<this._y; j++) {
            output += "[ ";
            for (int i=0; i<this._x; i++) {
                output += String.format( "%.1f", this._data[i][j] )+" ";
            }
            output +="]\n";
        }
        return output;
    }

    // Step 3 ----------------

    public void fillCell (int x, int y, double val) {
        if (x <=0 || x > this._x || y <= 0 || y > this._y ) {
            throw new IllegalArgumentException("Row or Column numbers are wrong for this Matrix");
        }
        this._data[x-1][y-1] = val;
    }    

    public void fillX (int y, double... val) {
        if ( val.length != this._x ) {
            throw new IllegalArgumentException("Row (X) count values is wrong for this Matrix");
        } 
        for (int i = 0; i < val.length; i++){
            this.fillCell(i+1, y, val[i]);
        }
    }

    public void fillY (int x, double... val) {
        if ( val.length != this._y ) {
            throw new IllegalArgumentException("Column (Y) count values is wrong for this Matrix");
        } 
        for (int i = 0; i < val.length; i++){
            this.fillCell(x, i+1, val[i]);
        }
    }    


    // Step 4 ----------------
    
    public double getCell (int x, int y) {
        if (x <=0 || x > this._x || y <= 0 || y > this._y ) {
            throw new IllegalArgumentException("Row or Column numbers are wrong for this Matrix");
        }
        return this._data[x-1][y-1];
    } 

    public double[] getX (int y) {
        double[] x_data = new double[this._x];
        for (int i = 0; i < this._x; i++){
            x_data[i] = this.getCell(i+1, y);
        }
        return x_data;
    }

    public double[] getY (int x) {
        double[] y_data = new double[this._y];
        for (int i = 0; i < this._y; i++){
            y_data[i] = this.getCell(x, i+1);
        }
        return y_data;
    }

    // Step 5 ----------------

    public String Rozm() {
        return String.valueOf(_x) + " x " + String.valueOf(_y) + "\n";
    }

    // Step 6 ----------------

    public int getHash() {
        return Arrays.deepHashCode(this._data);
    }

    public boolean isEqual(Matrix m) {
        if ( this == m || this.getHash() == m.getHash()) {
            return true;
        }
        return false;
    } 

    // Step 7 ----------------    
 
    public int XSize () {
        return this._x;
    }

    public int YSize () {
        return this._y;
    } 

    // Step 8 ----------------    

    public Matrix plus(Matrix m_other) {
        if (!this.Rozm().equals(m_other.Rozm())) {
            throw new IllegalArgumentException("Can't add Matrices with differ sizes");
        }
        Matrix m_plus = new Matrix(this._x, this._y); 
        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                m_plus.fillCell(i+1, j+1, this._data[i][j] + m_other.getCell(i+1, j+1));
        return m_plus;
    }    

    public Matrix mulScalar(double k) {
        Matrix m_mulScalar = new Matrix(this._x, this._y); 
        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                m_mulScalar.fillCell(i+1, j+1, this._data[i][j] * k);
        return m_mulScalar;
    }    

    // Step 9 ----------------    

    public void addCell (int x, int y, double val) {
        if (x <=0 || x > this._x || y <= 0 || y > this._y ) {
            throw new IllegalArgumentException("Row or Column numbers are wrong for this Matrix");
        }
        this._data[x-1][y-1] += val;
    }  

    public Matrix mul(Matrix m_other) {

        if (this.XSize() != m_other.YSize()) {
            throw new IllegalArgumentException("Can't multiply Matrices A*B where A(X) != B(Y)");
        }
        Matrix m_plus = new Matrix(m_other.XSize(), this.YSize()); 

        for (int i=0; i<m_other.XSize(); i++) 

            for (int j=0; j<this.YSize(); j++) 

                for (int ii=0; ii<this.XSize(); ii++) 
                    m_plus.addCell(i+1, j+1, this.getCell(ii+1, j+1) * m_other.getCell(i+1, ii+1));
        return m_plus;
    }

    // Step 10 ----------------    

    public Matrix transponse() {
        Matrix m_trans = new Matrix(this.YSize(), this.XSize()); 
        for (int i=0; i<this.YSize(); i++) 
            for (int j=0; j<this.XSize(); j++) 
                m_trans.addCell(i+1, j+1, this.getCell(j+1, i+1));
        return m_trans;
    }

    // Step 11 ----------------    

    public Matrix (double... vec) {
        if (vec.length == 0) {
            throw new IllegalArgumentException("Can't create zerro matrix");
        }
        this._x = vec.length;
        this._y = vec.length;        
        this._data = new double [this._x][this._y];
        for (int i=0; i<vec.length; i++) 
            for (int j=0; j<vec.length; j++) 
                if ( i == j ) this._data[i][j] = vec[i];
    }    

    // Step 12 ----------------    

    public Matrix (int m_rozmirn) {
        if (m_rozmirn == 0) {
            throw new IllegalArgumentException("Can't create zerro matrix");
        }
        this._x = m_rozmirn;
        this._y = m_rozmirn;         
        this._data = new double [this._x][this._y];
        for (int i=0; i<m_rozmirn; i++) 
            for (int j=0; j<m_rozmirn; j++) 
                if ( i == j ) this._data[i][j] = 1.0;
 
    }   
    
    // Step 13,14 ----------------    

    public Matrix createRandomX(int x_size, int from, int to) {
        if (x_size == 0) {
            throw new IllegalArgumentException("Can't create zerro matrix");
        }
        this._x = x_size;
        this._y = 1;
        this._data = new double [this._x][this._y];
        for (int i=0; i<x_size; i++) 
            if (from < to) this._data[i][0] = Math.random()*(to-from)+from;
                else this._data[i][0] = Math.random()*(from-to)+to;
        return this;
    }     
    
    public Matrix createRandomY(int y_size, int from, int to) {
        if (y_size == 0) {
            throw new IllegalArgumentException("Can't create zerro matrix");
        }
        this._x = 1;
        this._y = y_size;
        this._data = new double [this._x][this._y];
        for (int j=0; j<y_size; j++) 
            if (from < to) this._data[0][j] = Math.random()*(to-from)+from;
                else this._data[0][j] = Math.random()*(from-to)+to;
        return this;
    }    
    
    // Step 15 ----------------    

    public int getSquareSize() {
        if (this._x != this._y || this._x == 0 || this._y == 0) {
            return 0;
        }
        return this._x;
    }

    public Matrix modifyUpperTriang() {
        if (this.getSquareSize() == 0) {
            throw new IllegalArgumentException("Matrix not square or zerro. Can't change to upper triangular.");
        }

        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                if ( i < j ) this._data[i][j] = 0;
        return this;
    } 
    
    public Matrix modifyLowerTriang() {
        if (this.getSquareSize() == 0) {
            throw new IllegalArgumentException("Matrix not square or zerro. Can't change to lower triangular.");
        }

        for (int i=0; i<this._x; i++) 
            for (int j=0; j<this._y; j++) 
                if ( i > j ) this._data[i][j] = 0;
        return this;
    }     
    
    public static void main(String args[]){
        Matrix m = new Matrix(2, 3);
        Matrix m2 = new Matrix(3,2);
        
        m.fillRandom(10, 20);
        m2.fillRandom(10, 20);
        Matrix m3 = new Matrix(m2);
        //m.createRandomX(3, 01, 05);
       // Matrix m3 = im.mul(m);
        System.out.println(m.Druc());
        System.out.println(m2.Druc());
        System.out.println(m3.Druc());
    }



};