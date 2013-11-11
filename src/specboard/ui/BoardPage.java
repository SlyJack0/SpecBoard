package specboard.ui;

import javafx.scene.control.Tab;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jaakko
 */
public class BoardPage extends Tab {

    private int rows, cols;
    private String name;
    private GridPane grid;

    private SoundCell[][] cells;

    public BoardPage(String name, int rows, int cols) {
        super(name);

        this.rows = rows;
        this.cols = cols;

        this.name = name;

        //create grid
        grid = new GridPane();

        cells = new SoundCell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new SoundCell("");
            }
        }

        grid = createGrid(cells);

        setContent(grid);
    }

    public void setGridSize(int rows, int cols) {
        if (rows == this.rows && cols == this.cols) {
            return;
        }

        SoundCell[][] newCells = new SoundCell[rows][cols];

        for (int r = 0; r < Math.min(rows, this.rows); r++) {
            newCells[r] = Arrays.copyOf(cells[r], cols);
        }

        //Fill out added cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (newCells[r][c] == null) {
                    newCells[r][c] = new SoundCell("");
                }
            }
        }

        grid = createGrid(newCells);
        cells = newCells;
        this.rows = rows;
        this.cols = cols;

        setContent(grid);
    }

    public SoundCell getSoundCell(int row, int col) {
        return cells[row][col];
    }

    private GridPane createGrid(SoundCell[][] cells) {
        GridPane result = new GridPane();

        int rows = cells.length,
            cols = cells[0].length;

        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                result.add(cells[r][c], c, r);
            }
        }

        for (int r = 0; r < rows; r++) {
            RowConstraints row = new RowConstraints(100, 100, Double.MAX_VALUE);
            row.setVgrow(Priority.ALWAYS);
            result.getRowConstraints().add(row);
        }

        for (int c = 0; c < cols; c++) {
            ColumnConstraints col = new ColumnConstraints(100, 100, Double.MAX_VALUE);
            col.setHgrow(Priority.ALWAYS);
            result.getColumnConstraints().add(col);
        }

        result.setGridLinesVisible(true);

        return result;
    }
}
