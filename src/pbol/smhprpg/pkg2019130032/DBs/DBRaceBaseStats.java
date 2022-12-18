package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.RaceBaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.BaseStatModel;
import pbol.smhprpg.pkg2019130032.Models.ClassModel;
import pbol.smhprpg.pkg2019130032.Models.RaceModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBRaceBaseStats {
    private RaceBaseStatModel dt = new RaceBaseStatModel();
    
    public RaceBaseStatModel getRaceBaseStatModel() {
        return(dt);
    }
    
    public void setRaceBaseStatModel(RaceBaseStatModel s) {
        dt = s;
    }
    
    public ObservableList<RaceBaseStatModel> load(int kode) {
        try {
            ObservableList<RaceBaseStatModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT rbs.race_id, rbs.base_stat_id, bs.name AS basestatName, rbs.val FROM race_base_stats rbs JOIN base_stats bs ON (rbs.base_stat_id = bs.id) WHERE rbs.race_id LIKE '" + kode + "'");
            
            int i = 1;
            while (rs.next()) {
                RaceBaseStatModel d = new RaceBaseStatModel();
                d.setRace_id(rs.getInt("race_id"));
                d.setBase_stat_id(rs.getInt("base_stat_id"));
                d.setVal(rs.getInt("val"));
                d.setBasestatName(rs.getString("basestatName"));
                
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
            ResultSet rs = con.statement.executeQuery("SELECT COUNT(*) AS jml FROM race_base_stats WHERE race_id = '" + nomor + "' AND base_stat_id = '" + nomor1 + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO race_base_stats (race_id, base_stat_id, val) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getRaceBaseStatModel().getRace_id());
            con.preparedStatement.setInt(2, getRaceBaseStatModel().getBase_stat_id());
            con.preparedStatement.setInt(3, getRaceBaseStatModel().getVal());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM race_base_stats WHERE race_id  = ? AND base_stat_id = ?");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE race_base_stats SET val = ?  WHERE race_id  = ? AND base_stat_id = ?");
            con.preparedStatement.setInt(1, getRaceBaseStatModel().getVal());
            con.preparedStatement.setInt(2, getRaceBaseStatModel().getRace_id());
            con.preparedStatement.setInt(3, getRaceBaseStatModel().getBase_stat_id());
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
