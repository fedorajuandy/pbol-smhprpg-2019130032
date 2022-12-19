package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.ClassBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
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
public class DBClassBaseStats {
    private ClassBaseStatModel dt = new ClassBaseStatModel();
    
    public ClassBaseStatModel getClassBaseStatModel() {
        return(dt);
    }
    
    public void setClassBaseStatModel(ClassBaseStatModel s) {
        dt = s;
    }
     
    public ObservableList<ClassBaseStatModel> load(int kode) {
        try {
            ObservableList<ClassBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT cbs.class_id, cbs.base_stat_id, bs.name, cbs.levelup_levelup_val FROM class_base_stats cbs JOIN base_stats bs ON(cbs.base_stat_id = bs.id) WHERE cbs.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassBaseStatModel d = new ClassBaseStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setClass_id(rs.getInt("class_id"));
                d.setBasestatName(rs.getString("name"));
                d.setLevelup_val(rs.getInt("levelup_levelup_val"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_base_stats WHERE class_id = '" + nomor + "' AND base_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO class_base_stats (class_id, base_stat_id, levelup_val) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getClassBaseStatModel().getClass_id());
            con.preparedStatement.setInt(2, getClassBaseStatModel().getBase_stat_id());
            con.preparedStatement.setInt(3, getClassBaseStatModel().getLevelup_val());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM class_base_stats WHERE class_id  = ? AND base_stat_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE class_base_stats SET levelup_val = ?  WHERE class_id  = ? AND base_stat_id = ?");
            con.preparedStatement.setInt(1, getClassBaseStatModel().getLevelup_val());
            con.preparedStatement.setInt(2, getClassBaseStatModel().getClass_id());
            con.preparedStatement.setInt(3, getClassBaseStatModel().getBase_stat_id());
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
