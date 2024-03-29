//Name-Shivkumar Kothale
//BNumber-B01035183

//Compile the code first using javac FindGcd.java
//then run the code using java FindGcd 36 24


//Program to find the GCD of 2 numbers using Euclids GCD method.
//Taking two inputs from command line and converting them into interger and then finding the GCD
public class FindGcd{
    public static void main(String[] args) {
	// using try and catch block so that if there are no arguments passed then the code wil give a ArrayIndexOutOfBound exception
	try{
		int n1=Integer.parseInt(args[0]); //parsing argument 1 as int 
       		int n2=Integer.parseInt(args[1]); //parsing argument 2 as int
        	System.out.println(findGcd(n1, n2));  //pass the 2 variables n1, n2 to findGcd method to print the Greatest Common Divisor
	}catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Please enter 2 numbers at command line");
	}
    }
    
    // Actual method to find the GCD
    private static int findGcd(int a, int b) {
        if(b==0) return a;
        return findGcd(b,a%b);
    }
}
