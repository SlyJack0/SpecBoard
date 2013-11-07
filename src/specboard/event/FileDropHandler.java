package specboard.event;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import specboard.ui.SoundCell;

/**
 * @author Jaakko
 */
public class FileDropHandler implements EventHandler<DragEvent> {

    private SoundCell targetCell;

    public FileDropHandler(SoundCell targetCell) {
        this.targetCell = targetCell;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        String filename = dragEvent.getDragboard().getFiles().get(0).getAbsolutePath();
        targetCell.loadSound(filename);
    }
}
