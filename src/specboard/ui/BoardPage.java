package specboard.ui;

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

    private List<SoundCell> cells;

    public BoardPage(String name, int rows, int cols) {
        super(name);

        this.rows = rows;
        this.cols = cols;

        this.name = name;

        //create grid
        this.grid = new GridPane();

        this.cells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                SoundCell newCell = new SoundCell("");

                this.cells.add(newCell);

                this.grid.add(newCell, c, r);
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
