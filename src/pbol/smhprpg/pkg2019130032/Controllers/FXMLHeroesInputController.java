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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private Button btnExit;
    private ComboBox<Integer> cbRace;
    private ComboBox<Integer> cbCurrentclass;
    @FXML
    private TextField txtRace;
    private TextField txtCurrclass;
    @FXML
    private TextField txtImage;
    private TextField txtUser;
    @FXML
    private ComboBox<Integer> cbUser;
    private Spinner<Integer> spSkillpoints;
    private Spinner<Integer> spExp;
    private Spinner<Integer> spStatpoints;
    @FXML
    private ComboBox<Character> cbGender;
    @FXML
    private TextField txtSkillpoints;
    @FXML
    private TextField txtStatpoints;
    @FXML
    private TextField txtExp;
    @FXML
    private TableView<?> tbv;
    @FXML
    private TextField search;
    @FXML
    private TextField txtClass;
    @FXML
    private TableView<?> tbv1;
    @FXML
    private TextField search2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbGender.getItems().addAll('m', 'f'); 
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("SELECT id FROM races");
            while (rs.next()) {
                cbRace.getItems().addAll(rs.getInt("id")); 
            }
            
            rs = con.statement.executeQuery("SELECT id FROM classes");
            while (rs.next()) {
                cbCurrentclass.getItems().addAll(rs.getInt("id")); 
            }
            
            rs = con.statement.executeQuery("SELECT id FROM users");
            while (rs.next()) {
                cbUser.getItems().addAll(rs.getInt("id")); 
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void execute(HeroModel d) {
        if (d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtName.setText(d.getName());
          cbGender.setValue(d.getGender());
          cbRace.setValue(d.getRace_id());
          txtDes.setText(d.getDes());
          cbCurrentclass.setValue(d.getCurr_class_id());
          spExp.getValueFactory().setValue(d.getExp());
          spStatpoints.getValueFactory().setValue(d.getStat_points());
          spSkillpoints.getValueFactory().setValue(d.getSkill_points());
          txtImage.setText(d.getImage());
          cbUser.setValue(d.getUser_id());
          viewRacename();
          viewClassname();
          
          txtName.requestFocus();
        }
    }
    
    public void viewRacename() {
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT name FROM races where id = " + cbRace.getSelectionModel().getSelectedItem());

            while (rs.next()) {
                txtCurrclass.setText(rs.getString("name"));
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void viewClassname() {
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT name FROM classes where id = " + cbCurrentclass.getSelectionModel().getSelectedItem());

            while (rs.next()) {
                txtCurrclass.setText(rs.getString("name"));
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void viewUsername() {
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT name FROM users where id = " + cbUser.getSelectionModel().getSelectedItem());

            while (rs.next()) {
                txtUser.setText(rs.getString("name"));
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        HeroModel n = new HeroModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setGender(cbGender.getValue());
        n.setRace_id(cbRace.getValue());
        n.setDes(txtDes.getText());
        n.setCurr_class_id(cbCurrentclass.getValue());
        n.setExp(spExp.getValue());
        n.setStat_points(spStatpoints.getValue());
        n.setSkill_points(spSkillpoints.getValue());
        n.setImage(txtImage.getText());
        n.setUser_id(cbUser.getValue());
        
        FXMLMainMenuController.dth.setHeroModel(n);
        if (edited) {
            if (FXMLMainMenuController.dth.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dth.validasi(n.getId()) <= 0) {
            if (FXMLMainMenuController.dth.insert()) {
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
        cbGender.getSelectionModel().select(0);
        cbRace.getSelectionModel().select(0);
        viewRacename();
        cbCurrentclass.getSelectionModel().select(0);
        viewClassname();
        spExp.getValueFactory().setValue(0);
        spSkillpoints.getValueFactory().setValue(0);
        spStatpoints.getValueFactory().setValue(0);
        txtImage.setText("");
        cbUser.getSelectionModel().select(0);
        viewUsername();
        txtName.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
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
    private void validateSkill(KeyEvent event) {
    }

    @FXML
    private void validateStat(KeyEvent event) {
    }

    @FXML
    private void validateExp(KeyEvent event) {
    }

    @FXML
    private void select(MouseEvent event) {
    }

    @FXML
    private void firstClicked(ActionEvent event) {
    }

    @FXML
    private void prevClicked(ActionEvent event) {
    }

    @FXML
    private void nextClicked(ActionEvent event) {
    }

    @FXML
    private void lastClicked(ActionEvent event) {
    }

    @FXML
    private void searchData(KeyEvent event) {
    }

    @FXML
    private void clear1Clicked(ActionEvent event) {
    }

    @FXML
    private void firstClicked1(ActionEvent event) {
    }

    @FXML
    private void prevClicked1(ActionEvent event) {
    }

    @FXML
    private void nextClicked1(ActionEvent event) {
    }

    @FXML
    private void lastClicked1(ActionEvent event) {
    }

    @FXML
    private void searchData1(KeyEvent event) {
    }

    @FXML
    private void clear2Clicked(ActionEvent event) {
    }
}
