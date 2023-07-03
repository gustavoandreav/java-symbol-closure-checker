import java.util.Scanner;

public class ClosureChecker {

	// indexOf method that provides the index of a item within an array, if no coincidence returns -1
	private static int indexOf(String[] arr, String item) {
		int index = -1;
		for(int i = 0; i<arr.length; i++) {
			if(arr[i].equals(item)) {
				index = i;
				break;
			}
		}
		return index;
	}

	// verifyClosure method that receives a String and returns true if all ([{ are properly closed with }]), otherwise returns false
	private static boolean verifyClosure(String stream) {

		// constants to have the symbols at hand and in order so we can match them by index
		String[] openers = {"(", "{", "["};
		String[] closers = {")", "}", "]"};

		//chars is the string converted to array and stack is our datastructure to verify the correct match of symbols
		String[] chars = stream.split("");
		String[] stack = new String[chars.length];

		// test is the value we will be returning, initialized as true because we only need one missmatch to proof it is all wrong, otherwise is correct
		boolean test = true;
		// index is the index of the stack, initialized as -1 for practical purposes, it points to the position of the last inserted item that wasnt removed
		int index = -1;

		for(int i = 0; i<chars.length; i++) {
			// if it is an opener symbol then add it to the stack to verify match later
			if(indexOf(openers, chars[i]) != -1) {
				index++;
				stack[index] = chars[i];
			//if it is not an opener, then is a closer, but there must be at least one symbol in the stack to be correct
			} else if(index >= 0) {
				// we get the index of closer symbol that we are currently evaluating
				int ind = indexOf(closers, chars[i]);
				// we check that the closer symbol we have is matched with the opener symbol in the stack
				if(openers[ind].equals(stack[index]))
					// if matched, we decrease in 1 the index of stack to "erase" the last inserted item
					index --;
				else {
					// if not matched, then it is incorrect
					test = false;
					break;
				}
			// if there was a closer and the first item in the stack, then we already can say the string is incorrectly matched
			} else {
				test = false;
				break;
			}
		}
		// if until now all symbols were correctly matched, but there are symbols missing for matching up, then it is incorrect
		if(index >= 0)
			test = false;
		return test;
	}

	public static void main(String[] args) {
		System.out.println("Insert the string containing the following symbols: ( ) [ ] { }\n");
		Scanner input = new Scanner(System.in);
		String toEvaluate = input.nextLine();
		System.out.println(verifyClosure(toEvaluate));
	}
}
