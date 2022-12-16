package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class LevelModel {
    private int id, base_exp, max_lv, stat_points, skill_points;
    private double scale;

    public int getMax_lv() {
        return max_lv;
    }

    public void setMax_lv(int max_lv) {
        this.max_lv = max_lv;
    }

    public int getStat_points() {
        return stat_points;
    }

    public void setStat_points(int stat_points) {
        this.stat_points = stat_points;
    }

    public int getSkill_points() {
        return skill_points;
    }

    public void setSkill_points(int skill_points) {
        this.skill_points = skill_points;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBase_exp() {
        return base_exp;
    }

    public void setBase_exp(int base_exp) {
        this.base_exp = base_exp;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
