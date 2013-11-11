package specboard.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TabPane;
import specboard.ui.BoardPage;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jaakko
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    private static Pattern commandPattern = Pattern.compile("([a-zA-Z]) *([\\w,]+)?");

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

                int rows = Integer.parseInt(args[0]),
                    cols = Integer.parseInt(args[1]);

                currentPage.setGridSize(rows, cols);
            }

            return true;
        }

        return false;
    }
}
