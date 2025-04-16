package arbitraryarithmetic;

public class AInteger {

    private String number;

    public AInteger(){      // default constructor
        this.number = "0";
    }

    public AInteger(String s){  // constructor which initialises the instance by the number in its string representation
        this.number = s;
    }
    
    public String getInteger(){
        return number;
    }

    public AInteger(AInteger numbercopy){  // Copy constructor
        this.number = numbercopy.getInteger();
    }
    

    public static AInteger parse(String s){  
        return new AInteger(s);
    }


    private static String Addition(String s1 , String s2){
        String result = "";

        int lengthOfs1 = s1.length();
        int lengthOfs2 = s2.length();

        int index1 = lengthOfs1 - 1;
        int index2 = lengthOfs2 - 1;
        int carry = 0 ;

        while( index1 >= 0 && index2 >= 0 ){
            int sum = ( (int)s1.charAt(index1) - 48 + (int)s2.charAt(index2) - 48 + carry);
            carry = sum / 10 ;
            result = Integer.toString(sum - (10 * carry)).concat(result);

            index1 -- ;
            index2 -- ;
        }

        while (index1 >= 0){
            int sum = ( (int)s1.charAt(index1) - 48 + carry);
            carry = sum / 10 ;
            result = Integer.toString(sum - (10 * carry)).concat(result);

            index1 -- ;
        }

        while (index2 >= 0){
            int sum = ( (int)s2.charAt(index2) - 48 + carry);
            carry = sum / 10 ;
            result = Integer.toString(sum - (10 * carry)).concat(result);

            index2 -- ;
        }

        if(carry != 0){
            result = Integer.toString(carry).concat(result);
        }

        return result;
    }


    private static String subtract(String s1 , String s2){
        // Passing the arguments the function in such a way that s1 > s2

        String result = "";
        int lengthOfs1 = s1.length();
        int lengthOfs2 = s2.length();

        int index1 = lengthOfs1 - 1;
        int index2 = lengthOfs2 - 1;

        int prev = 0;
        int curr = 0;

        while( index1 >= 0 && index2 >= 0 ){
            int current_digit = (int)s1.charAt(index1) - prev - 48 ;
            
            if( current_digit   < (int)s2.charAt(index2) - 48 ){
                curr = 1 ;
            }
            else{
                curr = 0 ;
            }


            int diff = ( ( current_digit+ (10 * curr) ) - ((int)s2.charAt(index2) - 48));
            result = Integer.toString(diff).concat(result);
            
            prev = curr ;

            index1 -- ;
            index2 -- ;
            //System.out.println(result);
        }

        

        while( index1 >= 0 ){

            int current_digit = (int)s1.charAt(index1) - prev - 48 ;
            
            if( current_digit == -1 ){
                curr = 1 ;
            }
            else{
                curr = 0 ;
            }

            result = Integer.toString( current_digit + (10 * curr )).concat(result) ;
            prev = curr ;

            index1 -- ;
        }
        //System.out.println(result);
        
        // Handling the leading zeros after subtraction
        int leading_zeros_index = -1 ;
        for (int i = 0 ; i < result.length() ; i++ ){
            if(result.charAt(i) != '0' ){
                leading_zeros_index = i ;
                break ;
            }
        }

        return result.substring(leading_zeros_index < 0 ? result.length() - 1 : leading_zeros_index);
    }

    private static boolean isGreater(String number1 , String number2){
        int lengthOfNumber1 = number1.length();
        int lengthOfNumber2 = number2.length();

        boolean is_greater = true ;
        if(lengthOfNumber1 > lengthOfNumber2){
            is_greater = true ;
        }
        else if(lengthOfNumber1 < lengthOfNumber2){
            is_greater = false ;
        }
        else{
            for (int i = 0  ; i < lengthOfNumber1 ; i++ ){
                if( number1.charAt(i) > number2.charAt(i) ){
                    is_greater = true ;
                    break ;
                }
                else if( number1.charAt(i) < number2.charAt(i) ){
                    is_greater = false ;
                    break ;
                }        
            }
        }
        return is_greater ;

    }

    // Function to remove leading zeroes in integers

    private static String removeLeadingZeros(String number){

        boolean is_positive = (number.charAt(0) == '-') ? false : true ;

        //System.out.println(is_positive);
        
        String result = (is_positive) ? number : number.substring(1);

        //System.out.println(result);

        int index_of_nonzero_digit = -1 ;

        for ( int i = 0 ; i < result.length() ; i++ ){
            if( result.charAt(i) != '0' ){
                index_of_nonzero_digit = i;
                break;
            }
        }

        //System.out.println(index_of_nonzero_digit);
        
        result = (index_of_nonzero_digit == -1) ? "0" : (result.substring(index_of_nonzero_digit)) ;

        //System.out.println(result);

        if(result.equals("0")){
            return result;
        }

        return (is_positive) ? result : "-".concat(result);
    }

    // Add function for two integers
    public static String add(String number1 , String number2){

        number1 = removeLeadingZeros(number1);
        number2 = removeLeadingZeros(number2);

        // System.out.println(number1);
        // System.out.println(number2);

        boolean is_number1_positive = ( number1.charAt(0) != '-' ) ? true : false ;
        boolean is_number2_positive = ( number2.charAt(0) != '-' ) ? true : false ;

        if( is_number1_positive && is_number2_positive ){
            return Addition(number1, number2);
        }
        else if( is_number1_positive == false && is_number2_positive == false ){
            return "-" .concat( Addition( number1.substring(1) , number2.substring(1) ) );
        }
        else if( is_number1_positive == true && is_number2_positive == false ){
            if(isGreater( number1 , number2.substring(1))){
                return subtract(number1, number2.substring(1));
            }
            else{
                return "-".concat(subtract(number2.substring(1) , number1));
            }
        }
        else{
            if(isGreater( number2 , number1.substring(1))){
                return subtract(number2, number1.substring(1));
            }
            else{
                return "-".concat(subtract(number1.substring(1) , number2));
            }
        }

    }


    // Sub function for two integers
    public static String sub(String number1 , String number2){

        number1 = removeLeadingZeros(number1) ;
        number2 = removeLeadingZeros(number2) ;

        boolean is_number1_positive = ( number1.charAt(0) != '-' ) ? true : false ;
        boolean is_number2_positive = ( number2.charAt(0) != '-' ) ? true : false ;

        if( is_number1_positive && is_number2_positive ){
            if(isGreater(number1, number2)){
                return subtract(number1, number2);
            }
            else{
                return "-".concat(subtract(number2, number1));
            }
        }
        else if( is_number1_positive == false && is_number2_positive == false ){
            if(isGreater( number1.substring(1) , number2.substring(1) ) ){
                return "-".concat( subtract( number1.substring(1) , number2.substring(1) ) );
            }
            else{
                return subtract( number2.substring(1) , number1.substring(1) );
            }
        }
        else if( is_number1_positive == false && is_number2_positive == true ){
            return "-".concat(Addition(number1.substring(1), number2));
        }
        else{
            return Addition(number1, number2.substring(1));
        }
    }
}
