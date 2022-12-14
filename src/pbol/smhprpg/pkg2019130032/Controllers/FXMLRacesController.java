package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.RaceModel;
import pbol.smhprpg.pkg2019130032.Models.RaceBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.RaceTraitModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLRacesController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<RaceModel> tbv;
    @FXML
    private Button btnQuit;
    @FXML
    private TableView<RaceBaseStatModel> tbvd2;
    @FXML
    private TableView<RaceTraitModel> tbvd1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
        showDetails();
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
            
            col = new TableColumn("Parent Race Name");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentraceName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Race Id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentrace_id"));
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
    private void searchData(KeyEvent event) {
        RaceModel s = new RaceModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<RaceModel> data = FXMLMainMenuController.dtr.searchItems(key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("des"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Race Id");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentrace_id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Race Name");
                col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("parentraceName"));
                tbv.getColumns().addAll(col);
                
                tbv.setItems(data);
                tbv.getSelectionModel().selectFirst();
                showDetails();
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
    private void showDetails() {
        ObservableList<RaceTraitModel> data1 = FXMLMainMenuController.dtrt.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<RaceBaseStatModel> data2 = FXMLMainMenuController.dtrbs.load(tbv.getSelectionModel().getSelectedItem().getId());
        
        if (data1 != null && data2 != null) {
            tbvd1.getColumns().clear();
            tbvd1.getItems().clear();
            
            TableColumn col = new TableColumn("Trait Id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("trait_id"));
            tbvd1.getColumns().addAll(col);
            
            col = new TableColumn("Trait Name");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("traitName"));
            tbvd1.getColumns().addAll(col);
            
            tbvd1.setItems(data1);
            
            tbvd2.getColumns().clear();
            tbvd2.getItems().clear();
            
            col = new TableColumn("Base Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("base_stat_id"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("basestatName"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Val");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("val"));
            tbvd2.getColumns().addAll(col);
            
            tbvd2.setItems(data2);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvd1.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        showDetails();
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        showDetails();
    }

    @FXML
    private void addClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLRacesInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        showData(); 
        firstClicked(event);
    }

    @FXML
    private void updateClicked(ActionEvent event) {
        RaceModel s = new RaceModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLRacesInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLRacesInputController isidt = (FXMLRacesInputController)loader.getController();
            // isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        showData();
        firstClicked(event);
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        RaceModel s = new RaceModel();
        s = tbv.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dtr.delete(s.getId())) {
               Alert b = new Alert(Alert.AlertType.INFORMATION,"Item deleted.", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b = new Alert(Alert.AlertType.ERROR,"Failed to delete item.", ButtonType.OK);
               b.showAndWait();
           }
           
           showData();
           firstClicked(event);
        }
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        showDetails();
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        showDetails();
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void firstDClicked1(ActionEvent event) {
        tbvd1.getSelectionModel().selectFirst();
        tbvd1.requestFocus();
    }

    @FXML
    private void prevDClicked1(ActionEvent event) {
        tbvd1.getSelectionModel().selectAboveCell();
        tbvd1.requestFocus();
    }

    @FXML
    private void nextDClicked1(ActionEvent event) {
        tbvd1.getSelectionModel().selectBelowCell();
        tbvd1.requestFocus();
    }

    @FXML
    private void lastBtbClicked1(ActionEvent event) {
        tbvd1.getSelectionModel().selectLast();
        tbvd1.requestFocus();
    }

    @FXML
    private void firstDClicked2(ActionEvent event) {
        tbvd2.getSelectionModel().selectFirst();
        tbvd2.requestFocus();
    }

    @FXML
    private void prevDClicked2(ActionEvent event) {
        tbvd2.getSelectionModel().selectAboveCell();
        tbvd2.requestFocus();
    }

    @FXML
    private void nextDClicked2(ActionEvent event) {
        tbvd2.getSelectionModel().selectBelowCell();
        tbvd2.requestFocus();
    }

    @FXML
    private void lastBtbClicked2(ActionEvent event) {
        tbvd2.getSelectionModel().selectLast();
        tbvd2.requestFocus();
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        search.setText("");
        search.requestFocus();
    }
}
