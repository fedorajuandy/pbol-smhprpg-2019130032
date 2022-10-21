package pbol.smhprpg.pkg2019130032;

import pbol.smhprpg.pkg2019130032.Models.LevelModel;

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
public class FXMLLevelsInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtNeededexp;
    @FXML
    private TextField txtScale;
    @FXML
    private TextField txtMaxlv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void execute(LevelModel d) {
        if(d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtNeededexp.setText(Integer.toString(d.getNeeded_exp()));
          txtScale.setText(Double.toString(d.getScale()));
          txtMaxlv.setText(Integer.toString(d.getMax_lv()));
          
          txtNeededexp.requestFocus();
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        LevelModel n = new LevelModel();
        n.setId(id);
        n.setNeeded_exp(Integer.parseInt(txtNeededexp.getText()));
        n.setScale(Double.parseDouble(txtScale.getText())); 
        n.setMax_lv(Integer.parseInt(txtMaxlv.getText()));
        
        FXMLDocumentController.dtl.setLevelModel(n);
        if(edited) {
            if(FXMLDocumentController.dtl.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
               clearClicked(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed..", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if(FXMLDocumentController.dtl.validasi(n.getId()) <= 0) {
            if(FXMLDocumentController.dtl.insert()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data saved.", ButtonType.OK);
               a.showAndWait();
            } else {
               Alert a = new Alert(Alert.AlertType.ERROR, "Saving data failed.", ButtonType.OK);
               a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "There is already the same data.", ButtonType.OK);
            a.showAndWait();
            txtNeededexp.requestFocus();
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtNeededexp.setText("");
        txtScale.setText("");
        txtMaxlv.setText("");
        txtNeededexp.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateNeededexp(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test)) event.consume();
    }

    @FXML
    private void validateScale(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test) && test != '.') event.consume();
    }

    @FXML
    private void validateMaxlv(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test)) event.consume();
    }
}
