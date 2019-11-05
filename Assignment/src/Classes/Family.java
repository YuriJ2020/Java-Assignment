
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
        super(0, "", "", "", "", "", "", "", "", "", "", 40.00);
        this.noMembers = noMembers;
    }

    public Family(int id, String name, String last, String gender, String email, String phone, String streetNo, String streetName, String suburb, String state, String postcode, double baseFee, int noMembers) {
        super(id, name, last, gender, email, phone, streetNo, streetName, suburb, state, postcode, baseFee);
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
}
