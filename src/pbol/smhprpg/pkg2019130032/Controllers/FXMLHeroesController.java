package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;
import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;

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
public class FXMLHeroesController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableView<HeroModel> tbv;
    @FXML
    private TableView<HeroEffectModel> tbvd3;
    @FXML
    private TableView<HeroBaseStatModel> tbvd1;
    @FXML
    private Button btnQuit;
    @FXML
    private TableView<HeroSkillModel> tbvd4;
    @FXML
    private TableView<HeroClassModel> tbvd2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
        showDetails();
    }    

    public void showData() {
        ObservableList<HeroModel> data = FXMLMainMenuController.dth.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Race Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Curr class id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Curr class name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Parent Class Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("parentclass_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Gender");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("gender"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("lv"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Exp");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("exp"));
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
        HeroModel s = new HeroModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<HeroModel> data = FXMLMainMenuController.dth.searchItems(key, key, key, key, key, key, key, key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Race Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race_id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Curr class id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Curr class name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Parent Class Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("parentclass_id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Gender");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("gender"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Lv");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("lv"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Exp");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("exp"));
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
        ObservableList<HeroBaseStatModel> data1 = FXMLMainMenuController.dthbs.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroClassModel> data2 = FXMLMainMenuController.dthc.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroEffectModel> data3= FXMLMainMenuController.dthe.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroSkillModel> data4 = FXMLMainMenuController.dths.load(tbv.getSelectionModel().getSelectedItem().getId());
        
        if (data1 != null && data2 != null && data3 != null && data4 != null) {
            tbvd1.getColumns().clear();
            tbvd1.getItems().clear();
            
            TableColumn col = new TableColumn("Base Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("base_stat_id"));
            tbvd1.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("basestatName"));
            tbvd1.getColumns().addAll(col);
            
            col = new TableColumn("Value");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("val"));
            tbvd1.getColumns().addAll(col);
            
            tbvd1.setItems(data1);
            
            tbvd2.getColumns().clear();
            tbvd2.getItems().clear();
            
            col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Class Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("class_id"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Class Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbvd2.getColumns().addAll(col);
            
            col = new TableColumn("Mastery Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("mastery_lv"));
            tbvd2.getColumns().addAll(col);
            
            tbvd2.setItems(data2);
            
            tbvd3.getColumns().clear();
            tbvd3.getItems().clear();
            
            col = new TableColumn("Effect Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("effect_id"));
            tbvd3.getColumns().addAll(col);
            
            col = new TableColumn("Effect Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("effectName"));
            tbvd3.getColumns().addAll(col);
            
            col = new TableColumn("Duration Left");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("duration_left"));
            tbvd3.getColumns().addAll(col);
            
            tbvd3.setItems(data3);
            
            tbvd4.getColumns().clear();
            tbvd4.getItems().clear();
            
            col = new TableColumn("Skill Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skill_id"));
            tbvd4.getColumns().addAll(col);
            
            col = new TableColumn("Skill Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skillName"));
            tbvd4.getColumns().addAll(col);
            
            col = new TableColumn("Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("lv"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesInput.fxml"));
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
        HeroModel s = new HeroModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroesInputController isidt = (FXMLHeroesInputController)loader.getController();
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
        HeroModel s = new HeroModel();
        s = tbv.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dth.delete(s.getId())) {
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
        tbvd4.getSelectionModel().selectFirst();
        tbvd4.requestFocus();
    }

    @FXML
    private void prevDClicked3(ActionEvent event) {
        tbvd4.getSelectionModel().selectAboveCell();
        tbvd4.requestFocus();
    }

    @FXML
    private void nextDClicked3(ActionEvent event) {
        tbvd4.getSelectionModel().selectBelowCell();
        tbvd4.requestFocus();
    }
    
    @FXML
    private void lastBtbClicked3(ActionEvent event) {
        tbvd4.getSelectionModel().selectLast();
        tbvd4.requestFocus();
    }

    private void prevDClicked4(ActionEvent event) {
        tbvd4.getSelectionModel().selectAboveCell();
        tbvd4.requestFocus();
    }
    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void firstDClicked(ActionEvent event) {
        tbvd3.getSelectionModel().selectFirst();
        tbvd3.requestFocus();
    }

    @FXML
    private void prevDClicked(ActionEvent event) {
    }

    @FXML
    private void nextDClicked(ActionEvent event) {
        tbvd3.getSelectionModel().selectBelowCell();
        tbvd3.requestFocus();
    }

    @FXML
    private void lastBtbClicked(ActionEvent event) {
        tbvd3.getSelectionModel().selectLast();
        tbvd3.requestFocus();
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        search.setText("");
        search.requestFocus();
    }
}
