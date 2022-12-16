package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBLevel {
    private LevelModel dt = new LevelModel(); 

    public LevelModel getLevelModel() {
        return dt;
    }

    public void setLevelModel(LevelModel dt) {
        this.dt = dt;
    }
    
    public ObservableList<LevelModel> load() {
        try {
            ObservableList<LevelModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT id, max_lv, base_exp, scale, stat_points, skill_points FROM level");

            int i = 1;
            while (rs.next()) {
                LevelModel d = new LevelModel();
                d.setId(rs.getInt("id"));
                d.setScale(rs.getInt("max_lv"));
                d.setBase_exp(rs.getInt("base_exp"));
                d.setScale(rs.getDouble("scale"));
                d.setScale(rs.getInt("stat_points"));
                d.setScale(rs.getInt("skill_poitns"));
                
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM level WHERE id = '" + nomor + "'");
            
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return val;
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE level SET max_lv = ?, base_exp = ?, scale = ? stat_points = ?, skill_points = ? WHERE  id = ?");
            con.preparedStatement.setInt(1, getLevelModel().getMax_lv());
            con.preparedStatement.setInt(2, getLevelModel().getBase_exp());
            con.preparedStatement.setDouble(3, getLevelModel().getScale());
            con.preparedStatement.setInt(4, getLevelModel().getStat_points());
            con.preparedStatement.setInt(5, getLevelModel().getSkill_points());
            con.preparedStatement.setInt(6, getLevelModel().getId());
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
}
