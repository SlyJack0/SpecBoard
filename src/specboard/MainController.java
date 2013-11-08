package specboard;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import specboard.domain.CommandManager;
import specboard.event.KeyboardHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TabPane pagesPane;

    @FXML
    private TextField statusBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.setOnKeyPressed(new KeyboardHandler(pagesPane));

        statusBar.textProperty().bindBidirectional(CommandManager.getInstance().commandStringProperty());
        statusBar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CommandManager.getInstance().runCommand();
            }
        });
        statusBar.setFocusTraversable(false);
    }

}
