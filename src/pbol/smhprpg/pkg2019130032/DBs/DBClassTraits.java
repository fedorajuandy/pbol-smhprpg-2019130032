package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.ClassTraitModel;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;
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
public class DBClassTraits {
    private ClassTraitModel dt = new ClassTraitModel();
    
    public ClassTraitModel getClassTraitModel() {
        return(dt);
    }
    
    public void setClassTraitModel(ClassTraitModel s) {
        dt = s;
    }
    
    public ObservableList<ClassTraitModel> load(int kode) {
        try {
            ObservableList<ClassTraitModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT ct.class_id, ct.trait_id, t.name FROM class_traits ct JOIN traits t ON (ct.trait_id = t.id) WHERE ct.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassTraitModel d = new ClassTraitModel();
                d.setClass_id(rs.getInt("class_id"));
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_traits WHERE class_id = '" + nomor + "' AND trait_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO class_traits (class_id, trait_id) VALUES (?, ?)");
            con.preparedStatement.setInt(1, getClassTraitModel().getClass_id());
            con.preparedStatement.setInt(2, getClassTraitModel().getTrait_id());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM class_traits WHERE class_id  = ? AND trait_id = ?");
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
