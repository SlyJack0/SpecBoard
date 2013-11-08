package specboard.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jaakko
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    private static Pattern commandPattern = Pattern.compile("(?:(\\d+) *, *(\\d+))? *([a-zA-z]) *(\\w+)?");


    public StringProperty commandString;

    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
        commandString = new SimpleStringProperty("");
    }

    public String getCommandString() {
        return commandString.get();
    }

    public StringProperty commandStringProperty() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString.set(commandString);
    }

    public void appendString(String s) {
        setCommandString(getCommandString() + s);
    }

    public void runCommand() {
        Matcher matcher = commandPattern.matcher(getCommandString());

        if (matcher.find()) {
            int col = Integer.parseInt(matcher.group(1)),
                row = Integer.parseInt(matcher.group(2));

            String command = matcher.group(3);

            System.out.println("Running commandString " + command + " on cell (" + col + "," + row + ")");
        }

        setCommandString("");
    }
}
