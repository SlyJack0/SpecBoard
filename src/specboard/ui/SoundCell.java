package specboard.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import specboard.domain.Sound;
import specboard.event.FileDropHandler;
import specboard.event.SoundCellHandler;

/**
 * @author Jaakko
 */
public class SoundCell extends AnchorPane {

    private Sound targetSound;

    private Button button;
    private SoundCellMenu contextMenu;

    public SoundCell(String s) {
        targetSound = new Sound();

        button = new Button(s);
        button.setOnAction(new SoundCellHandler(this));

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

        button.getStyleClass().add("sound-button");
        button.textProperty().bindBidirectional(targetSound.nameProperty());

        getChildren().add(button);

        setTopAnchor(button, 0.0);
        setBottomAnchor(button, 0.0);
        setLeftAnchor(button, 0.0);
        setRightAnchor(button, 0.0);
    }

    private void createContextMenu() {
        contextMenu = new SoundCellMenu(targetSound);

        button.setContextMenu(contextMenu);
    }

    public boolean loadSound(String filename) {
        if (targetSound.loadFile(filename)) {
            if (contextMenu == null) {
                createContextMenu();
            }

            return true;
        }

        return false;
    }

    public Sound getTargetSound() {
        return targetSound;
    }

    public void removeSound() {
        targetSound = new Sound();
    }
}
