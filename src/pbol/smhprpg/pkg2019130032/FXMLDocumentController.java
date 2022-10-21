package pbol.smhprpg.pkg2019130032;

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
import pbol.smhprpg.pkg2019130032.DBs.DBLevels;
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
public class FXMLDocumentController implements Initializable {
    public static DBBaseStats dtbs = new DBBaseStats();
    public static DBBaseToBattleStats dtbtbs = new DBBaseToBattleStats();
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
    public static DBLevels dtl = new DBLevels();
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

    @FXML
    private void inputBasestatsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBaseStatsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inputBattlestatsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBattleStatsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inputEffectsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEffectsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inputLevelsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLevelsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inputSkillsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSkillsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inputTraitsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTraitsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayBasestatsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBaseStats.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayBattlestatsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBattleStats.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayEffectsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEffects.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayLevelsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLevels.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displaySkillsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSkills.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayTraitsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTraits.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
