package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.ClassSkillModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;
import pbol.smhprpg.pkg2019130032.Models.SkillModel;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBClassSkills {
    private ClassSkillModel dt = new ClassSkillModel();
    
    public ClassSkillModel getClassSkillModel() {
        return(dt);
    }
    
    public void setClassSkillModel(ClassSkillModel s) {
        dt = s;
    }
    
    public ObservableList<ClassSkillModel> load(int kode) {
        try {
            ObservableList<ClassSkillModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT cs.class_id, cs.skill_id, s.name FROM class_skills cs JOIN skills s ON (cs.skill_id = s.id) WHERE cs.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassSkillModel d = new ClassSkillModel();
                d.setClass_id(rs.getInt("class_id"));
                d.setSkill_id(rs.getInt("skill_id"));
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_skills WHERE class_id = '" + nomor + "' AND skill_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO class_skills (class_id, skill_id) VALUES (?, ?)");
            con.preparedStatement.setInt(1, getClassSkillModel().getClass_id());
            con.preparedStatement.setInt(2, getClassSkillModel().getSkill_id());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM class_skills WHERE class_id  = ? AND skill_id = ?");
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
     
    public ObservableList<ClassModel> searchClasses(String id, String parent, String nama, String desk) {
        try {
            ObservableList<ClassModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT c.id, c.parentclass_id, p.name AS parentclassName, c.name, c.des FROM classes c LEFT JOIN classes p ON(c.parentclass_id = p.id) WHERE c.id LIKE '%" + id + "%' OR c.parentclass_id LIKE '%" + parent + "%' OR c.name LIKE '%" + nama + "%' OR p.name LIKE '%" + nama + "%' OR c.des LIKE '%" + desk + "%'");
                        
            int i = 1;
            while (rs.next()) {
                ClassModel d = new ClassModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setParentclass_id(rs.getInt("parentclass_id"));
                if (rs.getInt("parentclass_id") != 0) {
                    d.setParentclassName(rs.getString("parentclassName"));
                } else {
                    d.setParentclassName("");
                }
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
