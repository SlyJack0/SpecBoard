package specboard.ui;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import specboard.domain.Sound;

/**
 * @author Jaakko
 */
public class SoundCellMenu extends ContextMenu {

    private Sound targetSound;

    private MenuItem renameItem, deleteItem;
    private CheckMenuItem loopItem;

    public SoundCellMenu(Sound targetSound) {
        this.targetSound = targetSound;

        renameItem = new MenuItem("Rename sound...");
        deleteItem = new MenuItem("Delete sound");
        loopItem = new CheckMenuItem("Loop");
        loopItem.selectedProperty().bindBidirectional(targetSound.loopProperty());

        getItems().addAll(renameItem, deleteItem, loopItem);
    }


}
