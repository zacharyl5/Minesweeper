import java.util.ArrayList;
import java.util.List;

public class Bombs {
    private List<String> bombsLocation = new ArrayList<String>();
    private List<String> noBombSpace = new ArrayList<String>();
    private Board allCordList;
    private List<String> usedAlphabetList;
    private String firstCord;
    private String yCord;
    private int xCord;

    public Bombs(Board allCord, Board alphabet, String firstChosenCord) {
        allCordList = allCord;
        usedAlphabetList = alphabet;
        firstCord = firstChosenCord;
    }

    private void findXAndYCord() {
        yCord = firstCord.substring(0, 1);
        xCord = Integer.parseInt(firstCord.substring(1, 2));
    }

    private void setNoBombSpace() {
    }

    public void createBombs(String firstCord) {
        String yCord = firstCord.substring(0, 1);
        int xCord = Integer.parseInt(firstCord.substring(1, 2));

    }
}
