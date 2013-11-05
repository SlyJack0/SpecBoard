package specboard.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jaakko
 */
public class BoardPage extends Tab {

    private int rows, cols;
    private String name;
    private GridPane grid;

    private List<SoundButton> buttons;

    public BoardPage(String name, int rows, int cols) {
        super(name);

        this.rows = rows;
        this.cols = cols;

        this.name = name;

        //create grid and buttons
        this.grid = new GridPane();

        this.buttons = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                SoundButton newButton = new SoundButton("");

                this.buttons.add(newButton);

                AnchorPane tmpPane = new AnchorPane();

                tmpPane.getChildren().add(newButton);
                AnchorPane.setTopAnchor(newButton, 0.0);
                AnchorPane.setBottomAnchor(newButton, 0.0);
                AnchorPane.setLeftAnchor(newButton, 0.0);
                AnchorPane.setRightAnchor(newButton, 0.0);

                this.grid.add(tmpPane, c, r);
            }
        }

        for (int r = 0; r < rows; r++) {
            RowConstraints row = new RowConstraints(100, 100, Double.MAX_VALUE);
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }

        for (int c = 0; c < cols; c++) {
            ColumnConstraints col = new ColumnConstraints(100, 100, Double.MAX_VALUE);
            col.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col);
        }

        grid.setGridLinesVisible(true);
        this.setContent(grid);
    }
}
