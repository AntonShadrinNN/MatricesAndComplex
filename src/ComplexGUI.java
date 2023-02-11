import java.io.IOException;
import java.util.Scanner;

/*
    Graphical console interface for working with complex numbers
    Contains the following methods:
        initialize(Scanner scanner) - The main method for activating the GUI
 */
public class ComplexGUI {
    /*
        A method for parsing a complex number
     */
    private static ComplexNumber parse(String s) throws IOException {
        return MatrixGUI.getComplexNumber(s);
    }

    /*
        A method for reading two complex numbers
     */
    private static ComplexNumber[] readComplex(Scanner scanner) throws IOException {
        ComplexNumber n1 = parse(scanner.nextLine());
        ComplexNumber n2 = parse(scanner.nextLine());
        return new ComplexNumber[] {n1, n2};
    }

    /*
        The main method that outputs information and calls the desired method
     */
    public static void initialize(Scanner scanner) throws IOException {
        ComplexNumber[] data;
        System.out.println("Choose operation:");
        System.out.println("0 Exit");
        System.out.println("1 Add");
        System.out.println("2 Sub");
        System.out.println("3 Mul");
        System.out.println("4 Div");
        while (true){
            int operation = 0;
            try {
                operation = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Wrong number format!");
            }
            try {
                switch (operation) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        System.out.println("Write two numbers");
                        data = readComplex(scanner);
                        System.out.println(ComplexNumber.add(data[0], data[1]));
                    }
                    case 2 -> {
                        System.out.println("Write two numbers");
                        data = readComplex(scanner);
                        System.out.println(ComplexNumber.sub(data[0], data[1]));
                    }
                    case 3 -> {
                        System.out.println("Write two numbers");
                        data = readComplex(scanner);
                        System.out.println(ComplexNumber.mul(data[0], data[1]));
                    }
                    case 4 -> {
                        System.out.println("Write two numbers");
                        data = readComplex(scanner);
                        System.out.println(ComplexNumber.div(data[0], data[1]));
                    }
                    default -> {System.out.println("Unhandled operation");}
                }
            }
            catch (IOException | NumberFormatException e){
                System.out.println("Wrong input format!");
            }
        }
    }
}
