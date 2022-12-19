package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBHeroes {
    private HeroModel dt = new HeroModel(); 
    
    public HeroModel getHeroModel() {
        return(dt);
    }
    
    public void setHeroModel(HeroModel s) {
        dt = s;
    }
    
    public ObservableList<HeroModel> load() {
        try {
            ObservableList<HeroModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("SELECT h.id, h.race_id, r.name AS raceName, h.curr_class_id, c.name AS className, h.name, h.gender, h.des, h.exp, h.stat_points, h.skill_points, h.image, h.user_id, u.username FROM heroes h JOIN races r ON(h.race_id = r.id) JOIN classes c ON(h.curr_class_id = c.id) LEFT JOIN users u ON(h.user_id = u.id)");

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
                d.setUser_id(rs.getInt("user_id"));
                d.setUsername(rs.getString("username"));
                d.setImage(rs.getString("image"));
                
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM heroes WHERE id = '" + nomor + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO heroes (race_id, curr_class_id, name, gender, des, stat_points, skill_points, exp, image, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroModel().getRace_id());
            con.preparedStatement.setInt(2, getHeroModel().getCurr_class_id());
            con.preparedStatement.setString(3, getHeroModel().getName());
            con.preparedStatement.setString(4, String.valueOf(getHeroModel().getGender()));
            con.preparedStatement.setString(5, getHeroModel().getDes());
            con.preparedStatement.setInt(6, getHeroModel().getStat_points());
            con.preparedStatement.setInt(7, getHeroModel().getSkill_points());
            con.preparedStatement.setInt(8, getHeroModel().getExp());
            con.preparedStatement.setString(9, getHeroModel().getImage());
            con.preparedStatement.setInt(10, getHeroModel().getUser_id());
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

    public boolean delete(int nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM heroes WHERE id  = ?");
            con.preparedStatement.setInt(1, nomor);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE heroes SET race_id = ?, curr_class_id = ?, name = ?, gender = ?, des = ?, stat_points = ?, skill_points = ?, exp = ?, image =?, user_id = ? WHERE  id = ?");
            con.preparedStatement.setInt(1, getHeroModel().getRace_id());
            con.preparedStatement.setInt(2, getHeroModel().getCurr_class_id());
            con.preparedStatement.setString(3, getHeroModel().getName());
            con.preparedStatement.setString(4, String.valueOf(getHeroModel().getGender()));
            con.preparedStatement.setString(5, getHeroModel().getDes());
            con.preparedStatement.setInt(6, getHeroModel().getStat_points());
            con.preparedStatement.setInt(7, getHeroModel().getSkill_points());
            con.preparedStatement.setInt(8, getHeroModel().getExp());
            con.preparedStatement.setString(9, getHeroModel().getImage());
            con.preparedStatement.setInt(10, getHeroModel().getUser_id());
            con.preparedStatement.setInt(11, getHeroModel().getId());
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
    
    public ObservableList<HeroModel> searchItems(String id, String ras_id, String ras, String kelas_id, String kelas, String nama, String jk, String desk, String skill, String stat, String pengalaman, String gambar, String pengguna_id, String pengguna) {
        try {
            ObservableList<HeroModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT h.id, h.race_id, r.name AS raceName, h.curr_class_id, c.name AS className, h.name, h.gender, h.des, h.exp, h.stat_points, h.skill_points, h.image, h.user_id, u.username FROM heroes h JOIN races r ON(h.race_id = r.id) JOIN classes c ON(h.curr_class_id = c.id) JOIN users u ON(h.user_id = u.id) WHERE h.id LIKE '%" + id + "%' OR r.race_id LIKE '%" + ras_id + "%' OR r.name LIKE '%" + ras + "%' OR c.class_id LIKE '%" + kelas_id + "%' OR c.name LIKE '%" + kelas + "%' OR h.name LIKE '%" + nama + "%' OR h.gender LIKE '%" + jk + "%' OR h.des LIKE '%" + desk + "%' OR h.stat_points LIKE '%" + stat + "%' OR h.skill_points LIKE '%" + skill + "%' OR h.exp LIKE '%" + pengalaman + "%' OR h.image LIKE '%" + gambar + "%' OR c.user_id LIKE '%" + pengguna_id + "%' OR u.username LIKE '%" + pengguna + "%'");
            
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
                d.setUser_id(rs.getInt("user_id"));
                d.setUsername(rs.getString("username"));
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
}
