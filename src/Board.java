import java.util.ArrayList;
import java.util.List;

public class Board {
    private int grid;
    private String gamemode;
    private List<String> alphabet = new ArrayList<String>();
    private List <String> cords = new ArrayList<String>();
    private List <String> notAllowBombs = new ArrayList<String>();


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

    public void setNotAllowBombs(String userInput) {
        String firstChar = userInput.substring(0, 1);
        int secondChar = Integer.parseInt(userInput.substring(1));
        if (firstChar.equals(alphabet.getFirst())) {
            if (secondChar == 1) {
                for (int i = 1;i < 5; i++) {
                    String add = "";
                    if (i < 3) {
                        add +=  alphabet.getFirst() + i;
                    } else {
                        add += alphabet.get(1) + (i - 2);
                    }
                    notAllowBombs.add(add);
                }
            } else if (secondChar == grid) {
                for (int i = grid; i > grid - 4; i--) {
                    String add = "";
                    if (i < 3) {
                        add +=  alphabet.getFirst() + i;
                    } else {
                        add += alphabet.get(1) + (i + 2);
                    }
                    notAllowBombs.add(add);
                }
            } else {
                int num = secondChar - 1;
                for (int i = 0; i < 6; i++) {
                    String add = "";
                    if (i < 3) {
                        add = alphabet.getFirst() + (num + i);
                    } else {
                        add = alphabet.get(1) + (num + i - 3);
                    }
                    notAllowBombs.add(add);
                }
            }
        }
        else if (firstChar.equals(alphabet.getLast())) {
            
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




