package View;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        System.out.println("Application, developed by Valeria Daukshis");
        System.out.println("Enter your command, or enter 'help' to get help.");
        UserEnterPoint userEnterPoint = new UserEnterPoint();
        try {
            userEnterPoint.UserCommands();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
