package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class LevelModel {
    private int id, needed_exp, max_lv;
    private Double scale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNeeded_exp() {
        return needed_exp;
    }

    public void setNeeded_exp(int needed_exp) {
        this.needed_exp = needed_exp;
    }

    public int getMax_lv() {
        return max_lv;
    }

    public void setMax_lv(int max_lv) {
        this.max_lv = max_lv;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }
}
