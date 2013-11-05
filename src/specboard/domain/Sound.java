package specboard.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jouvieje.bass.Bass;
import jouvieje.bass.structures.HSTREAM;

import java.io.File;

/**
 * @author Jaakko
 */
public class Sound {

    private StringProperty name;

    private HSTREAM stream;
    private boolean loop;

    private boolean playing;

    public Sound() {
        name = new SimpleStringProperty("");
    }

    public boolean loadFile(String filename) {
        stream = Bass.BASS_StreamCreateFile(false, filename, 0, 0, 0);

        if (stream != null && Bass.BASS_ErrorGetCode() == 0) {
            File tmpFile = new File(filename);

            setName(tmpFile.getName());
            return true;
        }

        return false;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void start() {
        if (stream != null) {
            Bass.BASS_ChannelPlay(stream.asInt(), true);
            playing = true;
        }
    }

    public void stop() {
        if (playing) {
            Bass.BASS_ChannelStop(stream.asInt());
            playing = false;
        }
    }


}
