package specboard.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jouvieje.bass.Bass;
import jouvieje.bass.defines.BASS_SAMPLE;
import jouvieje.bass.structures.HSTREAM;

import java.io.File;

/**
 * @author Jaakko
 */
public class Sound {

    private StringProperty name;

    private HSTREAM stream;
    private BooleanProperty loop;

    private boolean playing;

    public Sound() {
        name = new SimpleStringProperty("");
        loop = new SimpleBooleanProperty(false);

        loop.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                setStreamLoop(aBoolean2);
            }
        });
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

    public boolean getLoop() {
        return loop.get();
    }

    public BooleanProperty loopProperty() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop.set(loop);
    }

    public void start() {
        if (stream != null) {
            Bass.BASS_ChannelPlay(stream.asInt(), true);
            playing = true;

            System.out.println(Bass.BASS_ChannelFlags(stream.asInt(), 0, 0) & BASS_SAMPLE.BASS_SAMPLE_LOOP);
        }
    }

    public void stop() {
        if (playing) {
            Bass.BASS_ChannelStop(stream.asInt());
            playing = false;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (stream != null) {
            Bass.BASS_StreamFree(stream);
        }

        super.finalize();
    }

    private void setStreamLoop(boolean loop) {
        if (loop) {
            Bass.BASS_ChannelFlags(stream.asInt(), BASS_SAMPLE.BASS_SAMPLE_LOOP, BASS_SAMPLE.BASS_SAMPLE_LOOP);
        } else {
            Bass.BASS_ChannelFlags(stream.asInt(), 0, BASS_SAMPLE.BASS_SAMPLE_LOOP);
        }
    }
}
