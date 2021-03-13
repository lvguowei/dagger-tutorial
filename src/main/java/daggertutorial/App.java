package daggertutorial;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandProcessor processor = DaggerCommandProcessorFactory.create().processor();

        while (scanner.hasNextLine()) {
            processor.process(scanner.nextLine());
        }
    }
}
