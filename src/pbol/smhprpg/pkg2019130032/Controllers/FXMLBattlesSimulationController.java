package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Timer;
import java.util.TimerTask; 
import javafx.application.Platform;
import java.text.SimpleDateFormat;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLBattlesSimulationController implements Initializable {
    Timer t = new Timer();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy,hh:mm:ss");
    Random r = new Random();
    @FXML
    private ImageView imgHero;
    @FXML
    private ImageView imgEnemy;
    @FXML
    private TextArea txtDes;
    @FXML
    private ListView<?> txtFight;
    @FXML
    private TextField txtMp;
    @FXML
    private TextField txtDmg;
    @FXML
    private TextField txtSuccess;
    @FXML
    private Label lbNameEnemy;
    @FXML
    private ComboBox<?> cbHeroes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
             Platform.runLater(() -> {
                    // label.setText(sdf.format(new java.util.Date()));
                });
            }
        }, 0, 1000);
        
        // int a = ack.nextInt(100);
        int b = (int)(Math.random()*100);
        // System.out.println("Angka acak : " + a + " " + b);
        // lbRandom.setText(String.valueOf(b));
    }    

    @FXML
    private void fight(ActionEvent event) {
    }

    @FXML
    private void changeEnemy(ActionEvent event) {
    }
    
}
