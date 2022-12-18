package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Models.TraitBaseStatModel;
import pbol.smhprpg.pkg2019130032.Koneksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.TraitModel;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBTraitBaseStats {
    private TraitBaseStatModel dt = new TraitBaseStatModel();
    
    public TraitBaseStatModel getTraitBaseStatModel() {
        return(dt);
    }
    
    public void setTraitBaseStatModel(TraitBaseStatModel s) {
        dt = s;
    }
    
    public ObservableList<TraitBaseStatModel> load(int kode) {
        try {
            ObservableList<TraitBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT tbs.trait_id, tbs.base_stat_id, bs.name, tbs.val FROM trait_base_stats tbs JOIN base_stats bs ON (tbs.base_stat_id = bs.id) WHERE tbs.trait_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                TraitBaseStatModel d = new TraitBaseStatModel();
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setTrait_id(rs.getInt("trait_id"));
                d.setVal(rs.getInt("val"));
                d.setBasestatName(rs.getString("bs.name"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM trait_base_stats WHERE base_stat_id = '" + nomor + "' AND base_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO trait_base_stats (trait_id, base_stat_id, val) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getTraitBaseStatModel().getTrait_id());
            con.preparedStatement.setInt(2, getTraitBaseStatModel().getBase_stat_id());
            con.preparedStatement.setInt(3, getTraitBaseStatModel().getVal());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM trait_base_stats WHERE trait_id  = ? AND base_stat_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE trait_base_stats SET val = ?  WHERE trait_id  = ? AND base_stat_id = ?");
            con.preparedStatement.setInt(1, getTraitBaseStatModel().getVal());
            con.preparedStatement.setInt(2, getTraitBaseStatModel().getTrait_id());
            con.preparedStatement.setInt(3, getTraitBaseStatModel().getBase_stat_id());
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
