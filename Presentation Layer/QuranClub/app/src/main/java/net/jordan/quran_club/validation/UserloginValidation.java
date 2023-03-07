package net.jordan.quran_club.validation;

import android.util.Patterns;

public class UserloginValidation {


    public static boolean checkPattrenEmail(String email){

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
             return true;
        }
        else
            return false;
    }




}
