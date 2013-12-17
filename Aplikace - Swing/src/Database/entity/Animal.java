/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.entity;

/**
 *
 * @author Petr
 */
public class Animal{
    private int id;
    private String chip;
    private String birth;
    private String specie;
    private String name;

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getChip() {
        return chip;
    }
    
    public void setChip(String chip) {
        this.chip = chip;
    }
    
    public String getBirth() {
        return birth;
    }
    
    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    public String getSpecie() {
        return specie;
    }
    
    public void setSpecie(String specie) {
        this.specie = specie;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
    
    public Animal(int id,String chip, String birth, String specie, String name) {
        this.id = id;
        this.chip = chip;
        this.birth = birth;
        this.specie = specie;
        this.name = name;
    }
    
    public Animal(String chip, String birth, String specie, String name) {
        this.id = -1;
        this.chip = chip;
        this.birth = birth;
        this.specie = specie;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", chip=" + chip + ", birth=" + birth + ", specie=" + specie + ", name=" + name + '}';
    }
    
    

}
