package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.HeroClassModel;

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
    
    public ObservableList<HeroClassModel> load1() {
        try {
            ObservableList<HeroClassModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT hc.id, hc.hero_id, h.hero_name, hc.class_id, c.name as className, hc.mastery_lv FROM hero_classes hc JOIN classes c ON(hc.class_id = c.id) JOIN heroes h ON(hc.hero_id = h.id)");
            
            int i = 1;
            while (rs.next()) {
                HeroClassModel d = new HeroClassModel();
                d.setId(rs.getInt("id"));
                d.setHero_id(rs.getInt("hero_id"));
                d.setHeroName(rs.getString("heroName"));
                d.setClass_id(rs.getInt("class_id"));
                d.setClassName(rs.getString("className"));
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
    
    public ObservableList<HeroClassModel> load(int kode) {
        try {
            ObservableList<HeroClassModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT hc.id, hc.hero_id, h.hero_name, hc.class_id, c.name as className, hc.mastery_lv FROM hero_classes hc JOIN classes c ON(hc.class_id = c.id) JOIN heroes h ON(hc.hero_id = h.id) WHERE hc.hero_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                HeroClassModel d = new HeroClassModel();
                d.setId(rs.getInt("id"));
                d.setHero_id(rs.getInt("hero_id"));
                d.setHeroName(rs.getString("heroName"));
                d.setClass_id(rs.getInt("class_id"));
                d.setClassName(rs.getString("className"));
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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "SELECT COUNT(*) AS jml FROM hero_classes WHERE hero_id = '" + nomor + "' AND class_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO hero_classes (hero_id, class_id, mastery_lv) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getHeroClassModel().getHero_id());
            con.preparedStatement.setInt(2, getHeroClassModel().getClass_id());
            con.preparedStatement.setInt(3, getHeroClassModel().getMastery_lv());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM hero_classes WHERE hero_id  = ? AND class_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE hero_classes SET mastery_lv = ?  WHERE hero_id  = ? AND class_id = ?");
            con.preparedStatement.setInt(1, getHeroClassModel().getMastery_lv());
            con.preparedStatement.setInt(2, getHeroClassModel().getHero_id());
            con.preparedStatement.setInt(3, getHeroClassModel().getClass_id());
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
    
    public ObservableList<HeroClassModel> searchItems(String id, String hero_id, String pahlawan, String class_id, String kelas, String level) {
        try {
            ObservableList<HeroClassModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT hc.id, hc.hero_id, h.hero_name, hc.class_id, c.name as className, hc.mastery_lv FROM hero_classes hc JOIN classes c ON(hc.class_id = c.id) JOIN heroes h ON(hc.hero_id = h.id) WHERE id LIKE '%" + id + "%' OR class_id LIKE '%" + class_id + "%' OR className LIKE '%" + kelas + "%' OR hero_id LIKE '%" + hero_id  + "%' OR heroName LIKE '%" + pahlawan + "%' OR mastery_lv LIKE '%" + level + "%'");
            
            int i = 1;
            while (rs.next()) {
                HeroClassModel d = new HeroClassModel();
                d.setId(rs.getInt("id"));
                d.setHero_id(rs.getInt("hero_id"));
                d.setHeroName(rs.getString("heroName"));
                d.setClass_id(rs.getInt("class_id"));
                d.setClassName(rs.getString("className"));
                d.setMastery_lv(rs.getInt("mastery_lv"));
                
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
