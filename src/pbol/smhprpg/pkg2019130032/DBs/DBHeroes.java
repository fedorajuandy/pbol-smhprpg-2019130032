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
            
            ResultSet rs = con.statement.executeQuery("SELECT h.id, h.race_id, r.name, h.curr_class_id, name, c.name, h.gender, h.desc, h.lv, h.exp FROM heroes h JOIN races c ON(h.race_id = c.id) JOIN classes ON(h.curr_class_id = c.id)");

            int i = 1;
            while (rs.next()) {
                HeroModel d = new HeroModel();
                d.setId(rs.getInt("id"));
                d.setRace_id(rs.getInt("race_id"));
                d.setRaceName(rs.getString("r.name"));
                d.setCurr_class_id(rs.getInt("curr_class_id"));
                d.setClassName(rs.getString("c.name"));
                d.setName(rs.getString("name"));
                d.setGender(rs.getString("gender").charAt(0));
                d.setDesc(rs.getString("desc"));
                d.setLv(rs.getInt("lv"));
                d.setExp(rs.getInt("exp"));
                
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO heroes (race_id, curr_class_id, name, gender, desc, lv, exp) VALUES (?, ?, ?, ?, ?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroModel().getRace_id());
            con.preparedStatement.setInt(2, getHeroModel().getCurr_class_id());
            con.preparedStatement.setString(3, getHeroModel().getName());
            con.preparedStatement.setString(4, String.valueOf(getHeroModel().getGender()));
            con.preparedStatement.setString(5, getHeroModel().getDesc());
            con.preparedStatement.setInt(6, getHeroModel().getLv());
            con.preparedStatement.setInt(7, getHeroModel().getExp());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM heroes WHERE id  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE heroes SET race_id = ?, curr_class_id = ?, name = ?, gender = ?, desc = ?, lv = ?, exp = ?  WHERE  id = ? ");
            con.preparedStatement.setInt(1, getHeroModel().getRace_id());
            con.preparedStatement.setInt(2, getHeroModel().getCurr_class_id());
            con.preparedStatement.setString(3, getHeroModel().getName());
            con.preparedStatement.setString(4, String.valueOf(getHeroModel().getGender()));
            con.preparedStatement.setString(5, getHeroModel().getDesc());
            con.preparedStatement.setInt(6, getHeroModel().getLv());
            con.preparedStatement.setInt(7, getHeroModel().getExp());
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
    
    public ObservableList<HeroModel> searchItems(String ras, String kelas, String nama, String jk, String desk, String lv, String pengalaman) {
        try {
            ObservableList<HeroModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT h.id, h.race_id, r.name, h.curr_class_id, name, c.name, h.gender, h.desc, h.lv, h.exp FROM heroes h JOIN races c ON(h.race_id = c.id) JOIN classes ON(h.curr_class_id = c.id) WHERE r.name LIKE '" + ras + "%' OR c.name LIKE '" + kelas + "%' OR h.name LIKE '" + nama + "%' OR h.gender LIKE '" + jk + "%' OR h.desc LIKE '" + desk + "%' OR h.lv LIKE '" + lv + "%' OR h.exp LIKE '" + pengalaman + "%'");
            
            int i = 1;
            while(rs.next()) {
                HeroModel d = new HeroModel();
                d.setId(rs.getInt("id"));
                d.setRace_id(rs.getInt("race_id"));
                d.setRaceName(rs.getString("r.name"));
                d.setCurr_class_id(rs.getInt("curr_class_id"));
                d.setClassName(rs.getString("c.name"));
                d.setName(rs.getString("name"));
                d.setGender(rs.getString("gender").charAt(0));
                d.setDesc(rs.getString("desc"));
                d.setLv(rs.getInt("lv"));
                d.setExp(rs.getInt("exp"));
                
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
