package specboard.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import specboard.ui.SoundCell;

/**
 * @author Jaakko
 */
public class SoundCellHandler implements EventHandler<ActionEvent> {

    private SoundCell targetCell;

    public SoundCellHandler(SoundCell targetCell) {
        this.targetCell = targetCell;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        targetCell.getTargetSound().start();
    }
}
