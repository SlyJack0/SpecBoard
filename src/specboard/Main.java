package specboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jouvieje.bass.Bass;
import jouvieje.bass.BassInit;
import specboard.event.KeyboardHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        initBASS();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SpecBoard");

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("specboard/specboard.css");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void initBASS() {
        System.setProperty("java.library.path", "lib/win32;lib/win64");
        BassInit.loadLibraries();

        Bass.BASS_Init(1, 44100, 0, null, null);
    }
}
