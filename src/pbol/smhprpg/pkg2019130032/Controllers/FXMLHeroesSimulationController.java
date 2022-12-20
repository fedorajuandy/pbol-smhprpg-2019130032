package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static pbol.smhprpg.pkg2019130032.Controllers.FXMLLoginController.username;
import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.BattleStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;
import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;
import pbol.smhprpg.pkg2019130032.Models.HeroTraitModel;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLHeroesSimulationController implements Initializable {
    LevelModel level = FXMLMainMenuController.dtl.load();
    int user_id;

    @FXML
    private TextField txtName;
    @FXML
    private TableView<HeroModel> tbv;
    @FXML
    private Button btnQuit;
    @FXML
    private TableView<HeroBaseStatModel> tbvBase;
    @FXML
    private TextField txtStatpoints;
    @FXML
    private TextField txtStat;
    @FXML
    private TableView<BattleStatModel> tbvBattle;
    @FXML
    private TableView<HeroEffectModel> tbvEffect;
    @FXML
    private TableView<HeroTraitModel> tbvTrait;
    @FXML
    private Label lbGender;
    @FXML
    private TextField txtLv;
    @FXML
    private TableView<HeroSkillModel> tbvSkill;
    @FXML
    private TextField txtSkill;
    @FXML
    private TextField txtSkillpoints;
    @FXML
    private TextField txtExp;
    @FXML
    private TextField txtRace;
    @FXML
    private ImageView img;
    @FXML
    private TableView<HeroClassModel> tbvClass;
    @FXML
    private TextField txtClass;
    @FXML
    private Label lbUsername;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT * FROM users where username = '" + username + "'");
            lbUsername.setText(username);
            ObservableList<HeroModel> dt = FXMLMainMenuController.dth.load();

            while (rs.next()) {
                user_id = rs.getInt("id");
                if (rs.getInt("role") == 1) {
                    dt = FXMLMainMenuController.dth.loadUser(user_id);
                }
            }
            showData(dt);
            tbv.getSelectionModel().selectFirst();
            showDetails();
            tbvClass.getSelectionModel().selectFirst();
            tbvSkill.getSelectionModel().selectFirst();
            tbvBase.getSelectionModel().selectFirst();
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
}    

    public void showData(ObservableList<HeroModel> dt) {
        ObservableList<HeroModel> data = dt;
        
        if (data != null) {
            tbv.getColumns().clear();
            tbv.getItems().clear();
            
            TableColumn col = new TableColumn("Name");
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
            
            col = new TableColumn("Curr class name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Experience");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("exp"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Stat points");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("stat_points"));
            tbv.getColumns().addAll(col);
            
            col = new TableColumn("Skill points");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("skill_points"));
            tbv.getColumns().addAll(col);
            
            tbv.setItems(data);
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbv.getScene().getWindow().hide();
        }
    }
    
    public void showImage(){
        Image gambar = null;
        
        try {
            String temp = tbv.getSelectionModel().getSelectedItem().getImage();
            if (temp.equals("")) {
                temp = "src/pbol/smhprpg/pkg2019130032/imgs/placeholder.png";
            }
            gambar = new Image(new FileInputStream(temp));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLHeroesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        img.setImage(gambar);
    }

    @FXML
    private void prevClicked(ActionEvent event) {
        tbv.getSelectionModel().selectAboveCell();
        tbv.requestFocus();
        showDetails();
        tbvClass.getSelectionModel().selectFirst();
        tbvSkill.getSelectionModel().selectFirst();
        tbvBase.getSelectionModel().selectFirst();
    }

    @FXML
    private void nextClicked(ActionEvent event) {
        tbv.getSelectionModel().selectBelowCell();
        tbv.requestFocus();
        showDetails();
        tbvClass.getSelectionModel().selectFirst();
        tbvSkill.getSelectionModel().selectFirst();
        tbvBase.getSelectionModel().selectFirst();
    }

    @FXML
    private void firstClicked(ActionEvent event) {
        tbv.getSelectionModel().selectFirst();
        tbv.requestFocus();
        showDetails();
        tbvClass.getSelectionModel().selectFirst();
        tbvSkill.getSelectionModel().selectFirst();
        tbvBase.getSelectionModel().selectFirst();
    }

    @FXML
    private void addClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesCreate.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Add Hero");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        showData(FXMLMainMenuController.dth.load()); 
        firstClicked(event);
    }

    @FXML
    private void lastClicked(ActionEvent event) {
        tbv.getSelectionModel().selectLast();
        tbv.requestFocus();
        showDetails();
        tbvClass.getSelectionModel().selectFirst();
        tbvSkill.getSelectionModel().selectFirst();
        tbvBase.getSelectionModel().selectFirst();
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void saveClicked(ActionEvent event) {
        HeroBaseStatModel n = tbvBase.getSelectionModel().getSelectedItem();
        n.setVal(Integer.parseInt(txtStatpoints.getText()));
        
        FXMLMainMenuController.dthbs.setHeroBaseStatModel(n);
        if (FXMLMainMenuController.dthbs.update()) {
           Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
           a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
            a.showAndWait(); 
        }
        
        HeroSkillModel m = tbvSkill.getSelectionModel().getSelectedItem();
        if (!txtSkillpoints.getText().equals("")) m.setLv(Integer.parseInt(txtSkillpoints.getText()));
        else m.setLv(1);
        
        FXMLMainMenuController.dths.setHeroSkillModel(m);
        if (FXMLMainMenuController.dths.update()) {
           Alert a = new Alert(Alert.AlertType.INFORMATION, "Data successfully changed." , ButtonType.OK);
           a.showAndWait();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data changes failed.", ButtonType.OK);
            a.showAndWait(); 
        }
        
        firstClicked(event);
    }

    @FXML
    private void minStat(ActionEvent event) {
        if (Integer.parseInt(txtStat.getText()) == 0) {
            txtStatpoints.setText(Integer.toString(Integer.parseInt(txtStatpoints.getText()) - 1));
            txtStat.setText(Integer.toString(Integer.parseInt(txtStat.getText()) + 1));
        }
    }

    @FXML
    private void plusStat(ActionEvent event) {
        if (Integer.parseInt(txtStat.getText()) > 0) {
            txtStatpoints.setText(Integer.toString(Integer.parseInt(txtStatpoints.getText()) + 1));
            txtStat.setText(Integer.toString(Integer.parseInt(txtStat.getText()) - 1));
        }
    }

    @FXML
    private void showDetails() {
        ObservableList<HeroBaseStatModel> data1 = FXMLMainMenuController.dthbs.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroClassModel> data2 = FXMLMainMenuController.dthc.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroEffectModel> data3= FXMLMainMenuController.dthe.load(tbv.getSelectionModel().getSelectedItem().getId());
        ObservableList<HeroTraitModel> data5= FXMLMainMenuController.dtht.load(tbv.getSelectionModel().getSelectedItem().getId());
        
        if (data1 != null && data2 != null && data3 != null && data5 != null) {
            tbvBase.getColumns().clear();
            tbvBase.getItems().clear();
            
            TableColumn col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("basestatName"));
            tbvBase.getColumns().addAll(col);
            
            col = new TableColumn("Value");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("val"));
            tbvBase.getColumns().addAll(col);
            
            tbvBase.setItems(data1);
            
            tbvClass.getColumns().clear();
            tbvClass.getItems().clear();
            
            col = new TableColumn("Class Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("className"));
            tbvClass.getColumns().addAll(col);
            
            col = new TableColumn("Mastery Lv");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("mastery_lv"));
            tbvClass.getColumns().addAll(col);
            
            tbvClass.setItems(data2);
            
            tbvEffect.getColumns().clear();
            tbvEffect.getItems().clear();
            
            col = new TableColumn("Effect Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("effectName"));
            tbvEffect.getColumns().addAll(col);
            
            col = new TableColumn("Duration Left");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("duration_left"));
            tbvEffect.getColumns().addAll(col);
            
            tbvEffect.setItems(data3);
            
            tbvTrait.getColumns().clear();
            tbvTrait.getItems().clear();
            
            col = new TableColumn("Trait Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("traitName"));
            tbvTrait.getColumns().addAll(col);
            
            tbvTrait.setItems(data5);
            showImage();
         } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Empty data", ButtonType.OK);
            a.showAndWait();
            tbvBase.getScene().getWindow().hide();
        }
        
        HeroModel hm = tbv.getSelectionModel().getSelectedItem();
        
        txtName.setText(hm.getName());
        txtRace.setText(hm.getRaceName());
        int curr_class = hm.getCurr_class_id();
        for (int i = 0; i < tbvClass.getItems().size(); i++) {
            tbvClass.getSelectionModel().select(i);
            if (tbvClass.getSelectionModel().getSelectedItem().getId() == curr_class) {
                break;
            }
        }
        txtStat.setText(Integer.toString(hm.getStat_points()));
        txtSkill.setText(Integer.toString(hm.getSkill_points()));
        lbGender.setText(hm.getGender());
        txtExp.setText(Integer.toString(hm.getExp()));
        txtLv.setText(Integer.toString(FXMLMainMenuController.dtl.levelUp(hm, level)));
    }

    @FXML
    private void minSkill(ActionEvent event) {
        if (Integer.parseInt(txtSkill.getText()) == 0) {
            txtSkillpoints.setText(Integer.toString(Integer.parseInt(txtSkillpoints.getText()) - 1));
            txtSkill.setText(Integer.toString(Integer.parseInt(txtSkill.getText()) + 1));
        }
    }

    @FXML
    private void plusSkill(ActionEvent event) {
        if (Integer.parseInt(txtSkill.getText()) > 0) {
            txtSkillpoints.setText(Integer.toString(Integer.parseInt(txtSkillpoints.getText()) + 1));
            txtSkill.setText(Integer.toString(Integer.parseInt(txtSkill.getText()) - 1));
        }
    }

    @FXML
    private void validateName(KeyEvent event) {
        int batas = 50;
        if (txtName.getText().length() >= batas) event.consume();
    }

    @FXML
    private void selectStat(MouseEvent event) {
        txtStatpoints.setText(Integer.toString(tbvBase.getSelectionModel().getSelectedItem().getVal()));
    }

    @FXML
    private void selectSkill(MouseEvent event) {
        txtSkillpoints.setText(Integer.toString(tbvSkill.getSelectionModel().getSelectedItem().getLv()));
    }

    @FXML
    private void selectClass(MouseEvent event) {
        txtClass.setText(Integer.toString(tbvClass.getSelectionModel().getSelectedItem().getId()));
    }

    @FXML
    private void updateClicked(ActionEvent event) {
        HeroModel s = new HeroModel();
        s = tbv.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLHeroesCreate.fxml"));
            Parent root = (Parent)loader.load();
            FXMLHeroesCreateController isidt = (FXMLHeroesCreateController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.setTitle("Edit hero");
            stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        firstClicked(event);
    }
}
