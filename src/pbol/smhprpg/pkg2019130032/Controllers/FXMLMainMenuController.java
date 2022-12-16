package pbol.smhprpg.pkg2019130032.Controllers;

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
import pbol.smhprpg.pkg2019130032.DBs.DBLevel;
import pbol.smhprpg.pkg2019130032.DBs.DBRaceBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBRaceTraits;
import pbol.smhprpg.pkg2019130032.DBs.DBRaces;
import pbol.smhprpg.pkg2019130032.DBs.DBSkills;
import pbol.smhprpg.pkg2019130032.DBs.DBTraitBaseStats;
import pbol.smhprpg.pkg2019130032.DBs.DBTraits;
import pbol.smhprpg.pkg2019130032.DBs.DBUsers;

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
 * FXML Controller class
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
    public static DBLevel dtl = new DBLevel();
    public static DBRaceBaseStats dtrbs = new DBRaceBaseStats();
    public static DBRaceTraits dtrt = new DBRaceTraits();
    public static DBRaces dtr = new DBRaces();
    public static DBSkills dts = new DBSkills();
    public static DBTraitBaseStats dttbs = new DBTraitBaseStats();
    public static DBTraits dtt = new DBTraits();
    public static DBUsers dtu = new DBUsers();

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
    private void displayLogin(ActionEvent event) {
        display("Login");
    }

    @FXML
    private void displayRegister(ActionEvent event) {
        display("Register");
    }
    
}
