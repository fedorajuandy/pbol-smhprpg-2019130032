package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.TraitBaseStatModel;
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
public class DBTraitBaseStats {
    private TraitBaseStatModel dt = new TraitBaseStatModel();
    
    public TraitBaseStatModel getTraitBaseStatModel() {
        return(dt);
    }
    
    public void setTraitBaseStatModel(TraitBaseStatModel s) {
        dt = s;
    }
    
    public ObservableList<TraitBaseStatModel> load(String kode) {
        try {
            ObservableList<TraitBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT tbs.trait_id, tbs.base_stat_id, bs.name, tbs.val FROM trait_base_stats tbs JOIN base_stats bs ON (tbs.base_stat_id = bs.id) WHERE tbs.trait_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                TraitBaseStatModel d = new TraitBaseStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setTrait_id(rs.getInt("trait_id"));
                d.setVal(rs.getInt("val"));
                d.setBasestatName(rs.getString("bs.name"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM trait_base_stats WHERE id = '" + nomor + "'");
            
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
