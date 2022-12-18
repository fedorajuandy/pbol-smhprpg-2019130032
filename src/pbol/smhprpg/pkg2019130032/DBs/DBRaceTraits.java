package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.RaceTraitModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.RaceModel;
import pbol.smhprpg.pkg2019130032.Models.TraitModel;

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
    
    public int validasi(int nomor, int nomor1) {
        int val = 0;
        
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM race_traits WHERE id = '" + nomor + "' AND trait_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO race_traits (race_id, trait_id) VALUES (?, ?)");
            con.preparedStatement.setInt(1, getRaceTraitModel().getRace_id());
            con.preparedStatement.setInt(2, getRaceTraitModel().getTrait_id());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM race_traits WHERE race_id  = ? AND trait_id = ?");
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
    
    public ObservableList<RaceModel> searchRaces(String id, String parent, String nama, String desk) {
        try {
            ObservableList<RaceModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT r.id, r.parentrace_id, p.name AS parentraceName, r.name, r.des FROM races r LEFT JOIN races p ON(r.parentrace_id = p.id) WHERE r.idLIKE '%" + id + "%' OR c.name LIKE '%" + parent + "%' OR r.name LIKE '%" + nama + "%' OR p.name LIKE '%" + nama + "%' OR r.des LIKE '%" + desk + "%'");
                        
            int i = 1;
            while (rs.next()) {
                RaceModel d = new RaceModel();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setParentrace_id(rs.getInt("parentrace_id"));
                if (rs.getInt("parentrace_id") != 0) {
                    d.setParentraceName(rs.getString("parentRaceName"));
                } else {
                    d.setParentraceName("");
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
