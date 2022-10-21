package pbol.smhprpg.pkg2019130032;

import pbol.smhprpg.pkg2019130032.Models.SkillModel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLSkillsController implements Initializable {
    private boolean edited = false;
    private int id;

    private Button btnExit;
    private TextField txtMpcost;
    private TextField txtName;
    private TextArea txtDes;
    private TextField txtDmg;
    private TextField txtSuccessrate;
    @FXML
    private TextField search;
    @FXML
    private TableView<?> tbv;
    @FXML
    private Button btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void execute(SkillModel d) {
        if(d.getId() != 0) {
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

    private void saveClicked(ActionEvent event) {
        SkillModel n = new SkillModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setDes(txtDes.getText());
        n.setMp_cost(Integer.parseInt(txtMpcost.getText()));
        n.setDmg(Double.parseDouble(txtDmg.getText()));
        n.setSuccess_rate(Double.parseDouble(txtSuccessrate.getText()));
        
        FXMLDocumentController.dts.setSkillModel(n);
        if(edited) {
            if(FXMLDocumentController.dts.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
               clearClicked(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed..", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if(FXMLDocumentController.dts.validasi(n.getId()) <= 0) {
            if(FXMLDocumentController.dts.insert()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data saved.", ButtonType.OK);
               a.showAndWait();
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

    private void clearClicked(ActionEvent event) {
        txtName.setText("");
        txtDes.setText("");
        txtMpcost.setText("");
        txtDmg.setText("");
        txtSuccessrate.setText("");
        txtName.requestFocus();
    }

    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    private void validateMpcost(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test)) event.consume();
    }

    private void validateName(KeyEvent event) {
        int batas = 50;
        if(txtName.getText().length() >= batas) event.consume();
    }

    private void validateDes(KeyEvent event) {
        int batas = 255;
        if(txtDes.getText().length() >= batas) event.consume();
    }

    private void validateDmg(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test) && test != '.') event.consume();
    }

    private void validateSuccessrate(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test) && test != '.') event.consume();
    }

    @FXML
    private void searchData(KeyEvent event) {
    }

    @FXML
    private void showDetails(MouseEvent event) {
    }

    @FXML
    private void firstClicked(ActionEvent event) {
    }

    @FXML
    private void prevClicked(ActionEvent event) {
    }

    @FXML
    private void addClicked(ActionEvent event) {
    }

    @FXML
    private void updateClicked(ActionEvent event) {
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
    }

    @FXML
    private void nextClicked(ActionEvent event) {
    }

    @FXML
    private void lastClicked(ActionEvent event) {
    }

    @FXML
    private void quitClicked(ActionEvent event) {
    }
    
}
