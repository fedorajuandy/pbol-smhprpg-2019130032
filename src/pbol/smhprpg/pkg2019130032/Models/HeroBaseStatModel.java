package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class HeroBaseStatModel {
    private int hero_id, base_stat_id, val;
    private String basestatName;

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
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
