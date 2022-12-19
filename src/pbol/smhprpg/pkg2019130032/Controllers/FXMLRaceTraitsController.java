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
import pbol.smhprpg.pkg2019130032.Models.RaceTraitModel;
import pbol.smhprpg.pkg2019130032.Models.RaceModel;
import pbol.smhprpg.pkg2019130032.Models.TraitModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLRaceTraitsController implements Initializable {
    
    @FXML
    private TextField search1;
    @FXML
    private TableView<RaceModel> tbv;
    @FXML
    private TableView<TraitModel> tbv1;
    @FXML
    private TextField search;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtId1;
    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        showData1();
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
        ObservableList<TraitModel> data = FXMLMainMenuController.dtt.load();
        
        if (data != null) {
            tbv1.getColumns().clear();
            tbv1.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("id"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("name"));
            tbv1.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("des"));
            tbv1.getColumns().addAll(col);
            
            tbv1.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv1.getScene().getWindow().hide();;
        }
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
    private void searchData1(KeyEvent event) {
        TraitModel s = new TraitModel();
        String key = search1.getText();
        
        if (key != "") {
            ObservableList<TraitModel> data = FXMLMainMenuController.dtt.searchItems(key, key, key);
            if (data != null) {
                tbv1.getColumns().clear();
                tbv1.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("id"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("name"));
                tbv1.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("des"));
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
    private void clear1Clicked(ActionEvent event) {
        search.setText("");
        txtId.setText("");
        showData();
        search.requestFocus();
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        RaceTraitModel n = new RaceTraitModel();
        n.setRace_id(Integer.parseInt(txtId.getText()));
        n.setTrait_id(Integer.parseInt(txtId1.getText()));
        
        FXMLMainMenuController.dtrt.setRaceTraitModel(n);
        if (FXMLMainMenuController.dtrt.validasi(n.getRace_id(), n.getTrait_id()) <= 0) {
            if (FXMLMainMenuController.dtrt.insert()) {
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Data saved.", ButtonType.OK);
               a.showAndWait();
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
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }
    
}
