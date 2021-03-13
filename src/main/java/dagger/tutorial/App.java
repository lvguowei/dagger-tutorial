package dagger.tutorial;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        CommandRouterFactory commandRouterFactory = DaggerCommandRouterFactory.create();
        CommandRouter commandRouter = commandRouterFactory.router();
        while (scanner.hasNextLine()) {
            commandRouter.route(scanner.nextLine());
        }
    }
}
