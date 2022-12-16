/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesCreateController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private ComboBox<?> dbGender;
    @FXML
    private ComboBox<?> cbRace;
    @FXML
    private ComboBox<?> cbCurrentclass;
    @FXML
    private TextField txtRace;
    @FXML
    private TextField txtCurrclass;
    @FXML
    private TextField txtImage;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validateName(KeyEvent event) {
    }

    @FXML
    private void validateDes(KeyEvent event) {
    }

    @FXML
    private void validateSuccessrate(KeyEvent event) {
    }

    @FXML
    private void saveClicked(ActionEvent event) {
    }

    @FXML
    private void clearClicked(ActionEvent event) {
    }

    @FXML
    private void exitClicked(ActionEvent event) {
    }
    
}
