package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroBaseStatModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;

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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM hero_base_stats WHERE hero_id = '" + nomor + "' AND base_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO hero_base_stats (hero_id, base_stat_id, val) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroBaseStatModel().getHero_id());
            con.preparedStatement.setInt(2, getHeroBaseStatModel().getBase_stat_id());
            con.preparedStatement.setInt(3, getHeroBaseStatModel().getVal());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM hero_base_stats WHERE hero_id  = ? AND base_stat_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE hero_base_stats SET val = ?  WHERE hero_id  = ? AND base_stat_id = ?");
            con.preparedStatement.setInt(1, getHeroBaseStatModel().getVal());
            con.preparedStatement.setInt(2, getHeroBaseStatModel().getHero_id());
            con.preparedStatement.setInt(3, getHeroBaseStatModel().getBase_stat_id());
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
     
    public ObservableList<BaseStatModel> searchBaseStats(String id, String singkatan, String nama, String desk) {
        try {
            ObservableList<BaseStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM base_stats WHERE id LIKE '%" + id + "%' OR abbrev LIKE '%" + singkatan + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                BaseStatModel d = new BaseStatModel();
                d.setId(rs.getInt("id"));
                d.setAbbrev(rs.getString("abbrev"));
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
