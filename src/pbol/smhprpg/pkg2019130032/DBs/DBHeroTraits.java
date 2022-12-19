package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroTraitModel;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;
import pbol.smhprpg.pkg2019130032.Models.TraitModel;

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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_traits WHERE hero_id = '" + nomor + "' AND trait_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO hero_traits (hero_id, trait_id) VALUES (?, ?)");
            con.preparedStatement.setInt(1, getHeroTraitModel().getHero_id());
            con.preparedStatement.setInt(2, getHeroTraitModel().getTrait_id());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM hero_traits WHERE hero_id  = ? AND trait_id = ?");
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
                d.setGender(rs.getString("gender"));
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
     
    public ObservableList<TraitModel> searchTraits(String id, String nama, String desk) {
        try {
            ObservableList<TraitModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM traits WHERE id LIKE '%" + id + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                TraitModel d = new TraitModel();
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
