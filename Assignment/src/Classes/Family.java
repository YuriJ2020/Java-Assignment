
package Classes;

import java.io.Serializable;

/**
 *
 * @author Poonnamee
 * Date: 27/11/19
 * Family class (subclass)
 */
public class Family extends Members implements Serializable
{
    private int noMembers;
    
    public Family()
    {
        super(0, "", "", "", "", "", 40.00, "", 0);
        this.noMembers = noMembers;
    }

    public Family(int id, String name, String last, String gender, String email, String phone, 
            double baseFee, int noMembers, String type, int agentID) {
        super(id, name, last, gender, email, phone, baseFee, type, agentID);
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
            setFee(getFee() * 1.2);
        }
        else if(noMembers < 5)
        {
            setFee(getFee() * 1.8);
        }
        else
        {
            setFee(getFee() * 2.5);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "No. of Members: " + noMembers;
    }
    
    
}
