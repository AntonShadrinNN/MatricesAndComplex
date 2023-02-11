import java.io.IOException;
import java.util.Scanner;

/*
    Graphical console interface for working with matrices
    Contains the following methods:
        initialize(Scanner scanner) - The main method for activating the GUI
 */
public class MatrixGUI {

    /*
        Generalization method for parsing
     */
    private static ComplexNumber parse(String s) throws IOException {
        return getComplexNumber(s);
    }
    /*
        A method for reading a complex number
        If the input format is incorrect, it causes a NumberFormatException
     */
    static ComplexNumber getComplexNumber(String s) {
        String[] parsed = s.split("[+]");
        double real, imag;
        if (parsed.length > 2 || parsed.length == 0) {
            throw new NumberFormatException("Wrong format");
        }
        if (parsed.length == 1) {
            return new ComplexNumber(Double.parseDouble(parsed[0]), 0);
        }
        try {
            real = Double.parseDouble(parsed[0]);
            imag = Double.parseDouble(parsed[1].substring(0, parsed[1].length() - 1));
        } catch (NumberFormatException n) {
            throw new NumberFormatException("Wrong format");
        }

        return new ComplexNumber(real, imag);
    }

    /*
        A method for reading the matrix from the keyboard
        If the input is incorrect, it causes an IOException
     */
    private static Matrix readMatrix(Scanner scanner) throws IOException {
        System.out.println("Write size of matrix");
        System.out.println("Format:");
        System.out.println("5 5");
        String[] nums = scanner.nextLine().split(" ");
        int m = Integer.parseInt(nums[0]);
        int n = Integer.parseInt(nums[1]);
        ComplexNumber[][] result = new ComplexNumber[m][n];
        for (int i = 0; i < m; i++){
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                result[i][j] = parse(line[j]);
            }
        }
        return new Matrix(result);
    }

    /*
        The main method that outputs information and calls the desired method
     */
    public static void initialize(Scanner scanner) throws IOException {
        Matrix m1, m2;
        Matrix m;
        System.out.println("Choose operation");
        System.out.println("0 Exit");
        System.out.println("1 Transpose");
        System.out.println("2 Add two matrix");
        System.out.println("3 Sub two matrix");
        System.out.println("4 Mul two matrix");
        System.out.println("5 Find determinant");

        System.out.println("\nMatrix should be in form such");
        System.out.println("1+2j 3+5j");
        System.out.println("4+6j 7+8j");
        while (true){
            int operation = 0;
            try {
                operation = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Wrong number format!");
            }
            switch (operation){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    try {
                        m = readMatrix(scanner);
                    }
                    catch (IOException | NumberFormatException e){
                        System.out.println("Wrong input format");
                        return;
                    }
                    try {
                        m.transpose();
                    }
                    catch (ArithmeticException e){
                        System.out.println("Matrix is not squared");
                        return;
                    }
                    System.out.println(m);
                }
                case 5 -> {
                    try {
                        m = readMatrix(scanner);
                    }
                    catch (IOException | NumberFormatException e){
                        System.out.println("Wrong input format");
                        return;
                    }
                    try {
                        System.out.println(Matrix.determinant(m));
                    }
                    catch (ArithmeticException e){
                        System.out.println("Matrix is not squared");
                        return;
                    }
                }
                case 2 -> {
                    try {
                        m1 = readMatrix(scanner);
                        m2 = readMatrix(scanner);
                    }
                    catch (IOException | NumberFormatException e){
                        System.out.println("Wrong input format");
                        return;
                    }
                    try {
                        System.out.println(Matrix.add(m1, m2));
                    }
                    catch (ArithmeticException e) {
                        System.out.println("Size of matrices isn't equal");
                        return;
                    }
                }
                case 3 -> {
                    try {
                        m1 = readMatrix(scanner);
                        m2 = readMatrix(scanner);
                    }
                    catch (IOException | NumberFormatException e){
                        System.out.println("Wrong input format");
                        return;
                    }
                    try {
                        System.out.println(Matrix.sub(m1, m2));
                    }
                    catch (ArithmeticException e) {
                        System.out.println("Size of matrices isn't equal");
                        return;
                    }
                }
                case 4 -> {
                    try {
                        m1 = readMatrix(scanner);
                        m2 = readMatrix(scanner);
                    }
                    catch (IOException | NumberFormatException e){
                        System.out.println("Wrong input format");
                        return;
                    }
                    try {
                        System.out.println(Matrix.mul(m1, m2));
                    }
                    catch (ArithmeticException e){
                        System.out.println("Columns in a != rows in b");
                        return;
                    }
                }
                default -> {System.out.println("Unhandled operation");}
            }
        }
    }
}
