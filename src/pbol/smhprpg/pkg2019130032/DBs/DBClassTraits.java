package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.ClassTraitModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    
     public int validasi(int nomor) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM class_traits WHERE id = '" + nomor + "'");
            
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
