
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
    private String address;
    private String suburb;
    private String state;
    private String postcode;
    private double baseFee; 
    private String type;
    private int agentID;

    //Constructor
    public Members(int id, String name, String last, String gender, String email, 
            String phone, String address, String suburb, String state, String postcode, 
            double fee, String type, int agentID) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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
        return "\n--Member details--" + "\nMember id: " + id + "\nName: " + name + " " + last 
                + "\t\t" + gender + "\t" + email + "\t" + phone 
                + "\nAddress: " + address + "\t" + suburb + "\t" + state 
                + "\t" + postcode + "\nBase Fee: " + baseFee + "\tType: " + type 
                + "\tAgent id: " + agentID;
    }
    
    
}
