package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLUserMenuController implements Initializable {

    @FXML
    private Label lbUsername;
    @FXML
    private Button btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbUsername.setText(FXMLLoginController.getUsername());
    }
    
    private void display(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXML" + fxml + "Simulation.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setTitle("SMHPRPG");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            System.out.println("Something went wrong with " + fxml + "Clicked(event).");
            e.printStackTrace();
        }
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void displayHeroes(ActionEvent event) {
        display("Heroes");
    }

    @FXML
    private void displayBattle(ActionEvent event) {
        display("Battles");
    }
}
