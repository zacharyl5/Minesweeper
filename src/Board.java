import java.util.ArrayList;
import java.util.List;

public class Board {
    private int grid;
    private String gamemode;
    private List<String> alphabet = new ArrayList<String>();
    private List <String> cords = new ArrayList<String>();


    public Board(String gamemode) {
        if (gamemode.equalsIgnoreCase(("easy"))) {
            grid = 8;
        }
        if (gamemode.equalsIgnoreCase(("medium"))) {
            grid = 12;
        }
        if (gamemode.equalsIgnoreCase(("hard"))) {
            grid = 16;
        }
    }

    public void createBoard() {
        createAlphabet();
        createCord();
        printBoard();
    }

    private void printBoard() {
        for (int i = 0; i < grid + 1; i++) {
            if (i < grid) {
                System.out.print("  ");
                for (int j = 0; j <= grid * 4; j++) {
                    System.out.print("-");
                }
                System.out.print("\n" + alphabet.get(i) + " " + "|");

                for (int x = 0; x < grid; x++) {
                    System.out.print("   |");
                }
                System.out.println();
            } else {
                System.out.print("  ");
                for (int j = 0; j <= grid * 4; j++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.print("    ");
                for (int l = 1; l < i + 1; l++) {
                    if (l <= 9) {
                        System.out.print(l + "   ");
                    } else {
                        System.out.print(l + "  ");
                    }
                }
            }
        }
    }

    private void createAlphabet() {
        for (char i = 65; i <= 65 + grid; i++) {
            String letter = i + "";
            alphabet.addFirst(letter);
        }
    }

    private void createCord() {
        for (int i = 0; i < alphabet.size(); i++) {
            for (int j = 1; j <= grid; j++) {
                cords.add(alphabet.get(i) + j);
            }
        }
    }

}




