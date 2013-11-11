package specboard.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TabPane;
import specboard.ui.BoardPage;
import specboard.ui.SoundCell;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jaakko
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    private static Pattern commandPattern = Pattern.compile("([a-zA-Z]) *(.+)?");

    private TabPane pagesPane;

    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
    }

    public void setPagesPane(TabPane pagesPane) {
        this.pagesPane = pagesPane;
    }

    public boolean runCommand(String command) {
        Matcher matcher = commandPattern.matcher(command);

        if (matcher.find()) {
            BoardPage currentPage = (BoardPage) pagesPane.getSelectionModel().getSelectedItem();

            String action = matcher.group(1);
            String argString = matcher.group(2);

            if (action.equals("g")) {
                String[] args = argString.split(",");

                int rows = parseInt(args[0]),
                    cols = parseInt(args[1]);

                currentPage.setGridSize(rows, cols);
            } else if (action.equals("l")) {
                String[] args = argString.split(",");

                int row = parseInt(args[0]),
                    col = parseInt(args[1]);

                SoundCell soundCell = currentPage.getSoundCell(row, col);
                if (soundCell != null && soundCell.getTargetSound().isLoaded()) {
                    soundCell.getTargetSound().toggleLoop();
                }
            } else if (action.equals("p")) {
                BoardPage bp = new BoardPage(argString, 4, 4);
                pagesPane.getTabs().add(bp);
            }

            return true;
        }

        return false;
    }

    private int parseInt(String s) {
        return Integer.parseInt(s.trim());
    }
}
