package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class HeroModel {
    private int id, race_id, curr_class_id, exp, stat_points, skill_points, user_id;
    private String name, des, raceName, className, image;
    private char gender;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    public int getCurr_class_id() {
        return curr_class_id;
    }

    public void setCurr_class_id(int curr_class_id) {
        this.curr_class_id = curr_class_id;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
