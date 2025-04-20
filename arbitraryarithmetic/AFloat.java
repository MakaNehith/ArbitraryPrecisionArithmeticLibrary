package arbitraryarithmetic;
import java.lang.Math;

public class AFloat {

    private String number ;

    public AFloat(){   // default constructor
        this.number = "0.0" ;
    }

    public AFloat( String s ){  // constructor which initialises the instance by the number in its string representation
        this.number = s ;
    }

    public String getFloat(){  // Getter method for accessing the number
        return this.number ;
    }

    public AFloat( AFloat number_copy){  // Copy constructor
        this.number = number_copy.getFloat();
    }

    // Method that returns the instance of AFloat class
    public static AFloat parse(String s){
        return new AFloat( s ) ;
    }


    // Helper function for adding two positive integers
    private static String Addition(String s1 , String s2){
        String result = "";
        
            int lengthOfs1 = s1.length();
            int lengthOfs2 = s2.length();
    
            int index1 = lengthOfs1 - 1;
            int index2 = lengthOfs2 - 1;
            int carry = 0 ;

            // Implementing addition by summing digits from right to left, carrying over any excess to the next digit
            // This loop performs the addition of numbers from right till the minimum number of digits of two numbers 
            while( index1 >= 0 && index2 >= 0 ){
                int sum = ( (int)s1.charAt(index1) - '0' + (int)s2.charAt(index2) - '0' + carry);
                carry = sum / 10 ;
                result = Integer.toString(sum - (10 * carry)).concat(result);
    
                index1 -- ;
                index2 -- ;
            }


            // The below two loops concatenates the excess digits of either s1 or s2 to the start of the result
            // This step also carries the excess to next digit while concatenating the digits
            while (index1 >= 0){
                int sum = ( (int)s1.charAt(index1) - '0' + carry);
                carry = sum / 10 ;
                result = Integer.toString(sum - (10 * carry)).concat(result);
    
                index1 -- ;
            }
    
            while (index2 >= 0){
                int sum = ( (int)s2.charAt(index2) - '0' + carry);
                carry = sum / 10 ;
                result = Integer.toString(sum - (10 * carry)).concat(result);
    
                index2 -- ;
            }

            // Concatenting the carry to the start of result if it is not zero
            if(carry != 0){
                result = Integer.toString(carry).concat(result);
            }
    
            return result;
        }

