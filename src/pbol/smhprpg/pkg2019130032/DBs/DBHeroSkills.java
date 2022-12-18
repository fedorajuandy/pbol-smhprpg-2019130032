package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroSkillModel;
import pbol.smhprpg.pkg2019130032.Models.SkillModel;

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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_skills WHERE hero_class_id = '" + nomor + "' AND skill_id = '" + nomor1 + "'");
            
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return val;
    }
     
    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO hero_skills (hero_class_id, skill_id, lv) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroSkillModel().getHero_class_id());
            con.preparedStatement.setInt(2, getHeroSkillModel().getSkill_id());
            con.preparedStatement.setInt(3, getHeroSkillModel().getLv());
            con.preparedStatement.executeUpdate();
            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
     
    public boolean delete(int nomor, int nomor1) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM hero_skills WHERE hero_class_id  = ? AND skill_id = ?");
            con.preparedStatement.setInt(1, nomor);
            con.preparedStatement.setInt(2, nomor1);
            con.preparedStatement.executeUpdate();
            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
     
    public ObservableList<SkillModel> searchSkills(String id, String nama, String desk, String mp, String damage, String sr) {
        try {
            ObservableList<SkillModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT id, name, des, mp_cost, dmg, success_rate FROM skills WHERE id LIKE '%" + id + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%' OR mp_cost LIKE '%" + mp + "%' OR dmg LIKE '%" + damage + "%' OR success_rate LIKE '%" + sr + "%'");
            
            int i = 1;
            while (rs.next()) {
                SkillModel d = new SkillModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setDes(rs.getString("des"));
                d.setMp_cost(rs.getInt("mp_cost"));
                d.setDmg(rs.getDouble("dmg"));
                d.setSuccess_rate(rs.getDouble("success_rate"));
                
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
