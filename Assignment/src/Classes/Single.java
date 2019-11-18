
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */
public class Single extends Members implements Serializable {
    
    private String packLoad;
    
    public Single(){
        super("", "", "", "", "", "", "", "", "", 40.00, "", 0);
        this.packLoad = packLoad;
    }

    public Single(String name, String last, String gender, String email, String phone, 
            String address, String suburb, String state, String postcode, double fee, 
            String packLoad, String type, int agentID) {
        super(name, last, gender, email, phone, address, suburb, state, postcode, fee, type, agentID);
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
        System.out.println("Pack: " + packLoad);
        if(packLoad.equalsIgnoreCase("Saver"))
        {
            System.out.println("Saver");
            setFee(getFee());
        }
        else if(packLoad.equalsIgnoreCase("Bronze"))
        {
            System.out.println("Bronze");
            setFee(getFee() * 1.5); //Bronze Package pay 150%
        }
        else
        {
            System.out.println("Ultimate");
            setFee(getFee() * 2); //Ultimate Package pay 200%
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Type:" + packLoad ;
    }
    
    
}
