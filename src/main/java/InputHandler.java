import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public int getInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number: ");
            if (scanner.hasNext()) {
                scanner.next();
            }
        }
        return scanner.nextInt();
    }
}