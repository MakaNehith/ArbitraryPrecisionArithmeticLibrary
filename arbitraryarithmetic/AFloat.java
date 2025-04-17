package arbitraryarithmetic;
import java.lang.Math;

public class AFloat {

    private String number ;

    public AFloat(){
        this.number = "0.0" ;
    }

    public AFloat( String s ){
        this.number = s ;
    }

    public String getFloat(){
        return this.number ;
    }

    public AFloat( AFloat number_copy){
        this.number = number_copy.getFloat();
    }

    public static AFloat parse(String s){
        return new AFloat( s ) ;
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

            return result;
            //System.out.println(result);
            
            // int leading_zeros_index = -1 ;
            // for (int i = 0 ; i < result.length() ; i++ ){
            //     if(result.charAt(i) != '0' ){
            //         leading_zeros_index = i ;
            //         break ;
            //     }
            // }
    
            // return result.substring(leading_zeros_index < 0 ? result.length() - 1 : leading_zeros_index);
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
    
        private static String add_int(String number1 , String number2){
    
            boolean is_positive_number1 = ( number1.charAt(0) != '-' ) ? true : false ;
            boolean is_positive_number2 = ( number2.charAt(0) != '-' ) ? true : false ;
    
            if( is_positive_number1 && is_positive_number2 ){
                return Addition(number1, number2);
            }
            else if( is_positive_number1 == false && is_positive_number2 == false ){
                return "-" .concat( Addition( number1.substring(1) , number2.substring(1) ) );
            }
            else if( is_positive_number1 == true && is_positive_number2 == false ){
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
    
    
        private static String sub_int(String number1 , String number2){
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
                if(isGreater( number2.substring(1) , number1.substring(1) ) ){
                    return subtract( number2.substring(1) , number1.substring(1) );
                }
                else{
                    return "-".concat( subtract( number1.substring(1) , number2.substring(1) ) );
                }
            }
            else if( is_number1_positive == false && is_number2_positive == true ){
                return "-".concat(Addition(number1.substring(1), number2));
            }
            else{
                return Addition(number1, number2.substring(1));
            }
        }


        private static String removeLeadingZeros(String number){

            boolean is_positive = number.charAt(0) == '-' ? false : true ;

            String result = is_positive ? number : number.substring(1) ;

            //System.out.println(result);

            int leading_zeros_index = -1 ;
            for (int i = 0 ; i < result.length() ; i++ ){
                if(result.charAt(i) == '.'){
                    leading_zeros_index = i - 1 ;
                    break ;
                }
    
                if(result.charAt(i) != '0' ){
                    leading_zeros_index = i ;
                    break ;
                }
            }

            //System.out.println(result);
    
            result = (leading_zeros_index == -1) ? "0" : (result.substring(leading_zeros_index)) ;

            if( result.equals("0")){
                return "0";
            }


            return (is_positive) ? result : "-".concat(result) ;




        }

        private static String removeEndingZeros (String number){

            if( number.indexOf('.') == -1 ){
                return number ;
            }

            int index_of_nonzero_digit = -1 ;

            

            for ( int i = number.length() - 1 ; i >= 0 ; i -- ){
                if( number.charAt(i) == '.' ){
                    index_of_nonzero_digit = i ;
                    break;
                }

                if( number.charAt(i) != '0'){
                    index_of_nonzero_digit = i + 1 ;
                    break;
                }
            }

            return number.substring(0, index_of_nonzero_digit);
                
        }

        // add function for floating point numbers
        public static String add ( AFloat num1 , AFloat num2 ){

            String number1 = removeLeadingZeros(num1.getFloat());
            String number2 = removeLeadingZeros(num2.getFloat());


            int index_of_decimal_number1 = (number1.indexOf('.') != -1 ) ? number1.indexOf('.') : number1.length() ;
            int index_of_decimal_number2 = (number2.indexOf('.') != -1 ) ? number2.indexOf('.') : number2.length() ;
    
            String digits_after_point_num1 = number1.substring(Math.min(index_of_decimal_number1 + 1 , number1.length()) );
            String digits_before_point_num1 = number1.substring(0, index_of_decimal_number1 );
    
            //System.out.println(digits_before_point_num1 + " " + digits_after_point_num1);
    
            String digits_after_point_num2 = number2.substring(Math.min(index_of_decimal_number2 + 1 , number2.length()));
            String digits_before_point_num2 = number2.substring(0, index_of_decimal_number2);
    
            //System.out.println(digits_before_point_num2 + " " + digits_after_point_num2);
    
            int max_of_digits_after_point = Math.max(digits_after_point_num1.length(),digits_after_point_num2.length());
    
            //System.out.println(max_of_digits_after_point);
    
            digits_after_point_num1 = digits_after_point_num1.concat("0".repeat(max_of_digits_after_point - digits_after_point_num1.length()));
            digits_after_point_num2 = digits_after_point_num2.concat("0".repeat(max_of_digits_after_point - digits_after_point_num2.length()));
    
            // System.out.println(digits_after_point_num1);
            // System.out.println(digits_after_point_num2);
    
            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);
            
            // System.out.println(num1_int_type);
            // System.out.println(num2_int_type);

            //System.out.println(num1_int_type + " " + num2_int_type);
    
            String result = add_int(num1_int_type , num2_int_type);
            //System.out.println(result);
    
            // if(result.equals("0")){
            //     return "0.0";
            // }
    
            int index_of_point_in_result = result.length() - max_of_digits_after_point;

            //System.out.println(result.isEmpty());

            if(  result.substring(index_of_point_in_result).isEmpty() )
                result = result.substring(0, index_of_point_in_result);
            else
                result = result.substring(0,index_of_point_in_result).concat(".".concat(result.substring(index_of_point_in_result)));

                //System.out.println(result);
    
            //System.out.println(result);
    
            result = removeEndingZeros(result) ;
            
            result = removeLeadingZeros(result) ;

            return result ;
    
            //return result.charAt(result.length()-1) == '.' ? result.substring(0,result.length() - 1) : result ;
            
        }
    
        public static String sub( AFloat num1 , AFloat num2){

            String number1 = removeLeadingZeros(num1.getFloat());
            String number2 = removeLeadingZeros(num2.getFloat());

    
            int index_of_decimal_number1 = (number1.indexOf('.') != -1 ) ? number1.indexOf('.') : number1.length() ;
            int index_of_decimal_number2 = (number2.indexOf('.') != -1 ) ? number2.indexOf('.') : number2.length() ;
    
            String digits_after_point_num1 = number1.substring(Math.min(index_of_decimal_number1 + 1 , number1.length()) );
            String digits_before_point_num1 = number1.substring(0, index_of_decimal_number1 );
    
            //System.out.println(digits_before_point_num1 + " " + digits_after_point_num1);
    
            String digits_after_point_num2 = number2.substring(Math.min(index_of_decimal_number2 + 1 , number2.length()));
            String digits_before_point_num2 = number2.substring(0, index_of_decimal_number2);
    
            //System.out.println(digits_before_point_num2 + " " + digits_after_point_num2);
    
            int max_of_digits_after_point = Math.max(digits_after_point_num1.length(),digits_after_point_num2.length());
    
            //System.out.println(max_of_digits_after_point);
    
            digits_after_point_num1 = digits_after_point_num1.concat("0".repeat(max_of_digits_after_point - digits_after_point_num1.length()));
            digits_after_point_num2 = digits_after_point_num2.concat("0".repeat(max_of_digits_after_point - digits_after_point_num2.length()));
    
            // System.out.println(digits_after_point_num1);
            // System.out.println(digits_after_point_num2);
    
            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);

            //System.out.println(num1_int_type + " " + num2_int_type);
    
            String result = sub_int(num1_int_type , num2_int_type);

            //System.out.println(result);
    
            // if(result.equals("0")){
            //     return "0.0";
            // }
    
            int index_of_point_in_result = result.length() - max_of_digits_after_point;


            if(result.substring(index_of_point_in_result).isEmpty())
                result = result.substring(0, index_of_point_in_result);
            else
                result = result.substring(0,index_of_point_in_result).concat(".".concat(result.substring(index_of_point_in_result)));
    
            result = removeEndingZeros(result);
            result = removeLeadingZeros(result) ;


            return result;
    
            //return result.charAt(result.length()-1) == '.' ? result.substring(0,result.length() - 1) : result ;
    
        }

