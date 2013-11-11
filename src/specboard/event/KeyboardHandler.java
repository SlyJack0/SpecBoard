package specboard.event;

import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import specboard.domain.CommandManager;
import specboard.ui.BoardPage;

/**
 * @author Jaakko
 */
public class KeyboardHandler implements EventHandler<KeyEvent> {

    private TabPane pagesPane;
    private TextField commandLine;

    public KeyboardHandler(TabPane pagesPane, TextField commandLine) {
        this.pagesPane = pagesPane;
        this.commandLine = commandLine;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        if (keyEvent.isShortcutDown()) {
            if (code.getName().equals("P")) {
                BoardPage bp = new BoardPage("New page", 4, 4);
                pagesPane.getTabs().add(bp);
            } else if (code.getName().equals("C")) {
                commandLine.requestFocus();
            } else if (code.ordinal() >= 25 && code.ordinal() <= 33) {
                pagesPane.getSelectionModel().select(code.ordinal() - 25);
            }
        }


    }
}
