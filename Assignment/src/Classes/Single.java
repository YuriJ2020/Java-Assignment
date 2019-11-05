
package Classes;

import java.io.Serializable;

/**
 *
 * @author ppunme
 */
public class Single extends Members implements Serializable {
    
    private String type;
    
    public Single(){
        super(0, "", "", "", "", "", "", "", "", "", "", 40.00);
        this.type = type;
    }

    public Single(int id, String name, String last, String gender, String email, String phone, String streetNo, String streetName, String suburb, String state, String postcode, double baseFee, String type) {
        super(id, name, last, gender, email, phone, streetNo, streetName, suburb, state, postcode, baseFee);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
         
    public void calcFees()
    {
        if(type.equalsIgnoreCase("Saver"))
        {
            setBaseFee(getBaseFee() + 45);
        }
        else if(type.equalsIgnoreCase("Plus"))
        {
            setBaseFee(getBaseFee() + 60);
        }
        else
        {
            setBaseFee(getBaseFee() + 80);
        }
    }
}
