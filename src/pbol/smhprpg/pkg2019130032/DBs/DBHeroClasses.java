package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;
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
public class DBHeroClasses {
    private HeroClassModel dt = new HeroClassModel();
    
    public HeroClassModel getHeroClassModel() {
        return(dt);
    }
    
    public void setHeroClassModel(HeroClassModel s) {
        dt = s;
    }
    
    public ObservableList<HeroClassModel> load(String kode) {
        try {
            ObservableList<HeroClassModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT hc.id, hc.hero_id, hc.class_id, c.name, hc.mastery_lv" + "FROM hero_classes hc JOIN classes c ON(hc.class_id = c.id) WHERE hc.hero_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroClassModel d = new HeroClassModel();
                d.setId(rs.getInt("id"));
                d.setHero_id(rs.getInt("hero_id"));
                d.setClass_id(rs.getInt("class_id"));
                d.setClassName(rs.getString("name"));
                d.setMastery_lv(rs.getInt("mastery_lv"));
                
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
            ResultSet rs = con.statement.executeQuery(  "SELECT COUNT(*) AS jml FROM hero_classes WHERE id = '" + nomor + "'");
            
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
