package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.RaceTraitModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBRaceTraits {
    private RaceTraitModel dt = new RaceTraitModel();
    
    public RaceTraitModel getRaceTraitModel() {
        return(dt);
    }
    
    public void setRaceTraitModel(RaceTraitModel s) {
        dt = s;
    }
    
    public ObservableList<RaceTraitModel> load(int kode) {
        try {
            ObservableList<RaceTraitModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT rt.race_id, rt.trait_id, t.name FROM race_traits rt JOIN traits t ON (rt.trait_id = t.id) WHERE rt.race_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                RaceTraitModel d = new RaceTraitModel();
                d.setRace_id(rs.getInt("race_id"));
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM race_traits WHERE id = '" + nomor + "'");
            
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
