package arbitraryarithmetic;

public class AInteger {

    private String number;

    public AInteger(){      // default constructor
        this.number = "0";
    }

    public AInteger(String s){  // constructor which initialises the instance by the number in its string representation
        this.number = s;
    }
    
    public String getInteger(){  // Getter method for accessing the number
        return number;
    }

    public AInteger(AInteger numbercopy){  // Copy constructor
        this.number = numbercopy.getInteger();
    }
    
    // Method that returns the instance of AInteger class
    public static AInteger parse(String s){  
        return new AInteger(s);
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
    // Passing the arguments this function in such a way that s1 > s2
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
    
        // Handling the leading zeros in the final result after subtraction
        return removeLeadingZeros(result);
    }


    
    // Comparison function to check whether number1 is greater than or equal to number2
    // Here, the arguments which are being passed to this function should be non-negative and free from leading zeros
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


    // Function to remove leading zeroes in integers

    private static String removeLeadingZeros(String number){

        boolean is_positive = (number.charAt(0) == '-') ? false : true ;
        
        String result = (is_positive) ? number : number.substring(1);

        int index_of_nonzero_digit = -1 ;

        // Computing the index of the first non-zero number from start
        for ( int i = 0 ; i < result.length() ; i++ ){
            if( result.charAt(i) != '0' ){
                index_of_nonzero_digit = i;
                break;
            }
        }

        // If there is no non-zero digit, it implies that the number is zero
        result = (index_of_nonzero_digit == -1) ? "0" : (result.substring(index_of_nonzero_digit)) ;

        // Handling the case when result is zero to avoid concatenating a negative sign (-) to zero
        if(result.equals("0")){
            return result;
        }

        return (is_positive) ? result : "-".concat(result);
    }


    // Add function for adding two integers
    public static AInteger add(AInteger number1 , AInteger number2){

        //Accessing the numbers from AInteger Objects number1 and number2
        String num1 = number1.getInteger();
        String num2 = number2.getInteger();

        num1 = removeLeadingZeros(num1);
        num2 = removeLeadingZeros(num2);

        boolean is_number1_positive = ( num1.charAt(0) != '-' ) ? true : false ;
        boolean is_number2_positive = ( num2.charAt(0) != '-' ) ? true : false ;

        String result;

        // Performing the addition or subtraction based on the sign of num1 and num2

        // num1 is positive and num2 is positive
        if( is_number1_positive && is_number2_positive ){
            result = Addition(num1, num2);
        }
        // num1 is negative and num2 is negative
        else if( is_number1_positive == false && is_number2_positive == false ){
            result = "-" .concat( Addition( num1.substring(1) , num2.substring(1) ) );
        }
        // num1 is positive and num2 is negative
        else if( is_number1_positive == true && is_number2_positive == false ){
            if(isGreater( num1 , num2.substring(1))){
                result = subtract(num1, num2.substring(1));
            }
            else{
                result = "-".concat(subtract(num2.substring(1) , num1));
            }
        }
        // num1 is negative and num2 is positive
        else{
            if(isGreater( num2 , num1.substring(1))){
                result = subtract(num2, num1.substring(1));
            }
            else{
                result = "-".concat(subtract(num1.substring(1) , num2));
            }
        }

        return parse(result);

    }


