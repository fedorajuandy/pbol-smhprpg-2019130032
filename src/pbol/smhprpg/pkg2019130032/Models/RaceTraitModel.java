package pbol.smhprpg.pkg2019130032.Models;

/**
 *
 * @author 2019130032 - Fedora Yoshe Juandy
 */
public class RaceTraitModel {
    private int race_id, trait_id;
    private String traitName;

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    public int getTrait_id() {
        return trait_id;
    }

    public void setTrait_id(int trait_id) {
        this.trait_id = trait_id;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }
}
