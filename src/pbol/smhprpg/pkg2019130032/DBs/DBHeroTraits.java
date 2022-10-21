package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroTraitModel;
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
public class DBHeroTraits {
    private HeroTraitModel dt = new HeroTraitModel();
    
    public HeroTraitModel getHeroTraitModel() {
        return(dt);
    }
    
    public void setHeroTraitModel(HeroTraitModel s) {
        dt = s;
    }
    
    public ObservableList<HeroTraitModel> load(String kode) {
        try {
            ObservableList<HeroTraitModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT ht.hero_id, ht.trait_id, t.name FROM hero_traits ht JOIN traits t ON (ht.trait_id = t.id) WHERE ht.hero_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroTraitModel d = new HeroTraitModel();
                d.setHero_id(rs.getInt("hero_id"));
                d.setTrait_id(rs.getInt("trait_id"));
                d.setTraitName(rs.getString("t.name"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_traits WHERE id = '" + nomor + "'");
            
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
