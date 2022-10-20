package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class RaceModel {
    private int id, parentrace_id;
    private String name, desc, parentraceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentrace_id() {
        return parentrace_id;
    }

    public void setParentrace_id(int parentrace_id) {
        this.parentrace_id = parentrace_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParentraceName() {
        return parentraceName;
    }

    public void setParentraceName(String parentraceName) {
        this.parentraceName = parentraceName;
    }
}
