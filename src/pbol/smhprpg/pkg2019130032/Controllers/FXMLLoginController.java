package pbol.smhprpg.pkg2019130032.Controllers;

import pbol.smhprpg.pkg2019130032.Koneksi;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class FXMLLoginController implements Initializable {
    public static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        FXMLLoginController.username = username;
    }

    @FXML
    private Button btnExit;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginClicked(ActionEvent event) {
        username = txtUsername.getText();
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT password FROM users where username = " + username);

            int i = 1;
            while (rs.next()) {
                if (txtPassword.getText().equals(rs.getString("password"))) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032//pbol/smhprpg/pkg2019130032/Views/FXMLUserMenu.fxml"));
                        Parent root = (Parent)loader.load();
                        Scene scene = new Scene(root);
                        Stage stg = new Stage();
                        stg.initModality(Modality.APPLICATION_MODAL);
                        stg.setTitle("SMHPRPG");
                        stg.getIcons().add(new Image(getClass().getResourceAsStream("imgs/smhprpg.png")));
                        stg.setResizable(false);
                        stg.setIconified(false);
                        stg.setScene(scene);
                        stg.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Wrong username and/or password.", ButtonType.OK);
                    a.showAndWait(); 
                }

                i++;
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }
}