        // Helper Function for subtracting two positive integers (s1 - s2)
        // Passing the arguments to this function in such a way that s1 > s2
        private static String subtract(String s1 , String s2){
    
            String result = "";
            int lengthOfs1 = s1.length();
            int lengthOfs2 = s2.length();
    
            int index1 = lengthOfs1 - 1;
            int index2 = lengthOfs2 - 1;
    
            int prev = 0; // this is a check , whether the digit to its right has taken borrow from it
            int curr = 0; // this is a check , whether the current digit takes a borrow from the digit to its left


            // Implementing the basic subtraction from right to left
            // This loop performs the subtraction of numbers, that is, we subtract s2 from the substring of s1 of length s2 from right
            while( index1 >= 0 && index2 >= 0 ){
                int current_digit = (int)s1.charAt(index1) - prev - '0' ;
                

                // finding the check curr
                if( current_digit   < (int)s2.charAt(index2) - '0' ){
                    curr = 1 ;
                }
                else{
                    curr = 0 ;
                }
    
                // subtracting the digit of s2 from the same indexed digit in s1
                int diff = ( ( current_digit+ (10 * curr) ) - ((int)s2.charAt(index2) - '0'));
                result = Integer.toString(diff).concat(result);

                // assigning the curr check to prev check
                prev = curr ;
    
                index1 -- ;
                index2 -- ;
            }
    
            
            // The below loop concatenates the excess digits of s1 to the start of the result
            // This loop also follows the same algorithm , this is , taking a borrow from digit to its left if the current digit is less than 0
            while( index1 >= 0 ){
    
                int current_digit = (int)s1.charAt(index1) - prev - '0' ;
                
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
            
        }
    
        
        // Comparison function to check whether number1 is greater than or equal to number2
        // Here, the arguments which are being passed to this function should be non-negative anf free from leading zeros
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
            // If the length of both the numbers is same, then it compares the each digit from start
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



        // Helper function for adding two integers
        private static String add_int(String number1 , String number2){
    
            boolean is_positive_number1 = ( number1.charAt(0) != '-' ) ? true : false ;
            boolean is_positive_number2 = ( number2.charAt(0) != '-' ) ? true : false ;

            // Performing the addition or subtraction based on the sign of number1 and number2

            // number1 is positive and number2 is positive
            if( is_positive_number1 && is_positive_number2 ){
                return Addition(number1, number2);
            }
            // number1 is negative and number2 is negative
            else if( is_positive_number1 == false && is_positive_number2 == false ){
                return "-" .concat( Addition( number1.substring(1) , number2.substring(1) ) );
            }
            // number1 is positive and number2 is negative 
            else if( is_positive_number1 == true && is_positive_number2 == false ){
                if(isGreater( number1 , number2.substring(1))){
                    return subtract(number1, number2.substring(1));
                }
                else{
                    return "-".concat(subtract(number2.substring(1) , number1));
                }
            }
            // number1 is negative and number2 is positive
            else{
                if(isGreater( number2 , number1.substring(1))){
                    return subtract(number2, number1.substring(1));
                }
                else{
                    return "-".concat(subtract(number1.substring(1) , number2));
                }
            }
    
        }
    

        // Helper function for subtracting two integers
        private static String sub_int(String number1 , String number2){
            boolean is_number1_positive = ( number1.charAt(0) != '-' ) ? true : false ;
            boolean is_number2_positive = ( number2.charAt(0) != '-' ) ? true : false ;

            // Performing the addition or subtraction based on the sign of number1 and number2

            // number1 is positive and number2 is positive
            if( is_number1_positive && is_number2_positive ){
                if(isGreater(number1, number2)){
                    return subtract(number1, number2);
                }
                else{
                    return "-".concat(subtract(number2, number1));
                }
            }
            // number1 is negative and number2 is negative
            else if( is_number1_positive == false && is_number2_positive == false ){
                if(isGreater( number2.substring(1) , number1.substring(1) ) ){
                    return subtract( number2.substring(1) , number1.substring(1) );
                }
                else{
                    return "-".concat( subtract( number1.substring(1) , number2.substring(1) ) );
                }
            }
            // number1 is negative and number2 is positive
            else if( is_number1_positive == false && is_number2_positive == true ){
                return "-".concat(Addition(number1.substring(1), number2));
            }
            // number1 is positive and number2 is negative 
            else{
                return Addition(number1, number2.substring(1));
            }
        }


        // Function to remove leading zeros from a floating point number
        private static String removeLeadingZeros(String number){

            boolean is_positive = number.charAt(0) == '-' ? false : true ;

            String result = is_positive ? number : number.substring(1) ;


            // Computing the index of the first non-zero number from start till the decimal point
            int leading_zeros_index = -1 ;
            for (int i = 0 ; i < result.length() ; i++ ){

                // If there is no non-zero digit till decimal point, then it takes the index of digit (which is zero) before it.
                if(result.charAt(i) == '.'){
                    leading_zeros_index = i - 1 ;
                    break ;
                }
    
                if(result.charAt(i) != '0' ){
                    leading_zeros_index = i ;
                    break ;
                }
            }


            // If there is no non-zero digit and decimal point in the number, then it implies that the number is zero
            result = (leading_zeros_index == -1) ? "0" : (result.substring(leading_zeros_index)) ;

            // Handling the case when result is zero to avoid concatenating a negative sign (-) to zero
            if( result.equals("0")){
                return "0";
            }

            return (is_positive) ? result : "-".concat(result) ;

        }

        // Function for removing the trailing zeros after the decimal point
        private static String removeEndingZeros (String number){

            // When the number is an integer, there are no trailing zeros after decimal point (No decimal point)
            if( number.indexOf('.') == -1 ){
                return number ;
            }

            int index_of_nonzero_digit = -1 ;

            
            // Computing the index of the first non-zero digit from right to left before decimal point
            for ( int i = number.length() - 1 ; i >= 0 ; i -- ){
                // If there are no non-zero digits found after decimal point, then the number is a integer
                if( number.charAt(i) == '.' ){
                    index_of_nonzero_digit = i ;
                    break;
                }

                if( number.charAt(i) != '0'){
                    index_of_nonzero_digit = i + 1 ;
                    break;
                }
            }

            // Extracting the substring of number from start till the index found above
            return number.substring(0, index_of_nonzero_digit);
                
        }


        // Function to convert an integer to float by adding ".0" to the end
        private static String convertToFloat(String number){
            // Checking whether decimal point is present in the string(number)
            if( number.indexOf('.') == -1 ){
                // If decimal point is not present, appending ".0" to the number
                return number.concat(".0");
            }
            else{
                return number;
            }
        }


        // add function for adding floating point numbers
        public static AFloat add ( AFloat number1 , AFloat number2 ){

            // accessing the numbers from AFloat Objects number1 and number2 and removing leading zeros from the numbers
            String num1 = removeLeadingZeros(number1.getFloat());
            String num2 = removeLeadingZeros(number2.getFloat());


            // Finding the index of decimal point in num1 and num2
            // If there is no decimal point, assigning length of the number to the index
            int index_of_decimal_num1 = (num1.indexOf('.') != -1 ) ? num1.indexOf('.') : num1.length() ;
            int index_of_decimal_num2 = (num2.indexOf('.') != -1 ) ? num2.indexOf('.') : num2.length() ;

            // Splitting the number into two parts, the number before decimal point and the number after decimal point
            String digits_after_point_num1 = num1.substring(Math.min(index_of_decimal_num1 + 1 , num1.length()) );
            String digits_before_point_num1 = num1.substring(0, index_of_decimal_num1 );

            String digits_after_point_num2 = num2.substring(Math.min(index_of_decimal_num2 + 1 , num2.length()));
            String digits_before_point_num2 = num2.substring(0, index_of_decimal_num2);
    

            // Finding the maximum number of digits after decimal point between the two numbers
            int max_of_digits_after_point = Math.max(digits_after_point_num1.length(),digits_after_point_num2.length());
    

            /* Padding (concatenating) the zeros at the end of numbers after decimal point(digits_after_point_num2 and digits_after_point_num1)
             * to make the the number of digits after decimal point equal in both 
             */
            digits_after_point_num1 = digits_after_point_num1.concat("0".repeat(max_of_digits_after_point - digits_after_point_num1.length()));
            digits_after_point_num2 = digits_after_point_num2.concat("0".repeat(max_of_digits_after_point - digits_after_point_num2.length()));
    

            // Concatenating the digits before point and the digits after point padded with zeros to obtain the num1 and num2 in integer representation
            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);
            

            // Adding the integer representation of both the numbers to get the result without decimal point
            String result = add_int(num1_int_type , num2_int_type);


            /* Since the number of digits after the decimal point remain the same in the result after addition,
             * decimal point can be inserted in the result with the number of digits after decimal point same 
             * as that in num1 and num2 after being padded with zeros
             */
            int index_of_point_in_result = result.length() - max_of_digits_after_point;


            // handling the case that decimal point should not be inserted when the num1 and num2 are integers
            if(  result.substring(index_of_point_in_result).isEmpty() )
                result = result.substring(0, index_of_point_in_result);
            else
                result = result.substring(0,index_of_point_in_result).concat(".".concat(result.substring(index_of_point_in_result)));


            // Removing trailing and leading zeros from the result
    
            result = removeEndingZeros(result) ;
            
            result = removeLeadingZeros(result) ;

            return parse(convertToFloat(result)) ;
            
        }

