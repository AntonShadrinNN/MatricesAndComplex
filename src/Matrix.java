import java.util.Arrays;

/*
    A class for working with matrices of complex numbers
    Contains the following methods:
        Matrix(ComplexNumber[][] m) - Constructor
        getItem(int i, int j) - Getter-method
        add(Matrix a, Matrix b) - Addition of two matrices
        sub(Matrix a, Matrix b) - Subtracting two matrices
        transpose() - Matrix transposition
        mul(Matrix a, Matrix b) - Multiplication of two matrices
        determinant(Matrix m) - Search for the determinant of the matrix
 */
public class Matrix {
    int col, row;
    ComplexNumber[][] matrix;

    /*
         The constructor method
         Accepts a two-dimensional array of complex numbers
         col is the number of rows
         row - number of columns
     */
    public Matrix(ComplexNumber[][] m){
        this.col = m.length;
        this.row = m[0].length;
        this.matrix = m;
    }

    /*
        Getter method
        Accepts i - row and j - column indexes
        Causes IndexOutOfBoundsException exception when going out of bounds
     */
    public ComplexNumber getItem(int i, int j){
        if ((i > col || i < 0) || (j > row || j < 0)){
            throw new IndexOutOfBoundsException("No such element");
        }
        return this.matrix[i][j];
    }

    /*
        A private method for adding or subtracting two matrices
        If the dimensions do not match, it causes ArithmeticException
        Returns a new object of the Matrix class
     */
    private static Matrix operate(Matrix a, Matrix b, int n){
        if ((a.row != b.row) || (a.col != b.col)){
            throw new ArithmeticException(
                    String.format("Impossible to add/sub matrix (%d,%d) to matrix (%d,%d)", a.col, a.row, b.col, b.row));
        }
        ComplexNumber[][] m = new ComplexNumber[a.col][a.row];
        for (int i = 0; i < a.col; i++){
            for (int j = 0; j < a.row; j ++) {
                if (n == 0){
                    m[i][j] = ComplexNumber.add(a.getItem(i, j), b.getItem(i, j));
                }
                else{
                    m[i][j] = ComplexNumber.sub(a.getItem(i, j), b.getItem(i, j));
                }
            }
        }
        return new Matrix(m);
    }

    /*
        A method for adding two matrices
        Accepts two objects of the Matrix class
        Returns a new object of the Matrix class
        If the dimensions do not match, it causes an ArithmeticException
     */
    public static Matrix add(Matrix a, Matrix b){
        return Matrix.operate(a, b, 0);
    }

    /*
        A method for subtracting two matrices
        Accepts two objects of the Matrix class
        Returns a new object of the Matrix class
        If the dimensions do not match, it causes an ArithmeticException
     */
    public static Matrix sub(Matrix a, Matrix b){
        return Matrix.operate(a, b, 1);
    }

    /*
        A method for transposing a matrix
        Mutate current object of the Matrix class
     */
    public void transpose(){
        ComplexNumber[][] temp = new ComplexNumber[this.col][this.row];
        for (int i = 0; i < this.col; i++){
            for (int j = 0; j < this.row; j ++){
                temp[j][i] = this.matrix[i][j];
            }
        }
        this.matrix = temp;
    }

    /*
        A method for obtaining a value in a specific cell when multiplying matrices
     */
    private static ComplexNumber multiplySinglePart(Matrix a, Matrix b, int row, int column){
        ComplexNumber cell = new ComplexNumber(0, 0);
        for (int i = 0; i < b.col; i++) {
            cell = ComplexNumber.add(cell, ComplexNumber.mul(a.getItem(row, i), b.getItem(i, column)));
        }
        return cell;
    }

    /*
        A method for multiplying two matrices
        Accepts two objects of the Matrix class
        Returns a new object of the Matrix class
        If the number of rows of the first matrix does not match with the number of columns of the second matrix,
        it causes an ArithmeticException
     */
    public static Matrix mul(Matrix a, Matrix b){
        if (a.row != b.col){
            throw new ArithmeticException("Columns of A must be equal to rows of B");
        }
        ComplexNumber[][] result = new ComplexNumber[a.col][b.row];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplySinglePart(a, b, row, col);
            }
        }

        return new Matrix(result);
    }

    /*
        A method for creating a smaller matrix when searching for a determinant
     */
    private static Matrix subMatrix(Matrix matrix, int col) {
        ComplexNumber[][] res = new ComplexNumber[matrix.col - 1][matrix.row - 1];
        for (int i = 0; i < res.length; i++){
            int j = 0;
            int ind = 0;
            while (j < matrix.col) {
                if (j == col){
                    j ++;
                    continue;
                }
                res[i][ind] = matrix.getItem(i + 1 , j);
                j++;
                ind++;
            }
        }
        return new Matrix(res);
    }

    /*
        A method for searching for a determinant
        Accepts an object of the Matrix class
        Returns an object of the ComplexNumber class
        If the matrix is not square, it causes an ArithmeticException
     */
    public static ComplexNumber determinant(Matrix m) throws ArithmeticException{
        if (m.col != m.row) {
            throw new ArithmeticException("Only squared matrix have determinant");
        }
        if (m.col == 1) {
            return m.getItem(0, 0);
        } else if (m.col == 2) {
            return ComplexNumber.sub(ComplexNumber.mul(m.getItem(0, 0), m.getItem(1, 1)),
                   ComplexNumber.mul(m.getItem(0, 1), m.getItem(1, 0)));
        } else {
            ComplexNumber res = new ComplexNumber(0, 0);

            for (int col = 0; col < m.col; col++){
                if (col % 2 == 0) {
                    res = ComplexNumber.add(res, ComplexNumber.mul(m.matrix[0][col], determinant(Matrix.subMatrix(m, col))));
                } else {
                    res = ComplexNumber.sub(res, ComplexNumber.mul(m.matrix[0][col], determinant(Matrix.subMatrix(m, col))));
                }
            }
            return res;
        }
    }


    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.col; i++){
            str.append(String.format("%s\n", Arrays.toString(matrix[i])));
        }
        return str.toString();
    }
}
