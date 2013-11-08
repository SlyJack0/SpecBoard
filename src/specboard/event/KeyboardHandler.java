package specboard.event;

import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import specboard.domain.CommandManager;
import specboard.ui.BoardPage;

/**
 * @author Jaakko
 */
public class KeyboardHandler implements EventHandler<KeyEvent> {

    private TabPane pagesPane;

    public KeyboardHandler(TabPane pagesPane) {
        this.pagesPane = pagesPane;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        if (keyEvent.isControlDown() && code.getName().equals("P")) {
            BoardPage bp = new BoardPage("New page", 4, 4);
            pagesPane.getTabs().add(bp);
        } else {
            CommandManager commandManager = CommandManager.getInstance();
            if (code.getName().equals("Enter") && !commandManager.getCommandString().isEmpty()) {
                commandManager.runCommand();
            } else {
                commandManager.appendString(keyEvent.getText());
            }
        }


    }
}
