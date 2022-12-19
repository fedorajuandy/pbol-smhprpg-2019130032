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

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLClassesInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtParentclass;
    @FXML
    private TableView<ClassModel> tbv;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }
    
    public void execute(ClassModel d) {
        if (d.getId() != 0) {
          edited = true;
          
          id = d.getId();
          txtName.setText(d.getName());
          txtDes.setText(d.getDes());
          
          for (int i = 0; i < tbv.getItems().size(); i++) {
              tbv.getSelectionModel().select(i);
              if (tbv.getSelectionModel().getSelectedItem().getId() == id) {
                  break;
              }
          }
          txtParentclass.setText(Integer.toString(id));
          
          txtName.requestFocus();
        }
    }
    
    public void showData() {
        ObservableList<ClassModel> data = FXMLMainMenuController.dtc.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("des"));
            tbv.getColumns().addAll(col);
            
            tbv.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        ClassModel n = new ClassModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setDes(txtDes.getText());
        n.setParentclass_id(Integer.parseInt(txtParentclass.getText()));
        
        FXMLMainMenuController.dtc.setClassModel(n);
        if (edited) {
            if (FXMLMainMenuController.dtc.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dtc.validasi(n.getId()) <= 0) {
            if (FXMLMainMenuController.dtc.insert()) {
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
        txtParentclass.setText("");
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
    private void select(MouseEvent event) {
        txtParentclass.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        txtParentclass.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        txtParentclass.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        txtParentclass.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        txtParentclass.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void clear1Clicked(ActionEvent event) {
        search.setText("");
        txtParentclass.setText("");
        showData();
        search.requestFocus();
    }

    @FXML
    private void searchData(KeyEvent event) {
        ClassModel s = new ClassModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<ClassModel> data = FXMLMainMenuController.dtc.searchItems(key, key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Parent Class Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Class Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("des"));
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
}
