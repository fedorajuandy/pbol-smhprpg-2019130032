package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.ClassBaseStatModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBClassBaseStats {
    private ClassBaseStatModel dt = new ClassBaseStatModel();
    
    public ClassBaseStatModel getClassBaseStatModel() {
        return(dt);
    }
    
    public void setClassBaseStatModel(ClassBaseStatModel s) {
        dt = s;
    }
     
     public ObservableList<ClassBaseStatModel> load(int kode) {
        try {
            ObservableList<ClassBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT cbs.class_id, cbs.base_stat_id, bs.name, cbs.levelup_val FROM class_base_stats cbs JOIN base_stats bs ON(cbs.base_stat_id = bs.id) WHERE cbs.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassBaseStatModel d = new ClassBaseStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));                
                d.setClass_id(rs.getInt("class_id"));
                d.setBasestatName(rs.getString("name"));
                d.setLevelup_val(rs.getInt("levelup_val"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_base_stats WHERE id = '" + nomor + "'");
            
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
