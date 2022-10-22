package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.ClassBattleStatModel;
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
public class DBClassBattleStats {
    private ClassBattleStatModel dt = new ClassBattleStatModel();
    
    public ClassBattleStatModel getClassBattleStatModel() {
        return(dt);
    }
    
    public void setClassBattleStatModel(ClassBattleStatModel s) {
        dt = s;
    }
    
    public ObservableList<ClassBattleStatModel> load(int kode) {
        try {
            ObservableList<ClassBattleStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT cbs.class_id, cbs.battle_stat_id, bs.name, cbs.scale FROM class_battle_stats cbs JOIN battle_stats bs ON(cbs.battle_stat_id = bs.id) WHERE cbs.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassBattleStatModel d = new ClassBattleStatModel();
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));                
                d.setClass_id(rs.getInt("class_id"));
                d.setBattlestatName(rs.getString("name"));
                d.setScale(rs.getInt("scale"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_battle_stats WHERE id = '" + nomor + "'");
            
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        
        return val;
    }
}
