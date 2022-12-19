package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.ClassBattleStatModel;
import pbol.smhprpg.pkg2019130032.Models.BattleStatModel;
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
public class DBClassBattleStats {
    private ClassBattleStatModel dt = new ClassBattleStatModel();
    
    public ClassBattleStatModel getClassBattleStatModel() {
        return(dt);
    }
    
    public void setClassBattleStatModel(ClassBattleStatModel s) {
        dt = s;
    }
    
    public ObservableList<ClassBattleStatModel> load(int kode) {
        try {
            ObservableList<ClassBattleStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT cbs.class_id, cbs.battle_stat_id, bs.name, cbs.scale FROM class_battle_stats cbs JOIN battle_stats bs ON(cbs.battle_stat_id = bs.id) WHERE cbs.class_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                ClassBattleStatModel d = new ClassBattleStatModel();
                d.setBattle_stat_id(rs.getInt("battle_stat_id"));                
                d.setClass_id(rs.getInt("class_id"));
                d.setBattlestatName(rs.getString("name"));
                d.setScale(rs.getInt("scale"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_battle_stats WHERE class_id = '" + nomor + "' AND battle_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO class_battle_stats (class_id, battle_stat_id, scale) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getClassBattleStatModel().getClass_id());
            con.preparedStatement.setInt(2, getClassBattleStatModel().getBattle_stat_id());
            con.preparedStatement.setDouble(3, getClassBattleStatModel().getScale());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM class_battle_stats WHERE class_id  = ? AND battle_stat_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE class_battle_stats SET scale = ?  WHERE class_id  = ? AND battle_stat_id = ?");
            con.preparedStatement.setDouble(1, getClassBattleStatModel().getScale());
            con.preparedStatement.setInt(2, getClassBattleStatModel().getClass_id());
            con.preparedStatement.setInt(3, getClassBattleStatModel().getBattle_stat_id());
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
    
    public ObservableList<BattleStatModel> searchBattleStats(String id, String singkatan, String nama, String desk) {
        try {
            ObservableList<BattleStatModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM battle_stats WHERE id LIKE '%" + id + "%' OR abbrev LIKE '" + singkatan + "%' OR name LIKE '%" + nama + "%' OR des LIKE '%" + desk + "%'");
            
            int i = 1;
            while (rs.next()) {
                BattleStatModel d = new BattleStatModel();
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
