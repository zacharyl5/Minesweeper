import java.util.Scanner;

public class MinesweeperRunner {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        MainMenu obj = new MainMenu();

        while (obj.q()) {
            obj.Menu();

        }

        System.out.println("\n" + "Thanks for playing mindsweeper, its a great game i know ");


    }
}
