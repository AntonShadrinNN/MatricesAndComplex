import java.io.IOException;
import java.util.Scanner;

/*
    Graphical console interface for working with matrices
    Contains the following methods:
        initialize(Scanner scanner) - The main method for activating the GUI
*/
public class GUI {
    /*
        The main method that outputs information and calls the desired method
     */
    public static void initialize() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to work with");
        System.out.println("0 Exit");
        System.out.println("1 Complex Numbers");
        System.out.println("2 Matrix");
        while (true){
            int operation = 0;
            try {
                operation = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Wrong number format!");
            }
            switch (operation) {
                case 0 -> System.exit(0);
                case 1 -> ComplexGUI.initialize(scanner);
                case 2 -> MatrixGUI.initialize(scanner);
                default -> {System.out.println("Unhandled operation");}
            }
        }
    }
}
