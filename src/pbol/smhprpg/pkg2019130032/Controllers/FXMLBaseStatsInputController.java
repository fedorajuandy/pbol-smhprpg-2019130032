package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;

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
public class FXMLBaseStatsInputController implements Initializable {
    private boolean edited = false;
    private int id;
    
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtAbbrev;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void execute(BaseStatModel d) {
        if (d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtAbbrev.setText(d.getAbbrev());
          txtName.setText(d.getName());
          txtDes.setText(d.getDes());
          
          txtAbbrev.requestFocus();
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        BaseStatModel n = new BaseStatModel();
        n.setId(id);
        n.setAbbrev(txtAbbrev.getText());
        n.setName(txtName.getText()); 
        n.setDes(txtDes.getText());
        
        FXMLMainMenuController.dtbs.setBaseStatModel(n);
        if (edited) {
            if (FXMLMainMenuController.dtbs.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dtbs.validasi(n.getId()) <= 0) {
            if (FXMLMainMenuController.dtbs.insert()) {
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
            txtAbbrev.requestFocus();
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtAbbrev.setText("");
        txtName.setText("");
        txtDes.setText("");
        txtAbbrev.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateAbbrev(KeyEvent event) {
        int batas = 5;
        if (txtAbbrev.getText().length() >= batas) event.consume();
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
}
