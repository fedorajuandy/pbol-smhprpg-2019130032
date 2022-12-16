package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.UserModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLUsersInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<Integer> cbRole;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRole.getItems().addAll(0, 1); 
    }
    
    public void execute(UserModel d) {
        if (d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtUsername.setText(d.getUsername());
          txtPassword.setText(d.getPassword());
          cbRole.setValue(d.getRole());
          viewRoles();
          
          txtUsername.requestFocus();
        }
    }
    
    public void viewRoles() {
        if (cbRole.getValue() == 0) {
            txtRole.setText("Admin");
        } else {
            txtRole.setText("User");
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        UserModel n = new UserModel();
        n.setId(id);
        n.setUsername(txtUsername.getText()); 
        n.setPassword(txtPassword.getText());
        n.setRole(cbRole.getValue());
        
        FXMLMainMenuController.dtu.setUserModel(n);
        if (edited) {
            if (FXMLMainMenuController.dtu.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dtu.validasi(n.getUsername()) <= 0) {
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
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        cbRole.getSelectionModel().select(0);
        txtUsername.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateUsername(KeyEvent event) {
        int batas = 10;
        if (txtUsername.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validatePassword(KeyEvent event) {
        int batas = 128;
        if (txtUsername.getText().length() >= batas) event.consume();
    }
}
