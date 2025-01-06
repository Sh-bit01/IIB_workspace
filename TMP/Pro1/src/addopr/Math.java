package addopr;

public class Math {

	public static String name(String a, String b) {
		return a+b	;	
	}	
	
	public static String add(String a, String b) {
	    // Parse strings to integers
	    int num1 = Integer.parseInt(a);
	    int num2 = Integer.parseInt(b);
	    
	    // Add the numbers
	    int sum = num1 + num2;
	    
	    // Return the result as a string
	    return Integer.toString(sum);
	}
	
	
	
}
