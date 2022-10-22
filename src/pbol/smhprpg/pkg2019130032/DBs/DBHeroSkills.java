package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;
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
public class DBHeroSkills {
    private HeroSkillModel dt = new HeroSkillModel();
    
    public HeroSkillModel getHeroSkillModel() {
        return(dt);
    }
    
    public void setHeroSkillModel(HeroSkillModel s) {
        dt = s;
    }
    
    public ObservableList<HeroSkillModel> load(int kode) {
        try {
            ObservableList<HeroSkillModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT hs.hero_class_id, hs.skill_id, s.name, hs.lv FROM hero_skills hs JOIN skills s ON (hs.skill_id = s.id) WHERE hs.hero_class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroSkillModel d = new HeroSkillModel();
                d.setHero_class_id(rs.getInt("hero_class_id"));
                d.setSkill_id(rs.getInt("skill_id"));
                d.setLv(rs.getInt("lv"));
                d.setSkillName(rs.getString("s.name"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_skills WHERE id = '" + nomor + "'");
            
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
