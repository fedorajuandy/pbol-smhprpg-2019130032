package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.TraitModel;
import pbol.smhprpg.pkg2019130032.Models.TraitBaseStatModel;

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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLTraitsController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<TraitModel> tbv;
    @FXML
    private TableView<TraitBaseStatModel> tbvd;
    @FXML
    private Button btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
        showDetails();
    }
    
    public void showData() {
        ObservableList<TraitModel> data = FXMLMainMenuController.dtt.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Des");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("des"));
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
        TraitModel s = new TraitModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<TraitModel> data = FXMLMainMenuController.dtt.searchItems(key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("des"));
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
    public void showDetails() {
        ObservableList<TraitBaseStatModel> data = FXMLMainMenuController.dttbs.load(tbv.getSelectionModel().getSelectedItem().getId());
        
        if (data != null) {
            tbvd.getColumns().clear();
            tbvd.getItems().clear();
            
            TableColumn col = new TableColumn("Base Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("base_stat_id"));
            tbvd.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("basestatName"));
            tbvd.getColumns().addAll(col);
            
            col = new TableColumn("Val");
            col.setCellValueFactory(new PropertyValueFactory<TraitModel, String>("val"));
            tbvd.getColumns().addAll(col);
            
            tbvd.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvd.getScene().getWindow().hide();;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLTraitsInput.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("SMHPRPG");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
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
        TraitModel s = new TraitModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLTraitsInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLTraitsInputController isidt = (FXMLTraitsInputController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("SMHPRPG");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
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
        TraitModel s = new TraitModel();
        s = tbv.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dtt.delete(s.getId())) {
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
    private void firstDClicked(ActionEvent event) {
        tbvd.getSelectionModel().selectFirst();
        tbvd.requestFocus();
    }

    @FXML
    private void prevDClicked(ActionEvent event) {
        tbvd.getSelectionModel().selectAboveCell();
        tbvd.requestFocus();
    }

    @FXML
    private void nextDClicked(ActionEvent event) {
        tbvd.getSelectionModel().selectBelowCell();
        tbvd.requestFocus();
    }

    @FXML
    private void lastBtbClicked(ActionEvent event) {
        tbvd.getSelectionModel().selectLast();
        tbvd.requestFocus();
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        search.setText("");
        showData();
        showDetails();
        search.requestFocus();
    }

    @FXML
    private void addTBsClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLTraitBaseStats.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Trait Base Stats");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
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
    private void updateTBsClicked(ActionEvent event) {
        TraitBaseStatModel s = new TraitBaseStatModel();
        s = tbvd.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLTraitBaseStats.fxml"));
            Parent root = (Parent)loader.load();
            FXMLTraitBaseStatsController isidt = (FXMLTraitBaseStatsController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Trait Base Stats");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
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
    private void deleteTBsClicked(ActionEvent event) {
        TraitBaseStatModel s = new TraitBaseStatModel();
        s = tbvd.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dttbs.delete(s.getTrait_id(), s.getBase_stat_id())) {
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
}
