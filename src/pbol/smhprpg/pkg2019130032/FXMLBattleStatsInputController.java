package pbol.smhprpg.pkg2019130032;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLBattleStatsInputController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtAbbrev;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void validateAbbrev(KeyEvent event) {
    }

    @FXML
    private void validateName(KeyEvent event) {
    }

    @FXML
    private void validateDes(KeyEvent event) {
    }
    
}
