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
import javafx.scene.input.KeyEvent;
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
    
    private void clear() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }

    @FXML
    private void loginClicked(ActionEvent event) {
        username = txtUsername.getText();
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT * FROM users where username = '" + username + "'");

            while (rs.next()) {
                if (txtPassword.getText().equals(rs.getString("password"))) {
                    try {
                        FXMLLoader loader;
                        if (rs.getInt("role") == 0) {
                            loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLAdminMenu.fxml"));
                        } else {
                            loader = new FXMLLoader(getClass().getResource("/pbol/smhprpg/pkg2019130032/Views/FXMLUserMenu.fxml"));
                        }
                        
                        clear();
                        
                        Parent root = (Parent)loader.load();
                        Scene scene = new Scene(root);
                        Stage stg = new Stage();
                        stg.initModality(Modality.APPLICATION_MODAL);
                        stg.setTitle("SMHPRPG");
                        stg.getIcons().add(new Image(getClass().getResourceAsStream("/pbol/smhprpg/pkg2019130032/imgs/smhprpg.png")));
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
            }
            
            con.tutupKoneksi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearClicked(ActionEvent event) {
        clear();
    }

    @FXML
    private void exitClicked(ActionEvent event) {
        btnExit.getScene().getWindow().hide();
    }

    @FXML
    private void validateUsername(KeyEvent event) {
        int batas = 10;
        if (txtUsername.getText().length() >= batas) event.consume();
    }

    @FXML
    private void validatePassword(KeyEvent event) {
        int batas = 128;
        if (txtPassword.getText().length() >= batas) event.consume();
    }
}
