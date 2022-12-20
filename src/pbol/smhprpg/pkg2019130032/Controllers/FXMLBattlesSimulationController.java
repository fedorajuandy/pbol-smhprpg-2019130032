package pbol.smhprpg.pkg2019130032.Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Timer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import static pbol.smhprpg.pkg2019130032.Controllers.FXMLLoginController.username;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;
import pbol.smhprpg.pkg2019130032.Controllers.FXMLMainMenuController;
import pbol.smhprpg.pkg2019130032.Koneksi;

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
            enemy.setId(Integer.parseInt(enemyName.substring(0, enemyName.indexOf(" - "))));
            LevelModel level = FXMLMainMenuController.dtl.load();
            
            try {
                Koneksi con = new Koneksi();
                con.bukaKoneksi();
                con.statement = con.dbKoneksi.createStatement();

                ResultSet rs = con.statement.executeQuery("SELECT id, name, exp, stat_points, skill_points, image FROM heroes WHERE id = '" + hero.getId() + "'");

                while (rs.next()) {
                    hero.setId(rs.getInt("id"));
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
                    enemy.setId(rs.getInt("id"));
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
            
            int patkH = 0;
            int pdefH = 0;
            int hpH = 0;
            int spdH = 0;
            
            try {
                 Koneksi con = new Koneksi();
                 con.bukaKoneksi();
                 con.statement = con.dbKoneksi.createStatement();
                 ResultSet rs = con.statement.executeQuery("SELECT hbs.hero_id, hbs.base_stat_id AS hbsBSId, hbs.val, bs.id AS bsId, btb.base_stat_id AS btbBSId, btb.battle_stat_id, btb.scale, bt.id AS btId, bt.abbrev AS btAbbrev FROM hero_base_stats hbs LEFT JOIN base_stats bs ON(hbs.base_stat_id = bs.id) LEFT JOIN base_to_battle_stats btb ON (bs.id = btb.base_stat_id) LEFT JOIN battle_stats bt ON (btb.battle_stat_id = bt.id) WHERE hbs.hero_id LIKE '" + hero.getId() + "'");

                 while (rs.next()) {
                     String abbrev = rs.getString("btAbbrev");
                     if (abbrev != null) {
                        if (abbrev.equals("PATK")) {
                            patkH += rs.getInt("val") * rs.getDouble("scale") * 10;
                        } else if (abbrev.equals("PDEF")) {
                            pdefH += rs.getInt("val") * rs.getDouble("scale") * 10;
                        } else if (abbrev.equals("HP")) {
                            hpH += rs.getInt("val") * rs.getDouble("scale") * 100;
                        } else if (abbrev.equals("SPD")) {
                            spdH += rs.getInt("val") * rs.getDouble("scale") * 10;
                        }
                     }
                 }
                 
                 txtHPHero.setText(Integer.toString(hpH));
                 con.tutupKoneksi();
            } catch (Exception except) {
                except.printStackTrace();
            }
            
            int patkE = 0;
            int pdefE = 0;
            int hpE = 0;
            int spdE = 0;
            
            try {
                 Koneksi con = new Koneksi();
                 con.bukaKoneksi();
                 con.statement = con.dbKoneksi.createStatement();
                 ResultSet rs = con.statement.executeQuery("SELECT hbs.hero_id, hbs.base_stat_id AS hbsBSId, hbs.val, bs.id AS bsId, btb.base_stat_id AS btbBSId, btb.battle_stat_id, btb.scale, bt.id AS btId, bt.abbrev AS btAbbrev FROM hero_base_stats hbs LEFT JOIN base_stats bs ON(hbs.base_stat_id = bs.id) LEFT JOIN base_to_battle_stats btb ON (bs.id = btb.base_stat_id) LEFT JOIN battle_stats bt ON (btb.battle_stat_id = bt.id) WHERE hbs.hero_id LIKE '" + enemy.getId() + "'");

                 while (rs.next()) {
                     String abbrev = rs.getString("btAbbrev");
                     System.out.println(abbrev);
                     if (abbrev != null) {
                        if (abbrev.equals("PATK")) {
                            patkE += rs.getInt("val") * rs.getDouble("scale") * 10;
                        } else if (abbrev.equals("PDEF")) {
                            pdefE += rs.getInt("val") * rs.getDouble("scale") * 10;
                        } else if (abbrev.equals("HP")) {
                            hpE += rs.getInt("val") * rs.getDouble("scale") * 100;
                        } else if (abbrev.equals("SPD")) {
                            spdE += rs.getInt("val") * rs.getDouble("scale") * 10;
                        }
                     }
                 }
                 
                 txtHPEnemy.setText(Integer.toString(hpE));
                 con.tutupKoneksi();
            } catch (Exception except) {
                except.printStackTrace();
            }
            
            String attacker = hero.getName();
            String defender = enemy.getName();
            
            if (spdE > spdH) {
                attacker = enemy.getName();
                defender = hero.getName();
                
                while (hpH > 0 && hpE > 0) {
                   hpH -= patkE - pdefH;
                   txa.setText(txa.getText() + "\n" + attacker + " dealt " + patkE + " to " + defender + "!");
                   txtHPHero.setText(Integer.toString(hpH));
                   hpE -= patkH - pdefE;
                   txa.setText(txa.getText() + "\n" + defender + " dealt " + patkH + " to " + attacker + "!");
                   txtHPEnemy.setText(Integer.toString(hpE));
                }
            } else {
                while (hpH > 0 && hpE > 0) {
                   hpE -= patkH - pdefE;
                   txa.setText(txa.getText() + "\n" + attacker + " dealt " + patkH + " to " + defender + "!");
                   txtHPEnemy.setText(Integer.toString(hpE));
                   hpH -= patkE - pdefH;
                   txa.setText(txa.getText() + "\n" + defender + " dealt " + patkE + " to " + attacker + "!");
                   txtHPHero.setText(Integer.toString(hpH));
                }
            }
            
           if (hpH > hpE) {
                txtHPEnemy.setText(Integer.toString(0));
                txa.setText(txa.getText() + "\n" + hero.getName() + " won!");
                hero.setExp(hero.getExp() + level.getBase_exp() * FXMLMainMenuController.dtl.levelUp(enemy, level));
                hero.setStat_points(hero.getStat_points() + level.getStat_points());
                hero.setSkill_points(hero.getSkill_points() + level.getSkill_points());
                
                if (Integer.parseInt(txtLvHero.getText()) < FXMLMainMenuController.dtl.levelUp(hero, level)) {
                    txa.setText(txa.getText() + "\n" + attacker + " level up!");
                }
           } else {
                txtHPHero.setText(Integer.toString(0));
                txa.setText(txa.getText() + "\n" + attacker + " lose...");
           }
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
