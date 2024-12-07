import java.util.Scanner;

public class MinesweeperRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MainMenu obj = new MainMenu();
        Board board;
        String gamemode = obj.menu();
        board = new Board(gamemode);
        board.createBoard();
        System.out.println("Enter a coordinate you want to start at (ex: E5): ");
        String cord = scan.nextLine();

        board.addUserCords(cord);
        board.setNotAllowBombs(cord);
        board.createBomb();


        while (obj.getQ()) {
            System.out.println("Would you like to flag or select a space (flag or select): ");
            String flagOrSelect = scan.nextLine();

            if (flagOrSelect.equalsIgnoreCase("select")) {
                System.out.println("Enter a coordinate you want to start at (ex: E5): ");
                cord = scan.nextLine();
            }


        }

        System.out.println("\n" + "Thanks for playing minesweeper");


    }
}
