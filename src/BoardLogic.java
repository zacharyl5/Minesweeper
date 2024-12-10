import java.util.ArrayList;
import java.util.List;


public class BoardLogic {
    // Y axis
    private List<String> alphabet = new ArrayList<>();
    // All the possible cords for the grid
    private List <String> cords = new ArrayList<>();
    // The cords of all the bombs
    private List <String> cordsBomb = new ArrayList<>();
    // The cords of all inputted cords by user
    private List <String> userCords = new ArrayList<>();
    // The cords of spaces not allowed bombs
    private List <String> notAllowBombs = new ArrayList<>();
    // num of bombs surrounding each cords
    private List <Integer> numBombSurrounding = new ArrayList<>();
    // final of numBombSurrounding
    private final List <Integer> numBombSurrounding2 = new ArrayList<>();
    // reveal status of each cords
    private List <Boolean> revealStatus = new ArrayList<>();
    // all surrounding 0
    private List<String> surroundingOf0 = new ArrayList<String>();

    private int grid;
    private int numBombs;

    public BoardLogic(String gamemode) {
        if (gamemode.equalsIgnoreCase(("easy"))) {
            grid = 8;
            numBombs = 10;
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

    public BoardLogic() {
        grid = 10;
        numBombs = 15;
    }
    // initiate starting grid
    public void createBoard() {
        createAlphabet();
        createCord();
        printStartingBoard();
    }


    public void setNotAllowBombs(String userInput) {
        int firstIdx = alphabet.indexOf(userInput.substring(0, 1)) - 1;
        int secondChar = Integer.parseInt(userInput.substring(1)) - 1;

        for (int i = 0; i < 9; i++) {
            String add = getAdd(firstIdx, secondChar, i);
            if (!add.isEmpty()) {
                notAllowBombs.add(add);
            }
        }
    }

    //
    public void addUserCords(String input) {
        userCords.add(input);
    }


    // Starting hidden board
    public void printStartingBoard() {
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

    // Used for printing the board with the revealed spaces
    public void printBoard() {
        int iteration = 0;

        for (int i = 0; i < grid + 1; i++) {
            if (i < grid) {
                System.out.print("  ");
                for (int j = 0; j <= grid * 4; j++) {
                    System.out.print("-");
                }
                System.out.print("\n" + alphabet.get(i) + " " + "|");
                for (int x = 0; x < grid; x++) {
                    String character;
                    if (revealStatus.get(iteration)) {
                        if (numBombSurrounding.get(iteration) == 0) {
                            character = "   ";
                        } else if (numBombSurrounding.get(iteration) == -1) {
                            character = "\uD83D\uDCA3 ";
                        } else if (numBombSurrounding.get(iteration) == -2) {
                            character = "\uD83D\uDEA9 ";
                        } else {
                            character = " " + numBombSurrounding.get(iteration) + " ";
                        }
                    } else {
                        character = "███";
                    }
                    System.out.print(character + "|");
                    iteration++;
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

    // creates the y-axis
    public void createAlphabet() {
        for (char i = 65 - 1; i <= 65 + grid - 1; i++) {
            String letter = i + "";
            alphabet.addFirst(letter);
        }
    }

    // Create the coordinate for each square
    public void createCord() {
        for (String s : alphabet) {
            for (int j = 1; j <= grid; j++) {
                cords.add(s + j);
            }
        }
    }

    // Create the location of every bomb
    public void createBomb() {
        int bombsAdded = 0;
        while (bombsAdded < numBombs) {
            int firstIdxChar = (int) (Math.random() * (grid - 1)) + 2;
            int secondChar = (int) (Math.random() * (grid)) + 1;
            String add = alphabet.get(firstIdxChar - 1) + secondChar;
            if (!cordsBomb.contains(add) && !notAllowBombs.contains(add)) {
                cordsBomb.add(add);
                bombsAdded++;
            }
        }
    }

    // Checks surrounding for numbers of bombs
    public void checkSurrounding() {
        for (String s : cords) {
            List<String> surrounding = new ArrayList<String>();
            int firstIdx = alphabet.indexOf(s.substring(0, 1)) - 1;
            int secondChar = Integer.parseInt(s.substring(1)) - 1;
            if (!cordsBomb.contains(s)) {
                for (int j = 0; j < 9; j++) {
                    String add = getAdd(firstIdx, secondChar, j);
                    if (!add.isEmpty()) {
                        surrounding.add(add);
                    }
                }
                surrounding.remove(s);
            } else {
                numBombSurrounding.add(-1);
                numBombSurrounding2.add(-1);
                continue;
            }
            int count = 0;
            for (int j = 0; j < surrounding.size(); j++) {
                if (cordsBomb.contains(surrounding.get(j))) {
                    count++;
                }
            }
            numBombSurrounding.add(count);
            numBombSurrounding2.add(count);
        }
    }

    public void revealBoard() {
        for (String cord : cords) {
            if (userCords.contains(cord)) {
                revealStatus.add(true);
            } else {
                revealStatus.add(false);
            }
        }
    }

    public void CheckFor0(String cord) {
        int firstIdx = alphabet.indexOf(cord.substring(0, 1)) - 1;
        int secondChar = Integer.parseInt(cord.substring(1)) - 1;
        for (int j = 0; j < 9; j++) {
            String add = getAdd(firstIdx, secondChar, j);
            if (!add.isEmpty()) {
                int indexAdd = cords.indexOf(add);
                if (numBombSurrounding.get(indexAdd) == 0) {
                    surroundingOf0.add(add);
                }
            }
        }

        int surroundingSize = -1;
        while (surroundingOf0.size() != surroundingSize) {
            surroundingSize = surroundingOf0.size();
            for (int i = 0; i < surroundingSize; i++) {
                int firstIdx1 = alphabet.indexOf(surroundingOf0.get(i).substring(0, 1)) - 1;
                int secondChar1 = Integer.parseInt(surroundingOf0.get(i).substring(1)) - 1;
                for (int j = 0; j < 9; j++) {
                    String add = getAdd(firstIdx1, secondChar1, j);
                    if (!add.isEmpty()) {
                        int indexAdd = cords.indexOf(add);
                        if (numBombSurrounding.get(indexAdd) == 0 && !surroundingOf0.contains(add)) {
                            surroundingOf0.add(add);
                        }
                    }
                }
            }
        }

        for (String string : surroundingOf0) {
            if (!userCords.contains(string)) {
                userCords.add(string);
            }
        }
    }

    public void CheckFor0() {
        for (String cords : surroundingOf0) {
            int firstIdx = alphabet.indexOf(cords.substring(0, 1)) - 1;
            int secondChar = Integer.parseInt(cords.substring(1)) - 1;
            for (int i = 0; i < 9; i++) {
                String add = getAdd(firstIdx, secondChar, i);
                if (!add.isEmpty()) {
                    int idxAdd = this.cords.indexOf(add);
                    if (!revealStatus.get(idxAdd)) {
                        revealStatus.set(idxAdd, true);
                    }
                }
            }
        }
        surroundingOf0 = new ArrayList<>();
    }
    public void setRevealStatus() {
        for (String string : userCords) {
            int stringIdx = cords.indexOf(string);
            if (!revealStatus.get(stringIdx)) {
                revealStatus.set(stringIdx, true);
            }
        }
    }

    public void setFlag(String cord) {
        int flagIdx = cords.indexOf(cord);
        numBombSurrounding.set(flagIdx, -2);
    }

    public void removeFlag(String cord) {
        int flagIdx = cords.indexOf(cord);
        int num = numBombSurrounding2.get(flagIdx);
        revealStatus.set(flagIdx, false);
        userCords.remove(cord);
        numBombSurrounding.set(flagIdx, num);
    }


    public boolean checkCord(String cord) {
        if (cordsBomb.contains(cord)) {
            return true;
        }
        return false;
    }

    public int checkCordVal(String cord) {
        int idx = cords.indexOf(cord);
        return numBombSurrounding2.get(idx);
    }

    public boolean checkRevealStatus(String cord) {
        int idx = cords.indexOf(cord);
        return revealStatus.get(idx);
    }

    public boolean checkWin() {
        int count = 0;
        for (boolean status : revealStatus) {
            if (status) {
                count++;
            }
        }
        return count == (int) Math.pow(grid, 2);
    }

    public boolean checkIfFlag(String cord) {
        int idx = cords.indexOf(cord);
        if (numBombSurrounding.get(idx) == -2) {
            return true;
        }
        return false;
    }

    public boolean acceptableCord(String cord) {
        return cords.contains(cord);
    }

    public void setAllRevealTrue() {
        numBombSurrounding = numBombSurrounding2;
        for (int i = 0; i < revealStatus.size(); i++) {
            revealStatus.set(i, true);
        }
    }

    private String getAdd(int firstIdx, int secondChar, int j) {
        String add = "";
        if (firstIdx >= 0 && secondChar + j > 0 && secondChar + j < grid + 1 && j < 3) {
            add = alphabet.get(firstIdx) + (secondChar + j);
        } else if (secondChar + j - 3 > 0 && secondChar + j - 3 <= grid && j < 6 && j > 2) {
            add = alphabet.get(firstIdx + 1) + (secondChar + j - 3);
        } else if (firstIdx + 2 < grid && secondChar + j - 6 > 0 && secondChar + j - 6 <= grid && j > 5) {
            add = alphabet.get(firstIdx + 2) + (secondChar + j - 6);
        }
        return add;
    }
}