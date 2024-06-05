package javabasics;
import java.util.Arrays;

public class Variables {

	public static void main(String[] args) {
		int num = 5;
		String name = "Abhishek";
		char Firstchar = 'A';
		float dec = (float) 3.14;
		double dob = 3.141;
		boolean status = true;
		int[] array = new int[3]; 
		array[0] = 3;
		array[1] = 2;
		array[2] = 1;
		String[] array1 = new String[2];
		array1[0] = "\"First element\"";
		
		//loop in array
//       for(int i = 0; i<array.length;i++)
//       {
//    	   
//    	   System.out.println(array[i]);
//       }
		
       
       for(int a: array)    //The enhanced for loop
       {
    	   System.out.println(a);
       }
       
       
		System.out.println(num + " is stored in num varible \n in above varible");
		System.out.println("It is stored as " +dec+" in float varible can be treated as decimal");
		System.out.println("\033[1m" + dob + " is stored in dob variable\033[0m");
		System.out.println(Arrays.toString(array) + " array data");
		System.out.println(array[1] + " is stored in array");
		System.out.println(array1[0] + " String array1");
		
		
	}

}
