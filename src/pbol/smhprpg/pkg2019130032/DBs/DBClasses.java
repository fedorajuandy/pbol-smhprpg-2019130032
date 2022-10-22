package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBClasses {
    private ClassModel dt = new ClassModel(); 
    
    public ClassModel getClassModel() {
        return(dt);
    }
    
    public void setClassModel(ClassModel s) {
        dt = s;
    }
    
    public ObservableList<ClassModel> load() {
        try {
            ObservableList<ClassModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT c.id, c.parentclass_id, p.name AS parentclassName, c.name, c.des FROM classes c LEFT JOIN classes p ON(c.parentclass_id = p.id)");
                        
            int i = 1;
            while (rs.next()) {
                ClassModel d = new ClassModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setParentclass_id(rs.getInt("parentclass_id"));
                if(rs.getInt("parentclass_id") != 0) {
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM classes WHERE id = '" + nomor + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO classes (parentclass_id, name, des) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getClassModel().getParentclass_id());
            con.preparedStatement.setString(2, getClassModel().getName());
            con.preparedStatement.setString(3, getClassModel().getDes());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM classes WHERE id  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE classes SET parentclass_id = ?, name = ?, des = ?  WHERE  id = ? ");
            con.preparedStatement.setInt(1, getClassModel().getParentclass_id());
            con.preparedStatement.setString(2, getClassModel().getName());
            con.preparedStatement.setString(3, getClassModel().getDes());
            con.preparedStatement.setInt(4, getClassModel().getId());
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
    
    public ObservableList<ClassModel> searchItems(String parent, String nama, String desk) {
        try {
            ObservableList<ClassModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT c.id, c.parentclass_id, p.name AS parentclassName, c.name, c.des FROM classes c LEFT JOIN classes p ON(c.parentclass_id = p.id) WHERE c.parentclass_id LIKE '" + parent + "%' OR c.name LIKE '" + nama + "%' OR p.name LIKE '" + nama + "%' OR c.des LIKE '" + desk + "%'");
                        
            int i = 1;
            while (rs.next()) {
                ClassModel d = new ClassModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setParentclass_id(rs.getInt("parentclass_id"));
                if(rs.getInt("parentclass_id") != 0) {
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
}