        // sub function for subtracting two floating point numbers
        public static AFloat sub( AFloat num1 , AFloat num2){

            // accessing the numbers from AFloat Objects number1 and number2 and removing leading zeros from the numbers
            String number1 = removeLeadingZeros(num1.getFloat());
            String number2 = removeLeadingZeros(num2.getFloat());

            // Finding the index of decimal point in num1 and num2
            // If there is no decimal point, assigning length of the number to the index
            int index_of_decimal_number1 = (number1.indexOf('.') != -1 ) ? number1.indexOf('.') : number1.length() ;
            int index_of_decimal_number2 = (number2.indexOf('.') != -1 ) ? number2.indexOf('.') : number2.length() ;

    
            // Splitting the number into two parts, the number before decimal point and the number after decimal point
            String digits_after_point_num1 = number1.substring(Math.min(index_of_decimal_number1 + 1 , number1.length()) );
            String digits_before_point_num1 = number1.substring(0, index_of_decimal_number1 );
    
            String digits_after_point_num2 = number2.substring(Math.min(index_of_decimal_number2 + 1 , number2.length()));
            String digits_before_point_num2 = number2.substring(0, index_of_decimal_number2);


            // Finding the maximum number of digits after decimal point between the two numbers
            int max_of_digits_after_point = Math.max(digits_after_point_num1.length(),digits_after_point_num2.length());
    

            /* Padding (concatenating) the zeros at the end of numbers after decimal point(digits_after_point_num2 and digits_after_point_num1)
             * to make the the number of digits after decimal point equal in both 
             */
            digits_after_point_num1 = digits_after_point_num1.concat("0".repeat(max_of_digits_after_point - digits_after_point_num1.length()));
            digits_after_point_num2 = digits_after_point_num2.concat("0".repeat(max_of_digits_after_point - digits_after_point_num2.length()));
    

            // Concatenating the digits before point and the digits after point padded with zeros to obtain the num1 and num2 in integer representation
            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);

