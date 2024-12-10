import java.util.Scanner;

public class Gameplay {

    private Scanner scan;
    private BoardLogic board;
    private MainMenu obj;
    private boolean queue = true;
    private boolean winStatus = false;
    private String cord;

    public Gameplay(Scanner scan) {
        this.scan = scan;
        obj = new MainMenu(scan);
    }

    public void startGame() {
        initiateGame();
        start();
        printEndMenu();
    }


    public void initiateGame() {
        String gameMode = obj.menu();
        if (gameMode.equals("default")) {
            board = new BoardLogic();
        } else {
            board = new BoardLogic(gameMode);
        }
        board.createBoard();
        System.out.println();

    }

    public void start() {
        System.out.println("Enter a coordinate you want to start at (ex: E5): ");
        cord = scan.nextLine().toUpperCase();
        while (!board.acceptableCord(cord)) {
            System.out.println("Try Again: ");
            cord = scan.nextLine();
        }

        // Initiate the board
        board.addUserCords(cord);
        board.setNotAllowBombs(cord);
        board.createBomb();
        board.checkSurrounding();
        board.revealBoard();
        board.CheckFor0(cord);
        board.CheckFor0();
        board.printBoard();

        while (queue) {
            System.out.println();
            System.out.println("Would you like to flag or select a space (select (s) or flag (f) or remove flag (r)): ");
            String flagOrSelect = scan.nextLine();

            // If statement for selecting a cord
            if (flagOrSelect.equalsIgnoreCase("s") || flagOrSelect.equalsIgnoreCase("select")) {
                System.out.println("Enter a coordinate to reveal (ex: E5) or exit: ");
                cord = scan.nextLine().toUpperCase();

                if (!cord.equalsIgnoreCase("exit")) {
                    checkActualCord();
                    if (!cord.equalsIgnoreCase("exit")) {
                        while (!cord.equalsIgnoreCase("exit") && board.checkRevealStatus(cord)) {
                            checkRevealStatus();
                            checkActualCord();
                        }
                    }

                    // Lose if condition
                    if (!cord.equalsIgnoreCase("exit")) {
                        board.addUserCords(cord);
                        if (board.checkCord(cord)) {
                            queue = false;
                        }
                    }
                }
            // If statement for selecting to make a flag at a certain cord
            } else if (flagOrSelect.equalsIgnoreCase("f") || flagOrSelect.equalsIgnoreCase("flag")) {
                System.out.println("Enter a coordinate you want to flag (ex: E5) or exit: ");
                cord = scan.nextLine().toUpperCase();

                if (!cord.equalsIgnoreCase("exit")) {
                    checkActualCord();
                    if (!cord.equalsIgnoreCase("exit")) {
                        while (!cord.equalsIgnoreCase("exit") && board.checkRevealStatus(cord)) {
                            checkRevealStatus();
                            checkActualCord();
                        }
                    }

                    if (!cord.equalsIgnoreCase("exit")) {
                        board.addUserCords(cord);
                        board.setFlag(cord);
                    }
                }
            // If statement for selecting to remove a flag
            } else if (flagOrSelect.equalsIgnoreCase("r") || flagOrSelect.equalsIgnoreCase("replace flag")) {
                System.out.println("Enter a coordinate you want to remove a flag (ex: E5) or exit: ");
                cord = scan.nextLine().toUpperCase();

                if (!cord.equalsIgnoreCase("exit")) {
                    checkActualCord();
                    if (!cord.equalsIgnoreCase("exit")) {
                        while (!cord.equalsIgnoreCase("exit") && !board.checkIfFlag(cord)) {
                            checkIfFlag();
                            checkActualCord();
                        }
                    }

                    if (!cord.equalsIgnoreCase("exit")) {
                        board.removeFlag(cord);
                    }
                }
            }

            // Checks if the cord surround bomb is equal to 0
            if (!cord.equalsIgnoreCase("exit") && board.checkCordVal(cord) == 0 && !flagOrSelect.equalsIgnoreCase("remove flag")) {
                board.CheckFor0(cord);
                board.CheckFor0();
            }

            // Reveal the board
            board.setRevealStatus();
            board.printBoard();

            // Checks for win
            if (board.checkWin()) {
                queue = false;
                winStatus = true;
            }
        }
    }

    // prints the end menus and reveal the board if they lost
    private void printEndMenu() {
        if (winStatus) {
            obj.winMenu();
        } else {
            obj.loseMenu();
            board.setAllRevealTrue();
            board.printBoard();
        }
    }

    // Check if the cord is an actual cord or if they want to exit
    private void checkActualCord() {
        boolean repeat = true;
        while (repeat && !board.acceptableCord(this.cord)) {
            System.out.println("Try Again: ");
            this.cord = scan.nextLine().toUpperCase();
            if (this.cord.equalsIgnoreCase("exit")) {
                repeat = false;
            }
        }
    }

    // Check if the cord is already revealed
    private void checkRevealStatus() {
        boolean repeat = true;
        while (repeat && board.checkRevealStatus(this.cord)) {
            System.out.println("Try Again: ");
            this.cord = scan.nextLine().toUpperCase();
            checkActualCord();
            if (this.cord.equalsIgnoreCase("exit")) {
                repeat = false;
            }
        }
    }

    // Check if the cord has a flag or not
    private void checkIfFlag() {
        boolean repeat = true;
        while (repeat && !board.checkIfFlag(this.cord)) {
            System.out.println("Try Again: ");
            this.cord = scan.nextLine().toUpperCase();
            checkActualCord();
            if (this.cord.equalsIgnoreCase("exit")) {
                repeat = false;
            }
        }
    }
}