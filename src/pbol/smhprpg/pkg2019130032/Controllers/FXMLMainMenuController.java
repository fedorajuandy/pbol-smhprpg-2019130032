package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.IOException;
import pbol.smhprpg.pkg2019130032.DBs.DBBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBBaseToBattleStats;
import pbol.smhprpg.pkg2019130032.DBs.DBBattleStats;
import pbol.smhprpg.pkg2019130032.DBs.DBClassBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBClassBattleStats;
import pbol.smhprpg.pkg2019130032.DBs.DBClassSkills;
import pbol.smhprpg.pkg2019130032.DBs.DBClassTraits;
import pbol.smhprpg.pkg2019130032.DBs.DBClasses;
import pbol.smhprpg.pkg2019130032.DBs.DBEffects;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroClasses;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroEffects;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroSkills;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroTraits;
import pbol.smhprpg.pkg2019130032.DBs.DBHeroes;
import pbol.smhprpg.pkg2019130032.DBs.DBRaceBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBRaceTraits;
import pbol.smhprpg.pkg2019130032.DBs.DBRaces;
import pbol.smhprpg.pkg2019130032.DBs.DBSkills;
import pbol.smhprpg.pkg2019130032.DBs.DBTraitBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBTraits;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLMainMenuController implements Initializable {
    public static DBBaseStats dtbs = new DBBaseStats();
    public static DBBaseToBattleStats dtbtb = new DBBaseToBattleStats();
    public static DBBattleStats dtbt = new DBBattleStats();
    public static DBClassBaseStats dtcbs = new DBClassBaseStats();
    public static DBClassBattleStats dtcbt = new DBClassBattleStats();
    public static DBClassSkills dtcs = new DBClassSkills();
    public static DBClassTraits dtct = new DBClassTraits();
    public static DBClasses dtc = new DBClasses();
    public static DBEffects dte = new DBEffects();
    public static DBHeroBaseStats dthbs = new DBHeroBaseStats();
    public static DBHeroClasses dthc = new DBHeroClasses();
    public static DBHeroEffects dthe = new DBHeroEffects();
    public static DBHeroSkills dths = new DBHeroSkills();
    public static DBHeroTraits dtht = new DBHeroTraits();
    public static DBHeroes dth = new DBHeroes();
    public static DBRaceBaseStats dtrbs = new DBRaceBaseStats();
    public static DBRaceTraits dtrt = new DBRaceTraits();
    public static DBRaces dtr = new DBRaces();
    public static DBSkills dts = new DBSkills();
    public static DBTraitBaseStats dttbs = new DBTraitBaseStats();
    public static DBTraits dtt = new DBTraits();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void display(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXML" + fxml + ".fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
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
    private void simulateBattle(ActionEvent event) {
        display("Simulation");
    }
}
