package specboard;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import specboard.event.KeyboardHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TabPane pagesPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.setOnKeyPressed(new KeyboardHandler(pagesPane));
    }

}
