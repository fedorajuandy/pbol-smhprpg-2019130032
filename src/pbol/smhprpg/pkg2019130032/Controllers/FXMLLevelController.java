package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLLevelController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtBaseexp;
    @FXML
    private TextField txtScale;
    @FXML
    private TextField txtStatpoints;
    @FXML
    private TextField txtSkillpoints;

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
    private void validateName(KeyEvent event) {
    }
    
}
