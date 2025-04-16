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
    
            result = result.substring(leading_zeros_index);

            return (is_positive) ? result : "-".concat(result) ;




        }

        // add fucntion for floating point numbers
        public static String add ( AFloat num1 , AFloat num2 ){

            String number1 = num1.getFloat();
            String number2 = num2.getFloat();

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
    
            result = removeLeadingZeros(result) ;

            return result ;
    
            //return result.charAt(result.length()-1) == '.' ? result.substring(0,result.length() - 1) : result ;
            
        }
    
        public static String sub( AFloat num1 , AFloat num2){

            String number1 = num1.getFloat();
            String number2 = num2.getFloat();
    
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
    
    
    
            String result = sub_int(num1_int_type , num2_int_type);
    
            // if(result.equals("0")){
            //     return "0.0";
            // }
    
            int index_of_point_in_result = result.length() - max_of_digits_after_point;


            if(result.substring(index_of_point_in_result).isEmpty())
                result = result.substring(0, index_of_point_in_result);
            else
                result = result.substring(0,index_of_point_in_result).concat(".".concat(result.substring(index_of_point_in_result)));
    
            result = removeLeadingZeros(result) ;

            return result;
    
            //return result.charAt(result.length()-1) == '.' ? result.substring(0,result.length() - 1) : result ;
    
        }

}
