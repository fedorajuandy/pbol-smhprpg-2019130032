package pbol.smhprpg.pkg2019130032;

import java.io.IOException;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.BaseToBattleStatModel;

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
public class FXMLBaseStatsController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<BaseStatModel> tbvbs;
    @FXML
    private Button btnQuit;
    @FXML
    private TableView<BaseToBattleStatModel> tbvbtb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbvbs.getSelectionModel().selectFirst();
        showDetails();
    }
    
    public void showData() {
        ObservableList<BaseStatModel> data = FXMLDocumentController.dtbs.load();
        
        if(data != null) {
            tbvbs.getColumns().clear();
            tbvbs.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("id"));
            tbvbs.getColumns().addAll(col);
            
            col = new TableColumn("Abbrev");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("abbrev"));
            tbvbs.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("name"));
            tbvbs.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("des"));
            tbvbs.getColumns().addAll(col);
            
            tbvbs.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvbs.getScene().getWindow().hide();;
        }
    }
    
    @FXML
    public void showDetails() {
        ObservableList<BaseToBattleStatModel> data = FXMLDocumentController.dtbtb.load(tbvbs.getSelectionModel().getSelectedItem().getId());
        
        if(data != null) {
            tbvbtb.getColumns().clear();
            tbvbtb.getItems().clear();
            
            TableColumn col = new TableColumn("Battle Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("battle_stat_id"));
            tbvbtb.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("battleStatName"));
            tbvbtb.getColumns().addAll(col);
            
            col = new TableColumn("Scale");
            col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("scale"));
            tbvbtb.getColumns().addAll(col);
            
            tbvbtb.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvbtb.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void searchData(KeyEvent event) {
        BaseStatModel s = new BaseStatModel();
        String key = search.getText();
        
        if(key != "") {
            ObservableList<BaseStatModel> data = FXMLDocumentController.dtbs.searchItems(key, key, key);
            if(data != null) {
                tbvbs.getColumns().clear();
                tbvbs.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("id"));
                tbvbs.getColumns().addAll(col);

                col = new TableColumn("Abbrev");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("abbrev"));
                tbvbs.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("name"));
                tbvbs.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<BaseStatModel, String>("des"));
                tbvbs.getColumns().addAll(col);
                
                tbvbs.setItems(data);
                tbvbs.getSelectionModel().selectFirst();
                showDetails();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
                a.showAndWait();
                tbvbs.getScene().getWindow().hide();;
            }            
        } else {
           showData();
        }
    }

    @FXML
    private void firstBsClicked(ActionEvent event) {
        tbvbs.getSelectionModel().selectFirst();
        tbvbs.requestFocus();
        showDetails();
    }

    @FXML
    private void prevBsClicked(ActionEvent event) {
        tbvbs.getSelectionModel().selectAboveCell();
        tbvbs.requestFocus();
        showDetails();
    }

    @FXML
    private void addBsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBaseStatsInput.fxml"));
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
        firstBsClicked(event);
    }

    @FXML
    private void updateBsClicked(ActionEvent event) {
        BaseStatModel s = new BaseStatModel();
        s = tbvbs.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLBaseStatsInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLBaseStatsInputController isidt = (FXMLBaseStatsInputController)loader.getController();
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
        firstBsClicked(event);
    }

    @FXML
    private void deleteBsClicked(ActionEvent event) {
        BaseStatModel s = new BaseStatModel();
        s = tbvbs.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult() == ButtonType.YES) {
           if(FXMLDocumentController.dtbs.delete(s.getId())) {
               Alert b = new Alert(Alert.AlertType.INFORMATION,"Item deleted.", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b = new Alert(Alert.AlertType.ERROR,"Failed to delete item.", ButtonType.OK);
               b.showAndWait();
           }
           
           showData();
           firstBsClicked(event);
        }
    }

    @FXML
    private void nextBsClicked(ActionEvent event) {
        tbvbs.getSelectionModel().selectBelowCell();
        tbvbs.requestFocus();
        showDetails();
    }

    @FXML
    private void lastBsClicked(ActionEvent event) {
        tbvbs.getSelectionModel().selectLast();
        tbvbs.requestFocus();
        showDetails();
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void firstBtbClicked(ActionEvent event) {
        tbvbtb.getSelectionModel().selectFirst();
        tbvbtb.requestFocus();
    }

    @FXML
    private void prevBtbClicked(ActionEvent event) {
        tbvbtb.getSelectionModel().selectAboveCell();
        tbvbtb.requestFocus();
    }

    @FXML
    private void nextBtbClicked(ActionEvent event) {
        tbvbtb.getSelectionModel().selectBelowCell();
        tbvbtb.requestFocus();
    }

    @FXML
    private void lastBtbClicked(ActionEvent event) {
        tbvbtb.getSelectionModel().selectLast();
        tbvbtb.requestFocus();
    }
}
