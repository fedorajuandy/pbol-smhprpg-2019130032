package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class BaseToBattleStatModel {
    private int base_stat_id, battle_stat_id;
    private double scale;
    private String baseStatName, battleStatName;

    public int getBase_stat_id() {
        return base_stat_id;
    }

    public void setBase_stat_id(int base_stat_id) {
        this.base_stat_id = base_stat_id;
    }

    public int getBattle_stat_id() {
        return battle_stat_id;
    }

    public void setBattle_stat_id(int battle_stat_id) {
        this.battle_stat_id = battle_stat_id;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getBaseStatName() {
        return baseStatName;
    }

    public void setBaseStatName(String baseStatName) {
        this.baseStatName = baseStatName;
    }

    public String getBattleStatName() {
        return battleStatName;
    }

    public void setBattleStatName(String battleStatName) {
        this.battleStatName = battleStatName;
    }
}
