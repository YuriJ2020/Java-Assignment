
package Utilities;

/**
 *
 * @author Poonnamee
 * Date: 27/11/19
 * Validate input data
 */
public class Validation {
    
    public static boolean CheckNull(String first, String last, String gender, String email, String phone, String type) {
        //check to see if each TextField have data
        if(first.equals("") || last.equals("") || gender == null || email.equals("") || phone.equals("") || type == null) {
            return true;
        } else {
            return false;
        }
    }
    
    //check if first name and last name is String
    public static boolean isString(String first, String last){
        if(first.matches("[a-zA-Z]+") && last.matches("[a-zA-Z]+")){
            return true;
        } else {
            return false;
        }
    }
    
    //check phone is numeric and at least 9 number
    public static boolean checkPhone(String phone){
        if(phone.matches("-?\\d+(\\.\\d+)?") && phone.length() >= 10){
            return true;
        } else {
            return false;
        }
    }
    
    //Email must contains @ and dot(.)
    public static boolean checkEmail(String email){
        if(email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean checkNoMember(int noMember){
        if(noMember < 2){
            return false;
        } else {
            return true;
        }
    }
}
