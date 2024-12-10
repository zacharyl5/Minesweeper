import java.util.Scanner;

public class MinesweeperRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Gameplay game = new Gameplay(scan);
        boolean queue = true;
        while (queue) {
            game.startGame();
            System.out.print("\nWould you like to play another game of Minesweeper y/n: ");
            String response = scan.nextLine();
            System.out.println();
            if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
                queue = false;
                System.out.println("Thanks for playing minesweeper");
                continue;
            }
            game = new Gameplay(scan);
        }
    }
}
