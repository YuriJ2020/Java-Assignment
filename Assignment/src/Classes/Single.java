
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */
public class Single extends Members implements Serializable {
    
    private String packLoad;
    
    public Single(){
        super(0, "", "", "", "", "", "", "", "", "", 40.00, "", 0);
        this.packLoad = packLoad;
    }

    public Single(int id, String name, String last, String gender, String email, String phone, 
            String address, String suburb, String state, String postcode, double baseFee, 
            String packLoad, String type, int agentID) {
        super(id, name, last, gender, email, phone, address, suburb, state, postcode, baseFee, type, agentID);
        this.packLoad = packLoad;
    }

    public String getPackLoad() {
        return packLoad;
    }

    public void setPackLoad(String pack) {
        this.packLoad = packLoad;
    }
         
    public void calcFees()
    {
        if(getPackLoad().equals("Active Saver"))
        {
            setBaseFee(getBaseFee() + 45);
        }
        else if(getPackLoad().equals("Bronze Plus"))
        {
            setBaseFee(getBaseFee() + 60);
        }
        else
        {
            setBaseFee(getBaseFee() + 80);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Type:" + packLoad ;
    }
    
    
}
