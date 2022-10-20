package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBLevels {
    private LevelModel dt = new LevelModel(); 
    
    public LevelModel getLevelModel() {
        return(dt);
    }
    
    public void setLevelModel(LevelModel s) {
        dt = s;
    }
    
    public ObservableList<LevelModel> load() {
        try {
            ObservableList<LevelModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT id, needed_exp, scale, max_lv FROM levels");

            int i = 1;
            while (rs.next()) {
                LevelModel d = new LevelModel();
                d.setId(rs.getInt("id"));
                d.setNeeded_exp(rs.getInt("needed_exp"));
                d.setScale(rs.getDouble("scale"));
                d.setMax_lv(rs.getInt("max_lv"));
                
                tableData.add(d);
                i++;
            }
            
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(int nomor) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM levels WHERE id = '" + nomor + "'");
            
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO levels (needed_exp, scale, max_lv) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getLevelModel().getNeeded_exp());
            con.preparedStatement.setDouble(2, getLevelModel().getScale());
            con.preparedStatement.setInt(3, getLevelModel().getMax_lv());
            con.preparedStatement.executeUpdate();
            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(int nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM levels WHERE id  = ? ");
            con.preparedStatement.setInt(1, nomor);
            con.preparedStatement.executeUpdate();
            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE levels SET needed_exp = ?, scale = ?, max_lv = ?  WHERE  id = ? ");
            con.preparedStatement.setInt(1, getLevelModel().getNeeded_exp());
            con.preparedStatement.setDouble(2, getLevelModel().getScale());
            con.preparedStatement.setInt(3, getLevelModel().getMax_lv());
            con.preparedStatement.executeUpdate();
            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public ObservableList<LevelModel> searchItems(String exp, String skala, String lv) {
        try {
            ObservableList<LevelModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM levels WHERE needed_exp LIKE '" + exp + "%' OR scale LIKE '" + skala + "%' OR max_lv LIKE '" + lv + "%'");
            
            int i = 1;
            while(rs.next()) {
                LevelModel d = new LevelModel();
                d.setId(rs.getInt("id"));
                d.setNeeded_exp(rs.getInt("needed_exp"));
                d.setScale(rs.getDouble("scale"));
                d.setMax_lv(rs.getInt("max_lv"));
                
                tableData.add(d);
                i++;
            }
            
            con.tutupKoneksi();
            return tableData;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
