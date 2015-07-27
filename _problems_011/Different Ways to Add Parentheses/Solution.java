public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        
        ArrayList<Integer> list = reduce(input);
		Collections.sort(list);
		return list;
    
    }
    
    private ArrayList<Integer> reduce(String str) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		boolean nFound = false, opFound = false;

		// from the end of the string, check if there is a number occurring
		// after the operand
		for (int i = str.length() - 1; i >= 0 && !nFound; i--) {
			if (!Character.isDigit(str.charAt(i)))
				opFound = true;
			if (Character.isDigit(str.charAt(i)) && opFound) {
				nFound = true;
				break;
			}
		}

		// if there is no number occurring after the operand when reading from
		// the right, then it is a single number, add this number to the array
		// list and return
		if (!nFound) {
			int x = Integer.parseInt(str);
			result.add(x);
			return result;
		}

		// reduce on either sides of the operands
		for (int i = 0; i < str.length(); i++)
			if (!Character.isDigit(str.charAt(i)))
				result.addAll(compute(reduce(str.substring(0, i)),
						str.charAt(i), reduce(str.substring(i + 1))));

		return result;
	}

	private ArrayList<Integer> compute(ArrayList<Integer> aList,
			char operator, ArrayList<Integer> bList) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int a : aList)
			for (int b : bList)
				result.add(compute(a, operator, b));

		return result;
	}

	private int compute(int a, char operator, int b) {

		switch (operator) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}

		return 0;
	}
	
}