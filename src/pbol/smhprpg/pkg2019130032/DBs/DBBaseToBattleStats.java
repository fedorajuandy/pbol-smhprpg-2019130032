package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.BaseToBattleStatModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.BattleStatModel;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBBaseToBattleStats {
    private BaseToBattleStatModel dt = new BaseToBattleStatModel();
    
    public BaseToBattleStatModel getBaseToBattleStatModel() {
        return(dt);
    }
    
    public void setBaseToBattleStatModel(BaseToBattleStatModel s) {
        dt = s;
    }
    
    public ObservableList<BaseToBattleStatModel> load(int kode) {
        try {
            ObservableList<BaseToBattleStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT btb.base_stat_id, btb.battle_stat_id, bt.name, btb.scale FROM base_to_battle_stats btb JOIN battle_stats bt ON (btb.battle_stat_id = bt.id) WHERE btb.base_stat_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                BaseToBattleStatModel d = new BaseToBattleStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));
                d.setScale(rs.getDouble("scale"));
                d.setBattleStatName(rs.getString("bt.name"));
                
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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM base_to_battle_stats WHERE base_stat_id = '" + nomor + "' AND battle_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO base_to_battle_stats (base_stat_id, battle_stat_id, val) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getBaseToBattleStatModel().getBase_stat_id());
            con.preparedStatement.setInt(2, getBaseToBattleStatModel().getBattle_stat_id());
            con.preparedStatement.setDouble(3, getBaseToBattleStatModel().getScale());
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
     
    public boolean delete(int nomor, int nomor1) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM trait_base_stats WHERE base_stat_id  = ? AND battle_stat_id = ?");
            con.preparedStatement.setInt(1, nomor);
            con.preparedStatement.setInt(2, nomor1);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE base_to_battle_stats SET scale = ?  WHERE base_stat_id  = ? AND battle_stat_id = ?");
            con.preparedStatement.setDouble(1, getBaseToBattleStatModel().getScale());
            con.preparedStatement.setInt(2, getBaseToBattleStatModel().getBase_stat_id());
            con.preparedStatement.setInt(3, getBaseToBattleStatModel().getBattle_stat_id());
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
    
    public ObservableList<BaseStatModel> searchBaseStats(String id, String singkatan, String nama, String desk) {
        try {
            ObservableList<BaseStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM base_stats WHERE id LIKE '%" + id + "%' OR abbrev LIKE '%" + singkatan + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                BaseStatModel d = new BaseStatModel();
                d.setId(rs.getInt("id"));
                d.setAbbrev(rs.getString("abbrev"));
                d.setName(rs.getString("name"));
                d.setDes(rs.getString("des"));
                
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
    
    public ObservableList<BattleStatModel> searchBattleStats(String id, String singkatan, String nama, String desk) {
        try {
            ObservableList<BattleStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM battle_stats WHERE id LIKE '%" + id + "%' OR abbrev LIKE '" + singkatan + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                BattleStatModel d = new BattleStatModel();
                d.setId(rs.getInt("id"));
                d.setAbbrev(rs.getString("abbrev"));
                d.setName(rs.getString("name"));
                d.setDes(rs.getString("des"));
                
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
