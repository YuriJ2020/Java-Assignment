/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author ppunme
 */
public class Validation {
    
    public static boolean CheckNull(String first, String last, String gender, String email, String phone, String address, String suburb, String postcode, String type) {
        //check to see if each TextField have data
        if(first.equals("") || last.equals("") || gender == null || email.equals("") || phone.equals("") || address.equals("")
            || suburb.equals("") || postcode.equals("") || type == null) {
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
        if(phone.matches("-?\\d+(\\.\\d+)?") && phone.length() >= 9){
            return true;
        } else {
            return false;
        }
    }
    
    //Post codes must be in the range of 2000 – 9999
    public static boolean checkPost(String postcode){
        if(postcode.matches("-?\\d+(\\.\\d+)?") )
        {
            int newPostcode = Integer.parseInt(postcode);
            if(newPostcode > 2000 && newPostcode < 9999){
                return true;
            }
            else {
                return false;
            }
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
}