    // Sub function for subtraction of two integers
    public static AInteger sub(AInteger number1 , AInteger number2){

        //Accessing the numbers from AInteger Objects number1 and number2
        String num1 = number1.getInteger();
        String num2 = number2.getInteger();


        num1 = removeLeadingZeros(num1) ;
        num2 = removeLeadingZeros(num2) ;

        boolean is_number1_positive = ( num1.charAt(0) != '-' ) ? true : false ;
        boolean is_number2_positive = ( num2.charAt(0) != '-' ) ? true : false ;

        String result;

        // Performing the addition or subtraction based on the sign of num1 and num2

        // num1 is positive and num2 is positive
        if( is_number1_positive && is_number2_positive ){
            if(isGreater(num1, num2)){
                result = subtract(num1, num2);
            }
            else{
                result = "-".concat(subtract(num2, num1));
            }
        }
        // num1 is negative and num2 is negative
        else if( is_number1_positive == false && is_number2_positive == false ){
            if(isGreater( num2.substring(1) , num1.substring(1) ) ){
                result = subtract( num2.substring(1) , num1.substring(1) );
            }
            else{
                result = "-".concat( subtract( num1.substring(1) , num2.substring(1) ) );
            }
        }
        // num1 is negative and num2 is positive
        else if( is_number1_positive == false && is_number2_positive == true ){
            result =  "-".concat(Addition(num1.substring(1), num2));
        }
        // num1 is positive and num2 is negative
        else{
            result = Addition(num1, num2.substring(1));
        }

        return parse(result);
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


    // Helper function for multiplying two non negative integers
    private static String multiply (String number1 , String number2){

        //int length_of_number1 = number1.length() ;
        int length_of_number2 = number2.length() ;

        String result = "0" ;

        // Multiplying each digit from the number2 from right to left and adding (length_of_number2 - 1 - index) number of zeros at the end
        for (int index = length_of_number2 - 1 ; index >= 0  ; index -- ){

            String product_with_added_zeros = multiplicationWithDigit(number1, Character.toString(number2.charAt(index))).concat("0".repeat(length_of_number2 - 1 - index));

            // For each digit in number2, adding the computed product with approriate zeros at the end, with the result
            result = Addition(result, product_with_added_zeros );
        }

        return result ;
    }

    //mul function for multiplting two integers
    public static AInteger mul (AInteger number1 , AInteger number2 ){
        
        // Accessing the strings from objects number1 and number2 and removing leading zeros from the strings
        String num1 = removeLeadingZeros(number1.getInteger());
        String num2 = removeLeadingZeros(number2.getInteger());

        // Handling the case when num1 or num2 is 0
        if(num1 == "0" || num2 == "0"){
            return parse("0");
        }

        boolean is_num1_positive = num1.charAt(0) == '-' ? false : true ;
        boolean is_num2_positive = num2.charAt(0) == '-' ? false : true ;

        String result;

        // Handling the sign of the product by splitting it into four cases

        // num1 is positive and num2 is positive
        if( is_num1_positive && is_num2_positive ){
            result = multiply(num1, num2);
        }
        // num1 is negative and num2 is negative 
        else if( is_num1_positive == false && is_num2_positive == false ){
            result = multiply(num1.substring(1), num2.substring(1));
        }
        // num1 is positive and num2 is negative
        else if( is_num1_positive == true && is_num2_positive == false ){
            result = "-".concat(multiply(num1, num2.substring(1)));
        }
        // num1 is negative and num2 is positive
        else{
            result = "-".concat(multiply(num1.substring(1),num2));
        }

        return parse(result);

    }


    // div function for dividing two integers ( Integer division )
    public static AInteger div ( AInteger number1 , AInteger number2 ){

        // Accessing the strings from objects number1 and number2 and removing leading zeros from the strings
        String num1 = removeLeadingZeros(number1.getInteger());
        String num2 = removeLeadingZeros(number2.getInteger());
        
        boolean is_positive_num1 = num1.charAt(0) == '-' ? false : true ;
        boolean is_positive_num2 = num2.charAt(0) == '-' ? false : true ;

        // Extracting the absolute value of num1 and num2
        num1 = (is_positive_num1) ? num1 : num1.substring(1);
        num2 = (is_positive_num2) ? num2 : num2.substring(1);

        // Raising an arithmetic exception when divisor(num2) is zero
        if(num2.equals("0")){
            throw new ArithmeticException("Division by zero Error");
        }

        // If num1 is less than num2, the quotient is zero
        if( ! isGreater(num1, num2) ){
            return parse("0");
        }

        int length_of_num1 = num1.length();
        int length_of_num2 = num2.length();

        String result = "" ;

        String divisor = num2 ;

        // Accessing the number of digits equal to the number of digits in number2 from number1 from start
        String sub_remainder = num1.substring(0,length_of_num2) ;

        int endIndex_of_sub_dividend = length_of_num2 ;
        int quotient = 0 ;
        int flag = 0 ;

       /*
        * In this loop, we first check whether the sub_remainder(instanteous dividend) is greater than the divisor.
        * If it is, the quotient is incremented until the product of the quotient and the divisor 
        * is just less than the sub_dividend.
        * The quotient is then appended to the result.
        * The sub_remainder is updated with the remainder of sub_remainder divided by the divisor, 
        * and concatenated with the next digit from number1.
        * If the sub_remainder is less than the divisor, a quotient of zero is appended to the result, 
        * and the next digit from number1 is concatenated to the sub_remainder.
        * This process continues until the last digit of number1 is processed.
        */

        while(endIndex_of_sub_dividend <= length_of_num1){
            if(isGreater(sub_remainder,divisor)){
                if(flag == 0){
                    flag = 1 ;
                }
                quotient ++ ;
                divisor = add(new AInteger(divisor) , new AInteger(num2)).getInteger();
            }
            else{
                result = result.concat(Integer.toString(quotient));

                // The loop breaks when the index which iterates over num1 becomes greater than or equal to length of num1
                if(endIndex_of_sub_dividend == length_of_num1){
                    break;
                }

                // computing the remainder when sub_remainder is divided by divisor and concatenating it to the next digit from num1
                sub_remainder = sub(add(new AInteger(sub_remainder) , new AInteger(num2)), new AInteger(divisor)).getInteger().concat(Character.toString(num1.charAt(endIndex_of_sub_dividend)));
                sub_remainder = removeLeadingZeros(sub_remainder);
                endIndex_of_sub_dividend ++ ;

                // Making the quotient equal to zero and divisor equal to num2 after every division between sub_remainder and divisor with quotient not equal to zero
                if(flag == 1){
                    quotient = 0 ;
                    flag = 0;
                    divisor = num2 ;
                }
            }
        }

        // Handling the leading zeros in the result
        result = removeLeadingZeros(result);

        // Set the sign of quotient based on whether num1 and num2 have the same or different signs
        result = (is_positive_num1 ^ is_positive_num2 ) ? "-".concat(result) : result ;

        return parse(result);

    }
}
