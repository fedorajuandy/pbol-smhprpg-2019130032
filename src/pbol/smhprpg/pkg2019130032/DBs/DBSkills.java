package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.SkillModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.SkillModel;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBSkills {
    private SkillModel dt = new SkillModel(); 
    
    public SkillModel getSkillModel() {
        return(dt);
    }
    
    public void setSkillModel(SkillModel s) {
        dt = s;
    }
    
    public ObservableList<SkillModel> load() {
        try {
            ObservableList<SkillModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("SELECT id, name, desc, mp_cost, dmg, success_rate FROM skills");

            int i = 1;
            while (rs.next()) {
                SkillModel d = new SkillModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                d.setMp_cost(rs.getInt("lv"));
                d.setDmg(rs.getDouble("dmg"));
                d.setSuccess_rate(rs.getDouble("success_rate"));
                
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM skills WHERE id = '" + nomor + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO skills (name, desc, mp_cost, dmg, success_rate) VALUES (?, ?, ?, ?, ?)");
            con.preparedStatement.setString(1, getSkillModel().getName());
            con.preparedStatement.setString(2, getSkillModel().getDesc());
            con.preparedStatement.setInt(3, getSkillModel().getMp_cost());
            con.preparedStatement.setDouble(4, getSkillModel().getDmg());
            con.preparedStatement.setDouble(4, getSkillModel().getSuccess_rate());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM skills WHERE id  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE skills SET race_id = ?, curr_class_id = ?, name = ?, gender = ?, desc = ?, lv = ?, exp = ?  WHERE  id = ? ");
            con.preparedStatement.setString(1, getSkillModel().getName());
            con.preparedStatement.setString(2, getSkillModel().getDesc());
            con.preparedStatement.setInt(3, getSkillModel().getMp_cost());
            con.preparedStatement.setDouble(4, getSkillModel().getDmg());
            con.preparedStatement.setDouble(4, getSkillModel().getSuccess_rate());
            con.preparedStatement.executeUpdate();
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
    
    public ObservableList<SkillModel> searchItems(String nama, String desk, String mp, String damage, String sr) {
        try {
            ObservableList<SkillModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT id, name, desc, mp_cost, dmg, success_rate FROM skills WHERE name LIKE '" + nama + "%' OR desc LIKE '" + desk + "%' OR mp_cost LIKE '" + mp + "%' OR dmg LIKE '" + damage + "%' OR success_rate LIKE '" + sr + "%'");
            
            int i = 1;
            while(rs.next()) {
                SkillModel d = new SkillModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                d.setMp_cost(rs.getInt("lv"));
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