        private static String multiplicationWithDigit(String number , String digit){

            int length_of_number = number.length() ;
    
            int carry = 0 ;
    
            String result = "" ;
    
            for ( int i = length_of_number - 1 ; i >= 0  ; i -- ){
    
                int product = ( ( (int)number.charAt(i) - 48 ) * ( (int)digit.charAt(0) - 48 ) ) + carry ;
    
                carry = product / 10 ;
    
                result = Integer.toString(( product - (carry * 10) )).concat(result);
            }
    
            if( carry != 0 )
                result = Integer.toString( carry ).concat(result);
    
            return result ;
        }
    
        private static String multiply (String number1 , String number2){
    
            //int length_of_number1 = number1.length() ;
            int length_of_number2 = number2.length() ;
    
            String result = "" ;
    
            for (int index = length_of_number2 - 1 ; index >= 0  ; index -- ){
    
                String product_with_added_zeros = multiplicationWithDigit(number1, Character.toString(number2.charAt(index))).concat("0".repeat(length_of_number2 - 1 - index));
    
                result = Addition(result, product_with_added_zeros );
            }
    
            return result ;
        }

        public static String mul (AFloat number1 , AFloat number2 ){

            String num1 = removeLeadingZeros(number1.getFloat());
            String num2 = removeLeadingZeros(number2.getFloat());

            boolean is_num1_positive = num1.charAt(0) == '-' ? false : true ;
            boolean is_num2_positive = num2.charAt(0) == '-' ? false : true ;
            
            num1 = is_num1_positive ? num1 : num1.substring(1) ;
            num2 = is_num2_positive ? num2 : num2.substring(1) ;

            int index_of_decimal_number1 = (num1.indexOf('.') != -1 ) ? num1.indexOf('.') : num1.length() ;
            int index_of_decimal_number2 = (num2.indexOf('.') != -1 ) ? num2.indexOf('.') : num2.length() ;
    
            String digits_after_point_num1 = num1.substring(Math.min(index_of_decimal_number1 + 1 , num1.length()) );
            String digits_before_point_num1 = num1.substring(0, index_of_decimal_number1 );

            String digits_after_point_num2 = num2.substring(Math.min(index_of_decimal_number2 + 1 , num2.length()) );
            String digits_before_point_num2 = num2.substring(0, index_of_decimal_number2 );

            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);

