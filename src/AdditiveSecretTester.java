import java.util.ArrayList;

public class AdditiveSecretTester {
	/* You are given a function 'secret()' that accepts a single integer
	 * parameter and returns an integer. In your favorite programming language,
	 * write a command-line program that takes one command-line argument (a
	 * number) and determines if the secret() function is additive [secret(x+y) 
	 * = secret(x) + secret(y)], for all combinations x and y, where x and y 
	 * are all prime numbers less than the number passed via the command-line 
	 * argument.  Describe how to run your examples. Please generate the list 
	 * of primes without using built-in functionality.
	 * */
	public static void main (String[] args) {
		int number = 0;
		AdditiveSecretTester tester = new AdditiveSecretTester();
		
		// Parse argument
		if (args.length > 0) {
			try {
				number = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e) {
				System.err.println("Argument must be an interger.");
				System.exit(1);
			}
		}
		else {
			System.err.println("Plese provide an integer");
			System.exit(1);
		}
		
		// Test function
		if (tester.isSecretAdditive(number)){
			System.out.println("The secret() function is additive.");
		}
		else {
			System.out.println("The secret() function is not additive.");
		}
		
		System.exit(0);
	}
	
	private boolean isSecretAdditive(int number){
		ArrayList<Integer> primes = generatePrimes(number);
		
		for (Integer x : primes) {
			for (Integer y : primes) {
				// Prevent checking the same combination twice.
				if(x <= y){
					if (secret(x + y) != secret(x) + secret(y)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/*
	 * Generates primes up to 'number' using the Sieve of Eratosthenes.
	 */
	private ArrayList<Integer> generatePrimes(int number) {
		boolean[] isPrime = new boolean[number + 1];
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		
		// Assume all are prime
		for(int i = 2; i < number; i++) {
			isPrime[i] = true;
		}
		
		// determine if number is non-prime
		for(int factor=2; factor * factor < number; factor++) {
			if(isPrime[factor]){
				for(int j = factor; factor * j <= number; j++) {
					isPrime[factor * j] = false;
				}
			}
		}
		
		// put primes in a list and return
		for(int i = 2; i < number; i ++) {
			if (isPrime[i]) {
				primeList.add(i);
			}
		}
		
		return primeList;
	}
	
	private int secret(int number) {
		return number;
	}
}
