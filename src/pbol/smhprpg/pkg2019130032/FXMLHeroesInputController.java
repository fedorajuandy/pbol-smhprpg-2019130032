package pbol.smhprpg.pkg2019130032;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesInputController implements Initializable {

    @FXML
    private TextField txtAbbrev;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtName1;
    @FXML
    private TextField txtName2;
    @FXML
    private TextField txtName3;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtName4;

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
    
}
