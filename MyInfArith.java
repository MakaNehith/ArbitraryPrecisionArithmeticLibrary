import arbitraryarithmetic.AFloat;
import arbitraryarithmetic.AInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MyInfArith{
    public static void main(String [] args){

        // Verifying whether four arguments are being passed while executing the file
        if( args.length == 4 ){
            String dataType = args[0] ;
            String operator = args[1] ;
            String operand1 = args[2] ;
            String operand2 = args[3] ;

            //Input validation for the first argument
            if(!( dataType.equals("int") || dataType.equals("float"))){
                System.out.println("Invalid first Argument !");
                return;
            }

            // Input validation for the second argument
            if(!( operator.equals("add") || operator.equals("sub") || operator.equals("mul") || operator.equals("div"))){
                System.out.println("Invalid second Argument");
                return;
            }

            // For the third and the fourth arguments, validation is done using regular expression matching

            // Regular expression pattern for an integer
            String regex_int = "^-?\\d+$";

            // Regular expression pattern for a floating point number
            String regex_float = "^-?\\d+(\\.\\d+)?$";

            // Compile the regular expression for matching integer patterns
            Pattern pattern_int = Pattern.compile(regex_int);

            // Compile the regular expression for matching floating-point number patterns
            Pattern pattern_float = Pattern.compile(regex_float);


            if( dataType.equals("int") ){

                // Create a matcher to check if operand1 matches the integer pattern
                Matcher matcher_for_operand1 = pattern_int.matcher(operand1);

                // Create a matcher to check if operand2 matches the integer pattern
                Matcher matcher_for_operand2 = pattern_int.matcher(operand2);

                // If operand1 does not match the integer pattern, print an error message and terminate the program
                if(! matcher_for_operand1.matches()){
                    System.out.println("Invalid number (Argument 3)");
                    return;
                }

                // If operand2 does not match the integer pattern, print an error message and terminate the program
                if(! matcher_for_operand2.matches()){
                    System.out.println("Invalid number (Argument 4)");
                    return;
                }

                // creating AInteger objects for operand1 and operand2 so that they can passed as arguments to the arithmetic operation methods
                AInteger number1 = new AInteger(operand1);
                AInteger number2 = new AInteger(operand2);

                // Printing out the result by using operation method based on the arithmetic operation provided(argument2)
                switch(operator){
                    
                    case "add" :
                    System.out.println(AInteger.add( number1, number2 ).getInteger());
                    break;

                    case "sub" :
                    System.out.println(AInteger.sub( number1, number2 ).getInteger());
                    break;

                    case "mul" :
                    System.out.println(AInteger.mul( number1, number2 ).getInteger());
                    break;

                    // Handle the "div" (division) operation
                    // Catch and handle division by zero error using try-catch
                    case "div" :
                    try{
                        System.out.println(AInteger.div( number1, number2 ).getInteger());
                    }
                    catch( ArithmeticException e){
                        System.out.println("Division by zero Error");
                    }
                    break;
                }
            }

            else{

                // Create a matcher to check if operand1 matches the floating point number pattern
                Matcher matcher_for_operand1 = pattern_float.matcher(operand1);

                // Create a matcher to check if operand2 matches the floating point number pattern
                Matcher matcher_for_operand2 = pattern_float.matcher(operand2);

                // If operand1 does not match the floating point number pattern, print an error message and terminate the program
                if(! matcher_for_operand1.matches()){
                    System.out.println("Invalid number (Argument 3)");
                    return;
                }

                // If operand2 does not match the floating point number pattern, print an error message and terminate the program
                if(! matcher_for_operand2.matches()){
                    System.out.println("Invalid number (Argument 4)");
                    return;
                }

                AFloat number1 = new AFloat(operand1);
                AFloat number2 = new AFloat(operand2);

                // Printing out the result by using operation method based on the arithmetic operation provided(argument2)
                switch(operator){
                    
                    case "add" :
                    System.out.println(AFloat.add( number1, number2 ).getFloat());
                    break;

                    case "sub" :
                    System.out.println(AFloat.sub( number1, number2 ).getFloat());
                    break;

                    case "mul" :
                    System.out.println(AFloat.mul( number1, number2 ).getFloat());
                    break;

                    // Handle the "div" (division) operation
                    // Catch and handle division by zero error using try-catch
                    case "div" :
                    try{
                        System.out.println(AFloat.div( number1, number2 ).getFloat());
                    }
                    catch(ArithmeticException e){
                        System.out.println("Division by zero error");
                    }
                    
                    break;
                }

            }
        }

        // Printing out an error message if the number of arguments are not equal to 4
        else{
            System.out.println(" Invalid arguments! ");
        }
    }
}