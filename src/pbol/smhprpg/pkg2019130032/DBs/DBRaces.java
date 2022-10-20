package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
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
public class DBRaces {
    private RaceModel dt = new RaceModel(); 
    
    public RaceModel getRaceModel() {
        return(dt);
    }
    
    public void setRaceModel(RaceModel s) {
        dt = s;
    }
    
    public ObservableList<RaceModel> load() {
        try {
            ObservableList<RaceModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            
            ResultSet rs = con.statement.executeQuery("SELECT id, parentrace_id, name, desc FROM races");

            int i = 1;
            while (rs.next()) {
                RaceModel d = new RaceModel();
                d.setId(rs.getInt("id"));
                
                d.setParentrace_id(rs.getInt("parentrace_id"));
                int parentraceId;
                if(rs.getInt("parentrace_id") != 0) {
                    parentraceId = rs.getInt("parentrace_id");
                    ResultSet rs2 = con.statement.executeQuery("SELECT p.name FROM races c JOIN races p ON(c.parentrace_id = p.id) WHERE parentrace_id = " + parentraceId);
                    d.setParentraceName(rs2.getString("name"));
                } else {
                    d.setParentraceName("-");
                }
                
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                
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
            ResultSet rs = con.statement.executeQuery( "SELECT COUNT(*) AS jml FROM races WHERE id = '" + nomor + "'");
            
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("INSERT INTO races (parentrace_id, name, desc) VALUES (?, ?, ?)");
            con.preparedStatement.setInt(1, getRaceModel().getParentrace_id());
            con.preparedStatement.setString(2, getRaceModel().getName());
            con.preparedStatement.setString(3, getRaceModel().getDesc());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("DELETE FROM races WHERE id  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE races SET parentrace_id = ?, name = ?, desc = ?  WHERE  id = ? ");
            con.preparedStatement.setInt(1, getRaceModel().getParentrace_id());
            con.preparedStatement.setString(2, getRaceModel().getName());
            con.preparedStatement.setString(3, getRaceModel().getDesc());
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
    
    public ObservableList<RaceModel> searchItems(String parent, String nama, String desk) {
        try {
            ObservableList<RaceModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("SELECT * FROM races WHERE parentrace_id LIKE '" + parent + "%' OR name LIKE '" + nama + "%' OR desc LIKE '" + desk + "%'");
            
            int i = 1;
            while(rs.next()) {
                RaceModel d = new RaceModel();
                d.setId(rs.getInt("id"));
                
                d.setParentrace_id(rs.getInt("parentrace_id"));
                int parentraceId;
                if(rs.getInt("parentrace_id") != 0) {
                    parentraceId = rs.getInt("parentrace_id");
                    ResultSet rs2 = con.statement.executeQuery("SELECT p.name FROM races c JOIN races p ON(c.parentrace_id = p.id) WHERE parentrace_id = " + parentraceId);
                    d.setParentraceName(rs2.getString("name"));
                } else {
                    d.setParentraceName("-");
                }
                
                d.setName(rs.getString("name"));
                d.setDesc(rs.getString("desc"));
                
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
