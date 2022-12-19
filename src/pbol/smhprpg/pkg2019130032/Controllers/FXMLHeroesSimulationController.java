package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesSimulationController implements Initializable {

    @FXML
    private ImageView imgHero;
    @FXML
    private Label lbNameHero1;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<?> tbv;
    @FXML
    private Button btnQuit;
    @FXML
    private TableView<?> tbvBase;
    @FXML
    private ComboBox<?> cbClass;
    @FXML
    private TextField txtStatpoints;
    @FXML
    private TextField txtStat;
    @FXML
    private TableView<?> tbvBattle;
    @FXML
    private TableView<?> tbvEffect;
    @FXML
    private TableView<?> tbvTrait;
    @FXML
    private Label lbGender;
    @FXML
    private TextField txtLv;
    @FXML
    private TableView<?> tbvSkill;
    @FXML
    private TextField txtSkill;
    @FXML
    private TextField txtSkillpoints;
    @FXML
    private TextField txtExp;
    @FXML
    private TextField txtRace;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void nextDClicked1(ActionEvent event) {
    }

    @FXML
    private void prevClicked(ActionEvent event) {
    }

    @FXML
    private void nextClicked(ActionEvent event) {
    }

    @FXML
    private void firstClicked(ActionEvent event) {
    }

    @FXML
    private void addClicked(ActionEvent event) {
    }

    @FXML
    private void lastClicked(ActionEvent event) {
    }

    @FXML
    private void quitClicked(ActionEvent event) {
    }

    @FXML
    private void saveClicked(ActionEvent event) {
    }

    @FXML
    private void minStat(ActionEvent event) {
    }

    @FXML
    private void plusStat(ActionEvent event) {
    }
    
}
