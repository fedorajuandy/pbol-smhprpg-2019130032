package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
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
public class DBHeroEffects {
    private HeroEffectModel dt = new HeroEffectModel();
    
    public HeroEffectModel getHeroEffectModel() {
        return(dt);
    }
    
    public void setHeroEffectModel(HeroEffectModel s) {
        dt = s;
    }
    
    public ObservableList<HeroEffectModel> load(int kode) {
        try {
            ObservableList<HeroEffectModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT he.hero_id, he.effect_id, e.name, he.duration_left FROM hero_effects he JOIN effects e ON (he.effect_id = e.id) WHERE he.hero_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroEffectModel d = new HeroEffectModel();
                d.setHero_id(rs.getInt("hero_id"));
                d.setEffect_id(rs.getInt("effect_id"));
                d.setDuration_left(rs.getInt("duration_left"));
                d.setEffectName(rs.getString("e.name"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_effects WHERE id = '" + nomor + "'");
            
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
