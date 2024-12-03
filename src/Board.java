import java.util.ArrayList;
import java.util.List;

/**
 * This class is to create the board along with its difficulty
 *
 * @author Zachary Lin and Leo Zheng
 */
public class Board {
    /** The grid size; how many tiles each row/column is*/
    private int grid;
    /** The alphabet is the y axis of the board*/
    private List<String> alphabet = new ArrayList<String>();
    /** all possible cords of the board*/
    private List <String> cords = new ArrayList<String>();
    /** notAllowBombs is the set of cords that a bomb cannot spawn at*/
    private List <String> notAllowBombs = new ArrayList<String>();

    /**
     * Instantiates a board object
     *
     * @param gamemode The difficulty of the board
     */
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

    /**
     * sequence that creates a board
     */
    public void createBoard() {
        createAlphabet();
        createCord();
        printBoard();
    }

    /**
     * Makes an area where the bombs cannot spawn in
     *
     * @param userInput the user's input
     */
    public void setNotAllowBombs(String userInput) {
        int firstIdx = alphabet.indexOf(userInput.substring(0, 1)) - 1;
        int secondChar = Integer.parseInt(userInput.substring(1)) - 1;

        for (int i = 0; i < 9; i++) {
            String add = "";
            if (firstIdx >= 0 && secondChar > 0 && i < 3) {
                add = alphabet.get(firstIdx - 1) + (secondChar + i);
            } else if (firstIdx >= 0 && secondChar > 0 && i < 6) {
                add = alphabet.get(firstIdx) + (secondChar + i - 3);
            } else if (firstIdx >= 0 && secondChar > 0) {
                add = alphabet.get(firstIdx + 1) + (secondChar + i - 6);
            }
            if (!add.isEmpty()) {
                notAllowBombs.add(add);
                add = "";
            }
        }
    }

    /**
     * prints the board so the user can see it
     */
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

    /**
     * creates each unit of the y-axis of the board
     */
    private void createAlphabet() {
        for (char i = 65; i <= 65 + grid; i++) {
            String letter = i + "";
            alphabet.addFirst(letter);
        }
    }

    /**
     * creates the x-axis, numbers
     */
    private void createCord() {
        for (int i = 0; i < alphabet.size(); i++) {
            for (int j = 1; j <= grid; j++) {
                cords.add(alphabet.get(i) + j);
            }
        }
    }

    /**
     * creates bombs
     */
    private void createBomb() {
        int bombsAdded = 0;
        while (bombsAdded != 100) {
            int firstIdxChar = (int) (Math.random() * (grid + 1));
            int secondChar = (int) (Math.random() * (grid + 1));
            String add = alphabet.get(firstIdxChar) + secondChar;
            if (!cords.contains(add) && !notAllowBombs.contains(add)) {
                cords.add(add);
                bombsAdded++;
            }
        }
    }
}