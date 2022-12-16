package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.SkillModel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLSkillsInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtMpcost;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtDmg;
    @FXML
    private TextField txtSuccessrate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(SkillModel d) {
        if (d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtName.setText(d.getName());
          txtDes.setText(d.getDes());
          txtMpcost.setText(Integer.toString(d.getMp_cost()));
          txtDmg.setText(Double.toString(d.getDmg()));
          txtSuccessrate.setText(Double.toString(d.getSuccess_rate()));
          
          txtName.requestFocus();
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        SkillModel n = new SkillModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setDes(txtDes.getText());
        n.setMp_cost(Integer.parseInt(txtMpcost.getText()));
        n.setDmg(Double.parseDouble(txtDmg.getText()));
        n.setSuccess_rate(Double.parseDouble(txtSuccessrate.getText()));
        
        FXMLAdminMenuController.dts.setSkillModel(n);
        if (edited) {
            if (FXMLAdminMenuController.dts.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLAdminMenuController.dts.validasi(n.getId()) <= 0) {
            if (FXMLAdminMenuController.dts.insert()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data saved.", ButtonType.OK);
               a.showAndWait();
               clearClicked(event);
            } else {
               Alert a = new Alert(Alert.AlertType.ERROR, "Saving data failed.", ButtonType.OK);
               a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "There is already the same data.", ButtonType.OK);
            a.showAndWait();
            txtName.requestFocus();
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtName.setText("");
        txtDes.setText("");
        txtMpcost.setText("");
        txtDmg.setText("");
        txtSuccessrate.setText("");
        txtName.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateMpcost(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if (!Character.isDigit(test)) event.consume();
    }

    @FXML
    private void validateName(KeyEvent event) {
        int batas = 50;
        if (txtName.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validateDes(KeyEvent event) {
        int batas = 255;
        if (txtDes.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validateDmg(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if (!Character.isDigit(test) && test != '.') event.consume();
    }

    @FXML
    private void validateSuccessrate(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if (!Character.isDigit(test) && test != '.') event.consume();
    }
    
}
