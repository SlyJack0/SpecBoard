package specboard.event;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import specboard.ui.SoundButton;

/**
 * @author Jaakko
 */
public class FileDropHandler implements EventHandler<DragEvent> {

    private SoundButton targetButton;

    public FileDropHandler(SoundButton targetButton) {
        this.targetButton = targetButton;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        String filename = dragEvent.getDragboard().getFiles().get(0).getAbsolutePath();
        targetButton.loadSound(filename);
    }
}
