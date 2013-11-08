package specboard.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jaakko
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    private static Pattern commandPattern = Pattern.compile("(\\d+) *, *(\\d+) *([a-zA-z]) *(\\w+)?");


    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
    }


    public boolean runCommand(String command) {
        Matcher matcher = commandPattern.matcher(command);

        if (matcher.find()) {
            int col = Integer.parseInt(matcher.group(1)),
                row = Integer.parseInt(matcher.group(2));

            String action = matcher.group(3);

            System.out.println("Running command " + action + " on cell (" + col + "," + row + ")");

            return true;
        }

        return false;
    }
}
