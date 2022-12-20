package pbol.smhprpg.pkg2019130032.DBs;

import pbol.smhprpg.pkg2019130032.Koneksi;
import pbol.smhprpg.pkg2019130032.Models.LevelModel;

import java.sql.ResultSet;
import pbol.smhprpg.pkg2019130032.Models.HeroModel;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class DBLevel {
    private LevelModel dt = new LevelModel(); 

    public LevelModel getLevelModel() {
        return dt;
    }

    public void setLevelModel(LevelModel dt) {
        this.dt = dt;
    }
    
    public LevelModel load() {
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT id, max_lv, base_exp, scale, stat_points, skill_points FROM level");
            
            LevelModel d = new LevelModel();
            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setMax_lv(rs.getInt("max_lv"));
                d.setBase_exp(rs.getInt("base_exp"));
                d.setScale(rs.getDouble("scale"));
                d.setStat_points(rs.getInt("stat_points"));
                d.setSkill_points(rs.getInt("skill_points"));
            }
            
            con.tutupKoneksi();
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("UPDATE level SET max_lv = ?, base_exp = ?, scale = ?, stat_points = ?, skill_points = ? WHERE  id = ?");
            con.preparedStatement.setInt(1, getLevelModel().getMax_lv());
            con.preparedStatement.setInt(2, getLevelModel().getBase_exp());
            con.preparedStatement.setDouble(3, getLevelModel().getScale());
            con.preparedStatement.setInt(4, getLevelModel().getStat_points());
            con.preparedStatement.setInt(5, getLevelModel().getSkill_points());
            con.preparedStatement.setInt(6, getLevelModel().getId());
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
    
    public int levelUp(HeroModel h, LevelModel l) {
        int lv = 1;
        int exp = h.getExp();
        int max_lv = l.getMax_lv();
        int base_exp = l.getBase_exp();
        double scale = l.getScale();
        int temp = base_exp;
        
        while (exp - temp > 0) {
            if (lv >= max_lv) {
                return max_lv;
            } else {
                temp = (int) Math.round(temp + temp * scale);
                lv++;
            }
        }
        
        return lv;
    }
}
