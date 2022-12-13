package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.ClassModel;
import pbol.smhprpg.pkg2019130032.Models.ClassBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.ClassBattleStatModel;
import pbol.smhprpg.pkg2019130032.Models.ClassTraitModel;
import pbol.smhprpg.pkg2019130032.Models.ClassSkillModel;

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
public class FXMLClassesController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<ClassModel> tbv;
    @FXML
    private TableView<ClassTraitModel> tbvd3;
    @FXML
    private TableView<ClassBaseStatModel> tbvd1;
    @FXML
    private TableView<ClassBattleStatModel> tbvd2;
    @FXML
    private TableView<ClassSkillModel> tbvd4;
    @FXML
    private Button btnQuit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
        showDetails();
    }    

    public void showData() {
        ObservableList<ClassModel> data = FXMLDocumentController.dtc.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
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
    private void searchData(KeyEvent event) {
        ClassModel s = new ClassModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<ClassModel> data = FXMLDocumentController.dtc.searchItems(key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Des");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("des"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Class Id");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclass_id"));
                tbv.getColumns().addAll(col);
                
                col = new TableColumn("Parent Class Name");
                col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("parentclassName"));
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
        ObservableList<ClassBaseStatModel> data1 = FXMLDocumentController.dtcbs.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<ClassBattleStatModel> data2 = FXMLDocumentController.dtcbt.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<ClassTraitModel> data3= FXMLDocumentController.dtct.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<ClassSkillModel> data4 = FXMLDocumentController.dtcs.load(tbv.getSelectionModel().getSelectedItem().getId());
        
        if (data1 != null && data2 != null && data3 != null && data4 != null) {
            tbvd1.getColumns().clear();
            tbvd1.getItems().clear();
            
            TableColumn col = new TableColumn("Base Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("base_stat_id"));
            tbvd1.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("basestatName"));
            tbvd1.getColumns().addAll(col);
            
            col = new TableColumn("Level up Value");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("levelup_val"));
            tbvd1.getColumns().addAll(col);
            
            tbvd1.setItems(data1);
            
            tbvd2.getColumns().clear();
            tbvd2.getItems().clear();
            
            col = new TableColumn("Battle Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("battle_stat_id"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("battlestatName"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Scale");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("scale"));
            tbvd2.getColumns().addAll(col);
            
            tbvd2.setItems(data2);
            
            tbvd3.getColumns().clear();
            tbvd3.getItems().clear();
            
            col = new TableColumn("Trait Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("trait_id"));
            tbvd3.getColumns().addAll(col);
            
            col = new TableColumn("Trait Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("traitName"));
            tbvd3.getColumns().addAll(col);
            
            tbvd3.setItems(data3);
            
            tbvd4.getColumns().clear();
            tbvd4.getItems().clear();
            
            col = new TableColumn("Skill Id");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("skill_id"));
            tbvd4.getColumns().addAll(col);
            
            col = new TableColumn("Skill Name");
            col.setCellValueFactory(new PropertyValueFactory<ClassModel, String>("skillName"));
            tbvd4.getColumns().addAll(col);
            
            tbvd4.setItems(data4);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLClassesInput.fxml"));
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
        ClassModel s = new ClassModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLClassesInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLClassesInputController isidt = (FXMLClassesInputController)loader.getController();
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
        ClassModel s = new ClassModel();
        s = tbv.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLDocumentController.dtc.delete(s.getId())) {
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
    private void firstDClicked3(ActionEvent event) {
        tbvd3.getSelectionModel().selectFirst();
        tbvd3.requestFocus();
    }

    @FXML
    private void prevDClicked3(ActionEvent event) {
        tbvd3.getSelectionModel().selectAboveCell();
        tbvd3.requestFocus();
    }

    @FXML
    private void nextDClicked3(ActionEvent event) {
        tbvd3.getSelectionModel().selectBelowCell();
        tbvd3.requestFocus();
    }
    
    @FXML
    private void lastBtbClicked3(ActionEvent event) {
        tbvd3.getSelectionModel().selectLast();
        tbvd3.requestFocus();
    }

    @FXML
    private void firstDClicked4(ActionEvent event) {
        tbvd4.getSelectionModel().selectFirst();
        tbvd4.requestFocus();
    }

    @FXML
    private void prevDClicked4(ActionEvent event) {
        tbvd4.getSelectionModel().selectAboveCell();
        tbvd4.requestFocus();
    }

    @FXML
    private void nextDClicked4(ActionEvent event) {
        tbvd4.getSelectionModel().selectBelowCell();
        tbvd4.requestFocus();
    }
    
    @FXML
    private void lastBtbClicked4(ActionEvent event) {
        tbvd4.getSelectionModel().selectLast();
        tbvd4.requestFocus();
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }
}
