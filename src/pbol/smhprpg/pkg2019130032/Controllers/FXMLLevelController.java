package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLLevelController implements Initializable {
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtScale;
    @FXML
    private Spinner<Integer> spMaxlv;
    @FXML
    private Spinner<Integer> spBaseexp;
    @FXML
    private Spinner<Integer> spStatpoints;
    @FXML
    private Spinner<Integer> spSkillpoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveClicked(ActionEvent event) {
        LevelModel n = new LevelModel();
        n.setId(id);
        n.setMax_lv(spMaxlv.getValue());
        n.setBase_exp(spBaseexp.getValue());
        n.setScale(Double.parseDouble(txtScale.getText()));
        n.setStat_points(spStatpoints.getValue());
        n.setSkill_points(spSkillpoints.getValue());
        
        if (FXMLMainMenuController.dtl.update()) {
           Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
           a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
            a.showAndWait(); 
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        spMaxlv.getValueFactory().setValue(100);
        spBaseexp.getValueFactory().setValue(100);
        txtScale.setText("");
        spStatpoints.getValueFactory().setValue(5);
        spSkillpoints.getValueFactory().setValue(1);
        spMaxlv.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }
}
