package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;
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
public class DBHeroBaseStats {
    private HeroBaseStatModel dt = new HeroBaseStatModel();
    
    public HeroBaseStatModel getHeroBaseStatModel() {
        return(dt);
    }
    
    public void setHeroBaseStatModel(HeroBaseStatModel s) {
        dt = s;
    }
    
    public ObservableList<HeroBaseStatModel> load(int kode) {
        try {
            ObservableList<HeroBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT hbs.hero_id, hbs.base_stat_id, bs.name, hbs.val FROM hero_base_stats hbs JOIN base_stats bs ON (hbs.base_stat_id = bs.id) WHERE hbs.hero_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroBaseStatModel d = new HeroBaseStatModel();
                d.setHero_id(rs.getInt("hero_id"));
                d.setBase_stat_id(rs.getInt("base_stat_id"));
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_base_stats WHERE id = '" + nomor + "'");
            
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
