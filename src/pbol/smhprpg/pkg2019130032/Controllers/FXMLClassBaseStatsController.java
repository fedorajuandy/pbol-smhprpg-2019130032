package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;
import pbol.smhprpg.pkg2019130032.Models.ClassBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.ClassBaseStatModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLClassBaseStatsController implements Initializable {
    private boolean edited = false;
    private int id, id1;

    @FXML
    private TextField search1;
    @FXML
    private TableView<ClassModel> tbv;
    @FXML
    private TableView<BaseStatModel> tbv1;
    @FXML
    private TextField search;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtId1;
    @FXML
    private TextField txtInput;
    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        showData1();
    }
    
    public void execute(ClassBaseStatModel d) {
        id = d.getClass_id();
        id1 = d.getBase_stat_id();
          
        if (id != 0 && id1 != 0) {
          edited = true;
          
          for (int i = 0; i < tbv.getItems().size(); i++) {
              tbv.getSelectionModel().select(i);
              if (tbv.getSelectionModel().getSelectedItem().getId() == id) {
                  break;
              }
          }
          for (int i = 0; i < tbv1.getItems().size(); i++) {
              tbv1.getSelectionModel().select(i);
              if (tbv1.getSelectionModel().getSelectedItem().getId() == id1) {
                  break;
              }
          }
          tbv.setSelectionModel(null);
          tbv1.setSelectionModel(null);
          
          txtId.setText(Integer.toString(id));
          txtId1.setText(Integer.toString(id1));
          txtInput.setText(Integer.toString(d.getLevelup_val()));
          
          txtInput.requestFocus();
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
    
    public void showData1() {
        ObservableList<BaseStatModel> data = FXMLMainMenuController.dtbs.load();
        
        if (data != null) {
            tbv1.getColumns().clear();
            tbv1.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("id"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Abbrev");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("abbrev"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("name"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("des"));
            tbv1.getColumns().addAll(col);
            
            tbv1.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv1.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void searchData1(KeyEvent event) {
        BaseStatModel s = new BaseStatModel();
        String key = search1.getText();
        
        if (key != "") {
            ObservableList<BaseStatModel> data = FXMLMainMenuController.dtbs.searchItems(key, key, key, key);
            if (data != null) {
                tbv1.getColumns().clear();
                tbv1.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("id"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Abbrev");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("abbrev"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("name"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("des"));
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
    private void select(MouseEvent event) {
        txtId.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void select1(MouseEvent event) {
        txtId1.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        txtId.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        txtId.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        txtId.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        txtId.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void first1Clicked(ActionEvent event) {
        tbv1.getSelectionModel().selectFirst();
        tbv1.requestFocus();
        txtId1.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prev1Clicked(ActionEvent event) {
        tbv1.getSelectionModel().selectAboveCell();
        tbv1.requestFocus();
        txtId1.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void next1Clicked(ActionEvent event) {
        tbv1.getSelectionModel().selectBelowCell();
        tbv1.requestFocus();
        txtId1.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void last1Clicked(ActionEvent event) {
        tbv1.getSelectionModel().selectLast();
        tbv1.requestFocus();
        txtId1.setText(Integer.toString(tbv1.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void clear2Clicked(ActionEvent event) {
        search1.setText("");
        txtId1.setText("");
        showData1();
        search1.requestFocus();
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

    @FXML
    private void clear1Clicked(ActionEvent event) {
        search.setText("");
        txtId.setText("");
        showData();
        search.requestFocus();
    }

    @FXML
    private void validateInput(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        int batas = 10;
        if(!Character.isDigit(test) || txtInput.getText().length() >= batas) event.consume();
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        ClassBaseStatModel n = new ClassBaseStatModel();
        n.setClass_id(Integer.parseInt(txtId.getText()));
        n.setBase_stat_id(Integer.parseInt(txtId1.getText()));
        n.setLevelup_val(Integer.parseInt(txtInput.getText()));
        
        FXMLMainMenuController.dtcbs.setClassBaseStatModel(n);
        if (edited) {
            if (FXMLMainMenuController.dtcbs.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dtcbs.validasi(n.getClass_id(), n.getBase_stat_id()) <= 0) {
            if (FXMLMainMenuController.dtcbs.insert()) {
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
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtInput.setText("");
        txtInput.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }
}
