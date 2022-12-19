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
import pbol.smhprpg.pkg2019130032.Models.RaceModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLRacesInputController implements Initializable {
    private boolean edited = false;
    private int id;

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDes;
    @FXML
    private TextField txtParentrace;
    @FXML
    private TableView<RaceModel> tbv;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }
    
    public void execute(RaceModel d) {
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
          txtParentrace.setText(Integer.toString(id));
          
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

    @FXML
    private void saveClicked(ActionEvent event) {
        RaceModel n = new RaceModel();
        n.setId(id);
        n.setName(txtName.getText()); 
        n.setDes(txtDes.getText());
        if (!txtParentrace.getText().equals("")) n.setParentrace_id(Integer.parseInt(txtParentrace.getText()));
        
        FXMLMainMenuController.dtr.setRaceModel(n);
        if (edited) {
            if (FXMLMainMenuController.dtr.update()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
               a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
                a.showAndWait(); 
            }
        } else if (FXMLMainMenuController.dtr.validasi(n.getId()) <= 0) {
            if (FXMLMainMenuController.dtr.insert()) {
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
        txtParentrace.setText("");
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
        txtParentrace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        txtParentrace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        txtParentrace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        txtParentrace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        txtParentrace.setText(Integer.toString(tbv.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void clear1Clicked(ActionEvent event) {
        search.setText("");
        txtParentrace.setText("");
        showData();
        search.requestFocus();
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
}
