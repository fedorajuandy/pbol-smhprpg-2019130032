package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.BaseToBattleStatModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public ObservableList<BaseToBattleStatModel> load(String base, String battle, String skala) {
        try {
            ObservableList<BaseToBattleStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT btb.base_stat_id, bs.name, btb.battle_stat_id, bt.name, btb.scale" + "FROM base_to_battle_stats btb JOIN base_stats bs ON(btb.base_stat_id = bs.id) JOIN battle_stats bt ON (btb.battle_stat_id = bt.id) WHERE bs.name LIKE '" + base + "%' OR bt.name LIKE '" + battle +  "%' OR btb.scale LIKE '" + skala + "%'");
            
            int i = 1;
            while (rs.next()) {
                BaseToBattleStatModel d = new BaseToBattleStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));                
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));
                d.setScale(rs.getDouble("scale"));
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));
                d.setBaseStatName(rs.getString("bs.name"));
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
    
     public int validasi(int nomor) {
        int val = 0;
        
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "SELECT COUNT(*) AS jml FROM jual_detil WHERE id = '" + nomor + "'");
            
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        
        return val;
    }
    
    public ObservableList<BaseToBattleStatModel> searchItems(String base, String battle, String skala) {
        try {
            ObservableList<BaseToBattleStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT btb.base_stat_id, bs.name, btb.battle_stat_id, bt.name, btb.scale" + "FROM base_to_battle_stats btb JOIN base_stats bs ON(btb.base_stat_id = bs.id) JOIN battle_stats bt ON (btb.battle_stat_id = bt.id) WHERE bs.name LIKE '" + base + "%' OR bt.name LIKE '" + battle +  "%' OR btb.scale LIKE '" + skala + "%'");
            
            int i = 1;
            while(rs.next()) {
                BaseToBattleStatModel d = new BaseToBattleStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));                
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));
                d.setScale(rs.getDouble("scale"));
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));
                d.setBaseStatName(rs.getString("bs.name"));
                d.setBattleStatName(rs.getString("bt.name"));

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
