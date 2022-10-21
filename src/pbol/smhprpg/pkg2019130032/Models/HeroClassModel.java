package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class HeroClassModel {
    private int id, hero_id, class_id, mastery_lv;
    private String className;

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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getMastery_lv() {
        return mastery_lv;
    }

    public void setMastery_lv(int mastery_lv) {
        this.mastery_lv = mastery_lv;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
