package pbol.smhprpg.pkg2019130032.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Timer;
import java.util.TimerTask; 
import javafx.application.Platform;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLBattlesSimulationController implements Initializable {
    Timer t = new Timer();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy,hh:mm:ss");
    Random r = new Random();
    
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
    
}
