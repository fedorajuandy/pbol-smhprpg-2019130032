package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.BattleStatModel;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLBattleStatsController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<BattleStatModel> tbv;
    @FXML
    private Button btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
    }    

    public void showData() {
        ObservableList<BattleStatModel> data = FXMLMainMenuController.dtbt.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Abbrev");
            col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("abbrev"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("des"));
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
        BattleStatModel s = new BattleStatModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<BattleStatModel> data = FXMLMainMenuController.dtbt.searchItems(key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Abbrev");
                col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("abbrev"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<BattleStatModel, String>("des"));
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
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
    }

    @FXML
    private void addClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLBattleStatsInput.fxml"));
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
        BattleStatModel s = new BattleStatModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLBattleStatsInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLBattleStatsInputController isidt = (FXMLBattleStatsInputController)loader.getController();
            isidt.execute(s);
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
        BattleStatModel s = new BattleStatModel();
        s = tbv.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dtbt.delete(s.getId())) {
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
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        search.setText("");
        search.requestFocus();
    }
}
