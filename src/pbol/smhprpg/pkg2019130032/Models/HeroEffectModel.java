package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class HeroEffectModel {
    private int id, hero_id, effect_id, duration_left;
    private String effectName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public int getEffect_id() {
        return effect_id;
    }

    public void setEffect_id(int effect_id) {
        this.effect_id = effect_id;
    }

    public int getDuration_left() {
        return duration_left;
    }

    public void setDuration_left(int duration_left) {
        this.duration_left = duration_left;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }
}
