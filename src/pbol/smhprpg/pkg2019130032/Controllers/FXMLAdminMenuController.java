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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLAdminMenuController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void display(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032//pbol/smhprpg/pkg2019130032/Views/FXML" + fxml + ".fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setTitle("SMHPRPG");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("imgs/smhprpg.png")));
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
    private void inputBasestatsClicked(ActionEvent event) {
        display("BaseStatsInput");
    }

    @FXML
    private void inputBattlestatsClicked(ActionEvent event) {
        display("BattleStatsInput");
    }

    @FXML
    private void inputEffectsClicked(ActionEvent event) {
        display("EffectsInput");
    }

    @FXML
    private void inputSkillsClicked(ActionEvent event) {
        display("SkillsInput");
    }

    @FXML
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
        display("SimulationBattles");
    }

    @FXML
    private void simulationHeroes(ActionEvent event) {
        display("SimulationHeroes");
    }
}
