
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */

//Abstract class
public abstract class Members implements Serializable
{
    private int id;
    private String name;
    private String last;
    private String gender;
    private String email;
    private String phone;
    private double baseFee; 
    private String type;
    private int agentID;

    //Constructor
    public Members(int id, String name, String last, String gender, String email, 
            String phone, double fee, String type, int agentID) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.baseFee = fee;
        this.type = type;
        this.agentID = agentID;
    }
    
    public abstract void calcFees();
    //----Abstract Method----

    //GET and SET methods
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

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getFee() {
        return this.baseFee;
    }

    public void setFee(double fee) {
        this.baseFee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\t" + name + " " + last 
                + "\t" + gender + "\t" + email + "\t" + phone + "\t" + baseFee 
                + "\t" + type + "\t" + agentID;
    }
}
