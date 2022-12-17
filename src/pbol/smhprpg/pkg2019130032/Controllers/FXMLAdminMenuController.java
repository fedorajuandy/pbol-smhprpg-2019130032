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
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLAdminMenuController implements Initializable {

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXML" + fxml + ".fxml"));
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

    private void inputBasestatsClicked(ActionEvent event) {
        display("BaseStatsInput");
    }

    private void inputBattlestatsClicked(ActionEvent event) {
        display("BattleStatsInput");
    }

    private void inputEffectsClicked(ActionEvent event) {
        display("EffectsInput");
    }

    private void inputSkillsClicked(ActionEvent event) {
        display("SkillsInput");
    }

    private void inputTraitsClicked(ActionEvent event) {
        display("TraitsInput");
    }

    @FXML
    private void displayBasestatsClicked(ActionEvent event) {
        display("BaseStats");
    }

    @FXML
    private void displayBattlestatsClicked(ActionEvent event) {
        display("BattleStats");
    }

    @FXML
    private void displayEffectsClicked(ActionEvent event) {
        display("Effects");
    }

    @FXML
    private void displaySkillsClicked(ActionEvent event) {
        display("Skills");
    }

    @FXML
    private void displayTraitsClicked(ActionEvent event) {
        display("Traits");
    }

    @FXML
    private void displayHeroes(ActionEvent event) {
        display("Heroes");
    }

    @FXML
    private void displayRaces(ActionEvent event) {
        display("Races");
    }

    @FXML
    private void displayClasses(ActionEvent event) {
        display("Classes");
    }

    @FXML
    private void simulationBattles(ActionEvent event) {
        display("BattlesSimulation");
    }

    @FXML
    private void simulationHeroes(ActionEvent event) {
        display("HeroesSimulation");
    }

    @FXML
    private void displayUser(ActionEvent event) {
        display("Users");
    }

    @FXML
    private void manageLevel(ActionEvent event) {
        display("Level");
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }
}
