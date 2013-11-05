package specboard.ui;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import jouvieje.bass.Bass;
import jouvieje.bass.structures.HSTREAM;
import specboard.domain.Sound;
import specboard.event.FileDropHandler;
import specboard.event.SoundButtonHandler;

/**
 * @author Jaakko
 */
public class SoundButton extends Button {

    private Sound targetSound;

    public SoundButton(String s) {
        super(s);

        this.setOnAction(new SoundButtonHandler(this));
        this.setOnDragDropped(new FileDropHandler(this));
        this.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != this && dragEvent.getDragboard().hasFiles()) {
                    dragEvent.acceptTransferModes(TransferMode.LINK);
                }

                dragEvent.consume();
            }
        });

        this.getStyleClass().add("sound-button");

        this.targetSound = new Sound();

        this.textProperty().bindBidirectional(targetSound.nameProperty());
    }

    public boolean loadSound(String filename) {
        return targetSound.loadFile(filename);
    }

    public Sound getTargetSound() {
        return targetSound;
    }
}
