package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLLevelController implements Initializable {
    LevelModel n = FXMLMainMenuController.dtl.load();
    private int id = 1;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtScale;
    @FXML
    private TextField txtMaxlv;
    @FXML
    private TextField txtBaseexp;
    @FXML
    private TextField txtStatpoints;
    @FXML
    private TextField txtSkillpoints;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMaxlv.setText(Integer.toString(n.getMax_lv()));
        txtBaseexp.setText(Integer.toString(n.getBase_exp()));
        txtScale.setText(Double.toString(n.getScale()));
        txtStatpoints.setText(Integer.toString(n.getStat_points()));
        txtSkillpoints.setText(Integer.toString(n.getSkill_points()));
          
       FXMLMainMenuController.dtl.setLevelModel(n);
        txtMaxlv.requestFocus();
    }    

    @FXML
    private void saveClicked(ActionEvent event) {
        n.setId(id);
        n.setMax_lv(Integer.parseInt(txtMaxlv.getText()));
        n.setBase_exp(Integer.parseInt(txtBaseexp.getText()));
        n.setScale(Double.parseDouble(txtScale.getText()));
        n.setStat_points(Integer.parseInt(txtStatpoints.getText()));
        n.setSkill_points(Integer.parseInt(txtSkillpoints.getText()));
        
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
        txtMaxlv.setText("100");
        txtBaseexp.setText("1000");
        txtScale.setText("1.25");
        txtStatpoints.setText("0");
        txtSkillpoints.setText("0");
        txtMaxlv.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateScale(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test) && test != '.') event.consume();
    }

    @FXML
    private void validateMaxlv(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        int batas = 5;
        if(!Character.isDigit(test) || txtStatpoints.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validateBaseexp(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        int batas = 10;
        if(!Character.isDigit(test) || txtStatpoints.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validateStatpoints(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        int batas = 2;
        if(!Character.isDigit(test) || txtStatpoints.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validateSkillpoints(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        int batas = 1;
        if(!Character.isDigit(test) || txtSkillpoints.getText().length() >= batas) event.consume();
    }
}
