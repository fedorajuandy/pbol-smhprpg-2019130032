package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.HeroEffectModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.EffectModel;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;

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
    
    public int validasi(int nomor, int nomor1) {
        int duration_left = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_effects WHERE hero_id = '" + nomor + "' AND effect_id = '" + nomor1 + "'");
            
            while (rs.next()) {
                duration_left = rs.getInt("jml");
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return duration_left;
    }
     
    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO hero_effects (hero_id, effect_id, duration_left) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroEffectModel().getHero_id());
            con.preparedStatement.setInt(2, getHeroEffectModel().getEffect_id());
            con.preparedStatement.setInt(3, getHeroEffectModel().getDuration_left());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM hero_effects WHERE hero_id  = ? AND effect_id = ?");
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

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE hero_effects SET duration_left = ?  WHERE hero_id  = ? AND effect_id = ?");
            con.preparedStatement.setInt(1, getHeroEffectModel().getDuration_left());
            con.preparedStatement.setInt(2, getHeroEffectModel().getHero_id());
            con.preparedStatement.setInt(3, getHeroEffectModel().getEffect_id());
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
     
    public ObservableList<HeroModel> searchHeroes(String id, String ras, String kelas, String nama, String jk, String desk, String skill, String stat, String pengalaman, String gambar) {
        try {
            ObservableList<HeroModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT h.id, h.race_id, r.name AS raceName, h.curr_class_id, c.name AS className, h.name, h.gender, h.des, h.stat_points, h.skill_points, h.exp, h.image FROM heroes h JOIN races r ON(h.race_id = r.id) JOIN classes c ON(h.curr_class_id = c.id) WHERE h.id LIKE '%" + id + "%' OR r.name LIKE '%" + ras + "%' OR c.name LIKE '%" + kelas + "%' OR h.name LIKE '%" + nama + "%' OR h.gender LIKE '%" + jk + "%' OR h.des LIKE '%" + desk + "%' OR h.stat_points LIKE '%" + stat + "%' OR h.skill_points LIKE '%" + skill + "%' OR h.exp LIKE '%" + pengalaman + "%' OR h.image LIKE '%" + gambar + "%'");
            
            int i = 1;
            while (rs.next()) {
                HeroModel d = new HeroModel();
                d.setId(rs.getInt("id"));
                d.setRace_id(rs.getInt("race_id"));
                d.setRaceName(rs.getString("raceName"));
                d.setCurr_class_id(rs.getInt("curr_class_id"));
                d.setClassName(rs.getString("className"));
                d.setName(rs.getString("name"));
                d.setGender(rs.getString("gender").charAt(0));
                d.setDes(rs.getString("des"));
                d.setStat_points(rs.getInt("stat_points"));
                d.setSkill_points(rs.getInt("skill_points"));
                d.setExp(rs.getInt("exp"));
                d.setImage(rs.getString("image"));
                
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
    
    public ObservableList<EffectModel> searchEffects(String id, String nama, String desk) {
        try {
            ObservableList<EffectModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM effects WHERE id LIKE '%" + id +"%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                EffectModel d = new EffectModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setDes(rs.getString("des"));
                
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
