package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;
import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;
import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
import pbol.smhprpg.pkg2019130032.Models.HeroTraitModel;
import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroTraitModel;

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
    private Button btnQuit;
    @FXML
    private ImageView img;
    @FXML
    private TableView<HeroClassModel> tbvHC;
    @FXML
    private TableView<HeroBaseStatModel> tbvHBS;
    @FXML
    private TableView<HeroSkillModel> tbvHCS;
    @FXML
    private TableView<HeroEffectModel> tbvHE;
    @FXML
    private TableView<HeroTraitModel> tbvHT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        tbv.getSelectionModel().selectFirst();
        showDetails();
        showImage();
    }    

    public void showData() {
        ObservableList<HeroModel> data = FXMLMainMenuController.dth.load();
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("name"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Gender");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("gender"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Description");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("des"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Race Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Race name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Curr class id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Experience");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("exp"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Curr class name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Stat points");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("stat_points"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Skill points");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skill_points"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("User id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("user_id"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Username");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("username"));
            tbv.getColumns().addAll(col);
            
            tbv.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv.getScene().getWindow().hide();;
        }
    }
    
    public void showImage(){
        Image gambar = null;
        
        try {
            gambar = new Image(new FileInputStream(tbv.getSelectionModel().getSelectedItem().getImage()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLHeroesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        img.setImage(gambar);
    }

    @FXML
    private void searchData(KeyEvent event) {
        HeroModel s = new HeroModel();
        String key = search.getText();
        
        if (key != "") {
            ObservableList<HeroModel> data = FXMLMainMenuController.dth.searchItems(key, key, key, key, key, key, key, key, key, key, key, key, key, key);
            if (data != null) {
                tbv.getColumns().clear();
                tbv.getItems().clear();
                
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("name"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Gender");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("gender"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Description");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("des"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Race Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race_id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Race name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Curr class id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("raceName"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Experience");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("exp"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Curr class name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Stat points");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("stat_points"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Skill points");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skill_points"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("User id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("user_id"));
                tbv.getColumns().addAll(col);

                col = new TableColumn("Username");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("username"));
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
            tbvHBS.getColumns().clear();
            tbvHBS.getItems().clear();
            
            TableColumn col = new TableColumn("Base Stat Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("base_stat_id"));
            tbvHBS.getColumns().addAll(col);
            
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("basestatName"));
            tbvHBS.getColumns().addAll(col);
            
            col = new TableColumn("Value");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("val"));
            tbvHBS.getColumns().addAll(col);
            
            tbvHBS.setItems(data1);
            
            tbvHC.getColumns().clear();
            tbvHC.getItems().clear();
            
            col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id"));
            tbvHC.getColumns().addAll(col);
            
            col = new TableColumn("Class Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("class_id"));
            tbvHC.getColumns().addAll(col);
            
            col = new TableColumn("Class Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbvHC.getColumns().addAll(col);
            
            col = new TableColumn("Mastery Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("mastery_lv"));
            tbvHC.getColumns().addAll(col);
            
            tbvHC.setItems(data2);
            
            tbvHE.getColumns().clear();
            tbvHE.getItems().clear();
            
            col = new TableColumn("Effect Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("effect_id"));
            tbvHE.getColumns().addAll(col);
            
            col = new TableColumn("Effect Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("effectName"));
            tbvHE.getColumns().addAll(col);
            
            col = new TableColumn("Duration Left");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("duration_left"));
            tbvHE.getColumns().addAll(col);
            
            tbvHE.setItems(data3);
            
            tbvHCS.getColumns().clear();
            tbvHCS.getItems().clear();
            
            col = new TableColumn("Skill Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skill_id"));
            tbvHCS.getColumns().addAll(col);
            
            col = new TableColumn("Skill Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skillName"));
            tbvHCS.getColumns().addAll(col);
            
            col = new TableColumn("Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("lv"));
            tbvHCS.getColumns().addAll(col);
            
            tbvHCS.setItems(data4);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvHBS.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        showDetails();
        showImage();
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        showDetails();
        showImage();
    }

    @FXML
    private void addClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesInput.fxml"));
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
        HeroModel s = new HeroModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesInput.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroesInputController isidt = (FXMLHeroesInputController)loader.getController();
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
        showImage();
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        showDetails();
        showImage();
    }
    
    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        search.setText("");
        showData();
        showDetails();
        showImage();
        search.requestFocus();
    }

    @FXML
    private void addHEClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroEffects.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Effects");
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
    private void updateHEClicked(ActionEvent event) {
        HeroEffectModel s = new HeroEffectModel();
        s = tbvHE.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroEffects.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroEffectsController isidt = (FXMLHeroEffectsController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Effects");
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
    private void deleteHEClicked(ActionEvent event) {
        HeroEffectModel s = new HeroEffectModel();
        s = tbvHE.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dthe.delete(s.getHero_id(), s.getEffect_id())) {
               Alert b = new Alert(Alert.AlertType.INFORMATION,"Item deleted.", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b = new Alert(Alert.AlertType.ERROR,"Failed to delete item.", ButtonType.OK);
               b.showAndWait();
           }
           
           showData();
           firstClicked(event);
           showDetails();
        }
    }

    @FXML
    private void addHBSClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroBaseStats.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Base Stats");
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
    private void updateHBSClicked(ActionEvent event) {
        HeroBaseStatModel s = new HeroBaseStatModel();
        s = tbvHBS.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroBaseStats.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroBaseStatsController isidt = (FXMLHeroBaseStatsController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Base Stats");
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
    private void deleteHBSClicked(ActionEvent event) {
        HeroBaseStatModel s = new HeroBaseStatModel();
        s = tbvHBS.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dthbs.delete(s.getHero_id(), s.getBase_stat_id())) {
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
    private void addHCClicked(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroClasses.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Classes");
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
    private void updateHCClicked(ActionEvent event) {
        HeroClassModel s = new HeroClassModel();
        s = tbvHC.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroClasses.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroClassesController isidt = (FXMLHeroClassesController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Classes");
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
    private void deleteHCClicked(ActionEvent event) {
        HeroClassModel s = new HeroClassModel();
        s = tbvHC.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dthc.delete(s.getHero_id(), s.getClass_id())) {
               Alert b = new Alert(Alert.AlertType.INFORMATION,"Item deleted.", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b = new Alert(Alert.AlertType.ERROR,"Failed to delete item.", ButtonType.OK);
               b.showAndWait();
           }
           
           showData();
           firstClicked(event);
           showDetails();
        }
    }

    @FXML
    private void addHCSClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroSkills.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("HeroSkills");
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
    private void updateHCSClicked(ActionEvent event) {
        HeroSkillModel s = new HeroSkillModel();
        s = tbvHCS.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroSkills.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroSkillsController isidt = (FXMLHeroSkillsController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Hero Skills");
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
    private void deleteHCSClicked(ActionEvent event) {
        HeroSkillModel s = new HeroSkillModel();
        s = tbvHCS.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dths.delete(s.getHero_class_id(), s.getSkill_id())) {
               Alert b = new Alert(Alert.AlertType.INFORMATION,"Item deleted.", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b = new Alert(Alert.AlertType.ERROR,"Failed to delete item.", ButtonType.OK);
               b.showAndWait();
           }
           
           showData();
           firstClicked(event);
           showDetails();
        }
    }

    @FXML
    private void firstHEClicked(ActionEvent event) {
        tbvHE.getSelectionModel().selectFirst();
        tbvHE.requestFocus();
    }

    @FXML
    private void prevHEClicked(ActionEvent event) {
        tbvHE.getSelectionModel().selectAboveCell();
        tbvHE.requestFocus();
    }

    @FXML
    private void nextHEClicked(ActionEvent event) {
        tbvHE.getSelectionModel().selectBelowCell();
        tbvHE.requestFocus();
    }

    @FXML
    private void lastHEClicked(ActionEvent event) {
        tbvHE.getSelectionModel().selectLast();
        tbvHE.requestFocus();
    }

    @FXML
    private void firstHBSClicked(ActionEvent event) {
        tbvHBS.getSelectionModel().selectFirst();
        tbvHBS.requestFocus();
    }

    @FXML
    private void prevHBSClicked(ActionEvent event) {
        tbvHBS.getSelectionModel().selectAboveCell();
        tbvHBS.requestFocus();
    }

    @FXML
    private void nextHBSClicked(ActionEvent event) {
        tbvHBS.getSelectionModel().selectBelowCell();
        tbvHBS.requestFocus();
    }

    @FXML
    private void lastHBSClicked(ActionEvent event) {
        tbvHBS.getSelectionModel().selectLast();
        tbvHBS.requestFocus();
    }

    @FXML
    private void firstHCClicked(ActionEvent event) {
        tbvHC.getSelectionModel().selectFirst();
        tbvHC.requestFocus();
    }

    @FXML
    private void prev(ActionEvent event) {
        tbvHC.getSelectionModel().selectAboveCell();
        tbvHC.requestFocus();
    }

    @FXML
    private void nextHCClicked(ActionEvent event) {
        tbvHC.getSelectionModel().selectBelowCell();
        tbvHC.requestFocus();
    }

    @FXML
    private void lastHCClicked(ActionEvent event) {
        tbvHC.getSelectionModel().selectLast();
        tbvHC.requestFocus();
    }

    @FXML
    private void firstHCSClicked(ActionEvent event) {
        tbvHCS.getSelectionModel().selectFirst();
        tbvHCS.requestFocus();
    }

    @FXML
    private void prevHCSClicked(ActionEvent event) {
        tbvHCS.getSelectionModel().selectAboveCell();
        tbvHCS.requestFocus();
    }

    @FXML
    private void nextHCSClicked(ActionEvent event) {
        tbvHCS.getSelectionModel().selectBelowCell();
        tbvHCS.requestFocus();
    }

    @FXML
    private void lastHCSClicked(ActionEvent event) {
        tbvHCS.getSelectionModel().selectLast();
        tbvHCS.requestFocus();
    }

    @FXML
    private void firstHTClicked(ActionEvent event) {
        tbvHT.getSelectionModel().selectFirst();
        tbvHT.requestFocus();
    }

    @FXML
    private void prevHTClicked(ActionEvent event) {
        tbvHT.getSelectionModel().selectAboveCell();
        tbvHT.requestFocus();
    }

    @FXML
    private void nextHTClicked(ActionEvent event) {
        tbvHT.getSelectionModel().selectBelowCell();
        tbvHT.requestFocus();
    }

    @FXML
    private void lastHTClicked(ActionEvent event) {
        tbvHT.getSelectionModel().selectLast();
        tbvHT.requestFocus();
    }

    @FXML
    private void addHTClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroTraits.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Race Traits");
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
    private void deleteHTClicked(ActionEvent event) {
        HeroTraitModel s = new HeroTraitModel();
        s = tbvHT.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete item?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        
        if (a.getResult() == ButtonType.YES) {
           if (FXMLMainMenuController.dtht.delete(s.getHero_id(), s.getTrait_id())) {
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
