
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */
public class Single extends Members implements Serializable {
    
    private String typeLoad;
    
    public Single(){
        super(0, "", "", "", "", "", "", "", "", "", 40.00);
        this.typeLoad = typeLoad;
    }

    public Single(int id, String name, String last, String gender, String email, String phone, String address, String suburb, String state, String postcode, double baseFee, String typeLoad) {
        super(id, name, last, gender, email, phone, address, suburb, state, postcode, baseFee);
        this.typeLoad = typeLoad;
    }

    public String getTypeLoad() {
        return typeLoad;
    }

    public void setTypeLoad(String type) {
        this.typeLoad = typeLoad;
    }
         
    public void calcFees()
    {
        if(getTypeLoad().equals("Active Saver"))
        {
            setBaseFee(getBaseFee() + 45);
        }
        else if(getTypeLoad().equals("Bronze Plus"))
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
        return super.toString() + "Type=" + typeLoad ;
    }
    
    
}
