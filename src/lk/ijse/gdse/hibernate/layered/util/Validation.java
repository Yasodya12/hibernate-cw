package lk.ijse.gdse.hibernate.layered.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static Validation validation;
    private Validation() {
    }

    public static Validation getInstance(){
        return validation==null ? validation=new Validation()
                : validation;
    }

    public boolean userNamePattern(String pattern){
        Pattern namePattern=Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
        Matcher nameMAtcher=namePattern.matcher(pattern);
        boolean isNameMatches=nameMAtcher.matches();
        return isNameMatches;
    }

    public boolean passwordPattern(String pattern){
        Pattern passwordPattern=Pattern.compile(" ^[a-zA-Z0-9_.-]*$");
        Matcher passwordMatcher=passwordPattern.matcher(pattern);
        boolean isPasswordMatches=passwordMatcher.matches();
        return isPasswordMatches;
    }
    public boolean namePattern(String name){
        Pattern namePattern=Pattern.compile("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})");
        Matcher nameMAtcher=namePattern.matcher(name);
        boolean isNameMatches=nameMAtcher.matches();
        return isNameMatches;
    }
    public boolean contactPattern(String contact){
        Pattern contactPattern=Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher contactMatcher = contactPattern.matcher(contact);
        boolean isContactMatches = contactMatcher.matches();
        return isContactMatches;
    }
    public boolean numberPattern(String number){
        Pattern numberPattern=Pattern.compile("^(0|[1-9][0-9]*)$");
        Matcher numberMatcher = numberPattern.matcher(number);
        boolean isNumberMatces = numberMatcher.matches();
        return isNumberMatces;
    }
}
