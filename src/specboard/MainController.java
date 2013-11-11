package specboard;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import specboard.domain.CommandManager;
import specboard.event.KeyboardHandler;
import specboard.ui.BoardPage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TabPane pagesPane;

    @FXML
    private TextField commandLine;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.setOnKeyPressed(new KeyboardHandler(pagesPane, commandLine));

        commandLine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean success = CommandManager.getInstance().runCommand(commandLine.getText());

                if (success) {
                    commandLine.setText("");
                    pagesPane.requestFocus();
                }
            }
        });

        CommandManager.getInstance().setPagesPane(pagesPane);
        commandLine.setFocusTraversable(false);

    }

}
