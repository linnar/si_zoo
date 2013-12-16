/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.entity;

/**
 *
 * @author Petr
 */
public class Staff{
    int id;
    private String personalId;
    private String name;
    private String lastname;
    private String birth;
    private String contact;

    public int getId() {
        return id;
    }

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public void setId(int id) {
        this.id = id;
    }
    
    public String getPersonalId() {
        return personalId;
    }
    
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getBirth() {
        return birth;
    }
    
    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    //</editor-fold>
    
    public Staff(int id, String personalId, String name, String lastname, String birth, String contact) {
        this.id = id;
        this.personalId = personalId;
        this.name = name;
        this.lastname = lastname;
        this.birth = birth;
        this.contact = contact;
    }

    public Staff(String personalId, String name, String lastname, String birth, String contact) {
        this.id = -1;
        this.personalId = personalId;
        this.name = name;
        this.lastname = lastname;
        this.birth = birth;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", personalId=" + personalId + ", name=" + name + ", lastname=" + lastname + ", birth=" + birth + ", contact=" + contact + '}';
    }
    
    
}