            // System.out.println(num1_int_type);
            // System.out.println(num2_int_type);
            
            String result = multiply(num1_int_type, num2_int_type) ;

            //System.out.println(result);

            //System.out.println(result);
            
            int number_of_digits_after_point_InResult = result.length() - digits_after_point_num1.length() - digits_after_point_num2.length() ;

            //System.out.println(number_of_digits_after_point_InResult);

            if(result.substring(number_of_digits_after_point_InResult).isEmpty())
                result = result.substring(0, number_of_digits_after_point_InResult);
            else
                result = result.substring(0,number_of_digits_after_point_InResult).concat(".".concat(result.substring(number_of_digits_after_point_InResult)));
            
            //System.out.println(result);
            result = removeLeadingZeros(result) ;

            result = removeEndingZeros(result) ;

            if(result.equals("0")){
                return "0";
            }

            return (is_num1_positive ^ is_num2_positive) ? "-".concat(result) : result ; 

        }



        // div fucntion for the division of floating point numbers

        public static String div ( AFloat number1 , AFloat number2 ){

            String num1 = removeLeadingZeros(removeEndingZeros(number1.getFloat()));
            String num2 = removeLeadingZeros(removeEndingZeros(number2.getFloat()));

            //System.out.println(num1 + " " + num2);

            if(num2.equals("0")){
                throw new ArithmeticException("Division by zero error");
            }

            boolean is_num1_positive = num1.charAt(0) == '-' ? false : true ;
            boolean is_num2_positive = num2.charAt(0) == '-' ? false : true ;

            num1 = (is_num1_positive) ? num1 : num1.substring(1) ;
            num2 = (is_num2_positive) ? num2 : num2.substring(1) ;

            int index_of_decimal_number1 = (num1.indexOf('.') != -1 ) ? num1.indexOf('.') : num1.length() ;
            int index_of_decimal_number2 = (num2.indexOf('.') != -1 ) ? num2.indexOf('.') : num2.length() ;

            int number_of_digits_after_point_num1 = ((num1.indexOf('.')) == -1) ? 0 : (num1.length() - 1 - num1.indexOf('.')) ;
            int number_of_digits_after_point_num2 = ((num2.indexOf('.')) == -1) ? 0 : (num2.length() - 1 - num2.indexOf('.')) ;

            int max_number_of_digits_after_point = Math.max( number_of_digits_after_point_num1, number_of_digits_after_point_num2);

            num1 = num1.substring(0,index_of_decimal_number1).concat(num1.substring(Math.min(index_of_decimal_number1+1,num1.length()))).concat("0".repeat(max_number_of_digits_after_point - number_of_digits_after_point_num1));
            num2 = num2.substring(0,index_of_decimal_number2).concat(num2.substring(Math.min(index_of_decimal_number2+1,num2.length()))).concat("0".repeat(max_number_of_digits_after_point - number_of_digits_after_point_num2));

            num1 = removeEndingZeros(num1);
            num2 = removeLeadingZeros(num2);

            //System.out.println(num1 + " " + num2);

            String result = "" ;
            int quotient = 0 ;
            int index_of_dividend = 1 ;
            String divisor = num2 ;
            String sub_remainder = num1.substring(0,1);
            int flag = 0 ;
            
            while(true){
                if(isGreater(sub_remainder, divisor)){
                    if(flag == 0 ){
                        flag = 1 ;
                    }
                    quotient ++ ;
                    divisor = add_int(divisor, num2);
                }
                else{
                    if(flag  == 1){
                        result = result.concat(Integer.toString(quotient));
                        sub_remainder = removeLeadingZeros(sub_int(add_int(sub_remainder, num2), divisor));

                        divisor = num2 ;
                        quotient = 0 ;
                        flag = 0 ;

                        if(sub_remainder.equals("0") && index_of_dividend >= num1.length()){
                            break;
                        }

                        if( index_of_dividend >= 1000 + num1.length()){
                            break;
                        }

                        if( index_of_dividend >= num1.length()){
                            // if( flag2 == 0 ){
                            //     result = result.concat(".");
                            //     flag2 = 1 ;
                            // }
                            sub_remainder = sub_remainder.concat("0");
                        }

                        else{
                            sub_remainder = sub_remainder.concat(Character.toString(num1.charAt(index_of_dividend)));
                        }
                    }
                    else{
                        result = result.concat(Integer.toString(quotient));

                        if( index_of_dividend >= 1000 + num1.length()){
                            break;
                        }

                        if( index_of_dividend < num1.length() ){
                            sub_remainder = sub_remainder.concat(Character.toString(num1.charAt(index_of_dividend)));
                        }
                        else{
                            sub_remainder = sub_remainder.concat("0");
                        }
                    }
                    
                    sub_remainder = removeLeadingZeros(sub_remainder);
                    index_of_dividend ++ ;
                    //System.out.println(sub_remainder);
                    if( index_of_dividend - 1 == num1.length() ){
                        result = result.concat(".");
                    }

                    
                }
            }

            result = removeLeadingZeros(removeEndingZeros(result));

            if(result.equals("0")){
                return "0";
            }

            return ( is_num1_positive ^ is_num2_positive ) ? "-".concat(result) : result ;
        }

}
