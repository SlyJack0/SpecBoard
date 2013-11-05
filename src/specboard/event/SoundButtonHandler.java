package specboard.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jouvieje.bass.Bass;
import jouvieje.bass.structures.HSTREAM;
import specboard.ui.SoundButton;

/**
 * @author Jaakko
 */
public class SoundButtonHandler implements EventHandler<ActionEvent> {

    private SoundButton targetButton;

    public SoundButtonHandler(SoundButton targetButton) {
        this.targetButton = targetButton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        targetButton.getTargetSound().start();
    }
}
