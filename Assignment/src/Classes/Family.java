
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */
public class Family extends Members implements Serializable
{
    private int noMembers;
    
    public Family()
    {
        super(0, "", "", "", "", "", "", "", "", "", 40.00, "", 0);
        this.noMembers = noMembers;
    }

    public Family(int id, String name, String last, String gender, String email, String phone, 
            String address, String suburb, String state, String postcode, double baseFee, int noMembers, 
            String type, int agentID) {
        super(id, name, last, gender, email, phone, address, suburb, state, postcode, baseFee, type, agentID);
        this.noMembers = noMembers;
    }

    public int getNoMembers() {
        return noMembers;
    }

    public void setNoMembers(int noMembers) {
        this.noMembers = noMembers;
    }
    
    public void calcFees()
    {
        if(noMembers < 3)
        {
            setBaseFee(getBaseFee() + 500);
        }
        else{
            setBaseFee(getBaseFee() + 1000);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "No. of Members: " + noMembers;
    }
    
    
}
