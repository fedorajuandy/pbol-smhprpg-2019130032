package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.BattleStatModel;
        
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBBattleStats {
    private BattleStatModel dt = new BattleStatModel(); 
    
    public BattleStatModel getBattleStatModel() {
        return(dt);
    }
    
    public void setBattleStatModel(BattleStatModel s) {
        dt = s;
    }
    
    public ObservableList<BattleStatModel> load() {
        try {
            ObservableList<BattleStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT id, abbrev, name, desc FROM battle_stats");

            int i = 1;
            while (rs.next()) {
                BattleStatModel d = new BattleStatModel();
                d.setId(rs.getInt("id"));
                d.setAbbrev(rs.getString("abbrev"));
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM battle_stats WHERE id = '" + nomor + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO battle_stats (abbrev, name, desc) VALUES (?, ?, ?)");
            con.preparedStatement.setString(1, getBattleStatModel().getAbbrev());
            con.preparedStatement.setString(2, getBattleStatModel().getName());
            con.preparedStatement.setString(3, getBattleStatModel().getDesc());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM battle_stats WHERE id  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE battle_stats SET abbrev = ?, name = ?, desc = ?  WHERE  id = ? ");
            con.preparedStatement.setString(1, getBattleStatModel().getAbbrev());
            con.preparedStatement.setString(2, getBattleStatModel().getName());
            con.preparedStatement.setString(3, getBattleStatModel().getDesc());
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
    
    public ObservableList<BattleStatModel> searchItems(String singkatan, String nama, String desk) {
        try {
            ObservableList<BattleStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM battle_stats WHERE abbrev LIKE '" + singkatan + "%' OR name LIKE '" + nama + "%' OR desc LIKE '" + desk + "%'");
            
            int i = 1;
            while(rs.next()) {
                BattleStatModel d = new BattleStatModel();
                d.setId(rs.getInt("id"));
                d.setAbbrev(rs.getString("abbrev"));
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                
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
