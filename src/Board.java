import java.util.ArrayList;
import java.util.List;


public class Board {
    private int grid;
    private List<String> alphabet = new ArrayList<String>();
    private List <String> cords = new ArrayList<String>();
    private List <String> cordsBomb = new ArrayList<String>();
    private List <String> userCords = new ArrayList<String>();
    private List <String> notAllowBombs = new ArrayList<String>();
    private int numBombs;

    public Board(String gamemode) {
        if (gamemode.equalsIgnoreCase(("easy"))) {
            grid = 8;
            numBombs = 15;
        }
        if (gamemode.equalsIgnoreCase(("medium"))) {
            grid = 12;
            numBombs = 30;
        }
        if (gamemode.equalsIgnoreCase(("hard"))) {
            grid = 16;
            numBombs = 50;
        }
    }

    public void createBoard() {
        createAlphabet();
        createCord();
        printStartingBoard();
    }

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

    public boolean addUserCords(String input) {
        userCords.add(input);
        if (cordsBomb.contains(input)) {
            return true;
        }
        return false;
    }

    private void printStartingBoard() {
        String character = "███";
        for (int i = 0; i < grid + 1; i++) {
            if (i < grid) {
                System.out.print("  ");
                for (int j = 0; j <= grid * 4; j++) {
                    System.out.print("-");
                }
                System.out.print("\n" + alphabet.get(i) + " " + "|");

                for (int x = 0; x < grid; x++) {
                    System.out.print("" + character + "|");
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

//    private void revealBoard() {
//        for ()
//    }

    private void createAlphabet() {
        for (char i = 65 - 1; i <= 65 + grid - 1; i++) {
            String letter = i + "";
            alphabet.addFirst(letter);
        }
    }

    // Create the coordinate for each square
    private void createCord() {
        for (int i = 0; i < alphabet.size(); i++) {
            for (int j = 1; j <= grid; j++) {
                cords.add(alphabet.get(i) + j);
            }
        }
    }

    // Create the location of every bomb
    public void createBomb() {
        int bombsAdded = 1;
        while (bombsAdded <= numBombs) {
            int firstIdxChar = (int) (Math.random() * (grid + 1));
            int secondChar = (int) (Math.random() * (grid + 1));
            String add = alphabet.get(firstIdxChar) + secondChar;
            if (!cordsBomb.contains(add) && !notAllowBombs.contains(add)) {
                cordsBomb.add(add);
                bombsAdded++;
            }
        }
    }
}