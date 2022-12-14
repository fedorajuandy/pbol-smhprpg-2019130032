package pbol.smhprpg.pkg2019130032;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class PbolSmhprpg2019130032 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLDocument.fxml"));
        stage.setTitle("SMHPRPG");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("imgs/smhprpg.png")));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
