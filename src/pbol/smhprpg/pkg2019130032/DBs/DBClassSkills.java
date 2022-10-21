package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.ClassSkillModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public ObservableList<ClassSkillModel> load(String kode) {
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
    
     public int validasi(int nomor) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_skills WHERE id = '" + nomor + "'");
            
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return val;
    }
}
