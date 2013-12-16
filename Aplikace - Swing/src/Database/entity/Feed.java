/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database.entity;

/**
 *
 * @author Petr
 */
public class Feed{
    private int id;
    private String name;
    private String amount;
    private String minimum;

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getMinimum() {
        return minimum;
    }
    
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
    //</editor-fold>
    
    public Feed(int id, String name, String amount, String minimum) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.minimum = minimum;
    }

    public Feed(String name, String amount, String minimum) {
        this.id = -1;
        this.name = name;
        this.amount = amount;
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        return "Feed{" + "id=" + id + ", name=" + name + ", amount=" + amount + ", minimum=" + minimum + '}';
    }
    
}
