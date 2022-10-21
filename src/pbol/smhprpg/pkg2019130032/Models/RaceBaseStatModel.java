package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class RaceBaseStatModel {
    private int race_id, base_stat_id, val;
    private String basestatName;

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    public int getBase_stat_id() {
        return base_stat_id;
    }

    public void setBase_stat_id(int base_stat_id) {
        this.base_stat_id = base_stat_id;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getBasestatName() {
        return basestatName;
    }

    public void setBasestatName(String basestatName) {
        this.basestatName = basestatName;
    }
}
