package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetInputAndValidate {
	public static int inputValidation(int input, int minValue, int maxValue) {
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in)); 
		boolean choiceValidation = false;
		 while(!choiceValidation) {
			 try {
				 input = Integer.parseInt(inp.readLine());
				 if(input >= minValue && input <= maxValue) {
					 choiceValidation = true;
					 //System.out.println("The choice you have entered is " + input);
				 }
				 else {
					 if(maxValue - minValue == 1) {
						 System.out.println("Please enter either " + minValue + " or " + maxValue);
						 choiceValidation = false;	 
					 }
					 else if(maxValue == minValue) {
						 System.out.println("Please enter either " + minValue);
						 choiceValidation = false;	 
					 }
					 else {
						 System.out.println("Please enter a number between " + minValue + " and " + maxValue);
						 choiceValidation = false;	 
					 }
				 }
			} catch (NumberFormatException | IOException e) {
				choiceValidation = false;
				System.out.println("The entry is not a number. Please enter a number between " + minValue +  " and " + maxValue);
			}
		 }
	return input;
	}
}
