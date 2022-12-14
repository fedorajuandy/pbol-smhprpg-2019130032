package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.RaceBaseStatModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBRaceBaseStats {
    private RaceBaseStatModel dt = new RaceBaseStatModel();
    
    public RaceBaseStatModel getRaceBaseStatModel() {
        return(dt);
    }
    
    public void setRaceBaseStatModel(RaceBaseStatModel s) {
        dt = s;
    }
    
    public ObservableList<RaceBaseStatModel> load(int kode) {
        try {
            ObservableList<RaceBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT rbs.race_id, rbs.base_stat_id, bs.name AS basestatName, rbs.val FROM race_base_stats rbs JOIN base_stats bs ON (rbs.base_stat_id = bs.id) WHERE rbs.race_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                RaceBaseStatModel d = new RaceBaseStatModel();
                d.setRace_id(rs.getInt("race_id"));
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setVal(rs.getInt("val"));
                d.setBasestatName(rs.getString("basestatName"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM race_base_stats WHERE id = '" + nomor + "'");
            
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
