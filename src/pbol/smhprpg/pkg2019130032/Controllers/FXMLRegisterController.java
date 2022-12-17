package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.UserModel;

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

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLRegisterController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtConfirm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clearClicked(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirm.setText("");
        txtUsername.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void registerClicked(ActionEvent event) {
        if (txtPassword.getText().equals(txtConfirm.getText())) {
            UserModel n = new UserModel();
            n.setUsername(txtUsername.getText()); 
            n.setPassword(txtPassword.getText());
            n.setRole(1);

            FXMLMainMenuController.dtu.setUserModel(n);
            
            if (FXMLMainMenuController.dtu.validasi(n.getUsername()) <= 0) {
                if (FXMLMainMenuController.dtu.insert()) {
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
                txtUsername.requestFocus();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Passwords do not match.", ButtonType.OK);
            a.showAndWait();
            txtPassword.requestFocus();
        }
    }

    @FXML
    private void validateUsername(KeyEvent event) {
        int batas = 10;
        if (txtUsername.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validatePassword(KeyEvent event) {
        int batas = 128;
        if (txtPassword.getText().length() >= batas) event.consume();
    }
}
