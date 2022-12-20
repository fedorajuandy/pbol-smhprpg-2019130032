package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Timer;
import java.util.TimerTask; 
import javafx.application.Platform;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static pbol.smhprpg.pkg2019130032.Controllers.FXMLLoginController.username;
import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLBattlesSimulationController implements Initializable {
    Random r = new Random();
    Timer t = new Timer();
    int user_id;
    
    @FXML
    private ImageView imgHero;
    @FXML
    private ImageView imgEnemy;
    @FXML
    private TextField txtHPEnemy;
    @FXML
    private Label lbEnemy;
    @FXML
    private Label lbHero;
    @FXML
    private TextField txtHPHero;
    @FXML
    private TextField txtLvHero;
    @FXML
    private TextField txtLvEnemy;
    @FXML
    private TextArea txa;
    @FXML
    private ListView<String> lstEnemy;
    @FXML
    private ListView<String> lstHero;
    @FXML
    private Button btnQuit;
    @FXML
    private TextField txtCounter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txa.setWrapText(true);
        lstHero.getItems().clear();
        lstEnemy.getItems().clear();
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT id FROM users WHERE username = '" + username + "'");
            while (rs.next()) {
                user_id = rs.getInt("id");
            }
            rs = con.statement.executeQuery("SELECT * FROM users where username = '" + username + "'");
            ObservableList<HeroModel> data = FXMLMainMenuController.dth.load();
            ObservableList<HeroModel> data1 = FXMLMainMenuController.dth.load();
            
            while (rs.next()) {
                if (rs.getInt("role") == 1) {
                    data = FXMLMainMenuController.dth.loadUser(user_id);
                }

                if (data.isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "There is no hero :(", ButtonType.OK);
                    a.showAndWait();
                    txa.getScene().getWindow().hide();;
                } else {
                    for (int i=0; i<data.size(); i++) {
                        lstHero.getItems().addAll(data.get(i).getId()+ " - " + data.get(i).getName());
                    }

                    if (rs.getInt("role") == 1) {
                        data1 = FXMLMainMenuController.dth.loadNotUser(user_id);
                    }
                    if (data1.isEmpty()) {
                       Alert a = new Alert(Alert.AlertType.ERROR, "There is no enemy :v" ,ButtonType.OK);
                       a.showAndWait();
                       txa.getScene().getWindow().hide();;
                    } else {
                        for (int i=0; i<data1.size(); i++){
                            lstEnemy.getItems().addAll(data1.get(i).getId() + " - " + data1.get(i).getName());
                        }
                    }
                }
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void fight(ActionEvent event) {
        int counter = Integer.parseInt(txtCounter.getText());
        
        for (int i = 0; i < counter; i++) {
            int h = r.nextInt(lstHero.getItems().size());
            int e = r.nextInt(lstEnemy.getItems().size());
            HeroModel hero = new HeroModel();
            String heroName = lstHero.getItems().get(h);
            hero.setId(Integer.parseInt(heroName.substring(0, heroName.indexOf(" - "))));
            HeroModel enemy = new HeroModel();
            String enemyName = lstHero.getItems().get(e);
            enemy.setId(Integer.parseInt(enemyName.substring(0, heroName.indexOf(" - "))));
            LevelModel level = FXMLMainMenuController.dtl.load();
            
            try {
                Koneksi con = new Koneksi();
                con.bukaKoneksi();
                con.statement = con.dbKoneksi.createStatement();

                ResultSet rs = con.statement.executeQuery("SELECT id, name, exp, stat_points, skill_points, image FROM heroes WHERE id = '" + hero.getId() + "'");

                while (rs.next()) {
                    hero.setName(rs.getString("name"));
                    hero.setStat_points(rs.getInt("stat_points"));
                    hero.setSkill_points(rs.getInt("skill_points"));
                    hero.setExp(rs.getInt("exp"));
                    hero.setImage(rs.getString("image"));
                }
                lbHero.setText(hero.getName());
                txtLvHero.setText(Integer.toString(FXMLMainMenuController.dtl.levelUp(hero, level)));
                imgHero.setImage(showImage(hero.getImage()));
                
                rs = con.statement.executeQuery("SELECT id, name, exp, stat_points, skill_points, image FROM heroes WHERE id = '" + enemy.getId() + "'");

                while (rs.next()) {
                    enemy.setName(rs.getString("name"));
                    enemy.setStat_points(rs.getInt("stat_points"));
                    enemy.setSkill_points(rs.getInt("skill_points"));
                    enemy.setExp(rs.getInt("exp"));
                    enemy.setImage(rs.getString("image"));
                }
                lbEnemy.setText(enemy.getName());
                txtLvEnemy.setText(Integer.toString(FXMLMainMenuController.dtl.levelUp(enemy, level)));
                imgEnemy.setImage(showImage(enemy.getImage()));
                
                con.tutupKoneksi();
            } catch (Exception except) {
                except.printStackTrace();
            }
          
           
            
            // WHILE THE DANN HP > 0
            String attacker = "";
            String defender = "";
            int damage = 0;
            // PENDING ;v; I cannot calculate
            
            t.scheduleAtFixedRate(new TimerTask() {           
                @Override           
                public void run(){       
                    Platform.runLater(() -> {});
                }        
            }, 0, 5000);
            
            txa.setText(txa.getText() + "\n" + attacker + " dealt " + damage + " to " + defender + "!");
            // END
            
            // if whoever won
            txa.setText(txa.getText() + "\n" + attacker + " won!");
            hero.setExp(hero.getExp() + level.getBase_exp() * FXMLMainMenuController.dtl.levelUp(enemy, level));
            if (Integer.parseInt(txtLvHero.getText()) < FXMLMainMenuController.dtl.levelUp(hero, level)) {
                txa.setText(txa.getText() + "\n" + attacker + " level up!");
            }
            // else
            txa.setText(txa.getText() + "\n" + attacker + " lose...");
        }
    }

    @FXML
    private void quitClicked(ActionEvent event) {
        btnQuit.getScene().getWindow().hide();
    }

    @FXML
    private void validateCounter(KeyEvent event) {
        char test = event.getCharacter().charAt(0);
        if(!Character.isDigit(test)) event.consume();
    }
    
    public Image showImage(String temp){
        Image gambar = null;
        
        try {
            if (temp.equals("")) {
                temp = "src/pbol/smhprpg/pkg2019130032/imgs/placeholder.png";
            }
            gambar = new Image(new FileInputStream(temp));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLHeroesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return gambar;
    }
}