            // Subtracting the integer representaions of num1 and num2 to obtain the result without decimal point
            String result = sub_int(num1_int_type , num2_int_type);


            /* Since the number of digits after decimal point remain the same in the result after addition,
             * decimal point can be inserted in the result with the number of digits after decimal point same 
             * as that in num1 and num2 after being padded with zeros
             */
            int index_of_point_in_result = result.length() - max_of_digits_after_point;


            // handling the case that decimal point should not be inserted when the num1 and num2 are integers
            if(result.substring(index_of_point_in_result).isEmpty())
                result = result.substring(0, index_of_point_in_result);
            else
                result = result.substring(0,index_of_point_in_result).concat(".".concat(result.substring(index_of_point_in_result)));


            // Removing trailing and leading zeros from the result
            result = removeEndingZeros(result);
            result = removeLeadingZeros(result) ;

            return parse(convertToFloat(result));
    
    
        }


        // Helper function for multiplication of non-negative integers with a digit(0-9)
        // Performing the Multiplication of the number with a single digit by processing digits of number from right to left and carrying over any excess
        private static String multiplicationWithDigit(String number , String digit){

            int length_of_number = number.length() ;
    
            int carry = 0 ;
    
            String result = "" ;

            // Iterating over each digit in number and, multiplying it with given digit and adding the carry
            for ( int i = length_of_number - 1 ; i >= 0  ; i -- ){
    
                int product = ( ( (int)number.charAt(i) - 48 ) * ( (int)digit.charAt(0) - 48 ) ) + carry ;
    
                carry = product / 10 ;

                // Taking the last digit from the ( product + carry ) and concatenating it to the start of result
    
                result = Integer.toString(( product - (carry * 10) )).concat(result);
            }

            // concatenating the carry to the result if carry is not equal to zero
            if( carry != 0 )
                result = Integer.toString( carry ).concat(result);
    
            return result ;
        }
        
        
        // Helper function for multiplying two non-negative integers
        private static String multiply (String number1 , String number2){
    
            int length_of_number2 = number2.length() ;
    
            String result = "0" ;

            // Multiplying each digit from the number2 from right to left and concatenating (length_of_number2 - 1 - index) number of zeros at the end
            for (int index = length_of_number2 - 1 ; index >= 0  ; index -- ){
    
                String product_with_added_zeros = multiplicationWithDigit(number1, Character.toString(number2.charAt(index))).concat("0".repeat(length_of_number2 - 1 - index));

                // For each digit in number2, adding the computed product with approriate zeros at the end, with the result
                result = Addition(result, product_with_added_zeros );
            }
    
            return result ;
        }

        

        // mul fucntion for multiplying two floating point numbers
        public static AFloat mul (AFloat number1 , AFloat number2 ){

            // accessing the numbers from AFloat Objects number1 and number2 and removing leading zeros from the numbers
            String num1 = removeLeadingZeros(number1.getFloat());
            String num2 = removeLeadingZeros(number2.getFloat());

            
            boolean is_num1_positive = num1.charAt(0) == '-' ? false : true ;
            boolean is_num2_positive = num2.charAt(0) == '-' ? false : true ;
            
            // Extracting the absolute value of num1 and num2
            num1 = is_num1_positive ? num1 : num1.substring(1) ;
            num2 = is_num2_positive ? num2 : num2.substring(1) ;


            // Finding the index of decimal point in num1 and num2
            // If there is no decimal point, assigning length of the number to the index
            int index_of_decimal_number1 = (num1.indexOf('.') != -1 ) ? num1.indexOf('.') : num1.length() ;
            int index_of_decimal_number2 = (num2.indexOf('.') != -1 ) ? num2.indexOf('.') : num2.length() ;


            // Splitting the number into two parts, the number before decimal point and the number after decimal point
            String digits_after_point_num1 = num1.substring(Math.min(index_of_decimal_number1 + 1 , num1.length()) );
            String digits_before_point_num1 = num1.substring(0, index_of_decimal_number1 );

            String digits_after_point_num2 = num2.substring(Math.min(index_of_decimal_number2 + 1 , num2.length()) );
            String digits_before_point_num2 = num2.substring(0, index_of_decimal_number2 );


            // Removing the decimal point from num1 and num2 to obtain the num1 and num2 in integer representation
            String num1_int_type = digits_before_point_num1.concat(digits_after_point_num1);
            String num2_int_type = digits_before_point_num2.concat(digits_after_point_num2);


            // multiplying num1 and num2 to get the result (product) without decimal point 
            String result = multiply(num1_int_type, num2_int_type) ;


            /* By multiplying two floating point numbers,
             * the digits after the decimal point in the product is the sum of the number of digits after decimal point in num1 and num2
             */
            int number_of_digits_after_point_InResult = result.length() - digits_after_point_num1.length() - digits_after_point_num2.length() ;


            // handling the case that decimal point should not be inserted when the num1 and num2 are integers
            if(result.substring(number_of_digits_after_point_InResult).isEmpty())
                result = result.substring(0, number_of_digits_after_point_InResult);
            else
                result = result.substring(0,number_of_digits_after_point_InResult).concat(".".concat(result.substring(number_of_digits_after_point_InResult)));
            

            // Removing trailing and leading zeros from the result
            result = removeEndingZeros(result) ;            
            result = removeLeadingZeros(result) ;

            // Handling the case when result is zero to avoid concatenating a negative sign (-) to zero
            if(result.equals("0")){
                return parse("0.0");
            }

            // Handling the sign of the product based on the sign of num1 and num2
            result = (is_num1_positive ^ is_num2_positive) ? "-".concat(result) : result ; 

            return parse(convertToFloat(result));

        }



        // div function for the division of floating point numbers

        public static AFloat div ( AFloat number1 , AFloat number2 ){

            
            // Accessing the strings from objects number1 and number2 and removing leading zeros and trailing zeros from the strings
            String num1 = removeLeadingZeros(removeEndingZeros(number1.getFloat()));
            String num2 = removeLeadingZeros(removeEndingZeros(number2.getFloat()));


            // Raising an arithmetic exception when divisor(num2) is zero
            if(num2.equals("0")){
                throw new ArithmeticException("Division by zero error");
            }

            boolean is_num1_positive = num1.charAt(0) == '-' ? false : true ;
            boolean is_num2_positive = num2.charAt(0) == '-' ? false : true ;

            // Extracting the absolute value of num1 and num2
            num1 = (is_num1_positive) ? num1 : num1.substring(1) ;
            num2 = (is_num2_positive) ? num2 : num2.substring(1) ;

            
            // Finding the index of decimal point in num1 and num2
            // If there is no decimal point, assigning length of the number to the index
            int index_of_decimal_number1 = (num1.indexOf('.') != -1 ) ? num1.indexOf('.') : num1.length() ;
            int index_of_decimal_number2 = (num2.indexOf('.') != -1 ) ? num2.indexOf('.') : num2.length() ;

            // Finding the number of digits after decimal point in num1 and num2
            int number_of_digits_after_point_num1 = ((num1.indexOf('.')) == -1) ? 0 : (num1.length() - 1 - num1.indexOf('.')) ;
            int number_of_digits_after_point_num2 = ((num2.indexOf('.')) == -1) ? 0 : (num2.length() - 1 - num2.indexOf('.')) ;
            

            // Finding the maximum number of digits after decimal point between the two numbers
            int max_number_of_digits_after_point = Math.max( number_of_digits_after_point_num1, number_of_digits_after_point_num2);


            /* Padding (concatenating) the zeros at the end of numbers after decimal point(digits_after_point_num2 and digits_after_point_num1)
             * to make the the number of digits after decimal point equal in both.
             * Concatenating the digits before point and the digits after point padded with zeros to obtain the num1 and num2 in integer representation
             */
            num1 = num1.substring(0,index_of_decimal_number1).concat(num1.substring(Math.min(index_of_decimal_number1+1,num1.length()))).concat("0".repeat(max_number_of_digits_after_point - number_of_digits_after_point_num1));
            num2 = num2.substring(0,index_of_decimal_number2).concat(num2.substring(Math.min(index_of_decimal_number2+1,num2.length()))).concat("0".repeat(max_number_of_digits_after_point - number_of_digits_after_point_num2));

            num1 = removeEndingZeros(num1);
            num2 = removeLeadingZeros(num2);

            String result = "" ;
            int quotient = 0 ;
            int index_of_dividend = 1 ;
            String divisor = num2 ;
            String sub_remainder = num1.substring(0,1);
            int flag = 0 ;

            /*
             * In this loop, we first check whether the sub_remainder(Instanteous dividend) is greater than the divisor.
             * If it is, the quotient is incremented until the product of the quotient and the divisor 
             * is just less than the sub_dividend.
             * The quotient is then appended to the result.
             * The sub_remainder is updated with the remainder of sub_remainder divided by the divisor,
             * and concatenated with the next digit from number1.
             * If the sub_remainder is less than the divisor, a quotient of zero is appended to the result,
             * and the next digit from number1 is concatenated to the sub_remainder.
             * If the sub_remainder becomes zero at the last digit of number1, the loop breaks.
             * Otherwise, zeros are repeatedly appended to the end of the sub_remainder, and division continues 
             * until the sub_remainder becomes zero or until 1000 digits after the decimal point are computed.
             */
            
            while(true){
                if(isGreater(sub_remainder, divisor)){
                    if(flag == 0 ){
                        flag = 1 ;
                    }
                    quotient ++ ;
                    divisor = add_int(divisor, num2);
                }
                else{
                    result = result.concat(Integer.toString(quotient));
                    if(flag  == 1){

                        // computing the remainder when sub_remainder is divided by divisor
                        sub_remainder = removeLeadingZeros(sub_int(add_int(sub_remainder, num2), divisor));

                        // If the sub_remainder becomes zero, the loop breaks
                        if(sub_remainder.equals("0") && index_of_dividend >= num1.length()){
                            break;
                        }
                        // Making the quotient equal to zero and divisor equal to num2 after every division between sub_remainder and divisor with quotient not equal to zero
                        divisor = num2 ;
                        quotient = 0 ;
                        flag = 0 ;


                    }

                    // When 1000 digits are computed after the decimal point, the loop breaks
                    if( index_of_dividend >= 1000 + num1.length()){
                        break;
                    }

                    /* Appending the digit from num1 till the index which iterates over num1 is less than length of num1
                     * If the index is greater than or equal to length of num1, zero is appended to sub_remainder
                     */
                    if( index_of_dividend < num1.length() ){
                        sub_remainder = sub_remainder.concat(Character.toString(num1.charAt(index_of_dividend)));
                    }
                    else{
                        sub_remainder = sub_remainder.concat("0");
                    }
                    
                    sub_remainder = removeLeadingZeros(sub_remainder);
                    index_of_dividend ++ ;

                    // Appending the decimal point in the iteration when the sub_remainder at the last digit of the num1 is not zero
                    if( index_of_dividend - 1 == num1.length() ){
                        result = result.concat(".");
                    }
                }
            }


            // Removing trailing and leading zeros from the result
            result = removeLeadingZeros(removeEndingZeros(result));

            // Handling the case when result is zero to avoid concatenating a negative sign (-) to zero
            if(result.equals("0")){
                return parse("0.0");
            }

            // Handling the sign of the product based on the sign of num1 and num2
            result = ( is_num1_positive ^ is_num2_positive ) ? "-".concat(result) : result ;

            return parse(convertToFloat(result));
        }

}
