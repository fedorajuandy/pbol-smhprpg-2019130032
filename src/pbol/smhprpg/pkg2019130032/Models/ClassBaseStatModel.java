package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class ClassBaseStatModel {
    private int class_id, base_stat_id, levelup_val;
    private String basestatName;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getBase_stat_id() {
        return base_stat_id;
    }

    public void setBase_stat_id(int base_stat_id) {
        this.base_stat_id = base_stat_id;
    }

    public int getLevelup_val() {
        return levelup_val;
    }

    public void setLevelup_val(int levelup_val) {
        this.levelup_val = levelup_val;
    }

    public String getBasestatName() {
        return basestatName;
    }

    public void setBasestatName(String basestatName) {
        this.basestatName = basestatName;
    }
}
