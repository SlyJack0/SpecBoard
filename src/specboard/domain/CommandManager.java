package specboard.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import specboard.ui.BoardPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jaakko
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    private static Pattern commandPattern = Pattern.compile("(\\d+) *, *(\\d+) *([a-zA-z]) *(\\w+)?");

    private BoardPage currentPage;

    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
    }

    public void setCurrentPage(BoardPage currentPage) {
        this.currentPage = currentPage;
    }

    public boolean runCommand(String command) {
        Matcher matcher = commandPattern.matcher(command);

        if (matcher.find()) {
            int col = Integer.parseInt(matcher.group(1)),
                row = Integer.parseInt(matcher.group(2));

            String action = matcher.group(3);

            if (action.equals("g")) {
                currentPage.setGridSize(row, col);
            }

            return true;
        }

        return false;
    }
}
