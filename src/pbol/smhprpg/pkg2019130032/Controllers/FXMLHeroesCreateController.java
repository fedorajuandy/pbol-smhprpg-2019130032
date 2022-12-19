package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.RaceModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesCreateController implements Initializable {
    private boolean edited = false;
    private int id, id1, id2;

    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtRace;
    @FXML
    private TextField txtImage;
    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<String> cbGender;
    @FXML
    private TableView<RaceModel> tbv;
    @FXML
    private TextField search;
    @FXML
    private TextField txtClass;
    @FXML
    private TableView<ClassModel> tbv1;
    @FXML
    private TextField search1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        showData1();
        cbGender.getItems().addAll("female", "male", "other"); 
    }
    
    public void execute(HeroModel d) {
        id = d.getRace_id();
        id1 = d.getCurr_class_id();
        
        if (d.getId() != 0 && id1 != 0 && id2 != 0) {
          edited = true;
          
          id = d.getId();
          txtName.setText(d.getName());
          cbGender.setValue(d.getGender());
          txtDes.setText(d.getDes());
          for (int i = 0; i < tbv.getItems().size(); i++) {
              tbv.getSelectionModel().select(i);
              if (tbv.getSelectionModel().getSelectedItem().getId() == id1) {
                  break;
              }
          }
          for (int i = 0; i < tbv1.getItems().size(); i++) {
              tbv1.getSelectionModel().select(i);
              if (tbv1.getSelectionModel().getSelectedItem().getId() == id2) {
                  break;
              }
          }
          txtRace.setText(Integer.toString(id));
          txtClass.setText(Integer.toString(id1));
          txtImage.setText(d.getImage());
          
          txtName.requestFocus();
        }
    }
    
    public void showData() {
        ObservableList<RaceModel> data = FXMLMainMenuController.dtr.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Race Id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentrace_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Race Name");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentraceName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("des"));
            tbv.getColumns().addAll(col);
            
            tbv.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv.getScene().getWindow().hide();;
        }
    }
    
    public void showData1() {
        ObservableList<ClassModel> data = FXMLMainMenuController.dtc.load();
        
        if (data != null) {
            tbv1.getColumns().clear();
            tbv1.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("des"));
            tbv1.getColumns().addAll(col);
            
            tbv1.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv1.getScene().getWindow().hide();;
        }
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
    private void saveClicked(ActionEvent event) {
        HeroModel n = new HeroModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setGender(cbGender.getSelectionModel().getSelectedItem());
        n.setDes(txtDes.getText());
        n.setRace_id(Integer.parseInt(txtRace.getText()));
        n.setCurr_class_id(Integer.parseInt(txtClass.getText()));
        n.setStat_points(0);
        n.setSkill_points(0);
        n.setExp(0);
        n.setUsername(FXMLLoginController.getUsername());
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("SELECT id FROM users WHERE username = '" + n.getUsername() + "'");
            while (rs.next()) {
                n.setUser_id(rs.getInt("id"));
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
        n.setImage(txtImage.getText()); 
        
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
        firstClicked(event);
        firstClicked1(event);
        txtImage.setText("");
        txtName.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void select(MouseEvent event) {
        txtRace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }
    
    @FXML
    private void select1(MouseEvent event) {
        txtClass.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        txtRace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        txtRace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        txtRace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        txtRace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void searchData(KeyEvent event) {
        RaceModel s = new RaceModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<RaceModel> data = FXMLMainMenuController.dtr.searchItems(key, key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("name"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Race Id");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentrace_id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Race Name");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentraceName"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("des"));
                tbv.getColumns().addAll(col);
                
                tbv.setItems(data);
                tbv.getSelectionModel().selectFirst();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
                a.showAndWait();
                tbv.getScene().getWindow().hide();;
            }            
        } else {
           showData();
        }
    }

    @FXML
    private void clear1Clicked(ActionEvent event) {
        search.setText("");
        txtRace.setText("");
        showData();
        search.requestFocus();
    }

    @FXML
    private void firstClicked1(ActionEvent event) {
        tbv1.getSelectionModel().selectFirst();
        tbv1.requestFocus();
        txtClass.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prevClicked1(ActionEvent event) {
        tbv1.getSelectionModel().selectAboveCell();
        tbv1.requestFocus();
        txtClass.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void nextClicked1(ActionEvent event) {
        tbv1.getSelectionModel().selectBelowCell();
        tbv1.requestFocus();
        txtClass.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void lastClicked1(ActionEvent event) {
        tbv1.getSelectionModel().selectLast();
        tbv1.requestFocus();
        txtClass.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }
    
    @FXML
    private void searchData1(KeyEvent event) {
        ClassModel s = new ClassModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<ClassModel> data = FXMLMainMenuController.dtc.searchItems(key, key, key, key);
            if (data != null) {
                tbv1.getColumns().clear();
                tbv1.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Parent Class Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
                tbv1.getColumns().addAll(col);
                
                col = new TableColumn("Parent Class Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("des"));
                tbv1.getColumns().addAll(col);
                
                tbv1.setItems(data);
                tbv1.getSelectionModel().selectFirst();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
                a.showAndWait();
                tbv1.getScene().getWindow().hide();;
            }            
        } else {
           showData1();
        }
    }

    @FXML
    private void clear2Clicked(ActionEvent event) {
        search1.setText("");
        txtClass.setText("");
        showData1();
        search1.requestFocus();
    }
}
