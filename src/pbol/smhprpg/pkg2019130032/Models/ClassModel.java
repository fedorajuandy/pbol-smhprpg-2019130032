package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class ClassModel {
    private int id, parentclass_id;
    private String name, des, parentclassName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentclass_id() {
        return parentclass_id;
    }

    public void setParentclass_id(int parentclass_id) {
        this.parentclass_id = parentclass_id;
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

    public String getParentclassName() {
        return parentclassName;
    }

    public void setParentclassName(String parentclassName) {
        this.parentclassName = parentclassName;
    }
}
